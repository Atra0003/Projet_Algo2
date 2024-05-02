import java.io.File;  
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class Main {
    private Graph G;
    private AllLightUp allLightUp;
    private ArrayList<ArrayList<Integer>> bulds = new ArrayList<ArrayList<Integer>>();
    int size = 0;
    
    public static void main(String[] args) {
        Main main = new Main();
        main.processFile(args);
        main.processGraph();
        if(main.processAllLightUp()) {
            System.out.println("Tout les lights s'allume");
        }
        else {
            ProblemeNbrMaxAmpoulesAllumees objet_test = new ProblemeNbrMaxAmpoulesAllumees(main.bulds);
            System.out.println(objet_test.determiner_nbr_max_ampoules_allumees(0));
        }
    }


    private boolean processAllLightUp() {
        allLightUp = new AllLightUp(G);
        boolean answerAllLightUp = allLightUp.returnAllLightUp();
       return answerAllLightUp;
    }

    private void processGraph() {
        G = new Graph(size);
        for (ArrayList<Integer> data : bulds) {
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
                bulds.add(dataOfBuld);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur lors de la lecture du fichier.");
            e.printStackTrace();
        }
    }
}
