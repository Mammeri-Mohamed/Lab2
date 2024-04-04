package org.example;

public class Jeu {
    private Banque banque;
    private boolean ouvert=false;

    public Jeu(Banque labanque) {
        this.banque = labanque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!estOuvert()) {
            throw new JeuFermeException("Le jeu est fermé.");
        }

        try {
            joueur.debiter(joueur.mise());
            int sommeDes = de1.lancer() + de2.lancer();

            if (sommeDes != 7) {
                this.ouvert = false;
                return;
            }

            joueur.crediter(joueur.mise() * 2);
            if (!banque.est_solvable()) {
                this.ouvert = false;
            }
        } catch (DebitImpossibleException e) {
            this.ouvert = false;
            throw new JeuFermeException("Le joueur est insolvable.");
        }
    }
    public void fermer() {
        this.ouvert = false;
    }

    public boolean estOuvert() {
        return this.ouvert;
    }
}

