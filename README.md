Remarque sur l'exercice 2 : 
a- Notes sur l'utilisation de Mockito dans les tests
Dans nos tests, nous avons rencontré une limitation lors de la configuration du comportement d'un mock pour une méthode void à l'aide de la méthode thenReturn() de Mockito. En particulier, la méthode creerUtilisateur() de notre interface UtilisateurApi est de type void, ce qui signifie que nous ne pouvons pas utiliser thenReturn() pour simuler son comportement et retourner une valeur.

Pour contourner cette limitation, nous avons utilisé la méthode doAnswer() de Mockito pour effectuer une action lors de l'appel de creerUtilisateur(). Cette méthode nous permet de simuler le comportement désiré de la méthode tout en gérant les cas où elle ne retourne pas de valeur.
Cela nous a permis de simuler le succès de la méthode creerUtilisateur() sans retourner de valeur.
b- 
 nous avons également ajouté des tests pour vérifier le lancement d'une ServiceException dans certains cas, afin de garantir que notre code gère correctement les erreurs
