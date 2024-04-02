package org.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateur() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Mammeri", "Mohamed", "mammerim3@email.com");

        // Configuration du comportement du mock pour lancer une ServiceException
        doThrow(new ServiceException("Erreur lors de la création")).when(utilisateurApiMock).creerUtilisateur(utilisateur);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester dans un bloc try-catch pour capturer l'exception
        try {
            userService.creerUtilisateur(utilisateur);
            fail("Une ServiceException était attendue mais n'a pas été levée.");
        } catch (ServiceException e) {
            // Vérification de l'exception
            assertEquals("Erreur lors de la création", e.getMessage());
        }

        // Vérification de l'appel à l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }
}

