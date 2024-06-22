/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 *
 * @author Tsiory
 */
public class Jour {
    int idJour;
    String dateJour;
    String heureCoupure;
    double matin;
    double midi;
    Source source;
    Detail [] detail;
    double con;

    public double getCon() {
        return con;
    }

    public void setCon(double con) {
        this.con = con;
    }

    public Detail[] getDetail() {
        return detail;
    }

    public void setDetail(Detail[] detail) {
        this.detail = detail;
    }
    
    public Jour(){
    }

    public Jour(int idJour, String dateJour, String heureCoupure, double matin, double midi, Source source) {
        this.setIdJour(idJour);
        this.setDateJour(dateJour);
        this.setHeureCoupure(heureCoupure);
        this.setMatin(matin);
        this.setMidi(midi);
        this.setSource(source);
    }

    public int getIdJour() {
        return idJour;
    }

    public void setIdJour(int idJour) {
        this.idJour = idJour;
    }

    public String getDateJour() {
        return dateJour;
    }

    public void setDateJour(String dateJour) {
        this.dateJour = dateJour;
    }

    public double getHeureCoupure() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime heure = LocalTime.parse(heureCoupure, formatter);
        double h = heure.getHour() + (heure.getMinute() / 60.0);
        return h;
    }
    public LocalTime getHeure() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime heure = LocalTime.parse(heureCoupure, formatter);
        return heure;
    }
    
    public String getTapaka(){
        return heureCoupure;
    }

    public void setHeureCoupure(String heureCoupure) {
        this.heureCoupure = heureCoupure;
    }

    public double getMatin() {
        return matin;
    }

    public void setMatin(double matin) {
        this.matin = matin;
    }

    public double getMidi() {
        return midi;
    }

    public void setMidi(double midi) {
        this.midi = midi;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
    
    public static Jour [] getJour(Connection c,int idsource) throws Exception{
        Statement sm=c.createStatement();
        Vector<Jour> v=new Vector<Jour>();
        ResultSet res = sm.executeQuery("select*from jour where idsource="+idsource);
         while(res.next()){
             Source s =Source.getSource(c, res.getInt("idsource"));
            Jour j =new Jour(res.getInt("idjour"),res.getString("datejour"),res.getString("heurecoupure"),res.getDouble("matin"),res.getDouble("midi"),s);
            v.add(j);
        }
       return (Jour[]) v.toArray();
    }
    
    public static Jour getJourById(Connection c,int idjour) throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from jour where idjour="+idjour);
         while(res.next()){
             Source s =Source.getSource(c, res.getInt("idsource"));
              return new Jour(res.getInt("idjour"),res.getString("datejour"),res.getString("heurecoupure"),res.getDouble("matin"),res.getDouble("midi"),s);

        }
       return null;
    }
    
    public double getNumber(double l){
        if(l<12)return this.getMatin();
        return this.getMidi();      
    }
    
    public LocalTime getTimeCoupure(Connection c,double consomation) throws Exception{
        Vector<Detail> detail = new Vector<Detail>();
        double matin=this.getMatin();
        double midi=this.getMidi();
        Luminosite [] l=Luminosite.getLuminosite(c,this.getDateJour(),8);
        double coupure=8;
        Jour jour=new Jour();
        double total=this.getSource().getBattery();
        double minute=0;
        Detail dd = new Detail();
        for(int i=0;i<l.length;i++){
            Detail d = new Detail();
            double puissancePanneau = (this.getSource().getPanneau()*l[i].getValeur())/10;
            double conso=0;
            double con=0;
                        
            d.setResteBatterie(total);
            d.setHeure(l[i].getDebut());
            d.setPuissancePanneau(puissancePanneau);
//            System.out.println(puissancePanneau);
            detail.add(d);
            
            if(l[i].getDebut()<12){   
                con = consomation*matin;
                conso = (consomation*matin)-puissancePanneau;
            }else{
                conso = (consomation*midi)-puissancePanneau;
                con = consomation*midi;
            }
            d.setConsomation(con);
            if(con<puissancePanneau){              
                double surplus = puissancePanneau - con;
                double batt = total+surplus;
                double batterie = this.getSource().getBattery();
                if(batt<batterie){
                    total = batt;
                }
//                else{
//                    double a=batt-batterie;
//                    total = batt-a;
//                }
            }
            if(conso>0){
                total = total-conso;
                if(total<=(this.getSource().getBattery()/2)){
                    total = total+conso;
                    coupure = l[i].getDebut();
                    double reste = (total-(this.getSource().getBattery()/2))+puissancePanneau;
                    minute = (reste*60)/(this.getNumber(l[i].getDebut())*consomation);
                    con = this.getNumber(l[i].getDebut())*consomation;
                    break;
                }
            }
            
            if(coupure==8){
                coupure=18;
            }
            
            
        }
        LocalTime hh = LocalTime.of((int) coupure,0);
        LocalTime a=hh.plusMinutes((long)(minute));
        this.setDetail(detail.toArray(new Detail[detail.size()]));
        return a;

    }
    
    public double dichotomie(Connection c) throws Exception{
        double debut=0;
        double fin=100;
        double pas = fin;
        double conso = 0;
        LocalTime r = this.getHeure();
        while(fin-debut>0.00001){
            LocalTime heureDebut = this.getTimeCoupure(c, debut);
            LocalTime heureFin = this.getTimeCoupure(c, fin);
            if(heureDebut.isAfter(r) && heureFin.isAfter(r)){
                debut=fin;
                fin=fin+pas;
            }
            else if(heureDebut.isAfter(r) && heureFin.isBefore(r)){
                pas = (fin-debut)/2;
                fin=debut+pas;
            }
            else if(heureFin.toString().hashCode()==r.toString().hashCode()){
                conso =fin;
                debut = fin;
            }
            else if(heureDebut.toString().hashCode()==r.toString().hashCode()){
                conso =debut;
                fin = debut;
            }
            conso=fin;
        }
        return conso;
    }
    
    public double iteration(Connection c) throws Exception{
        double conso = 0;
        double itera = 0.1;
         LocalTime heure = this.getTimeCoupure(c, conso);
         LocalTime avant = null;
         LocalTime r = this.getHeure();
         while (heure.isAfter(r)) {
             conso=conso+itera;
             avant = heure;
             heure = this.getTimeCoupure(c, conso);
         }
         long d1 = Math.abs(r.until(avant, java.time.temporal.ChronoUnit.MINUTES)); 
         long d2 = Math.abs(r.until(heure, java.time.temporal.ChronoUnit.MINUTES)); 

         if (d1 <= d2) {
             conso = conso - itera;
         }
         return conso;
    }
    
    
    
    public Jour getPrevision(Connection c,String dateJour,Source source) throws Exception{
        Jour [] j = Jour.getListeByDateAndSource(c, dateJour, source);
        Jour [] jj = Jour.getListe(c, source);
        
        double matin=0;
        double midi=0;
        double consomation=0;
        for(int i=0;i<j.length;i++){
            matin = matin + j[i].getMatin();
            midi = midi + j[i].getMidi();
        }
        for(int i=0;i<jj.length;i++){
            consomation = consomation + jj[i].dichotomie(c);
        }
        System.out.println(j.length);
        System.out.println(jj.length);
        matin = matin/j.length;
        midi = midi/j.length;
        consomation = consomation/jj.length;
        Luminosite [] l=Luminosite.getLuminosite(c,dateJour,8);
        Jour jour=new Jour();
       

//        jour.setHeureCoupure(""+coupure);
        jour.setDateJour(dateJour);
        System.out.println(jour.getDateJour());
        jour.setMatin(matin);
        jour.setMidi(midi);
        jour.setSource(source);
        LocalTime time= jour.getTimeCoupure(c, consomation);
        String t = ""+time;
        jour.setHeureCoupure(t);
        jour.setCon(consomation);
//        System.out.println("sds"+t);
        return jour;
    }
    
    public static Jour [] getListe(Connection c,Source source)throws Exception{
        Statement sm=c.createStatement();
        Vector<Jour> v=new Vector<Jour>();
        ResultSet res = sm.executeQuery("select*from jour where idsource="+source.getIdSource());
         while(res.next()){
           Source s =Source.getSource(c, res.getInt("idsource"));
            v.add(new Jour(res.getInt("idjour"),res.getString("datejour"),res.getString("heurecoupure"),res.getDouble("matin"),res.getDouble("midi"),s));
        }
         
       Jour [] empl=new Jour[v.size()];
        for(int i=0;i<v.size();i++){
            empl[i]=(Jour)v.get(i);
        } 
        return empl;  
    }
    
    public static Jour [] getListeByDateAndSource(Connection c, String date, Source source) throws Exception {
        Jour[] jours = Jour.getListe(c,source);
        Vector<Jour> v=new Vector<Jour>();
        LocalDate dateAComparer = LocalDate.parse(date); 
        for (int i=0;i<jours.length;i++) {
            LocalDate dateJour = LocalDate.parse(jours[i].getDateJour()); 
            if (dateJour.getDayOfWeek() == dateAComparer.getDayOfWeek()) {
                v.add(jours[i]);
            }
        }
        Jour [] empl=new Jour[v.size()];
        for(int i=0;i<v.size();i++){
            empl[i]=(Jour)v.get(i);
        } 
        return empl;  
    }
    

}