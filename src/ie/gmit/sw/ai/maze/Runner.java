package ie.gmit.sw.ai.maze;
/*package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Runner {
	
	public static void main(String[] args) {
		String fileName = "fcl/funding.fcl";
		FIS fis = FIS.load(fileName, true);
		
		FunctionBlock fb  = fis.getFunctionBlock("Project");
		JFuzzyChart.get().chart(fb);
		
		fis.setVariable("funding", 38);
		fis.setVariable("staffing", 9);
		fis.evaluate();
		
        Variable risk = fb.getVariable("risk");
        JFuzzyChart.get().chart(risk, risk.getDefuzzifier(), true);

        System.out.println("Risk: " + fis.getVariable("risk").getValue() + "%"); //Output end result
        JFuzzyChart.get().chart(risk, true);
	}

}*/