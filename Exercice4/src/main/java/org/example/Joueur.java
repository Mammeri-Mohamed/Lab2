package org.example;

public class Joueur {
    private int solde;

    public Joueur(int soldeInitial) {
        this.solde = soldeInitial;
    }

    public int mise() {
        // Retourne la mise du joueur, à implémenter selon les règles du jeu
        return 10; // Par exemple, le joueur mise toujours 10 unités pour chaque tour
    }

    public void debiter(int somme) throws DebitImpossibleException {
        if (solde >= somme) {
            solde -= somme;
        } else {
            throw new DebitImpossibleException("Solde insuffisant.");
        }
    }

    public void crediter(int somme) {
        solde += somme;
    }

    public int getSolde() {
        return solde;
    }
}

