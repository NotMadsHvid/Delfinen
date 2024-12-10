package Medlemstype;
import Medlem.Medlem;

import java.util.ArrayList;

//Laver vores klasse
public class Medlemstype {
    private boolean juniormedlem;
    private boolean seniormedlem;
    private boolean aktivtmedlem;
    private boolean passivtmedlem;
    private boolean seniorrabat;
    private boolean konkurrencesvømmer;
//Laver vores constructor
public Medlemstype(boolean juniormedlem, boolean aktivtmedlem, boolean konkurrencesvømmer){
    this.juniormedlem = juniormedlem;
    this.aktivtmedlem = aktivtmedlem;
    this.konkurrencesvømmer = konkurrencesvømmer;
}
//laver en metode som kategoriserer medlemmerne
public void kategoriserMedlem(Medlem medlem){
    if (medlem.opdaterendeAlder() < 18){
        juniormedlem = true;
        seniormedlem = false;
        seniorrabat = false;
    } else {
        juniormedlem = false;
        seniormedlem = true;
    }
    if (aktivtmedlem){
        passivtmedlem = false;
    } else if (passivtmedlem){
        aktivtmedlem = true;
    }
    if (konkurrencesvømmer){
        if (juniormedlem){
            ArrayList<Medlem> juniorhold = new ArrayList<>();
            juniorhold.add(medlem);

        } else if (seniormedlem){
            ArrayList<Medlem> seniorhold = new ArrayList<>();
            seniorhold.add(medlem);
        }
    }

    }
}
