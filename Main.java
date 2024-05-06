import java.util.*;

// Classe principale Main qui contient le point d'entrée du programme (méthode main).
public class Main {
    public static void main(String[] args) {
        // Création d'un objet LectureFichier pour lire et analyser un fichier de texte.
        LectureFichier lectureFichier = new LectureFichier();
        // Définition du chemin du fichier à lire.
        String cheminFichier = "texte.txt";
        // Appel de la méthode lireFichier pour lire le contenu du fichier spécifié et construire un graphe.
        lectureFichier.lireFichier(cheminFichier);

        // Création d'un objet NodeUtils qui fournit des utilitaires pour travailler avec des listes de Node.
        NodeUtils nodeUtils = new NodeUtils();

        //NodeUtils1 nodeUtils1 = new NodeUtils1(lectureFichier.rightImpList);

        /*-----------------------------La conclusion------------------------------------------ */
        // Vérification si toutes les composantes fortement connexes du graphe peuvent être validées sans contradictions.
        if(lectureFichier.directedGraph.findStronglyConnectedComponents()){
            // Affichage positif si toutes les "ampoules" (nodes) peuvent être allumées selon les conditions du graphe.
            System.out.println("Oui toutes les ampoules peuvent être allumées");
        }else {
            // Affichage négatif si il existe des contradictions qui empêchent certaines "ampoules" d'être allumées.
            System.out.println("Non toutes les ampoules ne peuvent être allumées");
            System.out.println();
            // Construction de la plus grande liste de Node qui ne contient pas de contradictions.
            List<Node> largestNonContradictingList = nodeUtils.buildLargestNonContradictingList(lectureFichier.rightImpList);
            // Affichage de la taille de cette liste, divisée par 2, probablement pour obtenir le nombre de paires ou de groupes.
            System.out.println("Taille de la plus grande liste sans contradictions : " + largestNonContradictingList.size() / 2);
            System.out.println();
            // Affichage de chaque Node dans la liste sans contradictions.
            for (Node node : largestNonContradictingList) {
                System.out.println(node);
            }
        } 

        System.out.println();
        
        /*-----------------------------La liste des implications directent--------------------- */
        System.out.println("La liste des bonne implications : ");
        for(List<Node> l : lectureFichier.rightImpList){
            for(Node n : l){
                System.out.print(n.toString());
            }
            System.out.println();
        }

        /*--------------------------------------------------------------------------------------- 
        List<List<Node>> listeee = nodeUtils1.listAmpoule();
        System.out.println();
        System.out.print("La taille de la liste est : ");
        System.out.println(listeee.size());
        System.out.println();
        System.out.println("La liste des implications pour max ampoules : ");
        for(List<Node> lis : listeee){
            for(Node n : lis){
                System.out.print(n.toString());
            }
            System.out.println();
        }*/
       
    } 
}



/*-----------------------Impression du graphe------------------------------------------------ 
System.out.println("Le graphe est : ");
for (Map.Entry<Node, List<Node>> entry : lectureFichier.directedGraph.adjacencyList.entrySet()) {
    System.out.print("Ligne : " + entry.getKey().toString() + " Connecté à : ");
    for (Node n : entry.getValue()) {
        System.out.print(n.toString() + " ");
    }
    System.out.println();
} 
System.err.println();

/*----------------------Impression du graphe inverse---------------------------------------- 
System.out.println("Le graphe Inverse est : ");
lectureFichier.directedGraph.creatGraphReverse();
for (Map.Entry<Node, List<Node>> entry : lectureFichier.directedGraph.reverseAdjacencyList.entrySet()) {
    System.out.print("Ligne : " + entry.getKey().toString() + " Connecté à : ");
    for (Node n : entry.getValue()) {
        System.out.print(n.toString() + " ");
    }
    System.out.println();
} 
System.err.println();

/*----------------------Le dfs post-ordre---------------------------------------------------- 
    // Start DFS from the first entry if exists

System.out.println("Le parcours en dfs post_ordre inverse du graphe : ");
lectureFichier.directedGraph.findStronglyConnectedComponents();

System.out.println();

/*---------------------Les composantes fortement connexes----------------------------- 
System.out.print("La taille est : ");
System.out.println(lectureFichier.directedGraph.stronglyConnectedComponents.size());
int count = 1;
for (Set<Node> component : lectureFichier.directedGraph.stronglyConnectedComponents) {
    System.out.print("Les composantes numero : ");
    System.out.println(count);
    for (Node node : component) {
        System.out.print(node.toString() + " ");
    }
    count ++;
    System.out.println(); // Pour ajouter une ligne vide entre chaque composante
} */
/*-----------------------------La liste des implications directent--------------------- 
System.out.println("La liste des bonne implications : ");
for(List<Node> l : lectureFichier.rightImpList){
    for(Node n : l){
        System.out.print(n.toString());
    }
    System.out.println();
}
System.out.println();
/*-----------------------------La taille maximal---------------------------------------- */
/*
System.err.println();
System.err.println("La taille maximal est : ");
Map<String, Boolean> lis = nodeUtils.buildOptimalSet(lectureFichier.rightImpList);
System.out.println(nodeUtils.number(lectureFichier.rightImpList, lis));


for (Map.Entry<String, Boolean> entry : lis.entrySet()) {
    String key = entry.getKey();
    Boolean value = entry.getValue();
    System.out.println("Key: " + key + ", Value: " + value);
} 
System.out.println();

System.out.println("Le ampoules impliquées sont : ");
for(List<Node> entry : nodeUtils.goodImList){
    for(Node n : entry){
        System.out.print(n.toString());
    }
    System.out.println();
} 

Partie2---------------------------------------------------------------------------------------------
List<Node> largestSet = nodeUtils.buildLargestNonContradictingSet(lectureFichier.rightImpList);
System.out.println("Largest non-contradicting set of nodes:");
for (Node node : largestSet) {
    System.out.println(node.row + " is " + (node.isOpen ? "open" : "closed"));
}*/