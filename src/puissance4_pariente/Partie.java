package puissance4_pariente;

import java.util.Random;
import java.util.Scanner;

/*
Pariente Galit
TP3
 */
/**
 *
 * @author Pariente Galit
 */
public class Partie {

    Joueur Listejoueurs[] = new Joueur[2];
    Joueur JoueurCourant;
    Grille grilleDeJeu = new Grille();

    void attribuerCouleursAuxJoueurs() {
        Random x = new Random();
        boolean couleurDujoueur;
        couleurDujoueur = x.nextBoolean();
        if (couleurDujoueur) {
            Listejoueurs[0].Couleur = "Rouge";
            Listejoueurs[1].Couleur = "Jaune";
        } else {
            Listejoueurs[0].Couleur = "Jaune";
            Listejoueurs[1].Couleur = "Rouge";
        }
    }

    Joueur ProchainJoueur(Joueur un_joueur) {
        if (Listejoueurs[0] == JoueurCourant) {
            return Listejoueurs[1];
        }
        return Listejoueurs[0];
    }

    void initialiserPartie() {
        grilleDeJeu.viderGrille();

        // il faut créer nos deux joueurs
        Scanner sc = new Scanner(System.in); //Pour choisir les noms des joueurs
        System.out.println("choisir le nom du premier Joueur : ");
        Joueur UNJoueur = new Joueur(sc.nextLine());

        System.out.println("Choisir le nom du deuxième Joueur : ");
        Joueur AutreJoueur = new Joueur(sc.nextLine());

        Listejoueurs[0] = UNJoueur; //affecter les joueurs
        Listejoueurs[1] = AutreJoueur;
        attribuerCouleursAuxJoueurs();

        System.out.println(UNJoueur.Nom + "a la couleur " + UNJoueur.Couleur);
        System.out.println(AutreJoueur.Nom + "a la couleur " + AutreJoueur.Couleur);

        // ditribuer les jetons 
        for (int i = 0; i < 21; i++) {
            UNJoueur.ajouter_Jeton(new Jeton(UNJoueur.Couleur));
        }
        AutreJoueur.ajouter_Jeton(new Jeton(AutreJoueur.Couleur));

        Random r = new Random(); // fonction aléatoire pour savoir quel joueur commence 
        boolean premierJoueur;
        premierJoueur = r.nextBoolean();
        if (premierJoueur) {
            JoueurCourant = Listejoueurs[0];
        } else { // sinon
            JoueurCourant = Listejoueurs[1];
        }

        // initialisation des trous noirs
        int compteur = 0;
        for (int i = 0; i < 5; i++) {
            int lignedutrounoir;
            int colonnedutrounoir;
            lignedutrounoir = r.nextInt(6);
            colonnedutrounoir = r.nextInt(7);
            if (compteur < 2) {
                if (!grilleDeJeu.placerDesintegrateur(lignedutrounoir, colonnedutrounoir)) {
                    compteur--;
                }
                compteur++;
            }
            if (!grilleDeJeu.placerTrouNoir(lignedutrounoir, colonnedutrounoir)) {
                i--;
            }
        }

        for (int i = 0; i < 3; i++) {
            int lignedudesintegrateur;
            int colonnedudesintegrateur;
            lignedudesintegrateur = r.nextInt(6);
            colonnedudesintegrateur = r.nextInt(7);
            if (!grilleDeJeu.placerDesintegrateur(lignedudesintegrateur, colonnedudesintegrateur)) {
                i--;
            }
        }
        grilleDeJeu.afficherGrilleSurConsole();
    }

    int menu_joueur() {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Plusieurs choix possibles,que voulez-vous faire ?");
        System.out.println(" Premierement: Vous voulez jouer un jeton");
        System.out.println(" Deuxiement: Vous voulez récupérer un jeton");
        System.out.println(" Troisiement: Vous voulez utiliser un désintégrateur et désintegrer un jeton");
        int optionchoisi = sc.nextInt();
        while (optionchoisi < 1 || optionchoisi > 3) {
            System.out.println("Erreur vous avez fait un choix qui n'existe pas ");
            System.out.println(" Veuillez entrer un des choix possible:Premierement,Deuxiement,Troisiement");
            optionchoisi = sc.nextInt();
        }
        return optionchoisi;
    }

    void jouerJeton() {
        Scanner sc = new Scanner(System.in);
        boolean jetonjouer; //intialisation boolean
        System.out.println(" Choisissez la colonne dans laquelle vous voulez jouer le jeton");
        int colonnejeton = sc.nextInt();
        colonnejeton = colonnejeton - 1; //les colonnes du tableau commencent a 0
        while (colonnejeton < 0 || colonnejeton > 6) {
            System.out.println(" erreur vous avez choisi un numero de colonne inexistant");
            System.out.println(" Veuillez entrer un numéro de colonne qui existe : ");
            colonnejeton = sc.nextInt() - 1;
        }

        jetonjouer = grilleDeJeu.ajouterJetonDansColonne(JoueurCourant, colonnejeton);
        while (!jetonjouer) {
            System.out.println("Impossible!La colonne est deja pleine");
            System.out.println(" Veuillez choisir une autre colonne qui n'est pas encore remplie : ");
            colonnejeton = sc.nextInt() - 1;
            jetonjouer = grilleDeJeu.ajouterJetonDansColonne(JoueurCourant, colonnejeton); // on ajoute le jeton jouée dans la grille de jeu
        }
    }

    boolean recup_jeton() {
        int colonnejeton;
        int lignejeton;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez le numero de la colonne ou vous voulez recuperer le jeton : ");
        colonnejeton = sc.nextInt() - 1;
        while (colonnejeton < 0 || colonnejeton > 6) {
            System.out.println(" Erreur numero de colonne inexistant ");
            System.out.println(" Veuillez entrer un numéro de colonne qui existe : ");
            colonnejeton = sc.nextInt() - 1;
        }
        System.out.println("Maintenant que nous avons le numéro de la colonne,choisissez le numéro de la ligne du jeton que vous voulez récupérer : ");
        lignejeton = sc.nextInt() - 1;
        while (colonnejeton < 0 || colonnejeton > 5) {
            System.out.println("erreur numero de ligne inexistant");
            System.out.println(" Veuillez entrer un numéro de ligne qui existe : ");
            colonnejeton = sc.nextInt() - 1;
        }

 }
