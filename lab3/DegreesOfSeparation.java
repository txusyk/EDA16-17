package lab3;


public class DegreesOfSeparation {

    private static DegreesOfSeparation myDegrees;

    // this class cannot be instantiated
    private DegreesOfSeparation() {
    }

    public static DegreesOfSeparation getMyDegrees(){
        if (myDegrees == null){
            myDegrees = new DegreesOfSeparation();
        }
        return myDegrees;
    }

    public void calculateDegrees(String p1, String p2) throws InterruptedException {
        Graph G = SymbolGraph.getMySymbolGraph().graph();
        if (!SymbolGraph.getMySymbolGraph().contains(p1)) {
            System.out.println(p1 + " not in database.");
        } else {
            int s = SymbolGraph.getMySymbolGraph().indexOf(p1);
            BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
            if (SymbolGraph.getMySymbolGraph().contains(p2)) {
                int t = SymbolGraph.getMySymbolGraph().indexOf(p2);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        System.out.println("   " + SymbolGraph.getMySymbolGraph().nameOf(v));
                    }
                } else {
                    System.out.println("Not connected");
                }
            } else {
                System.out.println("   Not in database.");
            }
        }
    }
}
