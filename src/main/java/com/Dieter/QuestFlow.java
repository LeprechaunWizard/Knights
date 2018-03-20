package com.Dieter;
import java.util.List;
import java.util.ArrayList;

public class QuestFlow {
	Player sponsor;
	List<Player> partisipants;
	private List<Integer> stagePoints;
	private int turn;
	private int sponsorshipTurn;
	private int stages;
	private int currentStage;
	
	
	public QuestFlow(QuestCard quest, int aTurn) {
		partisipants = new ArrayList<Player>();
		turn = 0;
		sponsorshipTurn = aTurn;
		stagePoints = new ArrayList<Integer>();
	}
	
	/********************************************************************************************
	 * 										SPONSORSHIP
	 * ******************************************************************************************/
	
	public void addPlayer(Player p) {
		partisipants.add(p);
	}
	
	public void setSponsor(Player aSponsor) {
		sponsor = aSponsor;
	}
	
	
	
	/********************************************************************************************
	 * 										QUEST
	 * ******************************************************************************************/
	
	public Player getTurn() {
		return partisipants.get(turn);
	}
	
	
}
