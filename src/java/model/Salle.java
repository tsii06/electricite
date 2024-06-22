/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Tsiory
 */
public class Salle {
    int idSalle;
    Source source;
    String salle;

    public Salle(int idSalle, Source source, String salle) {
        this.setIdSalle(idSalle);
        this.setSource(source);
        this.setSalle(salle);
    }
    
    public Salle(){
        
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
    
    public static Salle getSalle(Connection c,int ida) throws Exception{
        Statement sm=c.createStatement();
        ResultSet res = sm.executeQuery("select*from salle where idsalle="+ida);
         while(res.next()){
             Source s =Source.getSource(c, res.getInt("idsource"));
           return new Salle(res.getInt("idsalle"),s,res.getString("nom"));
        }
       return null;
    }
}
