// Importation des classes ArrayList et List du package java.util.
import java.util.ArrayList;
import java.util.List;

// Définition de la classe publique NodeUtils qui contient des méthodes utilitaires pour les objets Node.
public class NodeUtils {

    // Méthode privée pour vérifier si deux nodes sont contradictoires.
    // Deux nœuds sont considérés contradictoires s'ils ont le même 'row' mais des états 'isOpen' différents.
    private boolean isContradictory(Node a, Node b) {
        return a.row.equals(b.row) && a.isOpen != b.isOpen;
    }

    // Méthode publique pour vérifier la contradiction entre deux listes de Node.
    // Retourne vrai si au moins une paire de nœuds contradictoires est trouvée entre les deux listes.
    public boolean isContradiction(List<Node> existingSet, List<Node> newImplication) {
        for (Node newStart : newImplication) {
            for (Node existing : existingSet) {
                if (isContradictory(newStart, existing)) {
                    return true;
                }
            }
        }
        return false; // Retourne faux si aucune contradiction n'est trouvée.
    }

    // Méthode pour construire la plus grande liste de nœuds sans contradictions à partir d'une liste d'implications.
    public List<Node> buildLargestNonContradictingList(List<List<Node>> implications) {
        List<List<Node>> bestSubsets = new ArrayList<>(); // Liste pour stocker les meilleurs sous-ensembles sans contradictions.
        List<Node> largestSubset = new ArrayList<>(); // Variable pour maintenir le plus grand sous-ensemble trouvé.

        for (List<Node> newImplication : implications) {
            List<List<Node>> newSubsets = new ArrayList<>(); // Liste temporaire pour stocker les nouveaux sous-ensembles valides.
            boolean added = false; // Indicateur pour vérifier si de nouveaux sous-ensembles ont été ajoutés.

            for (List<Node> subset : bestSubsets) {
                if (!isContradiction(subset, newImplication)) { // Vérifie l'absence de contradiction avec les sous-ensembles existants.
                    List<Node> newSubset = new ArrayList<>(subset);
                    newSubset.addAll(newImplication); // Ajoute la nouvelle implication au sous-ensemble.
                    newSubsets.add(newSubset); // Ajoute le nouveau sous-ensemble à la liste temporaire.
                    added = true;
                    if (newSubset.size() > largestSubset.size()) { // Met à jour le plus grand sous-ensemble si nécessaire.
                        largestSubset = newSubset;
                    }
                }
            }
            if (!added) { // Si aucun sous-ensemble n'a été ajouté, ajoute la nouvelle implication comme nouveau sous-ensemble.
                bestSubsets.add(new ArrayList<>(newImplication));
                if (newImplication.size() > largestSubset.size()) {
                    largestSubset = new ArrayList<>(newImplication); // Met à jour le plus grand sous-ensemble si nécessaire.
                }
            }
            bestSubsets.addAll(newSubsets); // Ajoute tous les nouveaux sous-ensembles valides à la liste des meilleurs sous-ensembles.
        }
        return largestSubset; // Retourne le plus grand sous-ensemble non contradictoire trouvé.
    }

}
