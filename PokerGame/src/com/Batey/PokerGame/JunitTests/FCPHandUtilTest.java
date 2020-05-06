/**
 * 
 */
package com.Batey.PokerGame.JunitTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.Batey.PokerGame.FCPHandUtil;

/**
 * Testing FCPHand Utils.
 * Tests covered are non-failing input tests. 
 * @author Brian Batey
 */
public class FCPHandUtilTest {
	FCPHandUtil cUT;
	private final ByteArrayOutputStream contentStream = new ByteArrayOutputStream();
	private final PrintStream originalConsole = System.out;
	
	/**
	 * Simple Tokenizer uses whitespace as a delimiter.
	 * @param str
	 * 		- input string to tokenize
	 * @return
	 * 		- ArrayList<String> object with resultant tokens
	 */
	private ArrayList<String> tokenGenerator(String str) {
		//Initialize variables
		ArrayList<String>output = new ArrayList<>();
		StringTokenizer tokens = new StringTokenizer(str);		
		
		while(tokens.hasMoreTokens()) { //Transfer tokens to array for output
				output.add(tokens.nextToken());
			}
		return output;
		}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Set system output to custom byte stream.
		System.setOut(new PrintStream(contentStream));
		cUT = new FCPHandUtil();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//Set output back to console
		System.setOut(originalConsole);
	}

	@Test
	public final void highCardPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with high card: ACE of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2H 3D 5S 9C AD  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void highCardPlayerTwoWins() {
		//Setup 
		String expected = "White wins. - with high card: ACE of HEARTS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void pairPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with pair: ACE of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2H 3D 5S AC AD  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void pairPlayerTwoWins() {
		//Setup 
		String expected = "White wins. - with pair: ACE of HEARTS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S AC AH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void twoPairPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with two pair: ACE of DIAMONDS over THREE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 3S 3D 5S AC AD  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void twoPairPlayerTwoWins() {
		//Setup 
		String expected = "White wins. - with two pair: ACE of DIAMONDS over THREE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH  White: 3S 3D 5S AC AD");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void threeOKPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with three of a kind: THREE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 3S 3D 3C KC AD White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void threeOKPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with three of a kind: THREE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH White: 3S 3D 3C JC AD");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void straightAllNumeralPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with straight: SIX of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2S 3D 4C 5C 6D  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void straightAllNumeralPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with straight: SIX of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH  White: 2S 3D 4C 5C 6D");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void straightMixPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with straight: JACK of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 7S 8D 9C TC JD  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void straightMixPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with straight: JACK of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH  White: 7S 8D 9C TC JD");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void flushAllNumeralPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with flush: SEVEN of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2S 3S 4S 5S 7S  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void flushAllNumeralPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with flush: SEVEN of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH White: 2S 3S 4S 5S 7S");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void flushMixPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with flush: ACE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2S 3S 4S 5S AS  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void flushMixPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with flush: ACE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH White: 2S 3S 4S 5S AS");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fullHouseAllNumeralPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with full house: FOUR of DIAMONDS over TWO of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2S 2D 4S 4D 4C White: 2C 3H 4H 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fullHouseAllNumeralPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with full house: FOUR of DIAMONDS over TWO of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4H 8C KH White: 2S 2D 4S 4D 4C");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fullHouseMixPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with full house: FOUR of SPADES over ACE of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: AS AD 4S 4D 4C White: 2C 3H 4H 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fullHouseMixPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with full house: FOUR of SPADES over ACE of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4H 8C KH White: AS AD 4S 4D 4C");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fourOKNumeralPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with four of a kind: THREE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 3S 3D 3C 3H AD White: 2C 2H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fourOKNumeralPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with four of a kind: THREE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 2H 4S 8C KH White: 3S 3D 3C 3H AD");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fourOKFacePlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with four of a kind: ACE of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: AS AD AC AH 3D White: 2C 2H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void fourOKFacePlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with four of a kind: ACE of DIAMONDS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 2H 4S 8C KH White: AS AD AC AH 3D");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void royalFlushPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with straight flush: ACE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: JS TS QS KS AS  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void royalFlushPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with straight flush: ACE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH White: JS TS QS KS AS");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void sFlushPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with straight flush: NINE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 5S 6S 7S 8S 9S  White: 2C 3H 4S 8C KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void sFlushPlayerTwoWins() {
		
		//Setup 
		String expected = "White wins. - with straight flush: NINE of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3H 4S 8C KH White: 5S 6S 7S 8S 9S");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void tieBreakPlayerOneWins() {
		
		//Setup 
		String expected = "Black wins. - with high card: SEVEN of SPADES";
		ArrayList<String> inputArr = tokenGenerator("Black: 2H 3D 7S 9C KD  White: 2D 3H 5C 9S KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void tieBreakPlayerTwoWins() {
		//Setup 
		String expected = "White wins. - with high card: SEVEN of CLUBS";
		ArrayList<String> inputArr = tokenGenerator("Black: 2C 3D 5S 9C KD  White: 2D 3H 7C 9S KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public final void tieTest() {
		//Setup 
		String expected = "Tie.";
		ArrayList<String> inputArr = tokenGenerator("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH");
		
		//Init class under test
		cUT.initialize(inputArr.toArray(new String[inputArr.size()]));
		cUT.run();
		
		//Remove line endings
		String actual = contentStream.toString().substring(0, contentStream.toString().length()-2);		
		
		assertEquals(expected, actual);
	}
}