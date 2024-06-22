/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Tsiory
 */
public class Luminosite {
    String dateJour;
    double debut;
    double valeur;

    public Luminosite(String dateJour,double debut, double valeur) {
        this.dateJour = dateJour;
        this.debut = debut;
        this.valeur = valeur;
    }
    
    public Luminosite(){
    }

    public double getDebut() {
        return debut;
    }

    public void setDebut(double debut) {
        this.debut = debut;
    }
    
    

    public String getDateJour() {
        return dateJour;
    }

    public void setDateJour(String dateJour) {
        this.dateJour = dateJour;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
    public static Luminosite [] getLuminosite(Connection c,String date,double limit) throws Exception{
        Statement sm=c.createStatement();
        Vector<Luminosite> v=new Vector<Luminosite>();
        ResultSet res = sm.executeQuery("select*from luminosite where datejour='"+date+"' order by debut ASC limit "+limit);
         while(res.next()){
           Luminosite l= new Luminosite(res.getString(1),res.getInt(2),res.getDouble(3));
           v.add(l);
        }
         
       Luminosite [] empl=new Luminosite[v.size()];
        for(int i=0;i<v.size();i++){
            empl[i]=(Luminosite)v.get(i);
        } 
        return empl;   
    }
}
