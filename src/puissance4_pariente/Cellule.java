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
public class Cellule {

    //Attributs
    Jeton JetonCourant;
    boolean TrouNoir;
    boolean Desintegrateur;

    public Cellule() {
        //Constructeur 
        JetonCourant = null;//Lors de la creation de notre cellule il n'y a aucun jeton 
        TrouNoir = false;//aucun trou noir 
        Desintegrateur = false;//Aucun desintegrateur 
    }

    public boolean affecter_Jeton(Jeton unJeton) {//Pour affecter un jeton a une colonne,  unJeton est le parametre
        if (JetonCourant == null) { //Il n'y a pas de jeton sur ma cellule 
            JetonCourant = unJeton;//je lui affecte une cellule
            return true;
        } else {
            return false; // si une cellule est deja remplie 
        }
    }

    public Jeton recuperer_Jeton() {//Pour recuperer un jeton 
        Jeton JetonRecuperer = JetonCourant;//creation d'un objet JetonRecuperer auquel on affecte JetonCourant
        JetonCourant = null;//Le jeton de la cellule devient null = pas de jeton
        return JetonRecuperer; //On retourne le jeton anciennement dans la cellule
    }

    public boolean supprimer_Jeton() {//Pour permettre de supprimer un jeton 
        if (JetonCourant == null) { //Permet de verifier si la cellule est vide ou non 
            return false; //Retourne faux si c'est le cas
        } else {
            JetonCourant = null; //Pour vider la cellule si celle si elle remplie
            return true;
        }
    }

    public boolean placer_TrouNoir() {//Pour placer un trou noir 
        if (TrouNoir == true) { //Verifier si il y en a deja un  (on n'etait pas obliger d'ecrire TrouNoir==true car c'est un boolean )
            return false; //Si c'est le cas 
        } else {
            TrouNoir = true;
            return true;
        }
    }

    public boolean placer_Desintegrateur() {//Pour placer un Desintegrateur 
        if (Desintegrateur == true) {//Verifier si il y en a deja un 
            return false; //Si c'est le cas cela retourne faux 
        } else {
            Desintegrateur = true;
            return true;
        }
    }

    public boolean presence_TrouNoir() {
        return TrouNoir;
    }

    public boolean presence_Desintegrateur() {
        return Desintegrateur;
    }

    public String lireCouleur_Jeton() {//Pour donner la couleur du jeton 
        if (JetonCourant == null) { //Si la cellule est vide on retourne Nonremplie
            return "NONREMPLIE";
        } else {
            return JetonCourant.LireCouleur();//Soit cela renvoie "NONREMPLIE" soit ca renvoit la couleur du jeton qui a ete affecte a la cellule 
        }
    }

    public boolean recuperer_Desintegrateur() {//Pour recuperer un desintegrateur 
        if (Desintegrateur == true) { //Verification qu'il y a un desintegrateur 
            Desintegrateur = false;
            return true;
        } else {
            return false;//Faux si il n'y a pas de desintegrateur 
        }
    }

    public boolean activer_TrouNoir() { //Pour activer un trou noir 
        if (TrouNoir == true) {
            JetonCourant = null;
            TrouNoir = false;
            System.out.println("Un trou noir a aspiré votre jeton "); //Affichage du message au joueur
            return true;
        } else {
            return false; // Si il n'y a pas de Trou NOIR
        }
    }

}
