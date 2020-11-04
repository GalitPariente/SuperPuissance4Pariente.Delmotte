package puissance4_pariente;

/**
 *
 * @author Pariente Galit
 */
public class Jeton {
String Couleur;
    // attributs de la classe

    public Jeton(String une_Couleur) {
        Couleur = une_Couleur;
        //cosntructeur de notre classe
    }

    String lireCouleur() {
        return Couleur;
    }

    @Override
    public String toString() {
        if ("Rouge".equals(Couleur)) {
            return "\u001B[31m 0 ";
        }
        return "\u001B[33m 0 ";
    }
}
