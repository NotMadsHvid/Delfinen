package Resultat;

import java.time.LocalDate;

public class Stævneresultat {
    private double stævneResultat;
    private LocalDate stævneDato;


    public Stævneresultat(double stævneResultat, int stævneDag, int stævneMåned, int stævneÅr){
        this.stævneResultat = stævneResultat;
        this.stævneDato = LocalDate.of(stævneÅr, stævneMåned, stævneDag);
    }

    //Getter og setter
    public double getStævneResultat(){
        return stævneResultat;
    }
    public LocalDate getstævneDato(){
        return stævneDato;
    }
    public void setStævneResultat(double stævneResultat){
        this.stævneResultat = stævneResultat;
    }
    public void setStævneDato(LocalDate stævneDato){
        this.stævneDato = stævneDato;
    }
}
