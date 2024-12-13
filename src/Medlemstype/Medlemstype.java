package Medlemstype;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Medlem.Medlem;
import Resultat.Resultater;

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

            //Sørger for at skippe overskriften når den skal læse informationer fra filen
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine().trim();
                opdateredeLinjer.add(header);
            }

            // Tjekker hvilke medlemmer som mangler at få tilføjet medlemstyper og tilføjer dem derefter.
            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine().trim();
                if (linje.isEmpty()) continue;

                String[] data = linje.split(";");
                //tjekker for at der er i hvertfald 4 fields i tekstfilen, hvis der ikke er 4 eller flere fields, så sletter den medlemmet.
                if (data.length < 4) continue;

                //skriver de variabler der skal printes ind i filen og giver dem en placering samt formaterer det på den ønskede måde.
                String navn = data[0];
                String[] fødselsdato = data[1].split("-");
                boolean aktivtmedlem = Boolean.parseBoolean(data[2]);
                boolean konkurrencesvømmer = Boolean.parseBoolean(data[3]);

                int fødselsDag = Integer.parseInt(fødselsdato[0]);
                int fødselsMåned = Integer.parseInt(fødselsdato[1]);
                int fødselsÅr = Integer.parseInt(fødselsdato[2]);

                // Opretter medlem for beregning
                Medlem medlem = new Medlem(navn, fødselsDag, fødselsMåned, fødselsÅr, aktivtmedlem, konkurrencesvømmer);

                //Bestemmer alderskategori
                String alderskategori;
                if (medlem.beregnAlder() < 18) {
                    juniormedlem = true;
                    alderskategori = "Junior";
                } else {
                    seniormedlem = true;
                    alderskategori = "Senior";
                }

                //Bestemmer hold og svømmeresultater
                String hold = "Ingen hold";
                if (konkurrencesvømmer) {
                    if (medlem.beregnAlder() < 18) {
                        hold = "Juniorhold";
                    } else {
                        hold = "Seniorhold";
                    }
                }



                //Laver en variable med formateringen og de ønskede variabler og tilføjer den til en arrayliste
                String opdateretLinje = String.format("%s;%s;%b;%b;%s;%s", navn, data[1], aktivtmedlem, konkurrencesvømmer, alderskategori, hold);
                opdateredeLinjer.add(opdateretLinje);
            }

            scanner.close();

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
