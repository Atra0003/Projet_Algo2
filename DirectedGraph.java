import java.util.*;

// Classe pour représenter un graphe dirigé. Cette classe inclut des fonctionnalités pour la construction du graphe,
// l'ajout d'arêtes, le parcours en profondeur (DFS), et la détection de composantes fortement connexes (SCC).
class DirectedGraph {
    // Map qui associe chaque nœud à une liste de nœuds voisins, représentant le graphe dirigé.
    Map<Node, List<Node>> adjacencyList;
    // Map similaire à adjacencyList mais pour le graphe inverse. Ceci est utile pour la détection des SCC.
    Map<Node, List<Node>> reverseAdjacencyList;
    // Deque pour enregistrer l'ordre des nœuds visités pendant le DFS, utilisé dans le calcul des SCC.
    Deque<Node> stackPostOrder = new ArrayDeque<>();
    // Liste contenant des ensembles de nœuds, chaque ensemble étant une composante fortement connexe du graphe.
    List<Set<Node>> stronglyConnectedComponents = new ArrayList<>();
    // Ensemble pour suivre les nœuds qui ont été visités pendant l'exécution du DFS.
    Set<Node> visited = new HashSet<>();

    // Constructeur qui initialise les listes d'adjacence et d'adjacence inverse.
    public DirectedGraph() {
        adjacencyList = new HashMap<>();
        reverseAdjacencyList = new HashMap<>();
    }

    // Retourne la liste d'adjacence du graphe.
    Map<Node, List<Node>> getAdjacencyList(){
        return adjacencyList;
    }

    // Retourne la liste d'adjacence inverse du graphe.
    Map<Node, List<Node>> getReverseAdjacencyList(){
        return reverseAdjacencyList;
    }

    // Ajoute une arête du nœud fromNode au nœud toNode dans la liste d'adjacence spécifiée.
    public void addEdge(Node fromNode, Node toNode, Map<Node, List<Node>> listMap) {
        // Utilise computeIfAbsent pour créer une liste si fromNode n'est pas déjà une clé dans listMap,
        // puis ajoute toNode à cette liste.
        listMap.computeIfAbsent(fromNode, k -> new ArrayList<>()).add(toNode);
    }

    // Crée le graphe inverse à partir du graphe original pour aider à détecter les SCC.
    public void creatGraphReverse(){
        for (Map.Entry<Node, List<Node>> entry : adjacencyList.entrySet()) {
            for(Node value : entry.getValue()){
                addEdge(value, entry.getKey(), reverseAdjacencyList);
            }
        }
    }

    // Effectue un parcours en profondeur (DFS) du graphe, enregistrant les nœuds dans stackPostOrder si storeOrder est vrai.
    public void dfs(Node startNode, Map<Node, List<Node>> graph, boolean storeOrder, Set<Node> currentComponent) {
        dfsHelper(startNode, visited, graph, storeOrder, currentComponent);
    }

    // Aide au DFS qui effectue le parcours, en ajoutant les nœuds à currentComponent et enregistrant leur visite.
    private void dfsHelper(Node node, Set<Node> visited, Map<Node, List<Node>> graph, boolean storeOrder, Set<Node> currentComponent) {
        if (visited.contains(node)) return;
        visited.add(node);
        currentComponent.add(node); // Ajoute le nœud à la composante actuelle.

        for (Node neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            dfsHelper(neighbor, visited, graph, storeOrder, currentComponent);
        }

        if (storeOrder) {
            stackPostOrder.push(node);  // Ajoute le nœud au stackPostOrder si storeOrder est vrai.
        }
    }

    // Identifie et valide les composantes fortement connexes du graphe, retournant vrai si toutes les composantes sont valides.
    public boolean findStronglyConnectedComponents() {
        for (Node node : adjacencyList.keySet()) {
            Set<Node> currentComponent = new HashSet<>();
            if (!visited.contains(node)) {
                dfs(node, adjacencyList, true, currentComponent);
                if (!isComponentValid(currentComponent)) {
                    return false;  // Arrête l'exécution si une contradiction est trouvée.
                }
                stronglyConnectedComponents.add(new HashSet<>(currentComponent));
            }
        }
        return true;
    }

    // Vérifie si une composante donnée est valide en s'assurant qu'il n'y a pas de contradictions internes.
    private boolean isComponentValid(Set<Node> component) {
        Map<String, Boolean> nodeStates = new HashMap<>();
        for (Node node : component) {
            String key = node.row;
            boolean oppositeState = !node.isOpen;
            if (nodeStates.containsKey(key) && nodeStates.get(key) == oppositeState) {
                return false; // Trouve une contradiction.
            }
            nodeStates.put(key, node.isOpen);
        }
        return true;
    }
}
