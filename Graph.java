import java.util.ArrayList;

public class Graph {
    private Switche[][] switches; // Tableau 2D pour les interrupteurs
    private Switche[][] tGraph; // Tableau 2D pour le graphe transpose
    int size; // Taille du graphe

    Switche switche_L_on;
    Switche switche_L_off;
    Switche switche_C_on;
    Switche switche_C_off;

    Switche tGraph_L_on;
    Switche tGraph_L_off;
    Switche tGraph_C_on;
    Switche tGraph_C_off;

    boolean contradiction = false; // Indique s'il y a une contradiction dans le graphe

    public Graph(int size) {
        switches = new Switche[2 * size][]; // Initialisation du tableau pour les interrupteurs
        tGraph = new Switche[2 * size][]; // Initialisation du tableau pour le graphe transpose
        for (int i = 0; i < 2 * size; i++) {
            
            switches[i] = new Switche[2]; // Initialisez chaque élément comme un tableau de 2 éléments
            tGraph[i] = new Switche[2];

            switches[i][0] = new Switche(i, "on");
            switches[i][1] = new Switche(i, "off");

            tGraph[i][0] = new Switche(i, "on");
            tGraph[i][1] = new Switche(i, "off");
        }
        this.size = size; // Attribution de la taille du graphe
    }

    // Méthode pour récupérer un interrupteur dans le graphe
    public Switche getSwitch(Switche[][] g, int i, String state) {
	    //System.out.println("i = " + i);
        if (i >= 0 && i < g.length && g[i] != null) {
            if(state.equals("on")) {
				return g[i][0];   
			}
			if(state.equals("off")) {
				return g[i][1];   
			}

        } else {
            System.out.println("Invalid index or null switch.");
        }
        return null;
    }

    // Méthode pour récupérer tous les interrupteurs du graphe
    public Switche[][] getAllSwitche() {
        return this.switches;
    }

    // Méthode pour récupérer tous les interrupteurs du graphe
    public Switche[][] getTGraph() {
        return this.tGraph;
    }
    
    // Méthode pour ajouter des liens entre les interrupteurs en fonction des conditions de satisfaction de l'ampoule
    public void addLink(ArrayList<Integer> data) {
        int l = data.get(0);
        int c = data.get(1);
        int x = data.get(2);
        int y = data.get(3);
        int z = data.get(4);
        int t = data.get(5);
        
        switche_L_on = getSwitch(switches, l - 1, "on");
        switche_L_off = getSwitch(switches, l - 1, "off");
        switche_C_on = getSwitch(switches, size + c - 1, "on");
        switche_C_off = getSwitch(switches, size + c - 1, "off");

        tGraph_L_on = getSwitch(tGraph, l - 1, "on");
        tGraph_L_off = getSwitch(tGraph, l - 1, "off");
        tGraph_C_on = getSwitch(tGraph, size + c - 1, "on");
        tGraph_C_off = getSwitch(tGraph, size + c - 1, "off");

        // mettre les liens
        if(x == 0) {
            switche_L_on.addEdge(switche_C_off);
            switche_C_on.addEdge(switche_L_off);
            tGraph_C_off.addEdge(tGraph_L_on);
            tGraph_L_off.addEdge(tGraph_C_on);
        }
        if(y == 0) {
        	switche_L_on.addEdge(switche_C_on);
        	switche_C_off.addEdge(switche_L_off);
            tGraph_C_on.addEdge(tGraph_L_on);
            tGraph_L_off.addEdge(tGraph_C_off);
        }
        if(z == 0) {
        	switche_L_off.addEdge(switche_C_off);
        	switche_C_on.addEdge(switche_L_on);
            tGraph_C_off.addEdge(tGraph_L_off);
            tGraph_L_on.addEdge(tGraph_C_on);
        }
        if(t == 0) {
            switche_L_off.addEdge(switche_C_on);
            switche_C_off.addEdge(switche_L_on);
            tGraph_C_on.addEdge(tGraph_L_off); 
            tGraph_L_on.addEdge(tGraph_C_off);
        }
        if(x+y+z+t == 1) {
            boolean checkLine = false;
            boolean checkColumn = false;
            if(x == 1) {
                if(switche_L_on.getfixed() == false) {switche_L_on.setFixed();}
                else{checkLine = checkContradiction(switche_L_on, switche_L_off);}

                if(switche_C_on.getfixed() == false) {switche_C_on.setFixed();}
                else{checkColumn=checkContradiction(switche_C_on, switche_C_off);}
            }
            if(y == 1) {
                if(switche_L_on.getfixed() == false) {switche_L_on.setFixed();}
                else{checkLine= checkContradiction(switche_L_on, switche_L_off);}

                if(switche_C_off.getfixed() == false) {switche_C_off.setFixed();}
                else{checkColumn=checkContradiction(switche_C_off, switche_C_on);}
            }
            if(z == 1) {
                if(switche_L_off.getfixed() == false) {switche_L_off.setFixed();}
                else{checkLine=checkContradiction(switche_L_off, switche_L_on);}

                if(switche_C_on.getfixed() == false) {switche_C_on.setFixed();}
                else{checkColumn=checkContradiction(switche_C_on, switche_C_off);}
            }
            if(t == 1) {
                if(switche_L_off.getfixed() == false) {switche_L_off.setFixed();}
                else{checkLine=checkContradiction(switche_L_off, switche_L_on);}

                if(switche_C_off.getfixed() == false) {switche_C_off.setFixed();}
                else{checkColumn=checkContradiction(switche_C_off, switche_C_on);}
            }
            if(checkLine == true || checkColumn == true) {
                setContradiction();
            }
        }
    }

    // Méthode pour définir la contradiction dans le graphe
    public void setContradiction() {
        contradiction=true;
    }

    // Méthode pour obtenir l'état de la contradiction dans le graphe
    public boolean getContradiction() {
        return contradiction;
    }

    // Méthode pour effacer les liens des interrupteurs dans le graphe
    public void clear() {
        for (int i = 0; i < switches.length; i++) {
            for (int j = 0; j < switches[i].length; j++) {
                switches[i][j].clear();
            }
        }
    }

    // Méthode pour vérifier s'il y a une contradiction entre deux interrupteurs
    public boolean checkContradiction(Switche L, Switche C) {
        if(L.getfixed() ==  true && C.getfixed() == true) {
            return true;
        }
        return false;
    }

    // Méthode pour afficher les voisins de chaque interrupteur dans le graphe
    public void seeNeighbors(Switche[][] g) {
        for(int i = 0; i < g.length; i++){
            for(int j = 0; j < g[i].length; j++){
                System.out.print(g[i][j].getId() + ": ");
                ArrayList<Switche> neighbors = g[i][j].getNeighbors();
                for(int k = 0; k < neighbors.size(); k++){
                    System.out.print(neighbors.get(k).getId() + " ");
                }
                System.out.println(); // Ajouter un saut de ligne après l'impression des voisins de chaque commutateur
            }
        }
    }
} 