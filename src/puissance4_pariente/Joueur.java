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
public class Joueur {

    //Attributs
    String Nom;
    String Couleur;
    Jeton ListeJetons[] = new Jeton[21];
    int nombreJetons;
    int NombreDesintegrateurs;

    public Joueur(String UnNom) {
        //Constructeur 
        Nom = UnNom;
        nombreJetons = 0;
        NombreDesintegrateurs = 0;

    }

    //Affectation de la couleur en parametre au joueur 
    public void affecterCouleur(String uneCouleur) {
        Couleur = uneCouleur;
    }

    //Retirer un jeton dans la liste du joueur 
    public Jeton enlever_Jeton() {
        nombreJetons = nombreJetons - 1;
        return ListeJetons[nombreJetons];
    }

    public void ajouter_Jeton(Jeton unJeton) {
        ListeJetons[nombreJetons++] = unJeton;
    }

    //Incrémente le nombre de desintegrateur 
    public void obtenir_Desintegrateurs() {
        NombreDesintegrateurs++;
        System.out.println("Vous venez de remporter un désintégrateur");//Message au joueur 
    }

    public boolean utiliser_Desintegrateur() {
        boolean res; //Création variable résultat
        if (NombreDesintegrateurs == 0) { //Verification que le joueur a bien un ou plusieurs desintegrateurs 
            res = false; //Si non ->faux
        } else {
            NombreDesintegrateurs--;
            res = true; //Retourne vraie
        }
        System.out.print("Vous avez " + NombreDesintegrateurs + " désintégrateurs"); //Message pour dire au joueur son nombre de desintegrateur 
        return res; //Renvoie du résultat
    }
}