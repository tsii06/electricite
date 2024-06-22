
import connexion.Connexion;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import model.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {
    public static void main(String[] args) throws Exception {
       Connection c=Connexion.getConnection(); 
//      Jour[] j = Source.getPrediction(c, "2023-12-27");
////       
////       
////       
//////       
////
////
////
Jour j = Jour.getJourById(c, 127);
////System.out.println(j[0].getTapaka());
////System.out.println(j[0].getMatin());
////System.out.println(j[0].getMidi());
////System.out.println(j[0].getDetail()[0].getResteBatterie());
//////System.out.println("dsd");
System.out.println(j.dichotomie(c));
c.close();

//for(int i=60;i<67;i++){
//    System.out.println(i+"="+j.getTimeCoupure(c, i));
////}
//
////System.out.println(j.getTimeCoupure(c, 65));
//////
//////System.out.println(j.getHeureMidi());
//////System.out.println(j.finalConsomation(c));
//////System.out.println(j.getHeureCoupure());
////
//
//}

    }
 
}


