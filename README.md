# OpenClassrooms

Ce dépôt contient une mini-application pour le P3 du parcours **Grande École du Numérique**.

Nom de l’application
Entrevoisins

IDE
Android Studio Arctic Fox | 2020.3.1

Java Version
11

Compile Sdk Version
28 (API 28: Android 9.0 (Pie))

Code de l’application
- Accéder au code à partir du lien suivant : https://github.com/Abdoul2021/Projet_P3_KAK.git
- Télécharger ou cloner le code à partir du bouton Code

- Lancer Android Studio et y importer le code
- Déterminer un émulateur (virtuel ou réel), un émulateur virtuel ayant été utilisé
- Exécuter et compiler le code à l'aide du bouton Run

Fonctionnalités présentes dans l’application Entrevoisins
- Lister mes voisins
- Ajouter un voisin
- Supprimer un voisin

Fonctionnalités développées dans l’application Entrevoisins:
- Afficher les détails d’un voisin: clic sur un voisin de la liste des voisins
- Retourner à la liste des voisins (ou quitter les détails du voisin): clic sur le bouton de retour à l'élément précédent (ReturnButton)
- Lister mes voisins dans l’onglet Favoris
  o ajouter un voisin dans la liste des favoris: clic sur le bouton d’ajout du voisin à la liste des favoris (FavoritesButton)
  o afficher les détails d’un voisin favori: clic sur un voisin de la liste des favoris
  o supprimer un voisin de la liste des favoris: clic sur le bouton de suppression de la liste des favoris

Tests unitaires développés dans l’application Entrevoisins
- getNeighbourByPositionWithSuccess
- getNeighbourFavoriteByPositionWithSuccess
- getNeighboursFavoritesWithSuccess
- addFavoriteNeighboursWithSuccess
- deleteFavoriteNeighbourWithSuccess