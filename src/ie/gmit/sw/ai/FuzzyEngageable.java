package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class FuzzyEngageable {

	private double health;
	public double fight(double arsenalStrength, double playerHealth, double spiderStrength ){  	   
       
    	System.out.println(spiderStrength);
        String fileName = "fcl/game.fcl"; //Take from FCL File
        
        FIS fis = FIS.load(fileName,true);

        FunctionBlock fb = fis.getFunctionBlock("game");

        //Inputs being set
        fis.setVariable("player",spiderStrength ); //Applies value
        fis.setVariable("arsenal",arsenalStrength ); //Applies value
        fis.evaluate();
        
        health = fis.getVariable("health").getValue();
        System.out.println("Damage Taken: " + health);
        playerHealth -=  health;
        
        System.out.println("New Player Health: " + playerHealth);
		return playerHealth;
       } 
}
