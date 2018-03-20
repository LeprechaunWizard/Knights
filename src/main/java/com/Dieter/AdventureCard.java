package com.Dieter;

public abstract class AdventureCard {

	protected String name;
	protected int battlePoints;
	
	protected AdventureCard(String aName, int someBattlePoints) {
		name = aName;
		battlePoints = someBattlePoints;
	}
	
	public String getName() { return name; }
	public int getBattlePoints() { return battlePoints; }
}
