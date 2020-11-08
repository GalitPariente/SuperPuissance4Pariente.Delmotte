package puissance4_pariente;

/*
TP SUPER PUISSANCE 4 
TDB
PARIENTE Galit et DELMOTTE Lucas
 */
/**
 *
 * @author Pariente Galit
 */
public class Grille {

    Cellule[][] Cellules = new Cellule[6][7];//La cellule [0][0] est en bas a gauche 
//On initialise la grille avec 42 cellules vides 
    Grille() {
        //Constructeur
        for (int i = 0; i < 6; i++) {//i pour les lignes 
            for (int j = 0; j < 7; j++) {//j pour les colonnes 
                Cellules[i][j] = new Cellule(); 
            }
        }
    }

    public boolean colonneRemplie(int i) {//Pour savoir si une colonne est remplie ou non i=indice colonne 
        if (Cellules[5][i].JetonCourant != null) {
            return true; //vrai si remplie 
        } else {
            return false; //Faux  si pas remplie 
        }
    }

    public boolean ajouterJetonDansColonne(Joueur unJoueur, int i) { //Pour ajouter un jeton dans la colonne choisi

        int j = 0;
        if (colonneRemplie(i) == true) { //Si la colonne est remplie on s'arrete et on retourne false 
            return false;//car on ne peut plus ajouter de jeton 
        } else { //Sinon
            while (Cellules[j][i].JetonCourant != null) { //On monte de ligne en ligne en partant du bas pour trouver une cellule vide 
                j++;
            }

            Jeton unJeton = unJoueur.enlever_Jeton();
            Cellules[j][i].JetonCourant = unJeton;

            //Verifier si il y a un trou noir 
            if (Cellules[j][i].presence_TrouNoir() == true) {
                Cellules[j][i].activer_TrouNoir();
            }

            //Idem pour desintegrateur 
            if (Cellules[j][i].presence_Desintegrateur() == true) {
                Cellules[j][i].recuperer_Desintegrateur();
                unJoueur.obtenir_Desintegrateurs();

            }
            return true; //Renvoie vrai après aavoir ajouter le jeton
        }
    }

    public boolean etreRemplie() {
        boolean res = false; //res = variable résultat
        for (int i = 0; i < 7; i++) {

            if (Cellules[5][i].JetonCourant == null) {
                res = false; //Si oui alors retourne faux
            } else { //Si elle est remplie alors retourne vrai
                res = true;
            }
        }
        return res; //Retourne res= retourne le résultat
    }

    public void viderGrille() {//pour vider la grille 

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                Cellules[j][i].JetonCourant = null;
            }
        }
    }

    //Pour permettre d'afficher la grille avec String 
    public void afficherGrilleSurConsole() {
        String[][] Cellules3 = new String[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (Cellules[i][j].JetonCourant == null) {
                    if (Cellules[i][j].presence_TrouNoir() == true) {
                        Cellules3[i][j] = " T "; //la cellule prend la valeur T
                    } else if (Cellules[i][j].presence_Desintegrateur() == true) {
                        Cellules3[i][j] = " D "; //la cellule prend la valeur D
                    } else {
                        Cellules3[i][j] = " N "; //On affiche un N si la case est Non remplie 
                    }  
                } else { //Pour affiche J ou R en fonction de la couleur atribuer au joueur 
                    if (Cellules[i][j].lireCouleur_Jeton().equals("Jaune")) {
                        Cellules3[i][j] = " J ";
                    }
                    if (Cellules[i][j].lireCouleur_Jeton().equals("Rouge")) {
                        Cellules3[i][j] = " R ";
                    }
                }
            }
        }
        //Afficher la grille ligne par ligne 

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(Cellules3[5 - i][j]); //Affiche cellule par cellule
            }
            System.out.print("\n"); //Ca change de ligne à chaque fin de ligne
        }

    }

    public boolean caseOccupee(int i, int j) {
        if (Cellules[i][j].JetonCourant.couleur.equals("Jaune") || Cellules[i][j].JetonCourant.couleur.equals("Rouge")) {
            return true;
        } else {
            return false;
        }
    }

    public String lireCouleurDuJeton(int i, int j) {
        if (Cellules[i][j].JetonCourant == null) {
            return "VIDE";
        } else {
            return Cellules[i][j].JetonCourant.LireCouleur(); //Sinon retourne la couleur du joueur qui a joue 
        }

    }

    //PASSONS maintenant au cas vainqueur 
    public boolean vainqueurJoueur(Joueur UNJoueur) {
        boolean res = false; //Variable res = resultat
        String CouleurDuJoueur = UNJoueur.Couleur; //couleur du joueur en parametre 

        //Si 4 jetons sont alignés en COLONNE
        for (int j = 0; j < 7; j++) {
            int nb_alignés = 0;//Compteur 
            for (int i = 0; i < 6; i++) {
                if (Cellules[i][j].lireCouleur_Jeton().equals(CouleurDuJoueur)) {
                    nb_alignés = nb_alignés + 1;//Incremente le compteur a chaque trouvaille d'un jeton 
                } else {
                    nb_alignés = 0;
                }
                if (nb_alignés == 4) { //Quand le compteur atteint 4 
                    System.out.print(UNJoueur.Nom + " VAINQUEUR de la partie");
                    res = true;
                }
            }
        }
        //Comme dans les regles du puissance 4 ,si 4 jetons sont en LIGNES meme principe que pour les colonnes 
        for (int i = 0; i < 6; i++) {
            int nb_alignés = 0; //Compteur 
            for (int j = 0; j < 7; j++) {
                if (Cellules[i][j].lireCouleur_Jeton().equals(CouleurDuJoueur)) {
                    nb_alignés = nb_alignés + 1; //Incrémenter le compteur a chaque trouvaille d'un jeton 
                } else {//SINON 
                    nb_alignés = 0;
                }
                if (nb_alignés == 4) { // Quand le compteur atteint 4
                    System.out.print(UNJoueur.Nom + " VAINQUEUR de la partie");
                    res = true; //Vrai s'il y a un gagnant                    
                }
            }
        }
        //Pour les diagonales :

        //Premierement diagonales montantes:
         for (int i = 0; i < 3; i++) { // rectangle par les 3 premières lignes et 4 premières colonnes
            int nb_alignés = 0; //Compteur
            for (int j = 0; j < 4; j++) { //Case a case
                if (Cellules[i][j].lireCouleur_Jeton().equals(CouleurDuJoueur)) {
                    nb_alignés = 1; //Incrementation du compteur si jeton au joueur 
                    while (Cellules[i + 1][j + 1].lireCouleur_Jeton().equals(CouleurDuJoueur) && nb_alignés != 4) {

                        nb_alignés = nb_alignés + 1; //incrementation
                        if (nb_alignés == 4) {
//Si on a 4 jetons de la meme couleurs alors on a un vainqueur
                            System.out.print(UNJoueur.Nom + " est le vainqueur de la partie");
                            res = true; // vrai                     
                        } else { //Sinon
                            i++;//incrementation de i 
                            j++;//incrementation de j
                        }
                    }
                }
            }
        }
        //Deuxiemement diagonales descendantes , meme raisonnement qu'au dessus 

        for (int i = 3; i < 6; i++) {

            int nb_alignés = 0;
            for (int j = 0; j < 4; j++) {
                if (Cellules[i][j].lireCouleur_Jeton().equals(CouleurDuJoueur)) {
                    nb_alignés = 1;
                    while (Cellules[i - 1][j + 1].lireCouleur_Jeton().equals(CouleurDuJoueur) && nb_alignés != 4) {

                        nb_alignés = nb_alignés + 1;

                        if (nb_alignés != 4) {
                            i--;//on diminue i
                            j++;//incrementation de j 
                        }
                    }
                    if (nb_alignés == 4) {
                        //4 jetons alors  vrai
                        System.out.print(UNJoueur.Nom + " est le vainqueur de la partie");//Message au joueur
                        res = true;
                    } else { //Sinon 
                        nb_alignés = 0;
                        //Compteur remis a 0 
                    }
                }
            }
        }
        return res; //Retourne le résultat
    }

//Pour permettre  de tasser la grille
    public void tasserGrille(int i) {

        for (int j = 0; j < 6; j++) {
            if (Cellules[j][i] == null) { //chercher une cellule non remplie 
                Cellules[j][i] = Cellules[j + 1][i]; //La transformer en la cellule de la ligne du dessus
                Cellules[j + 1][i] = null; //Vider la cellule du dessus
            }
        }
    }

    //Pour placer un desintegrateur 
    public boolean placerDesintegrateur(int i, int j) {
        if (Cellules[i][j].presence_Desintegrateur() == true) {
            return false;
        } else {
            Cellules[i][j].placer_Desintegrateur();
            return true;
        }
    }

    //Pour permettre de placer un trou noir dans la grille
    public boolean placerTrouNoir(int i, int j) {
        if (Cellules[i][j].presence_TrouNoir() == true) { //Si la cellule a un trou noir
            return false; //faux car on ne peut pas en mettre 2
        } else { //Sinon 
            Cellules[i][j].placer_TrouNoir();
            return true;//Vrai 
        }
    }

    //Supprimer le jeton 
    public boolean supprimerJeton(int i, int j) {
        boolean res;
        res = Cellules[i][j].supprimer_Jeton();
        return res;
    }

    //Recupèrer le jeton 
    public Jeton recupererJeton(int i, int j) {
        Jeton res;
        res = Cellules[i][j].recuperer_Jeton(); //recuperer le jeton
        Cellules[i][j].supprimer_Jeton();//le supprimer dans la cellule
        return res;//renvoie le résultat(res)
    }

}
