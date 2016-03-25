package unitTest;

import static org.junit.Assert.*;
import org.junit.Test;

import util.Tokenizer;

import java.util.ArrayList;



public class TokenizerTest {
	String formula = "x^2 + 3x*y / (x^2 + 2) + log(pow(x,3))";
	String[] ansArray = {"x","^","2","+","3x","*","y","/","(","x","^","2","+","2",")","+","log","(","pow","(","x",",","3",")",")"};
	ArrayList<String> tokens;

	@Test
	public void test() {
		tokens = Tokenizer.Tokenize(formula);
		assertEquals(ansArray.length, tokens.size(), 1.0);
		for(int i = 0; i < ansArray.length; i++) {
			assertEquals(true, ansArray[i].equals(tokens.get(i)));
		}
	}

}
