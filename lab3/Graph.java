package lab3;

import java.util.*;

public class Graph {
	HashMap<String, Integer> th;
      String[] keys;
      ArrayList<Integer>[] adjList;
	
	public void crearGrafo(FilmList lPeliculas){
		// Post: crea el grafo desde la lista de pel�culas
		//       Los nodos son nombres de actores y t�tulos de pel�culas

		ArrayList<Film> filmA = new ArrayList<>(lPeliculas.getFilmL().values());
		Iterator <Film> itrF = filmA.iterator();
		Film auxF = null;

		// Paso 1: llenar th
            // COMPLETAR C�DIGO
		while(itrF.hasNext()){
			auxF = itrF.next();

		}

            // Paso 2: llenar keys�
		keys = new String[th.size()];
		for (String k: th.keySet()) keys[th.get(k)] = k;

            // Paso 3: llenar adjList�
            // COMPLETAR C�DIGO
	}
	
	public void print(){
	   for (int i = 0; i < adjList.length; i++){
		System.out.print("Element: " + i + " " + keys[i] + " --> ");
		for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
		
		System.out.println();
	   }
	}
	
	public boolean estanConectadas(String p1, String p2){
		Queue<Integer> porExaminar = new LinkedList<Integer>();
		
		int pos1 = th.get(p1);
		int pos2 = th.get(p2);
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];

                 // COMPLETAR C�DIGO
		
		return enc;

	}
}
