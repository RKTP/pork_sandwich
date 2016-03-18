package parser;

import java.util.ArrayList;
import java.util.regex.*;

public class Tokenizer {
	private static final String pattern = "[+\\-*/\\(\\)\\^,]";
	
	public static ArrayList<String> Tokenize(String srcFormula) {
		ArrayList<String> tokens = new ArrayList<>();
		tokens = new ArrayList<>();
		String formula = srcFormula.replaceAll("\\s", "");
		
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(formula);
		
		int start = 0;
		while(matcher.find()) {
			if(start != matcher.start()) {
				tokens.add(formula.substring(start, matcher.start()).trim());
			}
			tokens.add(formula.substring(matcher.start(), matcher.end()).trim());
			start = matcher.end();	
		}
		if(start != formula.length()) {
			tokens.add(formula.substring(start).trim());
		}
		
		return tokens;
	}
}
