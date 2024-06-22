/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.util.Vector;

/**
 *
 * @author Tsiory
 */
public class Historique {
    String dateJour;
    double nombreEtudiant;
    Salle salle;
    double coupure;
    
    public Historique(){
        
    }

    public Historique( String dateJour, double nombreEtudiant, Salle salle, double coupure) {
        this.dateJour = dateJour;
        this.nombreEtudiant = nombreEtudiant;
        this.salle = salle;
        this.coupure = coupure;
    }

//    public String getDateJour() {
//        return dateJour;
//    }
//
//    public void setDateJour(String dateJour) {
//        this.dateJour = dateJour;
//    }
//
//    public double getNombreEtudiant() {
//        return nombreEtudiant;
//    }
//
//    public void setNombreEtudiant(double nombreEtudiant) {
//        this.nombreEtudiant = nombreEtudiant;
//    }
//
//    public Salle getSalle() {
//        return salle;
//    }
//
//    public void setSalle(Salle salle) {
//        this.salle = salle;
//    }
//
//    public double getCoupure() {
//        return coupure;
//    }
//
//    public void setCoupure(double coupure) {
//        this.coupure = coupure;
//    }
//    
//    public Historique [] getHistorique(Connection c,int idSalle)throws Exception{
//        Jour [] j = Jour.getJour(c, idSalle);
//        Vector<Historique> v=new Vector<Historique>();
//        for(int i=0;i<j.length;i++){
//            Historique h = new Historique(j[i].getDateJour(),j[i].getMatin()+j[i].getMidi(),j[i].getSalle(),j[i].finalConsomation(c));
//            v.add(h);
//        }
//        return (Historique[]) v.toArray();
//    }
//    
//    public Historique getPrevision(Connection c,int idSalle,String date) throws Exception{
//        Historique h=new Historique();
//        double nombre=0;
//        double coupure=0;
//        int d=0;
//        Historique [] hh = this.getHistorique(c, idSalle);
//        for(int i=0;i<hh.length;i++){
//            if(hh[i].getDateJour()==date){
//                nombre = nombre + hh[i].getNombreEtudiant();
//                coupure = coupure + hh[i].getCoupure();
//                d++;
//            }
//        }
//        h=new Historique(date,nombre,Salle.getSalle(c, idSalle),coupure);
//        return h;
//    }
//        public double getConsomation(Connection c) throws Exception{
//        double heureMatin=this.getHeureMatin();
//        double heureMidi=this.getHeureMidi();
//        Luminosite [] l=Luminosite.getLuminosite(c, this.getDateJour(),heureMatin+this.arrondi(heureMidi));
////        System.out.println(this.arrondi(heureMidi));
//        double total = 0;
//        for(int i=0;i<l.length;i++){
//            double puissancePanneau = (this.getSource().getPanneau()*l[i].getValeur())/10;
////            System.out.println(puissancePanneau);
//            
//            if(i==l.length-1){
//                puissancePanneau = (puissancePanneau*48)/60;
////                System.out.println("dd");
//            }
//            total = total + puissancePanneau;
//        }
////        System.out.println(total);
//        double batt  = this.getSource().getBattery()/2;
//        double x = (batt+total)/((heureMatin*this.getMatin())+(heureMidi*this.getMidi()));
////        System.out.println("midi"+heureMidi);
//        return x;
//    }
//    
//    public double finalConsomation(Connection c)throws Exception{
//        double heureMatin=this.getHeureMatin();
//        double heureMidi=this.getHeureMidi();
//        Luminosite [] l=Luminosite.getLuminosite(c, this.getDateJour(),heureMatin+5);
//        double total = 0;
//        double x =this.getConsomation(c);
//        double negative = 0;
//        for(int i=0;i<l.length;i++){
//            
//            double puissancePanneau = (this.getSource().getPanneau()*l[i].getValeur())/10;
//            
//            if(l[i].getDebut()<12){    
//                double conso = (x*this.getMatin()) - puissancePanneau;
//                if(conso<0){
//                    negative = negative + Math.abs(conso);
//                    heureMatin-=1;
//                }
//            }else{
//                if(i==l.length-1){
//                    puissancePanneau = (puissancePanneau*48)/60;
////                    System.out.println("dd"+puissancePanneau);
//                }
//                double conso = (x*this.getMidi()) - puissancePanneau;
//                if(conso<0){
//                    negative = negative + Math.abs(conso);
//                        heureMidi-=1;     
//                }
//                
//            }
//        }
//        double plus = 0;
//        if(negative>0){
//            plus = negative/((heureMatin*this.getMatin())+(2.8*this.getMidi()));
//        }
//
//        return x-plus;
//    }
    //    public double getHeureMatin(){
//        double heure = this.getHeureCoupure() -8;
//        if(heure>4){
//            return 4;
//        }return heure;
//    }
//    
//    public double getHeureMidi(){
//        double heure = this.getHeureCoupure() -8;
//        if(heure<4){
//            return 0;
//        }return heure-4;
//    }
//    
//    public double arrondi(double nombre) {
//        double partieDecimale = nombre - Math.floor(nombre);
//        if (partieDecimale > 0.01) {
//            return Math.ceil(nombre);
//        }return 0;
//    }
}
