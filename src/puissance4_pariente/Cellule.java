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
        JetonCourant = null;
        TrouNoir = false;
        Desintegrateur = false;
    }

    public boolean affecterJeton(Jeton unJeton) {//Pour affecter un jeton a une colonne 
        if (JetonCourant == null) { //Il n'y a pas de jeton 
            JetonCourant = unJeton;
            return true;
        } else {
            return false; // si une cellule est deja remplie 
        }
    }

    public Jeton recupererJeton() {//Pour recuperer un jeton 
        Jeton JetonRecuperer = JetonCourant;//On crée une variable de type Jeton dans laquelle on stocke le jeton de la cellule
        JetonCourant = null;//Le jeton de la cellule devient null == pas de jeton
        return JetonRecuperer; //On retourne le jeton anciennement dans la cellule
    }

    public boolean supprimerJeton() {//Pour permettre de supprimer un jeton 
        if (JetonCourant == null) { //Permet de verifier si la cellule est vide ou non 
            return false; //Retourne faux si c'est le cas
        } else {
            JetonCourant = null; //Pour vider la cellule si celle si elle remplie
            return true;
        }
    }

    public boolean placerTrouNoir() {//Pour placer un trou noir 
        if (TrouNoir == true) { //Verifier si il y en a deja un 
            return false; //Si c'est le cas 
        } else {
            TrouNoir = true;
            return true;
        }
    }

    public boolean placerDesintegrateur() {//Pour placer un Desintegrateur 
        if (Desintegrateur == true) {//Verifier si il y en a deja un 
            return false; //Si c'est le cas cela retourne faux 
        } else {
            Desintegrateur = true;
            return true;
        }
    }

    public boolean presenceTrouNoir() {
        return TrouNoir;
    }

    public boolean presenceDesintegrateur() {
        return Desintegrateur;
    }

    public String lireCouleurDuJeton() {//Pour donner la couleur du jeton 
        if (JetonCourant == null) { //Si la cellule est vide on retourne Nonremplie
            return "NONREMPLIE";
        } else {
            return JetonCourant.LireCouleur();
        }
    }

    public boolean recupererDesintegrateur() {//Pour recuperer un desintegrateur 
        if (Desintegrateur == true) { //Verification qu'il y a un desintegrateur 
            Desintegrateur = false;
            return true;
        } else {
            return false;//Faux si il n'y a pas de desintegrateur 
        }
    }

    public boolean activerTrouNoir() { //Pour activer un trou noir 
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
