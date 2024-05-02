import java.util.ArrayList;
import java.util.Collections;

public class AllLightUp {
    Graph graph;
    Switches[][] tGraph;
    boolean answerAllLightUp;
    ArrayList<String> listDFS = new ArrayList<String>();
    ArrayList<String> listSCC = new ArrayList<String>();


    public AllLightUp(Graph G) {
        this.graph = G;
        this.answerAllLightUp = true;
    }

    public boolean returnAllLightUp() {
        for (int i = 0; i < graph.getAllSwitche().length; i++) {
            for (int j = 0; j < graph.getAllSwitche()[i].length; j++) {
                if(graph.getAllSwitche()[i][j].getFixed() == false) {
                    dfs(graph.getAllSwitche()[i][j]);
                }
            }
        }
        // for(String s : listDFS) {
        //     System.out.print(s + " ");
        // }

        Collections.reverse(listDFS);
        // System.out.println();

        // for(String s : listDFS) {
        //     System.out.print(s + " ");
        // }
        // System.out.println();

        // recup tGraph
        tGraph = graph.getTGraph();

        for(String elem : listDFS) {
            for (int i = 0; i < tGraph.length; i++) {
                for (int j = 0; j < tGraph[i].length; j++) {
                    if(elem.equals(tGraph[i][j].getId()) && tGraph[i][j].getFixed() == false) {
                        //System.out.println("pass");
                        strongComponentConnexe(tGraph[i][j], answerAllLightUp);
                        listSCC.clear();
                    }
                }
            }
        }
        //System.out.println("rep = " + answerAllLightUp);
        return answerAllLightUp; // À modifier en fonction de votre logique réelle
    }


    public void strongComponentConnexe(Switches node, boolean answerAllLightUp) {
        if (node.getFixed()) {
            return; // Sortir de la méthode si le commutateur est déjà fixé
        }
        node.setFixed(); // Fixer le commutateur
        listSCC.add(node.getOpposite());
        //System.out.println("opp = " + node.getOpposite());
        for (Switches s : node.getNeighbors()) {
            if(listSCC.contains(s.getId()) && s.getFixed() == false) {
                this.answerAllLightUp = false;
                //System.out.println("detected");
                strongComponentConnexe(s, answerAllLightUp); // Appel récursif pour visiter les voisins non fixés
            }
            else {
                strongComponentConnexe(s, answerAllLightUp); // Appel récursif pour visiter les voisins non fixés
            }
        }
    }

    public void dfs(Switches node) {
        if (node.getFixed()) {
            return; // Sortir de la méthode si le commutateur est déjà fixé
        }
        node.setFixed(); // Fixer le commutateur
        for (Switches s : node.getNeighbors()) {
            dfs(s); // Appel récursif pour visiter les voisins non fixés
        }
        listDFS.add(node.getId()); // Ajouter l'ID du nœud à la liste après avoir visité tous ses voisins
    }



}