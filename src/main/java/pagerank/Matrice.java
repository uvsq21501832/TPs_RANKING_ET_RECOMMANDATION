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
        double r[]={0,0,0};
        int k=0;
        for(int i=0; i<3; i++){
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
}
