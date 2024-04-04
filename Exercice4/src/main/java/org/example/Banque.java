package org.example;

public class Banque {
    private int fonds;
    private final int fondsMinimum;

    public Banque(int fonds, int fondsMinimum) {
        this.fonds = fonds;
        this.fondsMinimum = fondsMinimum;
    }

    public void crediter(int somme) {
        fonds += somme;
    }

    public boolean est_solvable() {
        return fonds >= fondsMinimum;
    }

    public void debiter(int somme) {
        fonds -= somme;
    }
}

