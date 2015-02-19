package iteration2.testCases;

import static org.junit.Assert.*;
import iteration2.CardManager;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vendingmachinesareus.Card;
import com.vendingmachinesareus.CardSlot;

public class TestCardManager {

	@Before
	public void SetUp(){
		cardManager = new CardManager();
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void testCardEjected()
	{
		
	}
	
	@Test
	public void testcardInserted()
	{
		Mockery mockingContext = new Mockery();
		final Card card = mockingContext.mock(Card.class);
		mockingContext.checking(new Expectations() {
			{
			one(card).readCardData();
			will(returnValue(2));
			}
		final CardSlot cardSlot = mockingContext.mock(CardSlot.class);
		mockingContext.checking(new Expectations() {
			{
			one(cardSlot).readCardData();
			will(returnValue(2));
			}
			cardManager.cardInserted(arg0);
	}
	
	
	private CardManager cardManager;
}
