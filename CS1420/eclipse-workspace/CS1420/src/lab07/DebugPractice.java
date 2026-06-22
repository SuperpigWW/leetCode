package lab07;

import java.util.Random;

/**
 * This class contains code for practice using the Eclipse debugger (Lab 7).
 * 
 * @author Prof. Parker
 * @version September 28, 2023
 */
public class DebugPractice {

	public static void main(String[] args) {
	
		Random rng = new Random(123456);
		
		Fraction[] ratios = new Fraction[25];
		for(int i = 0; i < ratios.length; i++) {
			int randomDenominator = rng.nextInt(10) + 1;
			ratios[i] = new Fraction(rng.nextInt(randomDenominator), randomDenominator);
		}
		Fraction correctAverage = new Fraction(5659, 15750);
		
		Fraction[] ratio = new Fraction[1];
		ratio[0] = ratios[0];
		
		System.out.println("Welcome to Lab 7: Debugging");
		
		if(!correctAverage.equals(Part1.average(ratios)))
			System.out.println("average method needs debugging"); 
		if(!correctAverage.equals(Part2.average(ratios)))
			System.out.println("average method needs debugging"); 
		if(!correctAverage.equals(Part3.average(ratios)))
			System.out.println("average method needs debugging"); 
		if(!(new Fraction(3, 4).equals(Part3.average(ratio))))
			System.out.println("average method needs debugging"); 
		if(!(new Fraction(3, 4).equals(Part4.average(ratio))))
			System.out.println("average method needs debugging"); 
		
		
		/*
		Fraction[] simpleRatios = new Fraction[5];
		simpleRatios[0] = new Fraction(1, 4);
		simpleRatios[1] = new Fraction(2, 3);
		simpleRatios[2] = new Fraction(1, 2);
		simpleRatios[3] = new Fraction(6, 10);
		simpleRatios[4] = new Fraction(1, 8);
		if(!(new Fraction(13, 24).equals(Part5.rangeSize(simpleRatios))))
			System.out.println("rangeSize method needs debugging");
		*/
	}
}