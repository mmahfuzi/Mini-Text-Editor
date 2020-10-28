package backend;

import java.util.ArrayList;
import java.util.List;
/**
 * Class used as care taker for memento design pattern
 * @author mukrram
 *
 */
public class CareTaker {
   private List<Memento> mementoList;                 // Collection to store memenyos
   
   public CareTaker() {
	   mementoList = new ArrayList<Memento>();
   }

   public void add(Memento state, int index){         // Method to add Memento to collection
	  mementoList = mementoList.subList(0, index);
      mementoList.add(index, state);
   }

   public Memento get(int index){                    // Method to get Memento from collection
      return mementoList.get(index);
   }
}