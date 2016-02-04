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
        String nomFichier="GrapheDuWeb.txt";
        
        /*0. Affichage du graphe du web*/
        LectureGrapheDuWeb.afficheGrapheDuWeb(nomFichier);
        
       /*1. Construction du tableau de debut des predesseurs de chaque sommet*/
        int tabDebut[]=new int[4];
        tabDebut=LectureGrapheDuWeb.ConstructionTabDebut(nomFichier);
        System.out.println();
        System.out.println("n = "+LectureGrapheDuWeb.n);
        System.out.println("nz = "+LectureGrapheDuWeb.nz);
        System.out.print("TabDebut : ");
        for(int i=0; i<4; i++) System.out.print(tabDebut[i]+"  ");
        System.out.println();
        
       /*2. Construction du tableau des predecesseurs de chaque sommet*/
        double tabPreds[]=new double[LectureGrapheDuWeb.nz];
        tabPreds=LectureGrapheDuWeb.ConstructionTabPreds(nomFichier, tabDebut);
        System.out.print("TabPreds : ");
        for(int i=0; i<LectureGrapheDuWeb.nz; i++) System.out.print(tabPreds[i]+"  ");
        System.out.println();
        
       /*3. Calcul du produit de la matrice */
        double [] e={1,1,1}; System.out.print("Vecteur e : "); for(int i=0; i<e.length; i++) System.out.print(e[i]+"  ");
        double produit[]=Matrice.produit(e,tabPreds, tabDebut);
        System.out.println();
        System.out.print("Produit : "); for(int i=0; i<produit.length; i++) System.out.print(produit[i]+"  ");
        System.out.println("\n\n");
        
        //DETERMINATION DE LA PERTINENCE DES PAGES
        /*1. Initialisation de la pertinence de chaque sommet*/
        double [] P=new double[LectureGrapheDuWeb.n];
        for(int i=0; i<LectureGrapheDuWeb.n; i++) P[i]=(double)1/(double)3;
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
        }while(Matrice.norme(P)-Matrice.norme(K)<=0.000001);
        System.out.println("Nombre d'iteration : "+i);
        /**/
    }
}
