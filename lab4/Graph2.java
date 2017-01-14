package lab3;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph2 {
    private HashMap<String, Integer> th;
    private String[] keys;
    private ArrayList<Integer>[] adjList;

    private final int V; //number of vertices
    private int E; //number of edges

    public Graph2(int V) {
        this.V = V;
        this.E = 0;
        this.th = new HashMap<>();
    }

    public void createGraph(FilmList filmL) {
        // Post: crea el grafo desde la lista de pel�culas
        //       Los nodos son nombres de actores y t�tulos de pel�culas

        // Paso 1: llenar th
        long i = 0L;
        for (String f : filmL.getFilmL().keySet()) {
            if (!th.containsKey(f))
                th.put(f, (int) i);
            i++;
            for (String a : filmL.getFilm(f).getActorList().getActorL().keySet()) {
                if (!th.containsKey(a)) {
                    th.put(a, (int) i);
                    i++;
                }
            }
        }

        // Paso 2: llenar keys�
        keys = new String[th.size()];
        for (String k : th.keySet()) {
            keys[th.get(k)] = k;
        }
        // Paso 3: llenar adjList�
        adjList = (ArrayList<Integer>[]) new ArrayList[keys.length];
        int j = 0;
        for (String name : keys) {
            if (ActorCatalog.getmyActorCatalog().getActorL().get(name) != null) {
                ArrayList<Integer> auxFilms = new ArrayList<>();
                for (String film : ActorCatalog.getmyActorCatalog().getActorL().get(name).getFilmList().getFilmL().keySet()) {
                    if (th.get(film) != null) {
                        auxFilms.add(th.get(film));
                    }
                }
                adjList[j] = auxFilms;
                j++;
            } else if (FilmCatalog.getMyFilmCatalog().getFilmL().get(name) != null) {
                ArrayList<Integer> auxActors = new ArrayList<>();
                for (String actor : FilmCatalog.getMyFilmCatalog().getFilm(name).getActorList().getActorL().keySet()) {
                    if (th.get(actor) != null) {
                        auxActors.add(th.get(actor));
                    }
                }
                adjList[j] = auxActors;
                j++;
            }
        }
        print();
    }

    public void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print("Element: " + i + " " + keys[i] + " --> ");
            for (int k : adjList[i]) {
                System.out.print(keys[k] + " ### ");
            }

            System.out.println();
        }
    }

    public boolean areConnected(String p1, String p2) {
        int firstF = 0;
        int lastF = 0;

        boolean enc = false;

        if (th.get(p1) != null && th.get(p2) != null) {
            firstF = th.get(p1);
            lastF = th.get(p2);
        } else {
            if (th.get(p1) == null) {
                System.err.println("'"+p1 + "' it's not a film contained in the previously generated film list");
            } else {
                System.err.println("'" + p2 + "' it's not a film contained in the previously generated film list");
            }
        }

        return enc;

    }

}
