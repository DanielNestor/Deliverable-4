import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PinningTests extends MonkeySim {

	
//the 3 tests for getFirstMonkey
	
	//this checks to see that the first monkey in the list is gotten in both functions
	//if added it should always be known that if the monkey is set to have the value of m1
	//it should always select that monkey
	@Test
	public void firstMonkeyPinningTest(){
		Monkey m1 = new Monkey();
		Monkey m2 = new Monkey();
		
		//create a temporary list to store the monkeys is
		List<Monkey> _tempList = new LinkedList<Monkey>();
		
		
		_tempList.add(m1);
		_tempList.add(m2);
		m1.setNum(1);
		
		
		
		System.out.println(_tempList.toString());
		assertEquals(getFirstMonkey(_tempList),m1);
		
		
		
	}
	
	//check to see if the function tries to get monkies from an empty list
	//in this case so long as an empty list is passed in we should always get a null value
	//returned for the get first function
	@Test
	public void firstMonkeyPinningTestEmptyList(){
		List<Monkey> _tempList = new LinkedList<Monkey>();
		assertEquals(getFirstMonkey(_tempList), null);
	}
	
	//now check to see if the lists still get the first monkey if the list is bigger
	//and if they both grab the correct one
	
	//when running this test with the unrefactored function I got an m5 return the first time
	//as it selects the most recently inserted
	
	@Test
	public void firstMonkeyPinningTestWithManyMonkies(){
		Monkey m1 = new Monkey();
		Monkey m2 = new Monkey();
		Monkey m3 = new Monkey();
		Monkey m4 = new Monkey();
		Monkey m5 = new Monkey();
		Monkey m6 = new Monkey();
		
		//create a temporary list to store the monkeys isn
		List<Monkey> _tempList = new LinkedList<Monkey>();
		
		m1.setNum(1);
		m2.setNum(1);
		m3.setNum(1);
		m4.setNum(1);
		m5.setNum(1);
		m6.setNum(1);
		
		
		_tempList.add(m1);
		_tempList.add(m2);
		_tempList.add(m3);
		_tempList.add(m4);
		_tempList.add(m5);
		_tempList.add(m6);
		
	
		assertEquals(getFirstMonkey(_tempList), m5);
		
		
		
	}
	

	
//the 3 tests for get id
	
	//this checks to see if the id is set properly in that with the same input id
	//i get the same output
	//For this I put in a basic id and try to generate it
	//The result that this test should have is the value 223591
	@Test
	public void settingIdTest(){
		Monkey m1 = new Monkey();
		int m1_basic =m1.generateId(99);
		int m1_improved = m1.generateId(99);
		
		assertEquals(m1_basic, 223591);
		
	}
	
	//now check to see if we generate ids with value 0 that they are both good
	//This test should generate the base value for it's ID, This is good to help refactoring
	//because it should show me what the lowest ID value is because it should not be possible to have a negative ID
	
	//the result from the original test is the value 223492
	@Test
	public void settingIdTestZero(){
		Monkey m1 = new Monkey();
		int m1_basic =m1.generateId(0);
		
		
		assertEquals(m1_basic, 223492);
		
	}
	
	//This checks for the negative values
	//of generate id to see if my refactored function still gives a suitable
	//output 222493
	@Test
	public void settingIdTestNegative(){
		Monkey m1 = new Monkey();
		int m1_basic =m1.generateId(-999);
		int m1_improved = m1.generateId(-999);
		assertEquals(m1_basic, 222493);
		
	}
	
	//this test checks for a corner case if an invalid value is passed into generate id
	//the result that the original unmodified function returned is 223589
	@Test
	public void settingIdTestWithAChar(){
		Monkey m1 = new Monkey();
		int m1_basic =m1.generateId('a');
	
		assertEquals(m1_basic, 223589);
		
	}
	
	
	
	
//the 3 tests for stringifyResults
	
	//this test checks to see that if 2 objects are put in with the same values
	//and the same parameter is put in that the correct value returns
	//the value I got for the original run of the test is.
	//"//Round 7: Threw banana from Monkey (#6 / ID 223498) to Monkey (#7 / ID 223499)"
	@Test
	public void StringifyTestOutput() {
		String str1 = "";
		String str2 = "";
		//create monkies to use in this test
		Monkey m1 = new Monkey();
		int m1_basic =m1.generateId(12);
		
		Monkey m2 = new Monkey();
		int m2_basic =m2.generateId(15);
		
		
		//now the actual tests
		//had to surround in a try catch just to be sure
		try {
			str1 = stringifyResults(7, m1, m2);
		} catch (NoIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(str1,"//Round 7: Threw banana from Monkey (#6 / ID 223498) to Monkey (#7 / ID 223499)");
	}
	

	//this test checks to see if proper strings are created if a negative is
	//passed into the the id generator
	//the string listed in the assertion should be the expected output
	//this output was recorded for the first time on the original test and now should
	//be good with the refactor
	
	//Output pre-refactor: "//Round -5: Threw banana from Monkey (#15 / ID 223507) to Monkey (#16 / ID 223508)"
	
	
	@Test
	public void StringifyTestWithNegatives() {
		String str1 = "";
		String str2 = "";
		//create monkies to use in this test
		Monkey m1 = new Monkey();
		int m1_basic =m1.generateId(-5);
		
		Monkey m2 = new Monkey();
		int m2_basic =m2.generateId(-5);
		
			try {
				str1 = stringifyResults(-5, m1, m2);
			} catch (NoIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		assertEquals(str1, "//Round -5: Threw banana from Monkey (#15 / ID 223507) to Monkey (#16 / ID 223508)");
	}
	

	//this test checks to see if nothing is passed into the generate ids
	//and a zero is passed into the stringify results
	//this will check to see if there is an issue with a parameter of 0
	//for this pinning test the original result when ran with 0s for ids in each
	
	//original string from unrefactored function
	//"//Round 0: Threw banana from Monkey (#2 / ID 223494) to Monkey (#3 / ID 223495)"
	
	@Test
	public void StringifyTestWithZeroes() {
		String str1 = "";
		String str2 = "";
		//create monkies to use in this test
		Monkey m1 = new Monkey();
		m1.generateId(0);
		
		Monkey m2 = new Monkey();
		m2.generateId(0);
		
		
		//now the actual tests
		//had to surround in a try catch just to be sure
		try {
			str1 = stringifyResults(0, m1, m2);
		} catch (NoIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		assertEquals(str1,"//Round 0: Threw banana from Monkey (#2 / ID 223494) to Monkey (#3 / ID 223495)");
	}
	
	
	
	
}
