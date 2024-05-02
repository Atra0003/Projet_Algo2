import java.util.ArrayList;

public class Graph {
    private Switches[][] switches; // Déclarez switches en tant que champ de classe
    private Switches[][] tGraph; 
    int size;
    Switches switche_L_on;
    Switches switche_L_off;
    Switches switche_C_on;
    Switches switche_C_off;

    Switches tGraph_L_on;
    Switches tGraph_L_off;
    Switches tGraph_C_on;
    Switches tGraph_C_off;

    public Graph(int size) {
        switches = new Switches[2 * size][]; // Initialisez le tableau de switches dans le constructeur
        tGraph = new Switches[2 * size][];
        for (int i = 0; i < 2 * size; i++) {
            
            switches[i] = new Switches[2]; // Initialisez chaque élément comme un tableau de 2 éléments
            tGraph[i] = new Switches[2];

            switches[i][0] = new Switches(i, "on");
            switches[i][1] = new Switches(i, "off");

            tGraph[i][0] = new Switches(i, "on");
            tGraph[i][1] = new Switches(i, "off");
        }
        this.size = size;
    }

    public Switches getSwitch(Switches[][] g, int i, String state) {
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

    public Switches[][] getAllSwitche() {
        return this.switches;
    }

    public Switches[][] getTGraph() {
        return this.tGraph;
    }
    
    public void addLink(String data) {
        //System.out.println(data);
        String l, c, x, y, z, t;
        String[] arrOfStr = data.split(" ");
        l = arrOfStr[0];
        c = arrOfStr[1];
        x = arrOfStr[2];
        y = arrOfStr[3];
        z = arrOfStr[4];
        t = arrOfStr[5];
        // identifier les noueds
        switche_L_on = getSwitch(switches, Integer.valueOf(l) - 1, "on");
        switche_L_off = getSwitch(switches, Integer.valueOf(l) - 1, "off");
        switche_C_on = getSwitch(switches, size + Integer.valueOf(c) - 1, "on");
        switche_C_off = getSwitch(switches, size + Integer.valueOf(c) - 1, "off");

        tGraph_L_on = getSwitch(tGraph, Integer.valueOf(l) - 1, "on");
        tGraph_L_off = getSwitch(tGraph, Integer.valueOf(l) - 1, "off");
        tGraph_C_on = getSwitch(tGraph, size + Integer.valueOf(c) - 1, "on");
        tGraph_C_off = getSwitch(tGraph, size + Integer.valueOf(c) - 1, "off");

        // mettre les liens
        if(x.equals("0")) {
            switche_L_on.addEdge(switche_C_off);
            switche_C_on.addEdge(switche_L_off);
            tGraph_C_off.addEdge(tGraph_L_on);
            tGraph_L_off.addEdge(tGraph_C_on);
        }
        if(y.equals("0")) {
        	switche_L_on.addEdge(switche_C_on);
        	switche_C_off.addEdge(switche_L_off);
            tGraph_C_on.addEdge(tGraph_L_on);
            tGraph_L_off.addEdge(tGraph_C_off);
        }
        if(z.equals("0")) {
        	switche_L_off.addEdge(switche_C_off);
        	switche_C_on.addEdge(switche_L_on);

            tGraph_C_off.addEdge(tGraph_L_off);
            tGraph_L_on.addEdge(tGraph_C_on);
            
        }
        if(t.equals("0")) {
            switche_L_off.addEdge(switche_C_on);
            switche_C_off.addEdge(switche_L_on);
            tGraph_C_on.addEdge(tGraph_L_off); 
            tGraph_L_on.addEdge(tGraph_C_off);
        }
    }

    public void clear() {
        for (int i = 0; i < switches.length; i++) {
            for (int j = 0; j < switches[i].length; j++) {
                switches[i][j].clear();
            }
        }
    }

    public void seeNeighbors(Switches[][] g) {
        for(int i = 0; i < g.length; i++){
            for(int j = 0; j < g[i].length; j++){
                System.out.print(g[i][j].getId() + ": ");
                ArrayList<Switches> neighbors = g[i][j].getNeighbors();
                for(int k = 0; k < neighbors.size(); k++){
                    System.out.print(neighbors.get(k).getId() + " ");
                }
                System.out.println(); // Ajouter un saut de ligne après l'impression des voisins de chaque commutateur
            }
        }
    }
}

