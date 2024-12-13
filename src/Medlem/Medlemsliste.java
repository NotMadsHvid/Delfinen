package Medlem;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import Resultat.Resultater;

public class Medlemsliste {
    //private  static int næsteID = 1; // Statisk tæller for unikke ID'er
    ArrayList<Medlem> medlemmer;
    public Medlemsliste() {
        medlemmer = new ArrayList<>();
        læsMedlemmerFraFil();
    }

    // Læs medlemmer fra filen
    public void læsMedlemmerFraFil() {
        try (Scanner fileScanner = new Scanner(new File("src/Medlem/Medlemsliste.txt"))) {
            // Spring den første linje over (typisk header)
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();  // Læs og ignorer den første linje
            }

            while (fileScanner.hasNextLine()) {
                String linje = fileScanner.nextLine().trim();

                // Spring over tomme linjer
                if (linje.isEmpty()) {
                    continue;
                }

                String[] data = linje.split(";");

                // Hvis data ikke er korrekt delt, spring linjen over
                if (data.length < 6) {
                    System.out.println("Fejl i linjeformat: " + linje);
                    continue;  // Fortsæt til næste linje
                }

                String navn = data[0];
                String[] fødselsdato = data[1].split("-");
                boolean aktiv = Boolean.parseBoolean(data[2]);
                boolean konkurrencesvømmer = Boolean.parseBoolean(data[3]);
                String medlemsgruppe = data[4];
                String hold = data[5];

                // Fejlhåndtering for fødselsdato
                if (fødselsdato.length != 3) {
                    System.out.println("Fejl i fødselsdatoformat: " + data[1]);
                    continue;
                }

                int fødselsDag = 0, fødselsMåned = 0, fødselsÅr = 0;
                try {
                    fødselsDag = Integer.parseInt(fødselsdato[0]);
                    fødselsMåned = Integer.parseInt(fødselsdato[1]);
                    fødselsÅr = Integer.parseInt(fødselsdato[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Fejl i fødselsdatoens talformat: " + data[1]);
                    continue;  // Fortsæt til næste linje
                }

                // Opret medlem og tilføj til listen
                Medlem medlem = new Medlem(navn, fødselsDag, fødselsMåned, fødselsÅr, aktiv, konkurrencesvømmer);
                medlemmer.add(medlem);  // Tilføj medlem til listen
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    // Tilføj medlem fra input og opdater både fil og ArrayList
    public void tilføjMedlem() {
        Scanner scan = new Scanner(System.in);
        try (FileOutputStream file = new FileOutputStream("src/Medlem/Medlemsliste.txt", true);
             PrintStream medlemsliste = new PrintStream(file)) {

            System.out.println("Indtast navn: ");
            String navn = scan.nextLine();

            System.out.println("Indtast fødselsdag (dd): ");
            int fødselsDag = scan.nextInt();

            System.out.println("Indtast fødselsmåned (mm): ");
            int fødselsMåned = scan.nextInt();

            System.out.println("Indtast fødselsår (yyyy): ");
            int fødselsÅr = scan.nextInt();

            scan.nextLine();
            System.out.println("Vil medlemmet have et aktivt medlemskab? (ja/nej): ");
            String input = scan.nextLine();

            boolean aktiv = false;

            if (input.equalsIgnoreCase("ja")) {
                aktiv = true;
            }

            System.out.println("Vil medlemmet være konkurrencesvømmer? (ja/nej):");
            String input2 = scan.nextLine();

            boolean konkurrencesvømmer = false;

            if (input2.equalsIgnoreCase("ja")) {
                konkurrencesvømmer = true;
            }

            String output = String.format("%s;%02d-%02d-%d;%b;%b", navn, fødselsDag, fødselsMåned, fødselsÅr, aktiv, konkurrencesvømmer);
            Medlem nytMedlem = new Medlem(navn, fødselsDag, fødselsMåned, fødselsÅr, aktiv, konkurrencesvømmer);

            // Tilføj medlemmet til listen
            medlemmer.add(nytMedlem);

            // Skriv medlemmet til filen
            medlemsliste.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Constructor

    // Fjern et medlem baseret på medlemsID
    public void fjernMedlem() {
        System.out.print("Indtast medlemsID: ");
        Scanner scanner = new Scanner(System.in);
        int medlemsID = scanner.nextInt();
        boolean fundetMedlem = false;

        for (Medlem medlem : medlemmer) {
            if (medlem.getMedlemsID() == medlemsID) {
                System.out.println("Medlem med ID " + medlemsID + " er blevet fjernet");
                medlemmer.remove(medlem);
                fundetMedlem = true;
                break;
            }
        } if (!fundetMedlem)
        System.out.println("Medlem med ID " + medlemsID + " blev ikke fundet.");
    }


    public void getMedlemslisteInfo() { //opdateres løbende
        if (medlemmer.isEmpty()) {
            System.out.println("Ingen medlemmer på listen.");
        } else {
            System.out.println("Liste over alle medlemmer.");
            for (Medlem m : medlemmer) {
                System.out.println(m.getMedlemsID() + ". Navn: " + m.getNavn() + " |Alder: " + m.beregnAlder() + " |Aktivtmedlemskab: " + m.getAktiv() + " |Konkurrencesvømmer: " + m.getKonkurrencesvømmer());

            }
        }

    }
    public ArrayList<Medlem> getMedlemmer() {
        return medlemmer;
    }

}
