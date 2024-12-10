package Medlem;
import java.util.ArrayList;

public class MedlemManager {
    private static int næsteID = 1; // Statisk tæller for unikke ID'er
    private ArrayList <Medlem> medlemmer;

    // Constructer
    public  MedlemManager() {
        medlemmer = new ArrayList<>();
    }

    // Tilføjer et nyt medlem
    public Medlem tilføjMedlem(String navn, int fødselsDag, int fødselsMåned, int fødselsÅr) {

        // Standardværdier for Medlemstype
        boolean juniormedlem = false; // F.eks. alle starter som ikke-juniormedlem
        boolean aktivtmedlem = true; // Standard: Aktivt medlem
        boolean konkurrencesvømmer = false; // Standard: Ikke konkurrencesvømmer

        // Skaber et nyt Medlem med næsteID som medlemsID
        Medlem nytMedlem = new Medlem(navn, fødselsDag, fødselsMåned, fødselsÅr,
                juniormedlem, aktivtmedlem, konkurrencesvømmer, næsteID++);
        medlemmer.add(nytMedlem); // Tilføj medlem til listen
        return nytMedlem;
    }
    // Fjerner et medlem baseret på medlemsID
    public  boolean fjernMedlem(int medlemsID) {
        for(Medlem medlem : medlemmer) {
            if(medlem.getMedlemsID() == medlemsID) {
                medlemmer.remove(medlem);
                System.out.println("Medlem med ID " + medlemsID + " er blevet fjernet.");
                return true; // Succesfuldt fjernet
            }
        }
        System.out.println("Medlem med ID " + medlemsID + "blev ikke fundet.");
        return false; // Medlem blev ikke fundet
    }

    // Finder et medlem baseret på medlemsID
    public Medlem findMedlem(int medlemsID) {
        for (Medlem medlem : medlemmer) {
            if (medlem.getMedlemsID() == medlemsID) {
                return medlem;
            }
        }
        return null; // Hvis medlem ikke findes
    }

    //  Viser alle medlemmer
    public void visAlleMedlemmer () {
        if(medlemmer.isEmpty()) {
            System.out.println("Ingen medlemmer registreret.");
        } else {
            for(Medlem medlem : medlemmer) {
                System.out.println(medlem);
            }
        }
    }

    // Returnerer antallet af registrerede medlemmer
    public int antalMedlemmer() {
        return medlemmer.size();
    }

}
