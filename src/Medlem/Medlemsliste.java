package Medlem;

import java.util.ArrayList;
public class Medlemsliste {

    ArrayList<Medlem> klubMedlem = new ArrayList<>();

    public void tilføjMedlem(Medlem medlem){
        klubMedlem.add(medlem);
    }

    public void getMedlemslisteInfo(){ //opdateres løbende
        for (Medlem m : klubMedlem) {
            System.out.println("Navn: " + m.getNavn() + " |Alder: " + m.beregnAlder());

        }
    }




}