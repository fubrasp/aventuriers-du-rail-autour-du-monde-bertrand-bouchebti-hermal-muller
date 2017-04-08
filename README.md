# TESTED
* initialisation des pioches testee
    * nombre d'elements selon le type etc..
    * fonctionnement de la methode de pioche
    

# DONE
* fix
    * fix de la mauvaise id sur la mauvaise pioche (erreur d'innatention)
    * fix bateaux doubles url (mauvais liens vers les images)
    * fix cartes visibles bug (mauvais ordonnancement)
    * fix de l'incomprhension des regles (les trains et les bateaux peuvent etre au 6 emplacements des cartes visibles)
    * epuisement pioche a gerer (null)
    * bug quand on resize les barres verticales (le xNUMBER)
    * fix bug when reset cartes visibles and on or more pioches are empty
    * fix bug when reset and remain no enough card (<3 cards)

* refractor
    * merger handle des 2 pioches
    * merger les listes (Gestionnaire au final) de catesVisibles et refractor l'interface par consequent
    * cleaner le code
    * refractor methode de Jeu

* test
    * tester pioche
    * tester cartes visibles

* fonctionnalites
    * gerer indicateur nombre (ne pas afficher 2 fois la meme carte dans la main du joueur)
    * gerer cartes visibles
    * gerer ajout cartes main du joueur
    * gerer cas 3 jokers visibles (message d'informatione et reset)
    * messages d'informations divers (en rapport avec la pioche cf OutilDialog.java)

# TO DO
* fix bug au minimum une carte destination (quand on veut ajouter des cartes destinations depuis son ecran)
* fix bug test (quand on pioche un wagon ==> incomprhensible) 
* creer partie
* ecran d'acceuil destinations
* prendre une route
* echanger des wagons et des bateaux
* supprimer attribut bateau double ?
* vrai cartes en HD avec le modele derriere
    * fix image view sur toute la superficie des boutton (pas de solution satisfaisante pour l'instant)
    * Init
    * bug carte destinations
    * test cartes destinations
    * suppimer methodes jeu