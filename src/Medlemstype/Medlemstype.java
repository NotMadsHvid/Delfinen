
package Medlemstype;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Medlem.Medlem;

//Laver vores klasse
public class Medlemstype {
    private boolean juniormedlem;
    private boolean seniormedlem;

    //Laver vores constructor
    public Medlemstype() {
        this.juniormedlem = juniormedlem;
        this.seniormedlem = seniormedlem;
    }


    //laver en metode som kategoriserer medlemmerne
    public void kategoriserMedlem(String filsti) {
        ArrayList<String> opdateredeLinjer = new ArrayList<>();

        try {
            // Opretter scanner til at læse filen
            Scanner scanner = new Scanner(new File(filsti));

            // Sørger for at skippe overskriften når den skal læse informationer fra filen
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine().trim();
                opdateredeLinjer.add(header); // Add header to the updated lines
            }

            // Tjekker hvilke medlemmer som mangler at få tilføjet medlemstyper og tilføjer dem derefter.
            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine().trim();
                if (linje.isEmpty()) continue;

                String[] data = linje.split(";");
                // Tjekker for at der er mindst 4 fields i tekstfilen, hvis der ikke er 4 eller flere, så sletter den medlemmet.
                if (data.length < 4) continue;

                // Skriv de variabler der skal printes ind i filen og giver dem en placering samt formaterer det på den ønskede måde.
                String navn = data[0];
                String[] fødselsdato = data[1].split("-");
                boolean aktivtmedlem = Boolean.parseBoolean(data[2]);
                boolean konkurrencesvømmer = Boolean.parseBoolean(data[3]);

                int fødselsDag = Integer.parseInt(fødselsdato[0]);
                int fødselsMåned = Integer.parseInt(fødselsdato[1]);
                int fødselsÅr = Integer.parseInt(fødselsdato[2]);

                // Opretter medlem for beregning
                Medlem medlem = new Medlem(navn, fødselsDag, fødselsMåned, fødselsÅr, aktivtmedlem, konkurrencesvømmer);

                // Bestemmer alderskategori
                String alderskategori;
                if (medlem.beregnAlder() < 18) {
                    alderskategori = "Junior";
                } else {
                    alderskategori = "Senior";
                }

                // Bestemmer hold
                String hold = "Ingen hold";
                if (konkurrencesvømmer) {
                    if (medlem.beregnAlder() < 18) {
                        hold = "Juniorhold";
                    } else {
                        hold = "Seniorhold";
                    }
                }

                // Prepare the updated line
                String opdateretLinje = String.format("%s;%s;%b;%b;%s;%s", navn, data[1], aktivtmedlem, konkurrencesvømmer, alderskategori, hold);

                // Ensure that data[6] and data[7] are added if they exist
                String data6 = data.length >= 7 ? data[6] : ""; // In case data[6] exists
                String data7 = data.length >= 8 ? data[7] : ""; // In case data[7] exists

                // Append data6 and data7 (if available)
                opdateretLinje += ";" + data6 + ";" + data7;

                // Add the updated line to the list
                opdateredeLinjer.add(opdateretLinje);
            }

            scanner.close();

            // Write the updated lines back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filsti))) {
                for (String opdateretLinje : opdateredeLinjer) {
                    writer.println(opdateretLinje);
                }
            } catch (IOException e) {
                System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Fejl ved filhåndtering: " + e.getMessage());
        }
    }











    public void opdaterKategorisering() {
        Medlemstype medlemstype = new Medlemstype();
        medlemstype.kategoriserMedlem("src/Medlem/Medlemsliste.txt");
    }
}
