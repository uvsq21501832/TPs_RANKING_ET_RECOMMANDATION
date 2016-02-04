/*
 * Cette classe permet de lire le graphe du web et renvoi
 * des informations en fonction de la methode utilisée
 */
package pagerank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Poulmanogo
 */
public class LectureGrapheDuWeb {
    public static int n; //nombre de somme du graphe du web
    public static int nz; //nombre de valeurs nom nulles de la matrice du graphe du web
    
    //METHODE D'AFFICHAGE DU GRAPHE DU WEB (lecture simple du fichier)
     public static void afficheGrapheDuWeb(String nomFichier){
        double [] tableau;
        try{
            // OUVRE LE FICHIER
            BufferedReader br = new BufferedReader(new FileReader( new File(nomFichier)));                       
            String tmp;
            while ((tmp=br.readLine())!=null){
            // STOCK LA LIGNE DANS UN BUFFER                    
            // COUPE LA LIGNE SUIVANT LES ESPACES
            String [] args = tmp.split(" ");                       
            // ALLOUE NOTRE TABLEAU DE N ELEMENTS
            tableau = new double[args.length];                       
            // COPIE CHAMP A CHAMP + AFFICHAGE
            for( int i=0;i<tableau.length;i++){
                tableau[i] = Float.parseFloat( args[i] );                               
                System.out.print( tableau[i]+"   " );              
            } System.out.println();
            }
            br.close();
        }catch( IOException | NumberFormatException e ){
            System.out.println("Erreur, le fichier n'existe pas ou est mal formaté: ");
        }
   }
    
   //METHODE DE CONSTRUCTION DU TABLEAU DE DEBUT DES PREDECESSEURS DE CHAQUE SOMME
    public static int [] ConstructionTabDebut(String nomFichier){
        //int tabDebut[]=new int[4];
        //tabDebut[0]=0;
    	int tabDebut[];
    	int [] c;
        double [] tableau;
        try{
            // OUVRE LE FICHIER
            BufferedReader br = new BufferedReader(new FileReader( new File(nomFichier)));                       
            // COPIE LE PREMIER ELEMENT: n
            String tmp;
            tmp=br.readLine();
            String [] args = tmp.split(" ");
            n= Integer.parseInt( args[0] );
            // COPIE LE DEUXIEME ELEMENT: nz
            tmp=br.readLine();
            args = tmp.split(" ");
            nz= Integer.parseInt( args[0] );
            
            tabDebut=new int[n+1];
            tabDebut[0]=0;
            //initialisation des compteurs du nombre de predecesseurs de chaque sommet
            c=new int[n]; for(int i=0; i<n; i++) c[i]=0;
            while ((tmp=br.readLine())!=null){
            // STOCK LA LIGNE DANS UN BUFFER                    
            // COUPE LA LIGNE SUIVANT LES ESPACES
            args = tmp.split(" ");                       
            // ALLOUE NOTRE TABLEAU DE N ELEMENTS
            tableau = new double[args.length];                       
            // COPIE CHAMP A CHAMP
            for( int i=2;i<tableau.length;i++){
                tableau[i] = Float.parseFloat( args[i] );
                int s=(int)tableau[i];
                c[s-1]++;
                i++;              
            }
            }
         br.close();
         for(int i=0;i<n;i++){
         tabDebut[i+1]=tabDebut[i]+c[i];}
         return tabDebut;
        }catch( IOException | NumberFormatException e ){
            System.out.println("Erreur, le fichier n'existe pas ou est mal formaté: ");
        }
        return null;
    }
    
    //METHODE DE CONSTRUCTION DU TABLEAU DE DEBUT DES PREDECESSEURS DE CHAQUE SOMME
    public static double [] ConstructionTabPreds(String nomFichier, int [] tabDebut){
        double tabPreds[]=new double[LectureGrapheDuWeb.nz];
        int ips[]=new int[n];
        
        for(int i=0; i<n; i++){
         ips[i]=tabDebut[i];
        }
        double [] tableau;
        try{
            // OUVRE LE FICHIER
            BufferedReader br = new BufferedReader(new FileReader( new File(nomFichier)));                       
            // SAUT DES DEUX PREMIERES LIGNES: n et nz
            br.readLine();
            br.readLine();
            
            String tmp;
            while ((tmp=br.readLine())!=null){
            // STOCK LA LIGNE DANS UN BUFFER                    
            // COUPE LA LIGNE SUIVANT LES ESPACES
            String [] args = tmp.split(" ");                       
            // ALLOUE NOTRE TABLEAU DE N ELEMENTS
            tableau = new double[args.length];                       
            // COPIE CHAMP A CHAMP + AFFICHAGE
            int s;
            for( int i=2;i<tableau.length;i++){
                s= (int)Float.parseFloat( args[i] );
                tabPreds[ips[s-1]++]=Float.parseFloat( args[++i] );
            	}
            }
            br.close();
        }catch( IOException | NumberFormatException e ){
            System.out.println("Erreur, le fichier n'existe pas ou est mal formaté: ");
        }
        return tabPreds;
    }
                        
}
