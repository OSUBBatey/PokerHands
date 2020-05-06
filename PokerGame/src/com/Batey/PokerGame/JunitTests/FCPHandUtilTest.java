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
import org.junit.platform.commons.util.StringUtils;

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
	public final void pairPlayerTwoWins() {
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
	}