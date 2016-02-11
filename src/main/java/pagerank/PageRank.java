/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 *
 * @author Poulmanogo
 */
public class PageRank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String nomFichier="web1.txt";
        
        /*0. Affichage du graphe du web*/
        LectureGrapheDuWeb.afficheGrapheDuWeb(nomFichier);
        
       /*1. Construction du tableau de debut des predesseurs de chaque sommet*/
        int tabDebut[];//=new int[LectureGrapheDuWeb.n+1];
        tabDebut=LectureGrapheDuWeb.ConstructionTabDebut(nomFichier);
        System.out.println();
        //System.out.println("n = "+LectureGrapheDuWeb.n);
        //System.out.println("nz = "+LectureGrapheDuWeb.nz);
        System.out.print("TabDebut : ");
        for(int i=0; i<LectureGrapheDuWeb.n+1; i++) System.out.print(tabDebut[i]+"  ");
        System.out.println();
        
       /*2. Construction du tableau des predecesseurs de chaque sommet*/
        double tabPreds[][];//=new double[2][LectureGrapheDuWeb.nz];
        tabPreds=LectureGrapheDuWeb.ConstructionTabPreds(nomFichier, tabDebut);
        System.out.print("TabPreds : ");
        for(int i=0; i<LectureGrapheDuWeb.nz; i++) System.out.print(tabPreds[0][i]+"  ");
        System.out.print("\n           ");
        for(int i=0; i<LectureGrapheDuWeb.nz; i++) System.out.print(tabPreds[1][i]+"  ");
        System.out.println();
       
        //DETERMINATION DE LA PERTINENCE DES PAGES
        /*1. Initialisation de la pertinence de chaque sommet dans le tableau P*/
            double [] P=new double[LectureGrapheDuWeb.n];
            for(int i=0; i<LectureGrapheDuWeb.n; i++) P[i]=(double)1/(double)LectureGrapheDuWeb.n;
            //System.out.print("P 0 : "); for(int i=0; i<P.length; i++) System.out.print(P[i]+"  ");
            //System.out.println("\nNorme P : "+Matrice.norme(P));
       /*Itération*/
        int nombreIteration=1;
        double [] K, temp; double condition;
        do{
           K=P;
           P=Matrice.produit(P, tabPreds, tabDebut);
           System.out.print("\nP "+ (nombreIteration-1)+" : "); for(int i=0; i<P.length; i++) System.out.print(K[i]+"  ");
           System.out.print("\nP "+ (nombreIteration)+" : "); for(int b=0; b<P.length; b++) System.out.print(P[b]+"  "); System.out.println();
           temp= Matrice.difference(P,K);
           /*test*/System.out.print("P-K : "); for(int i=0; i<P.length; i++) System.out.print(temp[i]+"  ");
           condition=Matrice.norme(Matrice.difference(P,K));
           /*test*/
           //System.out.println("\nNorme P : "+Matrice.norme(P));
           System.out.println("\nnorme(diff) : "+valeurAbsolue(condition)); System.out.println();
           /*fin test*/
           nombreIteration++;
        }while(valeurAbsolue(condition)< Math.pow(10, -6));
        System.out.println("\nNombre d'iteration : "+(nombreIteration-1));
    }
    public static double valeurAbsolue(double valeur){
        if(valeur<0) valeur=valeur*(-1);
        return valeur;
    }
}