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

}