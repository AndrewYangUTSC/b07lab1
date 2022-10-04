import java.util.Scanner;
import java.io.*;

public class Polynomial {
	double[] coeff;
	int[] exp;

	public Polynomial() {
		//Initialize both arrays as null
		coeff = null;
		exp = null;
	}

	public Polynomial(double[] c, int[] e) {
		//Initialize coeff and exp arrays with the elements of c and e respectively
		coeff = c;
		exp = e;
	}
	
	public Polynomial(File f) {
		try {
			Scanner sc = new Scanner(f);
			String old_str = sc.nextLine();
			
			//Add plus signs in front of all minus signs to make it easier to split into terms
			String new_str = old_str.replaceAll("\\-", "\\+-");
			//Remove empty first element if it exists
			if(new_str.charAt(0) == '+') {
				new_str = new_str.substring(1, new_str.length());
			}
			
			//Split polynomial into terms
			String poly_terms[] = new_str.split("\\+");
			coeff = new double[poly_terms.length];
			exp = new int[poly_terms.length];
			
			//For each term, parse coefficient and exponent
			for(int i = 0; i < poly_terms.length; i++) {
				String terms[] = poly_terms[i].split("x");
				if(poly_terms[i].contains("x")) {
					coeff[i] = Double.parseDouble(terms[0]);
					exp[i] = Integer.parseInt(terms[1]);
				}
				else {
					coeff[i] = Double.parseDouble(terms[0]);
					exp[i] = 0;
				}
			}
			
			//For zero polynomial, set arrays to null
			if(old_str == "0") {
				coeff = null;
				exp = null;
			}
			
			sc.close();
		}
		
		catch(IOException e) {
			System.out.println("File reading error!");
		}
	}

	public Polynomial add(Polynomial p) { 
		//If either polynomial is zero, return the other
		if(this.coeff == null) {
			return p;
		}
		if(p.coeff == null) {
			return this;
		}
		
		//Add coefficients of corresponding exponents using temp array
		int this_deg = p.getDegree(this.exp);
		int p_deg = p.getDegree(p.exp);
		int deg = Math.max(this_deg, p_deg);
		double[] temp = new double[deg + 1];
		
		for(int i = 0; i < temp.length; i++) {
			temp[i] = 0;
		}
		
		for(int i = 0; i < this.exp.length; i++) {
			temp[this.exp[i]] += this.coeff[i];
		}
		
		for(int i = 0; i < p.exp.length; i++) {
			temp[p.exp[i]] += p.coeff[i];
		}
		
		int nonzero_count = 0;
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] != 0) {
				nonzero_count++;
			}
		}
		
		double[] sum_coeff = new double[nonzero_count];
		int[] sum_exp = new int[nonzero_count];
		
		//Remove all entries from temp that have coefficients of zero
		int index = 0;
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] != 0) {
				sum_coeff[index] = temp[i];
				sum_exp[index] = i;
				index++;
			}
		}
		
		if(sum_coeff.length == 0) {
			sum_coeff = null;
			sum_exp = null;
		}
		
		Polynomial sum = new Polynomial(sum_coeff, sum_exp);
		return sum;
	}
	
	public Polynomial multiply(Polynomial p) {
		if(this.coeff == null) {
			return p;
		}
		if(p.coeff == null) {
			return this;
		}
		
		int this_deg = p.getDegree(this.exp);
		int p_deg = p.getDegree(p.exp);
		double[] temp = new double[this_deg + p_deg + 1];
		
		for(int i = 0; i < temp.length; i++) {
			temp[i] = 0;
		}
		
		//Multiply coefficients and add exponents
		for(int i = 0; i < this.exp.length; i++) {
			for(int j = 0; j < p.exp.length; j++) {
				int exp = this.exp[i] + p.exp[j];
				temp[exp] += this.coeff[i] * p.coeff[j];
			}
		}
		
		int nonzero_count = 0;
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] != 0) {
				nonzero_count++;
			}
		}
		
		double[] prod_coeff = new double[nonzero_count];
		int[] prod_exp = new int[nonzero_count];
		
		//Remove all entries from temp that have coefficients of zero
		int index = 0;
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] != 0) {
				prod_coeff[index] = temp[i];
				prod_exp[index] = i;
				index++;
			}
		}
		
		if(prod_coeff.length == 0) {
			prod_coeff = null;
			prod_exp = null;
		}
		
		Polynomial prod = new Polynomial(prod_coeff, prod_exp);
		return prod;
		
	}
	
	public int getDegree(int[] arr) {
		int degree = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] > degree) {
				degree = arr[i];
			}
		}
		
		return degree;
	}

	public double evaluate(double x) {
		//Zero polynomial will always evaluate to 0
		if(coeff == null) {
			return 0;
		}
		
		double evaluation = 0;

		//Evaluate by summing up the product of the coefficient and the passed-in value raised to the exponent i
		for(int i = 0; i < this.coeff.length; i++) {
			evaluation += this.coeff[i] * Math.pow(x, this.exp[i]);
		}
		
		return evaluation;
	}

	public boolean hasRoot(double x) {
		//Use evaluate method to check if the passed-in x value evaluates the polynomial to 0
		if(this.evaluate(x) == 0) {
			return true;
		}

		return false;
	}
	
	public void saveToFile(String file_name) {
		try {
			File f = new File("C:\\Users\\andyy\\b07lab1\\" + file_name);
			FileWriter fw = new FileWriter(f);
			
			//Write 0 in the case of a zero polynomial
			if(coeff == null) {
				fw.write("0");
				fw.close();
				return;
			}
			
			String p = "";
			for(int i = 0; i < exp.length; i++) {
				if(coeff[i] > 0) {
					p += "+" + Double.toString(coeff[i]);
				}
				else {
					p += Double.toString(coeff[i]);
				}
			
				if(exp[i] > 0) {
					p += "x" + Integer.toString(exp[i]);			
				}	
			}
		
			if(p.charAt(0) == '+') {
				p = p.substring(1, p.length());
			}
		
			fw.write(p);
			fw.close();
			
		}
		catch(IOException e) {
			System.out.println("File writing error!");
		}
		
	}
}
