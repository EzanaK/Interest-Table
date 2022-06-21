package gui;

import java.lang.Math;
import java.text.NumberFormat;

public class Interest {
	
	public static String simpleInterestCalculator(String strPrincipal, String strRate, int years) {
		double principal = Double.parseDouble(strPrincipal);
		double rate = Double.parseDouble(strRate);
		double value =  principal + (principal * (rate/100) * years);
		return NumberFormat.getCurrencyInstance().format(value);
	}
	
	public static String compoundInterestCalculator(String strPrincipal, String strRate, int years) {
		double principal = Double.parseDouble(strPrincipal);
		double rate = Double.parseDouble(strRate);
		double value = principal * Math.pow(1 + rate/100, years);
		return NumberFormat.getCurrencyInstance().format(value);
	}
	
}
