package com.Dieter;

public class QuestCard extends StoryCard{
	private int numStages;
	private String questFoe;
	
	public QuestCard(String aName, int aNumStages, String aFoe) {
		super(aName);
		
		numStages = aNumStages;
		questFoe = aFoe;
	}
	
	public String getName() { return name; }
	public int getNumStages() { return numStages; }
	public String getQuestFoe() { return questFoe; }
}
