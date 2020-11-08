package puissance4_pariente;

/*
TP SUPER PUISSANCE 4 
TDB
PARIENTE Galit et DELMOTTE Lucas 
CLASS PARTIE 
 */
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Pariente Galit
 */
public class Partie {

    //Attributs 
    Joueur[] ListeJoueurs = new Joueur[2];
    Grille Grillejeu = new Grille();
    Joueur joueurCourant;

    //Création de variables couleurs, utilisées dans les méthodes suivantes
    String COULEUR_A;
    String COULEUR_B;

    //On attribut aleatoirement un couleur de jetons au joueur 
    public void attribuerCouleursAuxJoueurs() {

        Random rand = new Random();//Random fonction aleatoire 
        int nbAleatoire = rand.nextInt(2);

        if (nbAleatoire == 0) {
            COULEUR_A = "Jaune";
            COULEUR_B = "Rouge";
        } else {
            COULEUR_A = "Jaune";
            COULEUR_B = "Rouge";
        }
        //On attribue la couleur correspondante à chaque joueur
        ListeJoueurs[0].affecterCouleur(COULEUR_A);
        ListeJoueurs[0].Couleur = COULEUR_A;
        ListeJoueurs[1].affecterCouleur(COULEUR_B);
        ListeJoueurs[1].Couleur = COULEUR_B;

        System.out.println(ListeJoueurs[0].Nom + " possede les jetons " + COULEUR_A + "s");
        System.out.println(ListeJoueurs[1].Nom + " possede les jetons " + COULEUR_B + "s");
        //Messages aux joueurs pour les avertir 
    }

    //A present on intialise la partie 
    public void initialiserPartie() {
        // 1)Initialise la grille 
        Grillejeu.viderGrille();

        //2)Initialise partie 
        //Les joueurs 
        System.out.println("Choisissez le nom des joueurs  : ");
        Scanner sc = new Scanner(System.in);//Fonction Scanner 
        System.out.println("PLAYER 1 : ");
        Joueur PLAYER1 = new Joueur(sc.nextLine());
        System.out.println("PLAYER 2 : ");
        Joueur PLAYER2 = new Joueur(sc.nextLine());
        ListeJoueurs[0] = PLAYER1;
        ListeJoueurs[1] = PLAYER2;

        //3)on attribut une couleur 
        attribuerCouleursAuxJoueurs();

        //4)Creation des jetons 
        for (int i = 0; i < 21; i++) { //21 jetons 
            PLAYER1.ajouter_Jeton(new Jeton(PLAYER1.Couleur));
            PLAYER2.ajouter_Jeton(new Jeton(PLAYER2.Couleur));
        }

        //5)Fonction aleatoire random
        Random rand0 = new Random();
        int nbAleatoire0 = rand0.nextInt(2);
        if (nbAleatoire0 == 0) {
            joueurCourant = ListeJoueurs[0];
        } else {
            joueurCourant = ListeJoueurs[1];
        }

        System.out.println("ALLEZ!!!C'est à " + joueurCourant.Nom + " de commencer a jouer");

        /* Creation desintegrateurs plus trous noirs avec boucle  */
        for (int i = 0; i < 2; i++) {
            int nbAleatoireA, nbAleatoireB;
            Random rand1 = new Random();
            nbAleatoireA = rand1.nextInt(6);
            nbAleatoireB = rand1.nextInt(7);
            while (Grillejeu.Cellules[nbAleatoireA][nbAleatoireB].presence_TrouNoir() == true) {

                nbAleatoireA = rand1.nextInt(6);
                nbAleatoireB = rand1.nextInt(7);
            }
            if (Grillejeu.Cellules[nbAleatoireA][nbAleatoireB].presence_TrouNoir() == false) {

                Grillejeu.placer_TrouNoir(nbAleatoireA, nbAleatoireB);
                Grillejeu.placer_Desintegrateur(nbAleatoireA, nbAleatoireB);
            }

        }
        //Meme principe pour les 3 trous noirs restants
        for (int i = 0; i < 3; i++) {
            int nbAleatoireA, nbAleatoireB;
            Random rand2 = new Random();
            nbAleatoireA = rand2.nextInt(6);
            nbAleatoireB = rand2.nextInt(7);
            while (Grillejeu.Cellules[nbAleatoireA][nbAleatoireB].presence_TrouNoir() == true) {
                nbAleatoireA = rand2.nextInt(6);
                nbAleatoireB = rand2.nextInt(7);
            }
            if (Grillejeu.Cellules[nbAleatoireA][nbAleatoireB].presence_TrouNoir() == false) {
                Grillejeu.placer_TrouNoir(nbAleatoireA, nbAleatoireB);
            }
        }

        //Maintenant pour les desintegrateurs 
        for (int i = 0; i < 3; i++) {
            int nbAleatoireA, nbAleatoireB;
            Random rand3 = new Random();
            nbAleatoireA = rand3.nextInt(6);
            nbAleatoireB = rand3.nextInt(7);
            while (Grillejeu.Cellules[nbAleatoireA][nbAleatoireB].presence_TrouNoir() == true) {
                nbAleatoireA = rand3.nextInt(6);
                nbAleatoireB = rand3.nextInt(7);
            }
            if (Grillejeu.Cellules[nbAleatoireA][nbAleatoireB].presence_TrouNoir() == false) {
                Grillejeu.placer_Desintegrateur(nbAleatoireA, nbAleatoireB);
            }
        }

        //Affichage de la grille avec les 5 trous noirs,desintegrateurs
        Grillejeu.afficherGrilleSurConsole();

    }

    //Creations des differents choix pour les joueurs 
    public int menu() {
        //variable représentant le choix du joueur
        Scanner sc;
        int rep;
        sc = new Scanner(System.in);
        System.out.println("1 : POSER un jeton");
        System.out.println("2 : UTILISER un desintegrateur");
        System.out.println("3 : RECUPERER jeton");
        rep = sc.nextInt();
        //Possibilite du choix 
        while (rep > 3 || rep < 1) {
            System.out.println("CHOIX IMPOSSIBLE");
            rep = sc.nextInt();
        }
        return rep; //Retourne la réponse: rep 
    }

    public void poser_Jeton() {
        //pour choisir la colonnes
        Scanner sc;
        sc = new Scanner(System.in);
        if (joueurCourant.nombreJetonsRestants > 0) { //les jetons restants au joueur
            System.out.println("Saisir une colonne  : ");
            int j = sc.nextInt() - 1; //choix valide ou non 
            while (j > 7 || j < 0) {
                System.out.println("CHOIX IMPOSSIBLE");
                j = sc.nextInt() - 1;
            }
            /* Si la colonne est remplie on demande au joueur de choisir une autre */
            while (Grillejeu.ajouterJetonDansColonne(joueurCourant, j) == false) {
                System.out.println("La colonne est deja remplie,Choisissez en une autre : ");
                j = sc.nextInt() - 1;
            }
            Grillejeu.afficherGrilleSurConsole(); //On affiche la grille après l'ajout du jeton 
        } else {
            System.out.println("Plus aucun jetons en votre possession");
        }
    }

    public boolean recuperer_Jeton() {
        boolean res;
        //On demande quel jeton le joueur veut récupérer 
        Scanner sc;
        sc = new Scanner(System.in);
        System.out.println("Indiquer le jeton a recuperé : ");
        System.out.println("Choix de lignes  : ");
        int i = sc.nextInt() - 1;
        while (i > 5 || i < 0) {
            System.out.println("CHOIX IMPOSSIBLE ");
            System.out.println("Choix de lignes : ");
            i = sc.nextInt() - 1;
        }
        System.out.println("Choix Colonne : ");
        int j = sc.nextInt() - 1;
        while (j > 6 || j < 0) {
            System.out.println("CHOIX IMPOSSIBLE ");
            System.out.println("Choix Colonne : ");
            j = sc.nextInt() - 1;
        }
        //Verifions que le jeton est bien celui du joueur 
        if ((Grillejeu.Cellules[i][j].lireCouleur_Jeton().equals(joueurCourant.Couleur)) && Grillejeu.caseOccupee(i, j) == true) {
            joueurCourant.ajouter_Jeton(Grillejeu.recuperer_Jeton(i, j));
            //On tasse la grille
            Grillejeu.tasser_Grille(j);
            res = true;
        } else {
            System.out.println("Erreur, Case vide ou jeton qui n'est pas le votre");
            res = false;
        }
        Grillejeu.afficherGrilleSurConsole();
        //Affichage de la nouvelle grille
        return res;
    }

    public boolean activer_Desintegrateur() {

        boolean res;
        if (joueurCourant.NombreDesintegrateurs == 0) {
            System.out.println("Erreur, Pas de desintegrateur en votre possesion ");
            res = false;
        } else {
            //On demande quel jeton doit être désintégré meme principe que pour le jeton a recup 
            Scanner sc;
            sc = new Scanner(System.in);
            System.out.println("Choisissez le jeton à désintégrer : ");
            System.out.println("Choix de ligne : ");
            int i = sc.nextInt() - 1;
            while (i > 5 || i < 0) {
                System.out.println("CHOIX IMPOSSIBLE ");
                System.out.println("Choix de ligne : ");
                i = sc.nextInt() - 1;
            }

            System.out.println("Choix de colonne : ");
            int j = sc.nextInt() - 1;
            while (j > 6 || j < 0) {
                System.out.println("CHOIX IMPOSSIBLE ");
                System.out.println("Choix de colonne : ");
                j = sc.nextInt() - 1;
            }
            //Apres verification on supprime le jeton 
            while (Grillejeu.supprimer_Jeton(i, j) == false) {

                System.out.println("Choix impossible.Veuillez choisir une autre case : ");
                System.out.println("Choix de ligne : ");
                i = sc.nextInt() - 1;
                while (i > 5 || i < 0) {
                    System.out.println("CHOIX IMPOSSSIBLE ");
                    System.out.println("Choix de ligne : ");
                    i = sc.nextInt() - 1;
                }
                System.out.println("Choix de colonne : ");
                j = sc.nextInt() - 1;
                while (j > 6 || j < 0) {
                    System.out.println("Erreur, choix non valide");
                    System.out.println("Numéro de colonne : ");
                    j = sc.nextInt() - 1;
                }
            }

            Grillejeu.tasser_Grille(j);
            //Maintenant qu'on a supprimer le jeton on tasse la grille 

            joueurCourant.utiliser_Desintegrateur();
            res = true;//On enleve un desintegrateur au joueur

        }
        Grillejeu.afficherGrilleSurConsole(); //Affichage de la grille actuel
        return res;
    }

    public Joueur changement_Joueur(Joueur unJoueur) {
//Permet le changement de joueur après chaque tour
        if (ListeJoueurs[0] == joueurCourant) {
            return ListeJoueurs[1];
        } else {
            return ListeJoueurs[0];
        }
    }

    public void choix() {

        System.out.println("Faites un choix: ");
        int rep;

        do {
            rep = menu(); //Fonction menu
            switch (rep) {
                case 1:
                    poser_Jeton();
                    break;//arret
                case 2:
                    boolean res; //resultat
                    res = activer_Desintegrateur();
                    if (res == false) { //Le joueur peut rejouer
                        joueurCourant = changement_Joueur(joueurCourant);
                    }
                    break;
                case 3: //meme principe
                    boolean res2;
                    res2 = recuperer_Jeton();
                    if (res2 == false) {
                        joueurCourant = changement_Joueur(joueurCourant);
                    }
                    break;
            }

            joueurCourant = changement_Joueur(joueurCourant);
            System.out.println("C'est maintenant au tour de " + joueurCourant.Nom + " de jouer"); //Précise qui joue
        } while (rep <= 3 && Grillejeu.vainqueurJoueur(ListeJoueurs[0]) == false && Grillejeu.vainqueurJoueur(ListeJoueurs[1]) == false);

    }

    //Pour lancer une nouvelle la partie 
    public void debuter_Partie() {
        initialiserPartie();
        choix();
    }

}
