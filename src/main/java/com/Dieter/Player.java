package com.Dieter;
import java.util.List;
import java.util.ArrayList;


public class Player {
	private static final int  MAXHANDSIZE = 12;
	private String name;
	private int rank; // 1=squire 2=knight 3=knightCommander
	private int shields;
	private int battlePoints;
	private int baseBattlePoints;
	private boolean participatingInQuest;
	private boolean sponsoringQuest;

	private List<AdventureCard> hand;
	private List<Weapon> equipment;
	private List<Ally> allies;
	private List<Amour> amour;
  
	public Player(String aName) {
		name = aName;
		rank = 1;
		shields = 0;
		baseBattlePoints = 5;
		hand = new ArrayList<AdventureCard> (MAXHANDSIZE);
		equipment = new ArrayList<Weapon>();
		allies = new ArrayList<Ally>();
		amour = new ArrayList<Amour>();
	}

	public void rankup(){
		switch (rank) {
		case 1:
			shields -= 5;
			baseBattlePoints = 10;
			rank = 2;
			break;
		case 2:
			shields -= 7;
			baseBattlePoints = 20;
			rank = 3;
			break;
		case 3:
			rank = 4;
			shields -= 10;
			break;
		}
	}

	public int equip() {
	
		return 0;
	}

	/*
	handels drawing a story card
	perameters: accepts story card
	return: 1 if accepts quest 0 if not accept quest
	*/

	public void drawAdventureCard(List<AdventureCard> cards) {
		hand.addAll(cards);
	}


	// Get and set methods
	public int getRank() { return rank; }
	public int getShields() { return shields; }
    public int getBaseBattlePoints() { return baseBattlePoints; }
	public int getBattlePoints() { return battlePoints; }
	public void addShields(int winnings) { shields += winnings; }
    public List<AdventureCard> getHand() { return hand; }
    public List<String> getHandNames() {
    	List<String> playerHand = new ArrayList<String>();
    	
    	for(AdventureCard ac : hand) {
    		playerHand.add(ac.getName());
    	}
    	
    	return playerHand;
    }

}
