package com.Dieter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck<T> {
	private List<T> cards;
	
	public Deck() {
		cards = new ArrayList<T>();
	}
	
	public Deck(List<T> someCards) {
		cards = new ArrayList<T>(someCards);
		shuffle();
	}
	
	public T drawCard() {
		if(cards.isEmpty()) { return null; }
		
		T card = cards.get(cards.size()-1);
		cards.remove(cards.size()-1);
		return card;
	}
	
	public void addCard(T card) {
		cards.add(card);
		System.out.println("added card");
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
}
