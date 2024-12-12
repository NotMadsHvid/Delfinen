package Medlem;
import java.util.ArrayList;
import java.util.Scanner;

public class Medlemsliste {
    private  static int næsteID = 1; // Statisk tæller for unikke ID'er
    private ArrayList<Medlem> medlemmer;

    public void tilføjMedlemFraInput(Scanner scanner) {

        System.out.println("Indtast navn: ");
        String navn = scanner.nextLine();

        System.out.println("Indtast fødselsdag (dd): ");
        int fødselsDag = scanner.nextInt();

        System.out.println("Indtast fødselsmåned (mm): ");
        int fødselsMåned = scanner.nextInt();

        System.out.println("Indtast fødselsår (yyyy): ");
        int fødselsÅr = scanner.nextInt();

        scanner.nextLine(); // For at rydde bufferen efter næsteInt()

        // Opret medlem og tilføj fil til listen
        Medlem nytMedlem = new Medlem(navn, fødselsDag, fødselsMåned,fødselsÅr, næsteID++);
        medlemmer.add(nytMedlem);

        System.out.println("Nyt medlem tilføjet: " + nytMedlem.getInfo());



        /**try {
            FileOutputStream file = new FileOutputStream("src/outputt.txt", true);
            PrintStream outputt = new PrintStream(file);
            System.out.println("Skriv hvad der skal tilføjes til outputt.txt filen");
            String tekst = scan.nextLine();
            outputt.println(tekst);
            outputt.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
         **/
    }

    // Constructor
    public Medlemsliste() {
        medlemmer = new ArrayList<>();
    }

    // Fjern et medlem baseret på medlemsID
    public boolean fjernMedlem(int medlemsID) {
        for (Medlem medlem : medlemmer) {
            if (medlem.getMedlemsID() == medlemsID) {
                medlemmer.remove(medlem);
                System.out.println("Medlem med ID " + medlemsID + " er blevet fjernet");
                return true;
            }
        }
        System.out.println("Medlems med ID " + medlemsID + " blev ikke fundet.");
        return false;
    }


    public void getMedlemslisteInfo() { //opdateres løbende
        if (medlemmer.isEmpty()) {
            System.out.println("Ingen medlemmer på listen.");
        } else {
            System.out.println("Liste over alle medlemmer.");
            for (Medlem m : medlemmer) {
                System.out.println(m.getMedlemsID() + ". Navn: " + m.getNavn() + " |Alder: " + m.beregnAlder());

            }
        }

    }

}
