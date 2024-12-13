package Resultat;

import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Resultater {

    private double svømmeTider;
    private String svømmeDisciplin;
    private  boolean stævne;
    private  boolean træning;

    public Resultater(double svømmeTider, String svømmeDisciplin, boolean stævne, boolean træning){
        this.svømmeTider = svømmeTider;
        this.svømmeDisciplin = svømmeDisciplin;
        this.stævne = stævne;
        this.træning = træning;
    }

    @Override
    public String toString(){
        return "Svømmetid: " + svømmeTider +
                ", Disciplin: " + svømmeDisciplin +
                ", Stævne: " + (stævne ? "Ja" : "Nej") +
                ", Træning: " + (træning ? "Ja" : "Nej");
    }

    public static void tilføjKonkurrenceResultat() {
        Scanner scan = new Scanner(System.in);
        double tid = 0;

        while(true){
            try{
                System.out.println("Indtast svømmetid (sekunder): ");
                tid = Double.parseDouble(scan.nextLine());
                if (tid <=0 )throw new NumberFormatException("Tid skal være positiv");
                break;
            } catch (NumberFormatException e){
                System.out.println("Ugyldigt input. Prøv igen: " + e.getMessage());
            }
        }

        System.out.println("Indtast svømmedisciplin: ");
        String disciplin = scan.nextLine().trim();
        
        boolean erStævne = fåBooleanSvar(scan, "Er det et stævne? (ja/nej): ");
        boolean erTræning = fåBooleanSvar(scan, "Er det træning ? (ja/nej): ");

        Resultater resultat = new Resultater(tid, disciplin, erStævne, erTræning);

        // Tilføj resultat til medlemslisten eller skriv til fil
        System.out.println("Resultatet er tilføjet:");
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println((df.format(resultat)));


        try (FileOutputStream file = new FileOutputStream("src/Resultat/Resultatliste.txt", true);
             PrintStream resultatListe = new PrintStream("src/Resultat/Resultatliste.txt")) {

            String output = String.format("%f;%s;%b; %b", tid, disciplin, erStævne, erTræning).trim();
            resultatListe.println(output);

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    private static boolean fåBooleanSvar(Scanner scan, String besked) {
        while(true){
            System.out.println(besked);
            String svar = scan.nextLine().trim().toLowerCase();
            if (svar.equals("ja")) return true;
            if (svar.equals("nej")) return false;
            System.out.println("Ugyldigt svar. Skriv venligst 'ja' eller 'nej'. ");
        }
    }

    public static void SeKonkurrenceResultat(){
        try { Scanner fileScanner = new Scanner(new File("src/Resultat/Resultatliste.txt"));

            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine().trim();
                String[] data = line.split(";");

                if (line.isEmpty()){
                    continue;
                }

                if(data.length == 8){
                    try {
                        double tid = Double.parseDouble(data[0]);
                        String disciplin = data[1];
                        boolean erStævne = Boolean.parseBoolean(data[2]);
                        boolean erTræning = Boolean.parseBoolean(data[3]);

                        String output = String.format("Svømmetid: %.2f sek, Disciplin: %s, Stævne: %b, Træning: %b\n", tid, disciplin, erStævne ? "ja" : "nej", erTræning ? "ja" : "nej");
                        System.out.println(output);
                    } catch (NumberFormatException e){
                        System.out.println("Fejl i talformat på linje " + line);
                    }
                } else {
                    System.out.println("Ugyldig linje" + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af file" + e.getMessage());
        }
    }
}

