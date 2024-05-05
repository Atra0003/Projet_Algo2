import java.io.File;  
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class Main {
    private Graph G;
    private AllLightUp allLightUp;
    private ArrayList<ArrayList<Integer>> bulbs = new ArrayList<ArrayList<Integer>>();
    int size = 0;
    
    public static void main(String[] args) {
        // Création d'une instance de la classe Main
        Main lightingProblem = new Main();
        
        // Traitement du fichier d'entrée
        lightingProblem.processFile(args);
        
        // Construction du graphe
        lightingProblem.processGraph();
    
        System.out.println("aaa = " + lightingProblem.G.getContradiction());
        
        // Vérification de l'existence d'une contradiction dans le graphe
        if (lightingProblem.G.getContradiction()) {
            // Si aucune contradiction n'est détectée, on vérifie si toutes les ampoules peuvent être allumées
            if (lightingProblem.processAllLightUp()) {
                System.out.println("Toutes les Ampoules s'allument ? : OUI");
            } else {
                // Si toutes les ampoules ne peuvent pas être allumées, on détermine le nombre maximum d'ampoules qui peuvent être allumées
                System.out.println("Toutes les Ampoules s'allument ? : NON");
                System.out.print("Le nombre maximum d'Ampoules qui s'allument est : ");
                MaxNbrLightBulbsLit bulbsNbrLit = new MaxNbrLightBulbsLit(lightingProblem.bulbs);
                System.out.println(bulbsNbrLit.determiner_nbr_max_ampoules_allumees(0));
            }
        } else {
            // Si une contradiction est détectée dans le graphe, aucune ampoule ne peut être allumée
            System.out.println("Toutes les Ampoules s'allument ? : NON");
            System.out.print("Le nombre maximum d'Ampoules qui s'allument est : ");
            MaxNbrLightBulbsLit bulbsNbrLit = new MaxNbrLightBulbsLit(lightingProblem.bulbs);
            System.out.println(bulbsNbrLit.determiner_nbr_max_ampoules_allumees(0));
        }
    }
    

    
    
    private boolean processAllLightUp() {
        // Initialisation de l'intence pour déterminer si tout les lights s'allume.
        allLightUp = new AllLightUp(G); 
        boolean answerAllLightUp = allLightUp.returnAllLightUp();
       return answerAllLightUp;
    }

    private void processGraph() {
        G = new Graph(size); // Initialisation du Graph
        for (ArrayList<Integer> data : bulbs) {
            G.addLink(data);
        }
    }

    private void processFile(String[] args) {
        try {
            File file = new File(args[0]);
            if(!file.exists()) { // Vérifie que le fichier existe
                System.out.println("Le fichier n'existe pas.");
                return;
            }

            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String buld = myReader.nextLine();
                ArrayList<Integer> dataOfBuld = new ArrayList<>();
                String[] arrOfStr = buld.split(" ");
                for (int i = 0; i < arrOfStr.length; i++) {
                    dataOfBuld.add(Integer.valueOf(arrOfStr[i]));
                }
                
                if(dataOfBuld.get(0) > 0) {
                    size = dataOfBuld.get(0); // Déterminer la taille de la matrice
                }
                bulbs.add(dataOfBuld);
            }
            myReader.close();
        } catch (FileNotFoundException e) { // Erreur lors de la lecture du fichier
            System.out.println("Erreur lors de la lecture du fichier.");
            e.printStackTrace();
        }
    }
}