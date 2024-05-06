// Importation des utilitaires Java nécessaires.
import java.util.*;

// Définition de la classe Node.
class Node {
    // Déclaration des attributs de la classe.
    // 'row' stocke une chaîne de caractères, potentiellement utilisée comme identifiant ou information spécifique du nœud.
    // 'isOpen' est un booléen qui indique un état (ouvert ou fermé).
    String row;
    boolean isOpen;

    // Constructeur pour initialiser les objets Node avec des valeurs spécifiques.
    public Node(String row, boolean isOpen) {
        this.row = row;        // Attribution de la chaîne de caractères passée au constructeur à l'attribut 'row'.
        this.isOpen = isOpen;  // Attribution de la valeur booléenne passée au constructeur à l'attribut 'isOpen'.
    }

    // Surcharge de la méthode equals pour fournir une égalité logique entre deux objets Node.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Vérifie si les deux objets sont exactement les mêmes.
        if (o == null || getClass() != o.getClass()) return false;  // Vérifie si l'objet est nul ou si les classes sont différentes.
        Node node = (Node) o;  // Cast de l'objet o en un objet Node.
        return row.equals(node.row) && isOpen == node.isOpen;  // Vérifie l'égalité basée sur 'row' et 'isOpen'.
    }

    // Surcharge de la méthode hashCode pour assurer une bonne performance des collections basées sur le hachage.
    @Override
    public int hashCode() {
        return Objects.hash(row, isOpen);  // Utilise la classe helper 'Objects' pour générer un hashCode basé sur 'row' et 'isOpen'.
    }

    // Méthode pour vérifier si deux nœuds sont opposés (même 'row' mais isOpen différent).
    public boolean isOpposite(Node o){
        if (this.row.compareTo(o.row) == 0) {  // Compare les 'row' des deux nœuds.
            return this.isOpen != o.isOpen;    // Vérifie si les états 'isOpen' sont opposés.
        }
        return false;  // Retourne false si les 'row' ne sont pas égaux.
    }

    // Surcharge de la méthode toString pour fournir une représentation en chaîne de caractères de l'objet Node.
    @Override
    public String toString() {
        return "Node{" + "row='" + row + '\'' + ", isOpen=" + isOpen + '}';  // Formatage de la chaîne de sortie.
    }
}
