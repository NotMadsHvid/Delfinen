package Medlem;
import Medlemstype.Medlemstype;
import Kontingent.Kontingent;
public class Main {
    public static void main(String[] args) {
        Medlemsliste medlemsliste = new Medlemsliste();
        Medlemstype medlemstype = new Medlemstype();
        Kontingent kontingent = new Kontingent();
        medlemsliste.tilføjMedlem();
        medlemstype.opdaterKategorisering();
        kontingent.opdaterKontingent();
        medlemsliste.getMedlemslisteInfo();
        kontingent.setBetalt("src/Medlem/Medlemsliste.txt");
        kontingent.beregnTotalKontingent(medlemsliste);
        //kontingent.iRestance(medlemsliste);
    }
}