package iteration2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ChangeMaker {
	static public boolean makeChange(int amountOfChange, int coinValues[], Map<Integer, CoinRack> coinRackMap){
		//Sort coin values
		ArrayList <Integer> sortedSet = new ArrayList <Integer>();
		for(int i = 0; i < coinValues.length; i++){
			sortedSet.add(coinValues[i]);
		}
		Collections.sort(sortedSet);
		
		//Coin Racks are sorted by value so should line up with the sortedSet
		int numberNeeded[] = new int[sortedSet.size()];
		for(int i = sortedSet.size()-1; i >=0; i--){
			if(amountOfChange/sortedSet.get(i)< coinRackMap.get(sortedSet.get(i)).getAmount()){
				numberNeeded[i] = amountOfChange/sortedSet.get(i).intValue();
				amountOfChange= amountOfChange%sortedSet.get(i);
			}else{
				numberNeeded[i] = coinRackMap.get(sortedSet.get(i)).getAmount();
				amountOfChange = amountOfChange - coinRackMap.get(sortedSet.get(i)).getAmount()*sortedSet.get(i);
			}
		}
		
		//If amountOfChange is not equal to zero at this point change cannot be given
		if(amountOfChange != 0){
			return false;
		}
		
		//Release change
		for(int i = sortedSet.size()-1; i>=0; i--){
			for(int numberToRelease = 0; numberToRelease <numberNeeded[i]; numberToRelease++){
			try {
				coinRackMap.get(sortedSet.get(i)).releaseCoin();
				} catch (DisabledException e) {
					// No Requirement on how to handle this
				} catch (EmptyException e) {
					//Should never get here but if we do then notifyExactChange even though we dispensed some coins potentiall?
					return false;
				} catch (CapacityExceededException e) {
					try {
						return false;
					} catch (CapacityExceededException e1) {
						// If it gets here we are screwed
						e1.printStackTrace();
					} catch (DisabledException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return true;
	
	}
}
