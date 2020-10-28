package backend.receiver;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
   private List<Memento> mementoList = new ArrayList<Memento>();

   public void add(Memento state, int index){
	  System.out.println("--> " + index);
	  mementoList = mementoList.subList(0, index);
      mementoList.add(index, state);
   }

   public Memento get(int index){
	  System.out.println("Index : " + index + " --- " + mementoList.get(index).getState().getBufferContent());
      return mementoList.get(index);
   }
}