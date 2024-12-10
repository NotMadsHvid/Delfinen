package Medlem;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import Medlemstype.Medlemstype;


public class Medlem {
    // Attributes
    private int medlemsID;
    private String navn;
    private LocalDate fødselsDato;
    private Medlemstype medlemstype;


    // Constructor
    public Medlem(String navn, int fødselsDag, int fødselsMåned, int fødselsÅr, boolean juniormedlem, boolean aktivtmedlem, boolean konkurrencesvømmer, int medlemsID) {
        this.navn = navn;
        this.fødselsDato = LocalDate.of(fødselsÅr, fødselsMåned, fødselsDag);
        this.medlemstype = new Medlemstype(juniormedlem, aktivtmedlem, konkurrencesvømmer);
        this.medlemsID = medlemsID; // Initialiser medlemsID
    }

    // En getter til at få hele fødsels datoen på et medlem
    public LocalDate getFødselsdag () {
        return fødselsDato;
    }

    // En getter til at få navn
    public String getNavn() {
        return navn;
    }

    // Getter for medlemsID
    public int getMedlemsID(){
        return medlemsID;
    }

    //Metode til at returnere info om medlemmet
    public String getInfo () {
        DateTimeFormatter danskFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "MedlemsID: " + medlemsID + ", Navn: " + navn + ", Fødselsdato: " + fødselsDato.format(danskFormat);
    }

    // Overrider toString for læsevenlighed (readability)
    @Override
    public String toString () {
        DateTimeFormatter danskFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "MedlemsID: " + medlemsID + "Navn: " + navn + ", Fødselsdato: " + fødselsDato.format(danskFormat);
    }

    // Beregner alder baseret på nuværende dato
    public int beregnAlder() {
        LocalDate nuværendeTid = LocalDate.now();
        return Period.between(fødselsDato, nuværendeTid).getYears();
    }

    public int opdaterendeAlder() {
        int opdateretAlder = beregnAlder();
        return opdateretAlder;
    }
}