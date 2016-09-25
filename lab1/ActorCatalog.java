package lab1;

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

    private boolean exist(String pActorName){
        if (actorL.get(pActorName) != null){
            return true;
        }
        return false;
    }

    public void addActor(Actor pActor){
        if (!this.exist(pActor.getName())){
            this.actorL.put(pActor.getName(),pActor);
        }
    }
    
    public Actor searchActor(String pName, String pSurname){
        String actorName = pName+" "+pSurname;
        if (this.exist(actorName)){
            return this.actorL.get(actorName);
        }
        return null;
    }

}
