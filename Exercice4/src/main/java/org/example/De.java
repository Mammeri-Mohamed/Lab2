package org.example;

import java.util.Random;

public class De {
    private Random random;

    public De() {
        this.random = new Random();
    }

    public int lancer() {
        return random.nextInt(6) + 1; // Génère un nombre aléatoire entre 1 et 6 inclus
    }
}
