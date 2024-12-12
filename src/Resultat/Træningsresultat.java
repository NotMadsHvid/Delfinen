package Resultat;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Træningsresultat {
    private double svømmeResultat;
    private String svømmeDisciplin;

    public Træningsresultat(double svømmeResultat, String svømmeDisciplin){
        this.svømmeResultat = svømmeResultat;
        this.svømmeDisciplin = svømmeDisciplin;
    }


    //Metode der tager bruger input og gemmer det i en fil
    public void læsTræningsResultater(){
        String fileNavn = "TræningsResultater.txt";
        ArrayList<Træningsresultat> træningsResultat = new ArrayList<>();

        try{ Scanner scanner = new Scanner(new FileReader("TræningsResultater.txt"));
            String linje;
            while(scanner.hasNextLine()){
                linje = scanner.nextLine().trim();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingen file fundet.");
        }
    }


}
