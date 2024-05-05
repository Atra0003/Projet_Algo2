import java.util.ArrayList;

public class Switche {
    // Cette classe représente un interrupteur
    int Num; // Numéro de l'interrupteur
    String state; // État de l'interrupteur (on/off)
    boolean marked; // Indicateur pour le parcours du graphe
    boolean fixed; // Indicateur si l'interrupteur est fixé (lié à une ampoule)
    ArrayList<Switche> neighbors; // Liste des interrupteurs voisins

    // Constructeur de la classe Switche
    Switche(int num, String state) {
        this.Num = num; // Initialisation du numéro
        this.state = state; // Initialisation de l'état
        this.marked = false; // Initialisation de l'indicateur de marquage
        this.fixed = false; // Initialisation de l'indicateur de fixation
        this.neighbors = new ArrayList<>(); // Initialisation de la liste des voisins
    }

    // Méthode pour obtenir l'état de fixation de l'interrupteur
    public boolean getfixed() { 
        return this.fixed;
    }

    // Méthode pour fixer l'interrupteur
    public void setFixed() {
        this.fixed = true;
    }

    // Méthode pour obtenir l'identifiant de l'interrupteur
    public String getId() {
        return this.Num + "-" + this.state;
    }

    // Méthode pour obtenir le numéro de l'interrupteur
    public int getNum() {
        return this.Num;
    }
    
    // Méthode pour obtenir l'état opposé de l'interrupteur
    public String getOpposite() {
        if (this.state.equals("on")) {
            return this.Num + "-off";
        } else {
            return this.Num + "-on";
        }
    }

    // Méthode pour obtenir l'état de l'interrupteur
    public String getState() {
        return this.state;
    }
    
    // Méthode pour obtenir l'indicateur de marquage de l'interrupteur
    public boolean getMarked() {
        return this.marked;
    }
    
    // Méthode pour obtenir la liste des voisins de l'interrupteur
    public ArrayList<Switche> getNeighbors() {
        return this.neighbors;
    }
    
    // Méthode pour marquer l'interrupteur
    public void setMarked() {
        this.marked = true;
    }

    // Méthode pour ajouter une arête entre l'interrupteur courant et un voisin
    public void addEdge(Switche N) {
        neighbors.add(N); 
    }

    // Méthode pour vider la liste des voisins de l'interrupteur
    public void clear() {
        neighbors.clear();
    }
}