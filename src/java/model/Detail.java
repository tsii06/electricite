/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tsiory
 */
public class Detail {
    double heure;
    double resteBatterie;
    double puissancePanneau;
    double consomation;

    public double getConsomation() {
        return consomation;
    }

    public void setConsomation(double consomation) {
        this.consomation = consomation;
    }

    public double getHeure() {
        return heure;
    }
    

    public void setHeure(double heure) {
        this.heure = heure;
    }

    public double getResteBatterie() {
        return resteBatterie;
    }

    public void setResteBatterie(double resteBatterie) {
        this.resteBatterie = resteBatterie;
    }

    public double getPuissancePanneau() {
        return puissancePanneau;
    }

    public void setPuissancePanneau(double puissancePanneau) {
        this.puissancePanneau = puissancePanneau;
    }

    public Detail(int heure, double resteBatterie, double puissancePanneau,double consomation) {
        this.heure = heure;
        this.resteBatterie = resteBatterie;
        this.puissancePanneau = puissancePanneau;
        this.consomation = consomation;
    }
    public Detail(){
        
    }
    
    
    
}
