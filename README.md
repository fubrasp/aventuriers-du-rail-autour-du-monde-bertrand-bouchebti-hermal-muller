# Fonctionnalites implemantees
* Prise de route
    * Verification des cartes necessaires tout type de route confondues
    * Constuction de ports
    * Construction de routes
    * Mise dans la defausse des pioches respectives 
    * Mise a jour du score des joueurs
    * modelisation de la carte via un arbre DOM

* Echange de pions
    * Echange de pions poour le joueur
    * Mise a jour du score des joueurs 

* Piocher des cartes transport
    * Piocher des cartes wagons
    * Pioches des cartes bateaux
    * les transferer a la main du joueur
    
* Piocher des cartes destinations
    * Pioches des cartes destinations
    * Choisir 1 a plusieurs cartes
    * Obliger le choix d'au moins une carte
    
*  Gestion des cartes visibles
    * Choisir aleatoirement de piocher une carte pour mettre jour un emplacement ou les cartes ont ete piochees
    * Gerer la regle des 3 jokers

* Initialisation
    * Au depart du jeu
        * Initialisation des cartes transport 
        * Choix des cartes destinations
        
* Gestion des actions
    * Limiter les actions possibles
        * 1 seule prise de route
        * 1 seule carte joker visible
        * 2 cartes piochees 
        * 1 seul echange de pions
    * Reset des points d'actions a chaque tour
        
* Rafraichissement de l'interface
    * A chaque tour
    
* Gestion de la notion de tour par tour
    * Evenements
    * Passage au joueur suivant
    
* Sauvegarde d'une partie / Chargement d'une partie
    * Utilisation de la serialisation
    
* Gestion reseau via RMI (version fournie en supplement de cette derniere)
    * FONCTIONNEL
        * Enregistrement des clients aupres du serveur
        * Rafraichissement des clients (modele et UX) depuis via le serveur
        * Gestion des pioches (transport, destinations) et cartes visibles via le seveur
        * Tour par tour
    * NON TERMINE
        * Prise de route beugant (bug non determine)
    * NON FAIT 
        * Echanges avec serialisation
            * Peu de sens en utilisant RMI
            
* Technologies et librairies employees
    * JAVA SE, FX, RMI
    * JUNIT 5 M3
    * w3c DOM
    * jfoenix (fenetres)
    
* Patterns
    * MVC
    
# Liens avec les cours
    * Structuration de documents
        * DOM, plateau de jeu
        * XML, plateau de jeu
        * Jackson, enregistrement de parties
    
    * Reseau 
        * RMI ports, IP, Client/Serveur
    
    * POA
        * JAVA et librairies
        * MVC
        
    * GL
        * Partie conceptuelle
        * Architecture du projet
        * Methode agile    