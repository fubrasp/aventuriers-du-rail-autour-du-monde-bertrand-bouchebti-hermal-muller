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
    * fix bug quand reset cartes visibles et une ou plus pioches est vide
    * fix bug quand reset et pas assez de cartes restantes (<3 cards)
    * fix bug carte destinations (texte affichee)
    * fix texte des cartes destinations en dessous
    * carte transport avec indicateur sexy (juste l'image + chiffre)
    * gestion cas tres particulier cartes visibles (afficher image vides)
    * Init
    * fix cartes transport a refresh (verifier nombre OK)
    * fix changement de main a chaque tour :)
    * fix bug X0
    * fix bug au minimum une carte destination (quand on veut ajouter des cartes destinations depuis son ecran)
    * fix bug test (quand on pioche un wagon ==> incomprhensible) 
    * fix image view sur toute la superficie des boutton (pas de solution satisfaisante pour l'instant)
    * fix bug cartes visibles joker 3 regles initialisation (mauvais reset des points)

* refractor
    * merger handle des 2 pioches
    * merger les listes (Gestionnaire au final) de catesVisibles et refractor l'interface par consequent
    * cleaner le code
    * refractor methode de Jeu
        

* test
    * tester pioches
    * tester cartes visibles
    * test cartes destinations


* fonctionnalites
    * gerer indicateur nombre (ne pas afficher 2 fois la meme carte dans la main du joueur)
    * gerer cartes visibles
    * gerer ajout cartes main du joueur
    * gerer cas 3 jokers visibles (message d'informatione et reset)
    * messages d'informations divers (en rapport avec la pioche cf OutilDialog.java)
    * carte destinations clickables
    * mettre scroll bar carte destinations
    * mettre scroll bar pour la main du joueur
    * alert moins de 1 destinations choisie
    * gestion des actions limitees
    * gestion tour par tour
    * gestion de plusieurs joueurs    




# TO DO
* creer partie
* ecran d'acceuil destinations
* prendre une route
* echanger des wagons et des bateaux
* supprimer attribut bateau double ?
* vrai cartes en HD avec le modele derriere
    * refractor non repris d'exemple
    * (carte destination selectionne encadree en rouge)
    * Priorite
        * split des controllers
        * commentaires en anglais
    * En plus
        * empilement des cartes
        * bug cas des reinitialisation cartes visibles sans pioche ni defausse ? (a gerer une fois les autres parties dévellopees)
            * prevoir evenement quand pioche resetee ? 
            * si une carte visible est vide ? Quel attribut verifier ? --> repiocher ?
            * quand lancer le reset ?
                * des qu'une personne prend une route