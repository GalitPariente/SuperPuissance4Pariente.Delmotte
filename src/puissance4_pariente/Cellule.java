package puissance4_pariente;

/*
TP3
Super puissance 4
Pariente Galit

 */

/**
 *
 * @author Pariente Galit
 */
public class Cellule {
    Jeton jetonCourant ;
    boolean trouNoir ;
    boolean desintegrateur ;
    // attributs 
    
    
     public Cellule () {
        trouNoir = false ; // pas de trou noir
        desintegrateur = false ; // pas de desintegrateur
        jetonCourant = null; 
        //Quand on creer la cellule il n'y a pas de jeton 
        }
     
    boolean affecterJeton (Jeton un_jetonCourant){ 
         if (jetonCourant == null){ 
            jetonCourant = un_jetonCourant; 
            return true;
        }else{
            return false;
        }         
    } 
     //Pour permettre de recuperer un jeton 
   
    Jeton recupererJeton (){
         Jeton jetonretour = jetonCourant ; //creation objet jetonretour
         jetonCourant = null ;
         return jetonretour;
     }
   
    
    Boolean supprimerJeton(){
          if (jetonCourant != null ){
          jetonCourant = null;
            return true;
        }else{
            return false;
        }
     }
    Boolean placerTrouNoir (){
        if (trouNoir){
            return false ;
        }else{
            trouNoir = true ;
            return true ;
        }
    }
    Boolean presenceTrouNoir (){
        return trouNoir ;
    }
     
    Boolean activerTrouNoir (){
        if (trouNoir){
            jetonCourant = null ;
            trouNoir = false ;
            System.out.println("Pion arriver dans un trou noir");
            return true;
        }else{
            return false ;
        }
    }
    
    
    Boolean placerDesintegrateur (){
        if (desintegrateur){
            return false ;
        }else{
            desintegrateur = true ;
            return true ;
        }
    }

    Boolean presenceDesintegrateur (){
        return desintegrateur ;
     }
    
    
    String lireCouleurDuJeton (){
        if (jetonCourant == null){
            return "rien" ;
        }else{
            return jetonCourant.Couleur ;
    }
  
    }
    Boolean recupererDesintegrateur(){
        if ( presenceDesintegrateur() ){
            desintegrateur = false;
            return true;
        }else{
            return false;
        }
    }
     
}    


