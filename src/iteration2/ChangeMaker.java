package iteration2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.vendingmachinesareus.*;

public class ChangeMaker {
<<<<<<< Updated upstream
	static public boolean makeChange(int amountOfChange, Map<Integer, CoinRack> coinRackMap, CoinInventory inventory){
		//Sort coin values
		int coinValues[] = inventory.getCoinValues();
		ArrayList <Integer> sortedSet = new ArrayList <Integer>();
		for(int i = 0; i < coinValues.length; i++){
=======
	static public boolean makeChange(int amountOfChange, int coinValues[],
			Map<Integer, CoinRack> coinRackMap) {
		// Sort coin values
		ArrayList<Integer> sortedSet = new ArrayList<Integer>();
		for (int i = 0; i < coinValues.length; i++) {
>>>>>>> Stashed changes
			sortedSet.add(coinValues[i]);
		}
		Collections.sort(sortedSet);

		// Coin Racks are sorted by value so should line up with the sortedSet
		int numberNeeded[] = new int[sortedSet.size()];
<<<<<<< Updated upstream
		for(int i = sortedSet.size()-1; i >=0; i--){
			if(amountOfChange/sortedSet.get(i)< inventory.getNumberOfCoinsInRack(sortedSet.get(i))){
				numberNeeded[i] = amountOfChange/sortedSet.get(i).intValue();
				amountOfChange= amountOfChange%sortedSet.get(i);
			}else{
				numberNeeded[i] = inventory.getNumberOfCoinsInRack(sortedSet.get(i));
				amountOfChange = amountOfChange - inventory.getNumberOfCoinsInRack(sortedSet.get(i))*sortedSet.get(i);
=======
		for (int i = sortedSet.size() - 1; i >= 0; i--) {
			if (amountOfChange / sortedSet.get(i) < coinRackMap.get(
					sortedSet.get(i)).getAmount()) {
				numberNeeded[i] = amountOfChange / sortedSet.get(i).intValue();
				amountOfChange = amountOfChange % sortedSet.get(i);
			} else {
				numberNeeded[i] = coinRackMap.get(sortedSet.get(i)).getAmount();
				amountOfChange = amountOfChange
						- coinRackMap.get(sortedSet.get(i)).getAmount()
						* sortedSet.get(i);
>>>>>>> Stashed changes
			}
		}

		// If amountOfChange is not equal to zero at this point change cannot be
		// given
		if (amountOfChange != 0) {
			return false;
		}

		// Release change
		for (int i = sortedSet.size() - 1; i >= 0; i--) {
			for (int numberToRelease = 0; numberToRelease < numberNeeded[i]; numberToRelease++) {
				try {
					coinRackMap.get(sortedSet.get(i)).releaseCoin();
				} catch (DisabledException e) {
					// No Requirement on how to handle this
				} catch (EmptyException e) {
					// Should never get here but if we do then notifyExactChange
					// even though we dispensed some coins potentiall?
					return false;
				} catch (CapacityExceededException e) {
					return false;
				}
			}
		}
		return true;

	}
}
