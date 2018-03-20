package com.Dieter;

import java.util.List;
import java.util.ArrayList;


/* The main game logic class all other parts of logic are 
 * components of this class
 * */
public class Game {
	private List<AdventureCard> adventureCards;
	private List<StoryCard> storyCards;
	
	private Deck<StoryCard> storyDeck;
	private Deck<StoryCard> discardStoryDeck;
	private Deck<AdventureCard> adventureDeck;
	private Deck<AdventureCard> discardAdventureDeck;
	private List<Player> players;
	private Player sponsor;
	private int sponsorTurn;
	private int playerTurn;
	private StoryCard currentStory;
	private QuestFlow questStatus;
	
	public Game(int numOfPlayers) {
		playerTurn = 0;
		currentStory = null;
		questStatus = null;
		adventureCards = new ArrayList<AdventureCard>();
		storyCards = new ArrayList<StoryCard>();
		
		initCards();
		
		players = new ArrayList<Player>();
		storyDeck = new Deck<StoryCard>(storyCards);
		discardStoryDeck = new Deck<StoryCard>();
		adventureDeck = new Deck<AdventureCard>(adventureCards);
		discardAdventureDeck = new Deck<AdventureCard>();
		
		for(int i = 0; i < numOfPlayers; i++) {
			players.add(new Player("Player " + (i+1)));
			drawAdventureCards(i, 12);
		}
		
	}
	
	/****************************************************************************
 * 									DRAWING CARDS
	 ************************************************************************** */
	
	// TODO fix possible bug when run out of cards to draw(add reshiffle feture)
	public void drawAdventureCards(int pt) {
		List<AdventureCard> draw = new ArrayList<AdventureCard>();
		draw.add(adventureDeck.drawCard());
		players.get(pt).drawAdventureCard(draw);
	}
	
	public void drawAdventureCards(int pt, int num) {
		List<AdventureCard> draw = new ArrayList<AdventureCard>();
		
		for(int i = 0; i < num; i++) {
			draw.add(adventureDeck.drawCard());
		}
		players.get(pt).drawAdventureCard(draw);
	}
	
	//Drawing and discarding story card
	public void drawStoryCard() {
		System.out.println("Draw story card");
		currentStory = storyDeck.drawCard();
	}
	
	public void discardStoryCard() {
		discardStoryDeck.addCard(currentStory);
		currentStory = null;
	}
	
	/********************************************************************************************
	 * 										GET METHODS
	 * ******************************************************************************************/
	
	public List<String> getPlayerHand() {
		return players.get(playerTurn).getHandNames();
	}
	
	public Player getCurrentPlayer() {
		return players.get(playerTurn);
	}
	
	public int getCurrentPlayerRank() {
		return players.get(playerTurn).getRank();
	}
	
	public int getCurrentPlayerShields() {
		return players.get(playerTurn).getShields();
	}
	
	public StoryCard getCurrentStory() {
		return currentStory;
	}
	
	/********************************************************************************************
	 * 										CONTROL FLOW
	 * ******************************************************************************************/
	
	public void incrementTurn() {
		int lastPlayer = players.size() - 1;
		
		if(playerTurn == lastPlayer) {
			playerTurn = 0;
			return;
		}
		
		playerTurn++;
	}
	
	public void startFlow() {
		if(currentStory instanceof QuestCard) {
			QuestCard quest = (QuestCard)currentStory;
			questStatus = new QuestFlow(quest, playerTurn);
		}
	}
	
	public void sponsoring() {
		sponsorTurn = playerTurn;
	}
	
	public void setSponsor() {
		questStatus.setSponsor(players.get(sponsorTurn));
	}
	
	public void addPlayerToQuest() {
		questStatus.addPlayer(players.get(playerTurn));
	}
	
	/********************************************************************************************
	 * 										DEMO SCENARIOS
	 * ******************************************************************************************/
	
	// Basic Quest
	public static Game scenario1() {
		Game sc1 = new Game(2);
		
		return sc1;
	}
	
	
	/**********************************************************************************************
	 * 										CREATING CARDS
	 * ********************************************************************************************/
	private void initCards() {
		// Weapons
		for (int i = 0; i < 2; i++) { adventureCards.add(new Weapon("W Excalibur", 30)); }
        for (int i = 0; i < 6; i++) { adventureCards.add(new Weapon("W Lance", 20)); }
        for (int i = 0; i < 8; i++) { adventureCards.add(new Weapon("W Battle-ax", 15)); }
        for (int i = 0; i < 16; i++) { adventureCards.add(new Weapon("W Sword", 10)); }
        for (int i = 0; i < 11; i++) { adventureCards.add(new Weapon("W Horse", 10)); }
        for (int i = 0; i < 6; i++) { adventureCards.add(new Weapon("W Dagger", 5)); }
        
		// Foes
		adventureCards.add(new Foe("F Dragon", 50, 70, "Slay the Dragon"));
        for (int i = 0; i < 2; i++) { adventureCards.add(new Foe("F Giant", 40, 0, null)); }
        for (int i = 0; i < 4; i++) { adventureCards.add(new Foe("F Mordred", 30, 0, null)); }
        for (int i = 0; i < 2; i++) { adventureCards.add(new Foe("F Green Knight", 25, 40, "Test of the Green Knight")); }
        for (int i = 0; i < 3; i++) { adventureCards.add(new Foe("F Black Knight", 25, 35, "Rescue the Fair Maiden")); }
        for (int i = 0; i < 6; i++) { adventureCards.add(new Foe("F Evil Knight", 20, 30, "Journey through the Enchanted Forest")); }
        for (int i = 0; i < 8; i++) { adventureCards.add(new Foe("F Saxon Knight", 15, 25, "Repel the Saxon Raiders")); }
        for (int i = 0; i < 7; i++) { adventureCards.add(new Foe("F Robber Knight", 15, 0, null)); }
        for (int i = 0; i < 5; i++) { adventureCards.add(new Foe("F Saxons", 10, 20, "Repel the Saxon Raiders")); }
        for (int i = 0; i < 4; i++) { adventureCards.add(new Foe("F Boar", 5, 15, "Boar Hunt")); }     
        for (int i = 0; i < 8; i++) { adventureCards.add(new Foe("F Thieves", 5, 0, null)); }
     
		// TODO tests
       
		// TODO Amour
		
		//TODO Ally
		
		// Quests
        storyCards.add(new QuestCard("Journey through the Enchanted Forest", 3, "Evil Knight"));
        storyCards.add(new QuestCard("Search for the Questing Beast", 4, "none"));
        for (int i = 0; i < 2; i++) storyCards.add(new QuestCard("Vanquish King Arthur's Enemies", 3, "none"));
        storyCards.add(new QuestCard("Defend the Queen's Honor", 4, "all"));
        for (int i = 0; i < 2; i++) storyCards.add(new QuestCard("Repel the Saxon Raiders", 2, "Saxons"));
        storyCards.add(new QuestCard("Slay the Dragon", 3, "Dragon"));
        for (int i = 0; i < 2; i++) storyCards.add(new QuestCard("Boar Hunt", 2, "Boar"));
        storyCards.add(new QuestCard("Rescue the Fair Maiden", 3, "Black Knight"));
        storyCards.add(new QuestCard("Search for the Holy Grail", 5, "all"));
        storyCards.add(new QuestCard("Test of the Green Knight", 4, "Green Knight"));
		
		// TODO Tournament
		
		// TODO Events
	}
}
