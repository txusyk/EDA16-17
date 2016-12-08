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

    /**
     * Reads in a social network from a file, and then repeatedly reads in
     * individuals from standard input and prints out their degrees of
     * separation.
     * Takes three command-line arguments: the name of a file,
     * a delimiter, and the name of the distinguished individual.
     * Each line in the file contains the name of a vertex, followed by a
     * list of the names of the vertices adjacent to that vertex,
     * separated by the delimiter.
     *
     * @param p1 the source film
     * @param p2 the target film
     */
    public void calculateDegrees(String p1, String p2) throws InterruptedException {
        SymbolGraph sg = new SymbolGraph();
        Graph G = sg.graph();
        if (!sg.contains(p1)) {
            System.out.println(p1 + " not in database.");
        } else {
            int s = sg.indexOf(p1);
            BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
            if (sg.contains(p2)) {
                int t = sg.indexOf(p2);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        System.out.println("   " + sg.nameOf(v));
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
