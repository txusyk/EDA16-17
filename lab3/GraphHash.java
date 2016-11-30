package lab3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class GraphHash {

	private HashMap<String, ArrayList<String>> g;
	private static GraphHash myGraphHash;

	private GraphHash(){
		g = new HashMap<>();
	}

	public static GraphHash getMiGraphHash(){
		if(myGraphHash == null){
			myGraphHash = new GraphHash();
		}
		return myGraphHash;
	}
	
	public void crearGrafo(FilmList lPeliculas) {
		// Post: crea el grafo desde la lista de pel�culas
		//       Los nodos son nombres de actores y t�tulos de pel�culas 

		// COMPLETAR C�DIGO

		Film auxF = null;
		Actor auxA = null;
		int i = 0;
		Collection<Actor> auxActors = new ArrayList<>();
		Iterator<Actor> itrA = null;
		ArrayList<Film> auxFilms = new ArrayList<>(lPeliculas.getFilmL().values());
		Iterator<Film> itrF = auxFilms.iterator();

		while(itrF.hasNext()){
			auxF = itrF.next();
			itrA = auxF.getActorList().getActorL().values().iterator();
			while(itrA.hasNext()){
				auxA = itrA.next();
				if(!auxActors.contains(auxA)){
					auxActors.add(auxA);
				}
			}
		}

		itrA = auxActors.iterator();
		while(itrA.hasNext()) {
			auxA = itrA.next();
			if (!g.containsKey(auxA.getName())) {
				g.put(auxA.getName(), auxA.getFilmList().getFilmArray());
			}
		}
	}

	public void print(){
		int i = 1;
		for (String s: g.keySet()){
			System.out.print("Element: " + i++ + " " + s + " --> ");
			for (String k: g.get(s)){
				System.out.print(k + " ### ");
			}
			System.out.println();
		}
	}
	
	public boolean estanConectadas(String p1, String p2) {
		// COMPLETAR C�DIGO
		return true;
	}
}
