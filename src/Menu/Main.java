import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        boolean running = true;

        System.out.println("Velkommen til svømmeklubben Delfinen. Vælg en af følgende valgmuligheder: ");

        while(running){
            boolean runningKontigentMenu = true;
            visBrugergrænseflade();

            int valg = scanner.nextInt();
            scanner.nextLine();
            switch(valg){
                // Opretter nyt medlem
                case 1:
                    System.out.println("Du har valgt 1");
                    // Kode Her!
                    break;
                // Medlemsliste (
                case 2:
                    System.out.println("Du har valgt 2");

                    //Kode Her!
                    break;
                // Checker kontinget
                case 3:
                    System.out.println("Du har valgt 3");
                    while(runningKontigentMenu){
                        visKontingentMenu();

                        int valgKontigent = scanner.nextInt();
                        scanner.nextLine();
                        switch(valgKontigent){
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
                            default:
                                break;
                        }

                    }
                    //Kode Her!
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }
    public static void visBrugergrænseflade(){
        System.out.println(" ");
        System.out.println("Tast 1: ");
        System.out.println("Tast 2: ");
        System.out.println("Tast 3: ");
    }
    public static void visKontingentMenu(){
        System.out.println(" ");
        System.out.println("Vis forventet kontigent: ");
        System.out.println("Vis restance: ");
        System.out.println("Tilbage til hovedmenu: ");
    }
}
