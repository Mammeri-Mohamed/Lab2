package org.example;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class JeuTest {
    @Mock
    private Banque mockBanque;
    private Jeu jeu;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jeu = new Jeu(mockBanque);
    }

    // Test pour le cas où le jeu est fermé
    @Test(expected = JeuFermeException.class)
    public void testJouerJeuFerme() throws JeuFermeException {
        jeu.jouer(mock(Joueur.class), mock(De.class), mock(De.class));
    }

    // Test pour le cas où le joueur est insolvable
    @Test(expected = JeuFermeException.class)
    public void testJouerJoueurInsolvable() throws JeuFermeException, DebitImpossibleException {
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);

        when(mockJoueur.mise()).thenReturn(10); // Mise impossible
        doThrow(new DebitImpossibleException("Solde insuffisant.")).when(mockJoueur).debiter(10);

        jeu.jouer(mockJoueur, mockDe1, mockDe2);
    }

    // Test pour le cas où le joueur gagne
    @Test
    public void testJouerGagner() throws JeuFermeException, DebitImpossibleException {
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);

        when(mockDe1.lancer()).thenReturn(3); // Lancer de dé donnant 7
        when(mockDe2.lancer()).thenReturn(4);
        when(mockBanque.est_solvable()).thenReturn(true);
        when(mockJoueur.mise()).thenReturn(10); // Mise valide
        when(mockJoueur.getSolde()).thenReturn(20); // Solde suffisant

        jeu.jouer(mockJoueur, mockDe1, mockDe2);

        verify(mockJoueur).crediter(20); // Le joueur reçoit le double de sa mise
    }

    // Test pour le cas où le joueur perd
    @Test
    public void testJouerPerdre() throws JeuFermeException, DebitImpossibleException {
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);

        when(mockDe1.lancer()).thenReturn(2); // Lancer de dé ne donnant pas 7
        when(mockDe2.lancer()).thenReturn(4);

        jeu.jouer(mockJoueur, mockDe1, mockDe2);

        verify(mockJoueur, never()).crediter(anyInt()); // Le joueur ne reçoit aucun crédit
    }

    // Test pour le cas où la banque devient insolvable après avoir payé le gain du joueur
    @Test(expected = JeuFermeException.class)
    public void testJouerBanqueInsolvable() throws JeuFermeException, DebitImpossibleException {
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);

        when(mockDe1.lancer()).thenReturn(3); // Lancer de dé donnant 7
        when(mockDe2.lancer()).thenReturn(4);
        when(mockBanque.est_solvable()).thenReturn(false);
        when(mockJoueur.mise()).thenReturn(10); // Mise valide
        when(mockJoueur.getSolde()).thenReturn(20); // Solde suffisant

        jeu.jouer(mockJoueur, mockDe1, mockDe2);
    }
}
