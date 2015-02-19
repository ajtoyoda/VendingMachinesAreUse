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
import com.vendingmachinesareus.EmptyException;

public class TestCardManager {

	@Before
	public void SetUp(){
		cardManager = new CardManager();
	}
	
	@After
	public void tearDown(){
		
	}
	
	
	
	@Test
	public void testcardInserted()
	{
		Mockery mockingContext = new Mockery();
		final Card card = mockingContext.mock(Card.class);
		mockingContext.checking(new Expectations() {
			{
			one(card).getType();
			will(returnValue(0));
			one(card).getNumber();
			will(returnValue("123456789"));
			one(card).getName();
			will(returnValue("John Smith"));
			}}
		);
		final CardSlot cardSlot = mockingContext.mock(CardSlot.class);
		try {
			mockingContext.checking(new Expectations() {
				{
				one(cardSlot).readCardData();
				will(returnValue(card));
				}}
			);
		} catch (EmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			cardManager.cardInserted(cardSlot);
			assertTrue(cardManager.hasCard());
	}
	
	@Test
	public void testCardEjected()
	{
		Mockery mockingContext = new Mockery();
		final Card card = mockingContext.mock(Card.class);
		mockingContext.checking(new Expectations() {
			{
			one(card).getType();
			will(returnValue(0));
			one(card).getNumber();
			will(returnValue("123456789"));
			one(card).getName();
			will(returnValue("John Smith"));
			}}
		);
		final CardSlot cardSlot = mockingContext.mock(CardSlot.class);
		try {
			mockingContext.checking(new Expectations() {
				{
				one(cardSlot).readCardData();
				will(returnValue(card));
				}}
			);
		} catch (EmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			cardManager.cardInserted(cardSlot);
			cardManager.cardEjected(cardSlot);
			assertFalse(cardManager.hasCard());
	}
	
	@Test
	public void testHasCard()
	{

		Mockery mockingContext = new Mockery();
		final Card card = mockingContext.mock(Card.class);
		mockingContext.checking(new Expectations() {
			{
			one(card).getType();
			will(returnValue(0));
			one(card).getNumber();
			will(returnValue("123456789"));
			one(card).getName();
			will(returnValue("John Smith"));
			}}
		);
		final CardSlot cardSlot = mockingContext.mock(CardSlot.class);
		try {
			mockingContext.checking(new Expectations() {
				{
				one(cardSlot).readCardData();
				will(returnValue(card));
				}}
			);
		} catch (EmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			assertFalse(cardManager.hasCard());
			cardManager.cardInserted(cardSlot);
			assertTrue(cardManager.hasCard());
			cardManager.cardEjected(cardSlot);
			assertFalse(cardManager.hasCard());
	}
	
	private CardManager cardManager;
}
