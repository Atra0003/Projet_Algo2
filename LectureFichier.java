// Importations nécessaires pour la manipulation des fichiers et des collections.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Classe LectureFichier conçue pour lire des données à partir d'un fichier et construire un graphe dirigé.
class LectureFichier {
    // Attributs de la classe.
    DirectedGraph directedGraph;  // Un graphe dirigé pour stocker des relations entre les nœuds.
    List<List<Node>> rightImpList = new ArrayList<>();  // Liste pour stocker des listes de nœuds représentant des implications.

    // Configuration initiale de conditions booléennes stockées dans une liste de tableaux de booléens.
    List<boolean[]> config = new ArrayList<>(Arrays.asList(
        new boolean[]{true, true},
        new boolean[]{true, false},
        new boolean[]{false, true},
        new boolean[]{false, false}
    ));

    // Constructeur par défaut de la classe.
    public LectureFichier() {
        directedGraph = new DirectedGraph();  // Initialisation de l'instance de DirectedGraph.
    }

    // Méthode pour lire un fichier texte et traiter chaque ligne pour construire le graphe.
    public void lireFichier(String cheminFichier) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;  // Variable pour stocker les lignes lues du fichier.
            while ((ligne = lecteur.readLine()) != null) {  // Boucle de lecture de chaque ligne.
                String[] list = ligne.split(" ");  // Découpage de la ligne en parties.
                for (int i = 2; i < list.length; i++) {  // Itération à partir du troisième élément.
                    boolean[] condition = config.get(i - 2);  // Récupération de la configuration correspondante.

                    if (Integer.valueOf(list[i]) == 0) {  // Traitement basé sur la valeur lue.
                        Node fromNode = new Node("L" + list[0], condition[0]);  // Création du nœud source.
                        Node toNode = new Node("C" + list[1], !condition[1]);  // Création du nœud destination avec condition inversée.
                        directedGraph.addEdge(fromNode, toNode, directedGraph.getAdjacencyList());  // Ajout de l'arête au graphe.

                        fromNode = new Node("C" + list[1], condition[1]);  // Création d'un nouveau nœud source.
                        toNode = new Node("L" + list[0], !condition[0]);  // Création d'un nouveau nœud destination avec condition inversée.
                        directedGraph.addEdge(fromNode, toNode, directedGraph.getAdjacencyList());  // Ajout de la seconde arête au graphe.

                    } else {
                        List<Node> nodeList = new ArrayList<>();  // Création d'une nouvelle liste de nœuds.
                        nodeList.add(new Node("L" + list[0], condition[0]));  // Ajout du premier nœud à la liste.
                        nodeList.add(new Node("C" + list[1], condition[1]));  // Ajout du second nœud à la liste.
                        rightImpList.add(nodeList);  // Ajout de la liste de nœuds à la liste principale des implications.
                    }
                }
            }
        } catch (IOException e) {  // Gestion des exceptions d'entrée/sortie.
            e.printStackTrace();  // Impression de la trace de pile en cas d'erreur.
        }
    }
}

 



