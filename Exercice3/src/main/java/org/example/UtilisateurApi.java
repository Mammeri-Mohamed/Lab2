package org.example;

public interface UtilisateurApi {
    boolean creerUtilisateur(Utilisateur utilisateur) throws ServiceException;
    void validerUtilisateur(Utilisateur utilisateur) throws ServiceException; // Ajoutez cette méthode si nécessaire
}

