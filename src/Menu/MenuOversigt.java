package Menu;

import java.util.Scanner;
public class MenuOversigt {
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu() {
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
                                        System.out.println("1.");
                                        //Kode Her!
                                        break;

                                    case 2:
                                        System.out.println("2");
                                        //Kode Her!
                                        break;

                                    case 3:
                                        System.out.println("3");
                                        //Kode her!
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

                    // Menu til at se oversigt af svømmeresultater (
                    case 2:
                        boolean runningSvømmeresultat = true;

                        while (runningSvømmeresultat) {
                            visResultatMenu();

                            try {
                                String svømmeResultatInput = scanner.nextLine();
                                int svømmeResultatValg = Integer.parseInt(svømmeResultatInput);

                                switch (svømmeResultatValg) {
                                    case 1:
                                        System.out.println("1");
                                        //Kode Her!
                                        break;
                                    case 2:
                                        System.out.println("2");
                                        //Kode Her!
                                        break;
                                    case 3:
                                        System.out.println("3");
                                        //Kode Her!
                                        break;
                                    case 4:
                                        runningSvømmeresultat = false;
                                        break;
                                    default:
                                        System.out.println("Forket Input. Prøv igen");
                                }
                            } catch (Exception e) {
                                System.out.println("Forkert Input. Prøv Igen.");
                            }
                        }
                        break;

                    //Menu til kontigent
                    case 3:
                        boolean runningKontigentMenu = true;

                        while (runningKontigentMenu) {
                            visKontingentMenu();

                            try {
                                String kontigentInput = scanner.nextLine();
                                int valgKontigent = Integer.parseInt(kontigentInput);

                                switch (valgKontigent) {
                                    case 1:
                                        //Vis forventet kontigent
                                        System.out.println("1");
                                        //Kode Her!
                                        break;
                                    case 2:
                                        //Vis restance
                                        System.out.println("2");
                                        //Kode her!
                                        break;
                                    case 3:
                                        runningKontigentMenu = false;
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
        System.out.println("1: Se medlemsliste.");
        System.out.println("2: Se svømmeresultater.");
        System.out.println("3: Se Kontigent.");
        System.out.println("4: Afslut.");
    }

    //Metode til kontigent menuen
    public static void visKontingentMenu() {
        System.out.println(" ");
        System.out.println("1: Vis forventet kontigent.");
        System.out.println("2: Vis restance.");
        System.out.println("3: Tilbage til hovedmenu.");
    }

    public static void MedlemlisteMenu() {
        System.out.println(" ");
        System.out.println("1: Vis medlemsliste");
        System.out.println("2: Opret nyt medlem");
        System.out.println("3: Slet medlem.");
        System.out.println("4: Tilbage til hovedmenu. ");
    }

    public static void visResultatMenu() {
        System.out.println(" ");
        System.out.println("1: ");
        System.out.println("2: ");
        System.out.println("3: ");
        System.out.println("4: Tilbage til hovedmenu.");
    }
}
