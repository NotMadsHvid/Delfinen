package Medlem;
import Medlemstype.Medlemstype;
public class Main {
    public static void main(String[] args) {
        Medlemsliste medlemsliste = new Medlemsliste();
        Medlemstype medlemstype = new Medlemstype();
        medlemsliste.tilføjMedlem();
        medlemstype.opdaterKategorisering();
        medlemsliste.getMedlemslisteInfo();
    }
}