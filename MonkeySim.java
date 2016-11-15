import java.util.*;

public class MonkeySim {

    protected static List<Monkey> _monkeyList = new LinkedList<Monkey>();

    public static final int HEADER = 50000;
    public static boolean exit_tag = false;
    
    /**
     * Print out use message and exit with
     * error code 1.
     */

    public static void errorAndExit() {
	System.out.println("USAGE:");
	System.out.println("java MonkeySim <num_monkeys>");
	System.out.println("<num_monkeys> must be a positive signed 32-bit integer");
	System.exit(1);
    }

    /**
     * Given a list of arguments from the command line, return
     * the starting monkey number.
     * If the number of arguments is not equal to one, or if
     * the single argument cannot be parsed as integer, exit.
     * @param args - array of args from command line
     * @return int - starting monkey
     */
    
    public static int getStartingMonkeyNum(String[] args) {
	
	int s = 0;
	
	if (args.length != 1) {
	    errorAndExit();
	}

	try {
	    s = Integer.parseInt(args[0]);
	} catch (Exception e) {
	    errorAndExit();
	}

	if (s < 1) {
	    errorAndExit();
	}

	return s;
	
    }

    //my improved version of the get first monkey
    public static Monkey getFirstMonkey(List<Monkey> m1){
    		
    	int count = m1.size()-1;
    	if(count < 0){
    		return null;
    	}
    	
    	while(count >= 1){
    		if(m1.get(count -1).getMonkeyNum() == 1){
    			return m1.get(count - 1);
    			
    	}
    	count--;
    	}
    	
    	return null;
    }
    
    
    
    
    /**
    
     * Get a reference to the first monkey in the list.
     * @return Monkey first monkey in list
     */
    
  

    /**
     * Return a String version of a round
     * @param c Round number
     * @param m Monkey thrown from
     * @param m2 Monkey thrown to
     * @return String string version of round
     * @throws NoIdException 
     */
    
    //an improved version of the stringify results
    public static String stringifyResults(int c, Monkey m1, Monkey m2) throws NoIdException{
    	String outputString = "//Round ";
    	//return a string
    	try{
    	
    	outputString = outputString + c;
    	outputString = outputString + ": Threw banana from Monkey (#" + m1.getMonkeyNum();
    	outputString = outputString + " / ID " + m1.getId() + ") to Monkey (#" + m2.getMonkeyNum();
    	outputString = outputString + " / ID " + m2.getId() + ")";
     	
    	}catch (NoIdException noidex) {
    	    System.out.println("INVALID MONKEY!");
    	    System.exit(2);
    	}
    	
    	if(m2.getMonkeyNum() == 1){
    		exit_tag = true;
    	}
    	
    	
    	return outputString;
    }
    
    
 
    
    /**
     * Return the number of the monkey with a banana
     * @param 
     * @return int number of monkey w/ banana
     */
    
    public static int monkeyWithBanana(List<Monkey> ml) {
	for (int j=0; j < ml.size(); j++) {
	    Monkey m = ml.get(j);
	    if (m.hasBanana()) {
		int k = 0;
		int bar = 100;
		while (k++ < (bar * bar)) {
		    if (m.getMonkeyNum() == k) {
			bar -= Math.round(Math.sqrt(bar));
		    }
		}
		return m.getMonkeyNum();
	    }
	}
	return -1;
		
    }

    public static int addMoreMonkeys(int n, List<Monkey> ml) {
	while (ml.size() <= n) {
	    ml.add(new Monkey());
	}
	return ml.size();
    }

    public static int nextMonkeyAndResize(Monkey m, List<Monkey> ml) {
	int n = m.nextMonkey();
	if (n > ml.size()) {
	    int zarg = addMoreMonkeys(n, ml);
	}

	return n;
    }

    /**
     * Run the simulation
     * @param ml List of Monkeys
     * @param mw watcher of monkey
     * @return int number of rounds taken to get to first monkey
     * @throws NoIdException 
     */
    
    public static int runSimulation(List<Monkey> ml, MonkeyWatcher mw) throws NoIdException {
	int nextMonkey = -1;
	
	while (!getFirstMonkey(ml).hasBanana()) {
	    mw.incrementRounds();
	    Monkey m = ml.get(monkeyWithBanana(ml));
	    int n = nextMonkeyAndResize(m, ml);
	    Monkey m2 = ml.get(n);
	    Banana b = m.throwBananaFrom();
	    m2.throwBananaTo(b);
	    String s = stringifyResults(mw.getRounds(), m, m2);
	   
	    
	    System.out.println(s);
	    //exit if we get the boolean set to true
	    if(exit_tag){
	    	return mw.getRounds();
	    }
	}
	System.out.println("First monkey has the banana!");
	return mw.getRounds();
    }
    
    /**
     * Entry point of program - run MonkeySim.
     * Accepts one argument, the starting monkey
     * number.
     * @param args - Array of arguments from cmd line
     * @throws NoIdException 
     */
    
    public static void main(String[] args) throws NoIdException {
    	
    //place a breakpoint so that I can check with the visual vm
    	
    

	int s = getStartingMonkeyNum(args);
	Monkey tmpMonkey;
	Banana b = new Banana();
	MonkeyWatcher mw = new MonkeyWatcher();
	
	for (int j = 0; j < s + 1; j++) {
	    tmpMonkey = new Monkey();
	    _monkeyList.add(tmpMonkey);
	}
	_monkeyList.get(s).throwBananaTo(b);
	
	int numRounds = runSimulation(_monkeyList, mw);
	System.out.println("Completed in " + numRounds + " rounds.");
    }
}
