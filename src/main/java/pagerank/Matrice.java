/*
 * cette classe Matrice servira à definit les
 * les operations matricielles.
 */
package pagerank;

public class Matrice {
    //METHODE QUI RENVOI LA PRODUIT DE DEUX MATRICES
    public static double[] produit(double[] e, double[][] tabPreds, int [] tabDebut){
    	double r[]= new double[e.length];
    	for(int i=0; i<e.length; i++){
    		r[i]=0;
    	}
        for(int i=0; i<e.length; i++){
            int k=0;
            int j=tabDebut[i];
            do{
                if(k+1==tabPreds[0][j]){
                    r[i]+=e[k]*tabPreds[1][j];
                    k++; j++;
                } else k++; 
            }while(j<tabDebut[i+1] && k<e.length);
        }
        return r;
    }
    //METHODE QUI RENVOI LA NORME D'UN VECTEUR (tableau)
    public static double norme(double [] vecteur){
        double norme=0;
        for(int i=0;i<vecteur.length;i++) norme+=valeurAbsolue(vecteur[i]);
        return norme;   
    }
    
    //METHODE QUI RENVOI LA DIFFERENCE DE NORME DE DEUX MATRICES
    public static double [] difference(double [] vecteur2, double[] vecteur1){
        double[] diff=new double[vecteur1.length];
        for(int i=0;i<vecteur1.length;i++) diff[i]=0;
        for(int i=0;i<vecteur1.length;i++) diff[i]=vecteur2[i]-vecteur1[i];
        return diff;
    }
    //VELEUR ABSOLUE
    public static double valeurAbsolue(double valeur){
        if(valeur<0) valeur=valeur*(-1);
        return valeur;
    }
}
