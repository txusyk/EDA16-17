package lab1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Josu on 25/09/2016.
 */
public class ActorCatalog {

    private static ActorCatalog myActorCatalog;
    private HashMap<String,Actor> actorL;

    private ActorCatalog(){
        this.actorL = new HashMap<>();
    }

    public static ActorCatalog getmyActorCatalog(){
        if (myActorCatalog == null){
            myActorCatalog = new ActorCatalog();
        }
        return myActorCatalog;
    }

    public HashMap<String,Actor> getActorL(){
        return this.actorL;
    }

    private boolean exist(String pActorName, String pActorSurname){
        if (actorL.get(pActorName+" "+pActorSurname) != null){
            return true;
        }
        return false;
    }

    public void addActor(Actor pActor){
        if (!this.exist(pActor.getName(),pActor.getSurname())){
            this.actorL.put(pActor.getName()+" "+pActor.getSurname(),pActor);
            System.out.println("Actor added: "+pActor.getName()+" "+pActor.getSurname());
        }
        else{
            System.out.println("Actor already exists");
        }

    }
    
    public Actor searchActor(Actor pActor){
        if (this.exist(pActor.getName(),pActor.getSurname())){
            return this.actorL.get(pActor.getName()+" "+pActor.getSurname());
        }
        return null;
    }

    public void removeActor(Actor pActor){
        if (this.exist(pActor.getName(),pActor.getSurname())){
            this.actorL.remove(pActor.getName()+" "+pActor.getSurname());
            System.out.println("Actor erased");
        }else{
            System.out.println("Actor not found");
        }
    }

    public String[] quickSortList(){
        String[] auxS = new String[actorL.size()];
        int i=0;
        for ( String key : actorL.keySet() ) {
            auxS[i] = key;
            i++;
        }
        StringQuickSort.sort(auxS);
        System.out.println("\n-------------------------------------------------------------");
        this.printList(auxS);
        return auxS;
    }

    public void printList(String[] auxS){
        for (String key : auxS){
            System.out.println(key);
        }
    }


}
