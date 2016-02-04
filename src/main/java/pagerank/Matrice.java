/*
 * cette classe Matrice servira à definit les
 * les operations matricielles.
 */
package pagerank;

/**
 *
 * @author Poulmanogo
 */
public class Matrice {
    
    //METHODE QUI RENVOI LA PRODUIT DE DEUX MATRICES
    public static double[] produit(double[] e, double[] tabPreds, int [] tabDebut){
    	double r[]= new double[e.length];
    	for(int i=0; i<e.length; i++){
    		r[i]=0;
    	}
        int k=0;
        for(int i=0; i<e.length; i++){
            for(int j=tabDebut[i]; j<tabDebut[i+1]; j++){
                r[i]+=e[k++]*tabPreds[j];
            }
            k=0;
        }
        return r;
    }
    
    public static double norme(double [] vecteur){
        int norme=0;
        for(int i=0;i<vecteur.length;i++) norme+=vecteur[i];
        return norme;
        
    }
    public static double [] difference(double [] vecteur1, double[] vecteur2){
        double[] somme=new double[vecteur1.length];
        for(int i=0;i<vecteur1.length;i++) somme[i]=0;
        for(int i=0;i<vecteur1.length;i++) somme[i]=vecteur1[i]+vecteur2[i];
        
        return somme;
        
    }
}
