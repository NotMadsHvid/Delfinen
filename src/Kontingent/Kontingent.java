package Kontingent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Medlem.Medlem;
import Medlem.Medlemsliste;

public class Kontingent {
    private Medlemsliste medlemsliste;
    boolean aktivtmedlem;
    boolean passivtmedlem;
    //int passivtmedlem = 500;
    boolean aktivseniorRabat;
    //boolean aktivseniorRabat = totalKontingent * 0.75;
    double totalKontingent = 0;
    boolean erBetalt;


    public Kontingent() {
        this.aktivtmedlem = aktivtmedlem;
        this.passivtmedlem = passivtmedlem;
        this.aktivseniorRabat = aktivseniorRabat;
        this.totalKontingent = totalKontingent;
        this.erBetalt = false;
    }

    public void setKontingent(String filsti) {
        ArrayList<String> opdateredeLinjer = new ArrayList<>();
        try {
            // Opretter scanner til at læse filen
            Scanner scanner = new Scanner(new File(filsti));

            if (scanner.hasNextLine()) {
                String header = scanner.nextLine().trim();
                opdateredeLinjer.add(header);
            }

            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine().trim();
                if (linje.isEmpty()) continue;


                String[] data = linje.split(";");
                //tjekker for at der er i hvertfald 6 fields i tekstfilen, hvis der ikke er 6 eller flere fields, så sletter den medlemmet.
                if (data.length < 6) continue;
                String navn = data[0];
                String[] fødselsdato = data[1].split("-");
                boolean aktivtmedlem = Boolean.parseBoolean(data[2]);
                boolean konkurrencesvømmer = Boolean.parseBoolean(data[3]);
                String alderskategori = data[4];
                String hold = data[5];
                double kontingent = 0;



                if (data.length >= 7) {
                    // Replace comma with period in the Kontingent value and parse it
                    kontingent = Double.parseDouble(data[6].replace(",", ".").trim());

                    // Check if erBetalt is already true, only update it if it's not already true
                    if ("true".equalsIgnoreCase(data[7].trim())) {
                        // If 'true' in the data, set erBetalt to true
                        if (!erBetalt) {  // Only update if it wasn't true already
                            erBetalt = true;
                        }
                    }

                    // If there is a 'Betalt' field (data length >= 8), parse it
//                 if (data.length >= 8) {
//                      erBetalt = Boolean.parseBoolean(data[7].trim()); // Update erBetalt from the file
//                   }
                }

                 else {
                    // Handle the case where there are fewer than 7 fields
                    System.out.println("Data format error: Missing Kontingent value in line");
                }

                int fødselsDag = Integer.parseInt(fødselsdato[0]);
                int fødselsMåned = Integer.parseInt(fødselsdato[1]);
                int fødselsÅr = Integer.parseInt(fødselsdato[2]);

                Medlem medlem = new Medlem(navn, fødselsDag, fødselsMåned, fødselsÅr, aktivtmedlem, konkurrencesvømmer);

                int alder = medlem.beregnAlder();


                if (aktivtmedlem) {
                    if (alder < 18) {
                        totalKontingent = 1000;  // Junior members
                    } else {
                        totalKontingent = 1600;  // Senior members
                        if (alder > 60) {
                            totalKontingent = 1600 * 0.75;  // Senior discount
                        }
                    }
                } else {
                    // If not an active member, set Kontingent to 500
                    totalKontingent = 500;
                }
                if (kontingent != 0) {
                    totalKontingent = kontingent;
                }
                String opdateretLinje = String.format("%s;%s;%b;%b;%s;%s;%.2f;%b", navn, data[1], aktivtmedlem, konkurrencesvømmer, alderskategori, hold, totalKontingent, erBetalt);
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

    public void opdaterKontingent() {
        Kontingent kontingent = new Kontingent();
        kontingent.setKontingent("src/Medlem/Medlemsliste.txt");
    }


    //skal bruges senere til at når vi opretter et medlem så kører setteren og sætter erBetalt som false som default.
//    public boolean seterBetalt() {
//        return erBetalt = true;
//    }
//    public boolean geterBetalt() {
//        return erBetalt;
//    }

    public void setBetalt(String filsti) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv navnet på medlemmet som skal markeres som betalt: ");
        String medlemNavn = scanner.nextLine();

        ArrayList<String> updatedLines = new ArrayList<>();
        boolean medlem = false;

        try {
            Scanner fileScanner = new Scanner(new File(filsti));
            String header = fileScanner.nextLine().trim();
            updatedLines.add(header);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(";");
                if (data.length < 6) continue;

                String navn = data[0];

                if (navn.equalsIgnoreCase(medlemNavn)) {
                    data[7] = "true";
                    medlem = true;
                }


                String updatedLine = String.join(";", data);
                updatedLines.add(updatedLine);
            }

            fileScanner.close();

            if (!medlem) {
                System.out.println("medlemmet  " + medlemNavn + " er ikke i filen.");
            } else {
                PrintWriter writer = new PrintWriter(new FileWriter(filsti));
                for (String updatedLine : updatedLines) {
                    writer.println(updatedLine);
                }
                writer.close();
                System.out.println("Medlem sat til at de har betalt.");
            }
        } catch (IOException e) {
            System.out.println("Error handling the file: " + e.getMessage());
        }
    }

    public void beregnTotalKontingent(Medlemsliste medlemsliste) {
        double totalKontingent = 0;

        // Iterate over the list of members
        for (Medlem medlem : medlemsliste.getMedlemmer()) {
            int alder = medlem.beregnAlder();
            double kontingent = 0;

            // Check if the member is active
            if (medlem.getAktiv()) {
                if (alder < 18) {
                    kontingent = 1000;  // Junior members
                } else {
                    kontingent = 1600;  // Senior members
                    if (alder > 60) {
                        kontingent = 1600 * 0.75;  // Senior discount
                    }
                }
            } else {
                kontingent = 500;  // Inactive members
            }

            // Add the kontingent to the total
            totalKontingent += kontingent;
        }

        // Print the total Kontingent for all members
        System.out.println("Forventet kontigentindbetaling: " + totalKontingent + " kr.");
    }
    public void iRestance(Medlemsliste medlemsliste){
        for (Medlem medlem : medlemsliste.getMedlemmer()) {
           if (!erBetalt) {
                System.out.println(medlem);

            }
        }

    }
}

