package builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.*;

import parser.*;
import model.*;

public class FunctionBuilder {
	public static Function buildFunction(String sourceString, String funcName) throws Exception {
		ArrayList<String> tokens = Tokenizer.Tokenize(sourceString);
		ExprNode root = new Parser().parse(tokens);
		Set<Character> varNames = new HashSet<>();
		
		for(String e : tokens) {
			if(e.length() == 1 && e.matches("[a-z]")) {
				varNames.add(e.charAt(0));
			} else if(e.matches("[0-9]+[a-z]")) {
				Matcher match = Pattern.compile("[a-z]").matcher(e);
				match.find();
				int varPos = match.start();
				varNames.add(e.charAt(varPos));
			}
		}
		
		Map<Character,Variable> varMap = new HashMap<>();
		Iterator<Character> it = varNames.iterator();
		
		for(;it.hasNext();) {
			Character varName = it.next();
			varMap.put(varName, new Variable(varName));
		}
		
		return new Function(root.buildUpExpression(varMap), funcName);
	}
}
