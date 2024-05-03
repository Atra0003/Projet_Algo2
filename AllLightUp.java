import java.util.ArrayList;
import java.util.Collections;

public class AllLightUp {
    Graph graph;
    Switche[][] tGraph;
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

        Collections.reverse(listDFS);
        tGraph = graph.getTGraph();

        for(String elem : listDFS) {
            for (int i = 0; i < tGraph.length; i++) {
                for (int j = 0; j < tGraph[i].length; j++) {
                    if(elem.equals(tGraph[i][j].getId()) && tGraph[i][j].getFixed() == false) {
                        strongComponentConnexe(tGraph[i][j], answerAllLightUp);
                        listSCC.clear();
                    }
                }
            }
        }
        return answerAllLightUp;
    }


    public void strongComponentConnexe(Switche node, boolean answerAllLightUp) {
        if (node.getFixed()) {
            return;
        }
        node.setFixed();
        listSCC.add(node.getOpposite());
        for (Switche s : node.getNeighbors()) {
            if(listSCC.contains(s.getId()) && s.getFixed() == false) {
                this.answerAllLightUp = false;
                strongComponentConnexe(s, answerAllLightUp);
            }
            else {
                strongComponentConnexe(s, answerAllLightUp);
            }
        }
    }

    public void dfs(Switche node) {
        if (node.getFixed()) {
            return;
        }
        node.setFixed(); // Fixer le commutateur
        for (Switche s : node.getNeighbors()) {
            dfs(s);
        }
        listDFS.add(node.getId());
    }



}