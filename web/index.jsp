<%-- 
    Document   : index
    Created on : 12 déc. 2023, 20:24:23
    Author     : Tsiory
--%>
<%@page import="model.Jour"%>
<%
    Jour [] s= (Jour[]) request.getAttribute("jour");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <% for(int i=0;i<s.length;i++) { %>
            <h1>Detail Consomation <%= s[i].getDateJour()%></h1>
            <p>Coupure : <%= s[i].getTapaka()%></p>
            <p>Matin : <%= s[i].getMatin()%></p>
            <p>Midi : <%= s[i].getMidi()%></p>
            <p>Consomation : <%= s[i].getCon()%></p>
            <table class="table table-striped" 
                <thead>
                    <tr>
                        <th>Heure</th>
                        <th>Panneau</th>
                        <th>Batterie</th>
                        <th>Consomation</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(int j=0;j<s[i].getDetail().length;j++) { %>
                    <tr>
                        <td><%= s[i].getDetail()[j].getHeure() %> </td>
                        <td><%= s[i].getDetail()[j].getPuissancePanneau() %></td>
                        <td><%= s[i].getDetail()[j].getResteBatterie() %></td>
                        <td><%= s[i].getDetail()[j].getConsomation() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
             
             <% } %>
        </div>
    </body>
</html>
