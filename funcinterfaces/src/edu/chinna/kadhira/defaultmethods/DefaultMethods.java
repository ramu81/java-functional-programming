package edu.chinna.kadhira.defaultmethods;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Random;
/**
Rule 1: Subtypes automatilcally carry over the default methods form their super types.

Rule 2: For Interfaces that contribute a default method, the implementation in a subtype 
takes precedence over the one in subtypes.

Rule 3: Implementation in classes, including abstract declarations take precedence over
all interface defaults. Class hierarchy takes more precedence than interface hierarchy.

Rule 4: If there's a conflict between two or more default method implementations, or there's 
a default-abstract conflict between two interfaces,the inheriting class should disambiguate.

*/
public class DefaultMethods  {
	public void rules() {
		Sub sub = new Sub();
		out.println("*******************************");
		sub.ruleOne(); //Rule - 1
		sub.ruleTwo(); //Rule - 2
		sub.ruleThree(); //Rule - 3
		sub.ruleFour(); //Rule - 4
		try {SECONDS.sleep(1);}catch(InterruptedException e) {out.println(e.getMessage());}
		sub.ruleFour(); //Rule - 4
		out.println("*******************************");
	}
}

class Sub extends BaseClass implements SubInterface,AmbiguateInterface { 
	public void ruleFour() { 
			if(new Random().nextBoolean()) SubInterface.super.ruleFour();
			else AmbiguateInterface.super.ruleFour();
	 }
}

class BaseClass {
	public void ruleThree() {out.println("BaseClass::ruleThree");}
}
interface BaseInterface {
	default void ruleOne() {out.println("BaseInterface::ruleOne");}
	default void ruleTwo() {out.println("BaseInterface::ruleTwo");}
	default void ruleThree() {out.println("BaseInterface::ruleThree");}
	default void ruleFour() {out.println("BaseInterface::ruleFour");}
}

interface SubInterface extends BaseInterface { 
	default void ruleTwo() {out.println("SubInterface::ruleTwo");}
}

interface AmbiguateInterface { default void ruleFour() {out.println("AmbiguateInterface::ruleFour");}}
	