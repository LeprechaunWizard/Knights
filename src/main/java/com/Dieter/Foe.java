package com.Dieter;
import java.util.List;
import java.util.ArrayList;

public class Foe extends AdventureCard{
	
	int enhancedBattlePoints;
	String linkedQuest;
	List<Weapon> equipment;

	public Foe(String name, int bp, int ebp, String aLinkedQuest) {
		super(name, bp);
		enhancedBattlePoints = ebp;
		linkedQuest = aLinkedQuest;
		equipment = new ArrayList<Weapon>();
	}
	
	public int equip(Weapon weaponToEquip) {
		// return 1 if weapon already equip 
		for(Weapon w : equipment) {
			if(w.getName().compareTo(weaponToEquip.getName()) == 0) { return 1; }
		}
		
		equipment.add(weaponToEquip);
		return 0;
	}
	
	private boolean questBonus(QuestCard quest) {
		// aid function for calculating foe strength
		return (linkedQuest.compareTo(quest.getName()) == 0) ? true : false;
	}
	
	public int calqulateFoeStrength(QuestCard quest) {
		// returns for strength of the foe
		int foeStrength = (questBonus(quest)) ? enhancedBattlePoints : getBattlePoints();
				
		for(Weapon w : equipment) {
			foeStrength += w.getBattlePoints();
		}
		
		return foeStrength;
	}
	
	
	
	
	
	
}
