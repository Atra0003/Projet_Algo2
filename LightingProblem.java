import java.io.File;  
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class LightingProblem {
    private Graph G;
    private AllLightUp allLightUp;
    private ArrayList<ArrayList<Integer>> bulbs = new ArrayList<ArrayList<Integer>>();
    int size = 0;
    
    public static void main(String[] args) {
        LightingProblem lightingproblem = new LightingProblem();
        lightingproblem.processFile(args);
        lightingproblem.processGraph();
        if(lightingproblem.processAllLightUp()) {
            System.out.println("Toutes les Ampoules s'allument ? : OUI");
        }
        else {
            System.out.println("Toutes les Ampoules s'allument ? : NON");
            System.out.print("Le nombre maximum d'Ampoules qui s'allument est : ");
            MaxNbrLightBulbsLit bulbs_nbr_lit = new MaxNbrLightBulbsLit(lightingproblem.bulbs);
            System.out.println(bulbs_nbr_lit.determiner_nbr_max_ampoules_allumees(0));
        }
    }


    private boolean processAllLightUp() {
        allLightUp = new AllLightUp(G);
        boolean answerAllLightUp = allLightUp.returnAllLightUp();
       return answerAllLightUp;
    }

    private void processGraph() {
        G = new Graph(size);
        for (ArrayList<Integer> data : bulbs) {
            G.addLink(data);
        }
    }

    private void processFile(String[] args) {
        try {
            File file = new File(args[0]);
            if(!file.exists()) {
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
                    size = dataOfBuld.get(0); 
                }
                bulbs.add(dataOfBuld);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur lors de la lecture du fichier.");
            e.printStackTrace();
        }
    }
}