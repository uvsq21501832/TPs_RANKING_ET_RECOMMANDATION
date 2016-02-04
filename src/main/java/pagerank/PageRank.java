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
        // TODO code application logic here
        String nomFichier="web1.txt";
        
        /*0. Affichage du graphe du web*/
        LectureGrapheDuWeb.afficheGrapheDuWeb(nomFichier);
        
       /*1. Construction du tableau de debut des predesseurs de chaque sommet*/
        int tabDebut[]=new int[LectureGrapheDuWeb.n+1];
        tabDebut=LectureGrapheDuWeb.ConstructionTabDebut(nomFichier);
        System.out.println();
        System.out.println("n = "+LectureGrapheDuWeb.n);
        System.out.println("nz = "+LectureGrapheDuWeb.nz);
        System.out.print("TabDebut : ");
        for(int i=0; i<LectureGrapheDuWeb.n+1; i++) System.out.print(tabDebut[i]+"  ");
        System.out.println();
        
       /*2. Construction du tableau des predecesseurs de chaque sommet*/
        double tabPreds[]=new double[LectureGrapheDuWeb.nz];
        tabPreds=LectureGrapheDuWeb.ConstructionTabPreds(nomFichier, tabDebut);
        System.out.print("TabPreds : ");
        for(int i=0; i<LectureGrapheDuWeb.nz; i++) System.out.print(tabPreds[i]+"  ");
        System.out.println();
        
       /*3. Calcul du produit de la matrice */
        double [] e=new double[LectureGrapheDuWeb.n];
        for(int i=0; i<LectureGrapheDuWeb.n; i++) e[i]=1;
        System.out.print("Vecteur e : "); for(int i=0; i<e.length; i++) System.out.print(e[i]+"  ");
        double produit[]=Matrice.produit(e,tabPreds, tabDebut);
        System.out.println();
        System.out.print("Produit : "); for(int i=0; i<produit.length; i++) System.out.print(produit[i]+"  ");
        System.out.println("\n\n");
        
        //DETERMINATION DE LA PERTINENCE DES PAGES
        /*1. Initialisation de la pertinence de chaque sommet*/
        double [] P=new double[LectureGrapheDuWeb.n];
        for(int i=0; i<LectureGrapheDuWeb.n; i++) P[i]=(double)1/(double)LectureGrapheDuWeb.n;
        System.out.print("P : "); for(int i=0; i<P.length; i++) System.out.print(P[i]+"  ");
        /*definition de la matrice H*/
        
        double [][] H = new double[LectureGrapheDuWeb.n][LectureGrapheDuWeb.n];
        int k=0;
        System.out.println("\nMatrice H");
        for(int i=0; i<LectureGrapheDuWeb.n; i++){
            for(int j=0; j<tabDebut[i+1]-tabDebut[i]; j++){
                H[i][j]=tabPreds[k++];
                System.out.print("  "+H[i][j]);
            }System.out.println();
        }
       /*Itération*/
        int i=0;
        double [] K;
        do{
           K=P;
           P=Matrice.produit(P, tabPreds, tabDebut);
           i++;
        }while(Matrice.norme(Matrice.difference(P,K))<=Math.pow(10, -6));
        System.out.println("Nombre d'iteration : "+i);
        
        System.out.println("Norme K : "+Matrice.norme(K));
        System.out.println("Norme P : "+Matrice.norme(P));
        
        /**/
    }
}
