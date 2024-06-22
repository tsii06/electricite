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
public class Source {
    int idSource;
    double panneau;
    double battery;

    public Source(int idSource, double panneau, double battery) {
        this.setIdSource(idSource);
        this.setPanneau(panneau);
        this.setBattery(battery);
    }
    
    public Source(){
        
    }

    public int getIdSource() {
        return idSource;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public double getPanneau() {
        return panneau;
    }

    public void setPanneau(double panneau) {
        this.panneau = panneau;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }
    
    public static Source getSource(Connection c,int ida) throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from source where idsource="+ida);
         while(res.next()){
           return new Source(res.getInt("idsource"),res.getDouble("panneau"),res.getDouble("battery"));
        }
       return null;
    }
    public static Source [] getAllSource(Connection c) throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from source");
        Vector<Source> so = new Vector<Source>();
         while(res.next()){
           Source s = new Source(res.getInt("idsource"),res.getDouble("panneau"),res.getDouble("battery"));
           so.add(s);
        }
       return (Source[]) so.toArray(new Source[so.size()]);
    }
    
    public static Jour [] getPrediction(Connection c,String date)throws Exception{
        Source [] s = Source.getAllSource(c);
        Jour [] j = new Jour[s.length];
        for(int i = 0;i<s.length;i++){
            j[i] = new Jour().getPrevision(c, date, s[i]);
        }
        return j; 
    }
    
}
