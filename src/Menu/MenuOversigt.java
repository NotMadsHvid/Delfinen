package Menu;

import Medlem.Medlemsliste;
import Resultat.Resultater;
import Kontingent.Kontingent;
import Medlemstype.Medlemstype;
import java.util.Scanner;

public class MenuOversigt {
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu() {
        Medlemsliste medlemsliste = new Medlemsliste();
        Medlemstype medlemstype = new Medlemstype();
        Kontingent kontingent = new Kontingent();
        Resultater resultat = new Resultater(0.0, "0", true, false);
        boolean running = true;

        System.out.print("Velkommen til svømmeklubben Delfin. Vælg en af følgende valgmuligheder: ");

        //Loop til brugergrænseoverfladen.
        while (running) {
            visBrugergrænseflade();

            try {
                String input = scanner.nextLine();
                int valg = Integer.parseInt(input);
                switch (valg) {
                    // Menu til medlemliste (Opret medlem, slet medlemt, se alle medlemmer).
                    case 1:
                        boolean runningMedlemlisteMenu = true;

                        while (runningMedlemlisteMenu) {
                            MedlemlisteMenu();

                            try {
                                String MedlemlisteInput = scanner.nextLine();
                                int MedlemlisteValg = Integer.parseInt(MedlemlisteInput);

                                switch (MedlemlisteValg) {
                                    case 1:
                                        //Opret medlem
                                        System.out.println("--- Opret medlem ---");
                                        medlemsliste.tilføjMedlem();
                                        medlemstype.opdaterKategorisering();
                                        break;
                                    case 2:
                                        //Se medlemsliste
                                        System.out.println("--- Liste af medlemsoversigt ---");
                                        medlemsliste.getMedlemslisteInfo();
                                        break;
                                    case 3:
                                        //Fjern medlem
                                        System.out.println("--- Fjern medlem ---");
                                        medlemsliste.getMedlemslisteInfo();
                                        medlemsliste.fjernMedlem();
                                        System.out.print("► ");
                                        break;
                                    case 4:
                                        runningMedlemlisteMenu = false;
                                        break;
                                    default:
                                        System.out.println("Forkert input. Prøv igen.");
                                }
                            } catch (Exception e) {
                                System.out.println("Forkert input. Prøv igen.");
                            }
                        }
                        break;

                    // Menu til at se oversigt af svømmeresultater
                    case 2:
                        boolean runningSvømmeresultat = true;

                        while (runningSvømmeresultat) {
                            visResultatMenu();

                            try {
                                String svømmeResultatInput = scanner.nextLine();
                                int svømmeResultatValg = Integer.parseInt(svømmeResultatInput);

                                switch (svømmeResultatValg) {
                                    //Se træningsresultater
                                    case 1:
                                        System.out.println("--- Liste af svømmesresultater ---");
                                        resultat.SeKonkurrenceResultat();
                                        break;
                                    case 2:
                                        System.out.println("--- Tilføj svømmeresultater ---");
                                        resultat.tilføjKonkurrenceResultat();
                                        break;
                                    case 3:
                                        runningSvømmeresultat = false;
                                        break;
                                    default:
                                        System.out.println("Forkert input. Prøv igen");
                                }
                            } catch (Exception e) {
                                System.out.println("Forkert Input. Prøv Igen.");
                            }
                        }
                        break;

                    //Menu til kontingent
                    case 3:
                        boolean runningKontingentMenu = true;

                        while (runningKontingentMenu) {
                            visKontingentMenu();

                            try {
                                String kontingentInput = scanner.nextLine();
                                int valgKontingent = Integer.parseInt(kontingentInput);

                                switch (valgKontingent) {
                                    case 1:
                                        //Vis forventet kontingent
                                        System.out.println("--- Liste af Kontingent ---");
                                        kontingent.beregnTotalKontingent(medlemsliste);
                                        break;
                                    case 2:
                                        //Vis restance
                                        System.out.println("--- Liste af restance ---");
                                        kontingent.iRestance(medlemsliste);
                                        break;
                                    case 3:
                                        runningKontingentMenu = false;
                                        break;
                                    default:
                                        System.out.println("Forkert input. Prøv igen.");
                                }
                                //Hvis inputtet ikke er et tal
                            } catch (Exception e) {
                                System.out.println("Forket input. Prøv igen");
                            }
                        }
                        break;
                    //Afslutter programmet
                    case 4:
                        System.out.println("Afslut.");
                        running = false;
                        break;
                    default:
                        System.out.println("Forkert input. Tast 1, 2, 3 eller 4.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Forket input. Tast 1, 2, 3 eller 4");
            }
        }
        scanner.close();
    }


    //Metode start menu oversigten
    public static void visBrugergrænseflade() {
        System.out.println(" ");
        System.out.println("--- Hovedmenu ---");
        System.out.println("1: Se medlemsliste.");
        System.out.println("2: Se svømmeresultater.");
        System.out.println("3: Se Kontingent.");
        System.out.println("4: Afslut.");
        System.out.print("► ");
    }

    //Metode til kontigent menuen
    public static void visKontingentMenu() {
        System.out.println(" ");
        System.out.println("--- Kontingent Menu ---");
        System.out.println("1: Vis forventet kontingent.");
        System.out.println("2: Vis restance.");
        System.out.println("3: Tilbage til hovedmenu.");
        System.out.print("► ");
    }

    public static void MedlemlisteMenu() {
        System.out.println(" ");
        System.out.println("--- Medlems Menu ---");
        System.out.println("1: Opret medlem.");
        System.out.println("2: Vis medlemsliste.");
        System.out.println("3: Slet medlem.");
        System.out.println("4: Tilbage til hovedmenu. ");
        System.out.print("► ");
    }

    public static void visResultatMenu() {
        System.out.println(" ");
        System.out.println("--- Resultat Menu ---");
        System.out.println("1: Vis resultat");
        System.out.println("2: Tilføj resultat");
        System.out.println("3: Tilbage til hovedmenu");
        System.out.print("► ");
    }
}
