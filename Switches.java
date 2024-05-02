import java.util.ArrayList;

public class Switches {
    int Num;
    String state;
    boolean fixed;
    ArrayList<Switches> neighbors;

    Switches(int num, String state) {
        this.Num = num;
        this.state = state;
        this.fixed = false;
        this.neighbors = new ArrayList<>();
    }


    public String getId() {
        return this.Num + "-" + this.state;
    }

    public int getNum() {
        return this.Num;
    }
    
    public String getOpposite() {
        if (this.state.equals("on")) {
            return this.Num + "-off";
        } else {
            return this.Num + "-on";
        }
    }

    public String getState() {
        return this.state;
    }
    
    public boolean getFixed() {
        return this.fixed;
    }
    
    public ArrayList<Switches> getNeighbors() {
        return this.neighbors;
    }
    
    public void setFixed() {
        this.fixed = true;
    }

    public void addEdge(Switches N) {
        neighbors.add(N);
    }

    public void clear() {
        neighbors.clear();
    }
}

