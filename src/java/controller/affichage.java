/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connexion.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Jour;
import model.Source;

/**
 *
 * @author Tsiory
 */
public class affichage extends HttpServlet {

   

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Connection c=Connexion.getConnection(); 
            Jour[] j = Source.getPrediction(c, "2024-01-03");
            request.setAttribute("jour",j);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                c.close();
        }
        catch(Exception e){
            
        }
      
    }

}
