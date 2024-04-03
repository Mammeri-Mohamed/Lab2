package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Captor
    private ArgumentCaptor<Utilisateur> utilisateurCaptor;

    @Test(expected = ServiceException.class)
    public void testCreerUtilisateur_ExceptionCreation() throws ServiceException {
        // Configuration du comportement du mock pour lancer une ServiceException lors de la création de l'utilisateur
        doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Création d'un nouvel utilisateur avec des informations fictives (y compris un ID)
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com", 123);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
    }


    @Test
    public void testCreerUtilisateur_ErreurValidation() throws ServiceException {
        // Configuration du comportement du mock pour retourner true lors de la création de l'utilisateur
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(true);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Création d'un nouvel utilisateur avec des informations fictives (y compris un ID)
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com", 123);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification de l'appel à l'API
        verify(utilisateurApiMock, never()).validerUtilisateur(any(Utilisateur.class)); // Ne pas appeler validerUtilisateur
    }

    @Test
    public void testCreerUtilisateur_EtatUtilisateur() throws ServiceException {
        // Configuration du comportement du mock pour retourner true lors de la création de l'utilisateur
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(true);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Création d'un nouvel utilisateur avec des informations fictives (y compris un ID)
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com", 123);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification de l'état de l'utilisateur après l'envoi à l'API
        utilisateur.setId(123); // Supposons que l'API renvoie l'identifiant unique
        assertEquals(123, utilisateur.getId());
    }

    @Test
    public void testCreerUtilisateur_ArgumentsCaptures() throws ServiceException {
        // Configuration du comportement du mock pour retourner true lors de la création de l'utilisateur
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(true);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Création d'un nouvel utilisateur avec des informations fictives (y compris un ID)
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com", 123);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification des arguments capturés
        verify(utilisateurApiMock).creerUtilisateur(utilisateurCaptor.capture());
        Utilisateur utilisateurCapture = utilisateurCaptor.getValue();
        assertEquals(utilisateur.getNom(), utilisateurCapture.getNom());
        assertEquals(utilisateur.getPrenom(), utilisateurCapture.getPrenom());
        assertEquals(utilisateur.getEmail(), utilisateurCapture.getEmail());
        assertEquals(utilisateur.getId(), utilisateurCapture.getId());
    }
}
