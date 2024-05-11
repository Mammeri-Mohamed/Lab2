Remarque sur l'exercice 2 : 
a- Notes sur l'utilisation de Mockito dans les tests
Dans nos tests, nous avons rencontré une limitation lors de la configuration du comportement d'un mock pour une méthode void à l'aide de la méthode thenReturn() de Mockito. En particulier, la méthode creerUtilisateur() de notre interface UtilisateurApi est de type void, ce qui signifie que nous ne pouvons pas utiliser thenReturn() pour simuler son comportement et retourner une valeur.

Pour contourner cette limitation, nous avons utilisé la méthode doAnswer() de Mockito pour effectuer une action lors de l'appel de creerUtilisateur(). Cette méthode nous permet de simuler le comportement désiré de la méthode tout en gérant les cas où elle ne retourne pas de valeur.
Cela nous a permis de simuler le succès de la méthode creerUtilisateur() sans retourner de valeur.
b- 
 nous avons également ajouté des tests pour vérifier le lancement d'une ServiceException dans certains cas, afin de garantir que notre code gère correctement les erreurs
EXERCICE 04
1-
 la méthode jouer de la classe Jeu, les objets dont dépendent forcément la classe Jeu et qui doivent être mockés sont :

Le joueur (interface Joueur) : pour simuler les actions du joueur telles que la mise, le débit et le crédit.
Les dés (interface De) : pour simuler les lancers des dés et obtenir une somme de lancers simulée.
La banque (interface Banque) : pour simuler les interactions avec la banque telles que le crédit, le débit et la vérification de la solvabilité.
Ces objets doivent être mockés car le test unitaire de la méthode jouer doit se concentrer uniquement sur le comportement de la classe Jeu et ne pas dépendre du fonctionnement réel des autres classes.
2-
Scénarios pour tester la méthode jouer :

Scénario 1 : Le joueur mise une somme positive, la somme est débitée avec succès, la somme des lancers des dés ne donne pas 7, le jeu s'arrête.
Scénario 2 : Le joueur mise une somme positive, la somme est débitée avec succès, la somme des lancers des dés donne 7, le joueur gagne et la somme est créditée avec succès, la banque est solvable.
Scénario 3 : Le joueur mise une somme positive, la somme est débitée avec succès, la somme des lancers des dés donne 7, le joueur gagne et la somme est créditée avec succès, mais la banque devient insolvable.
Scénario 4 : Le joueur mise une somme positive, mais le joueur est insolvable, le jeu s'arrête.
Scénario 5 : Le jeu est fermé, aucun joueur ne peut jouer.
Code Java pour la classe Jeu :
3-
import java.util.Random;

public class Jeu {
    private Banque banque;
    private boolean ouvert;

    public Jeu(Banque banque) {
        this.banque = banque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!ouvert) {
            throw new JeuFermeException("Le jeu est fermé.");
        }

        int mise = joueur.mise();
        try {
            joueur.debiter(mise);
            int sommeDes = de1.lancer() + de2.lancer();
            if (sommeDes == 7) {
                banque.crediter(2 * mise);
                if (!banque.est_solvable()) {
                    ouvert = false;
                }
            } else {
                ouvert = false;
            }
        } catch (DebitImpossibleException e) {
            ouvert = false;
            throw new JeuFermeException("Le joueur est insolvable.");
        }
    }

    public void fermer() {
        ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
}
4-
Le test le plus simple est celui où le jeu est fermé. C'est un test d'état car il vérifie simplement l'état du jeu après avoir été fermé.
5-
Pour tester le cas où le joueur est insolvable sans toucher aux dés, nous utilisons un mock du joueur (interface Joueur) et simulons le lancer de dés avec un résultat qui ne donnera pas 7. C'est principalement un test d'état car nous vérifions si le jeu est correctement fermé lorsque le joueur est insolvable.
6-
Nous continuons avec les autres scénarios tels que décrits dans la question 2.
7-
Pour intégrer la banque dans un test, nous devons créer une implémentation de l'interface Banque. Dans ce test, nous vérifions qu'une fois que la banque devient insolvable, le jeu est fermé. La différence entre ce test et le précédent réside dans le fait que nous testons l'intégration entre la classe Jeu et une implémentation réelle de Banque, tandis que dans le premier test, nous utilisons un mock de Banque pour isoler la classe Jeu.
