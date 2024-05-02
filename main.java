import java.io.File;  
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class main {
    private static Graph G; // Déclarez G comme un champ de classe statique
    private static AllLightUp allLightUp;
    private static ArrayList<ArrayList<Integer>> bulds = new ArrayList<ArrayList<Integer>>();
    
    public static void main(String[] args) {
        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            int size = 0;
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
            
            G = new Graph(size);
            for (ArrayList<Integer> data : bulds) {
                G.addLink(data);
            }

            allLightUp = new AllLightUp(G);
            boolean res = allLightUp.returnAllLightUp();
            System.out.println(res);
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
