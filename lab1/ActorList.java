package lab1;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by david on 25/09/2016.
 */
public class ActorList {

    private ArrayList<Actor> actorList;


    public ActorList(){

    }

    private Iterator<Actor> getIterator(){
        return this.actorList.iterator();
    }

    public boolean exist(String actorName){
        int i=0;
        Iterator<Actor> itr = this.getIterator();
        Actor auxActor;
        boolean flag=false;

        while(itr.hasNext() && !flag){
            auxActor=itr.next();
            if(auxActor.getName() == actorName){
                flag = true;
            }

        }

        return flag;
    }

    public void addActor(Actor auxActor){
        if (!this.exist(auxActor.getName())){
            this.actorList.add(auxActor);
        }
    }

    public Actor getActor(String actorName){
        Iterator<Actor> itr = this.getIterator();
        Actor auxAct=null;
        boolean flag = false;

        while(itr.hasNext() && !flag){
            auxAct = itr.next();
            if(auxAct.getName() == actorName){
                flag = true;
            }
        }
        if(!flag){
            auxAct=null;
        }
        return auxAct;
    }

    public void removeActor(Actor actor){
        this.actorList.remove(actor);
    }


}
