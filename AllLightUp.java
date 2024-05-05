import java.util.ArrayList;
import java.util.Collections;

public class AllLightUp {
    Graph graph; // Le graphe sur lequel les opérations seront effectuées
    Switche[][] tGraph; // Le graphe transposé pour Kosaraju
    boolean answerAllLightUp; // Réponse finale : toutes les ampoules peuvent-elles être allumées ?
    ArrayList<String> listDFS = new ArrayList<String>(); // Liste pour le parcours DFS
    ArrayList<String> listSCC = new ArrayList<String>(); // Liste pour les composantes fortement connexes

    // Constructeur de la classe AllLightUp
    public AllLightUp(Graph G) {
        this.graph = G; // Initialisation du graphe
        this.answerAllLightUp = true; // Initialisation de la réponse à true (par défaut)
    }

    // Méthode pour déterminer si toutes les ampoules peuvent être allumées
    public boolean returnAllLightUp() {
        // Parcours DFS pour générer la liste des nœuds dans l'ordre de fin de parcours
        for (int i = 0; i < graph.getAllSwitche().length; i++) {
            for (int j = 0; j < graph.getAllSwitche()[i].length; j++) {
                if(graph.getAllSwitche()[i][j].getMarked() == false) {
                    dfs(graph.getAllSwitche()[i][j]);
                }
            }
        }

        // Inversion de la liste pour Kosaraju
        Collections.reverse(listDFS);
        // Récupération du graphe transposé pour Kosaraju
        tGraph = graph.getTGraph();

        // Parcours des nœuds dans l'ordre de fin de parcours
        for(String elem : listDFS) {
            for (int i = 0; i < tGraph.length; i++) {
                for (int j = 0; j < tGraph[i].length; j++) {
                    if(elem.equals(tGraph[i][j].getId()) && tGraph[i][j].getMarked() == false) {
                        // Détection des composantes fortement connexes
                        strongComponentConnexe(tGraph[i][j], answerAllLightUp);
                        listSCC.clear();
                    }
                }
            }
        }
        return answerAllLightUp; // Retourner la réponse finale
    }

    // Méthode pour détecter les composantes fortement connexes
    public void strongComponentConnexe(Switche node, boolean answerAllLightUp) {
        if (node.getMarked()) {
            return;
        }
        node.setMarked(); // Marquer le nœud visité
        listSCC.add(node.getOpposite()); // Ajouter l'état opposé à la liste
        for (Switche s : node.getNeighbors()) {
            // Si le voisin est déjà dans la liste des composantes, il y a une contradiction
            if(listSCC.contains(s.getId()) && s.getMarked() == false) {
                this.answerAllLightUp = false;
                strongComponentConnexe(s, answerAllLightUp);
            }
            else {
                strongComponentConnexe(s, answerAllLightUp);
            }
        }
    }

    // Méthode de parcours DFS pour générer l'ordre de fin de parcours
    public void dfs(Switche node) {
        if (node.getMarked()) {
            return;
        }
        node.setMarked(); // Marquer le nœud visité
        for (Switche s : node.getNeighbors()) {
            dfs(s);
        }
        listDFS.add(node.getId()); // Ajouter le nœud à la liste DFS
    }
}
