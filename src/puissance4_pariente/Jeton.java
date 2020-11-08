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
public class Jeton {

    //Atttribut
    String couleur;

    //Constructeur
    public Jeton(String UneCouleur) {
        couleur = UneCouleur;
    }

    //Renvoie de la couleur du jeton
    public String LireCouleur() {
        return couleur;
    }
public String toString(){
    String LireCouleur;
    LireCouleur = "La couleur du joueur est"+couleur;
    return LireCouleur ;//Fonction qui doit retourner quelque chose 
}
}

