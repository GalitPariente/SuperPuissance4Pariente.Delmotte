package puissance4_pariente;

/*
Pariente Galit 
Super puisssance 4 
TP3
 */
import java.util.Random;
/**
 *
 * @author Pariente Galit
 */
public class Joueur {
String Nom;
String Couleur;
Jeton ListeJetons[] = new Jeton [21];
int nombreDesintegrateurs;
int nombreJetonsRestants;
    //les attributs
    
    Joueur (String Un_Nom){
        Nom = Un_Nom;
        nombreDesintegrateurs = 0;
        nombreJetonsRestants = 0;
        
        String Une_Couleur[] = {"Jaune","Rouge"};
        Random generateurAleatoire = new Random();
        int m = generateurAleatoire.nextInt(1);
        Couleur= Une_Couleur [m];
        //On a construit notre classe       
                        
    }
    void ajouter_Jeton (Jeton Jeton_a_ajouter){
        ListeJetons [nombreJetonsRestants ++] = Jeton_a_ajouter;
        
    }
        Jeton recupererJeton (){
            nombreJetonsRestants -- ;
            return ListeJetons[nombreJetonsRestants] ;
        }
    
        void obtenirDesintegrateur(){
            nombreDesintegrateurs += 1 ;
        }
        
        boolean utiliserDesintegrateur (){
            if (nombreDesintegrateurs == 0) {
                return false ;
            } else {
                nombreDesintegrateurs -- ;
                return true ;
            }
        }
        
        @Override
        public String toString() {
            String lireCouleur ;
            lireCouleur = "La couleur du joueur est " +Couleur;
            return lireCouleur ; 
        } 
}

