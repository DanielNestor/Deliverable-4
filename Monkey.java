import java.util.*;

public class Monkey {

    private static int _monkeyNum = 0;

    private int _thisMonkeyNum = 0;
    
    private int _id = -1;

    private Banana _b = null;

    /**
     * Get this monkey's number
     * @return int monkey number
     */
    
    public int getMonkeyNum() {
	return _thisMonkeyNum;
    }
    
    /**
     * Getter for id.
     * @return id of monkey
     */
    
    public int getId() throws NoIdException {
	if (_id < 0) {
	    throw new NoIdException();
	} else {
	    return _id;
	}
    }

    /**
     * Return which monkey should get banana next.
     * @return int which monkey should get banana.
     */

    public int nextMonkey() {
	if (_thisMonkeyNum % 2 == 0) {
	    return _thisMonkeyNum / 2;
	} else {
	    return (_thisMonkeyNum * 3) + 1;
	}
    }

    /**
     * Checks to see if this monkey has a banana
     * @return true if has banana, false otherwise
     */
    
    public boolean hasBanana() {
	return _b != null;
    }

    /**
     * Receive a banana from another monkey
     * @param b - Banana given to this monkey
     */
    
    public void throwBananaTo(Banana b) {
	_b = b;
    }

    /**
     * 
     * @return Banana - the banana this monkey held
     */
    
    public Banana throwBananaFrom() {
	Banana toReturn = _b;
	_b = null;
	return toReturn;
    }
    
    //an improved version of the generate id function
    public int generateId(int n){
    	int x = 223500 + n - 8;
    	return x;
    }
    
    //for testing purposes let us set thr num
    public void setNum(int x){
    	_thisMonkeyNum = x;
    }
    
    
    /**
     * Generate a unique ID for this monkey.
     * Note that monkey ID generation must 
     * always return the correct value for
     * a given n (i.e., the id for the first
     * monkey should always be the same).
     * @param int n - monkey number
     * @return int - id for this monkey
     */
    
  

    /**
     * Monkey constructor
     */
    
    public Monkey() {
	_thisMonkeyNum = _monkeyNum;
	_monkeyNum++;
	_id = generateId(_thisMonkeyNum);
    }
    
}
