import java.io.File;  
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class main {
    private static Graph G; // Déclarez G comme un champ de classe statique
    private static ArrayList<String> buld;
    private static AllLightUp allLightUp;
    
    public static void main(String[] args) {
        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            buld = new ArrayList<>();
            int size = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                buld.add(data);
                String[] arrOfStr = data.split(" ");
                if (Integer.valueOf(arrOfStr[0]) > size) { 
                    size = Integer.valueOf(arrOfStr[0]); // number of line and column
                }
            }
            myReader.close();
            
            G = new Graph(size);
            for (String data : buld) {
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
