import java.io.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Test polynomial constructor using file argument
		//Commented out because files are local
		/*File f = new File("C:\\Users\\andyy\\b07lab1\\test.txt");
		Polynomial p = new Polynomial(f);
		p.saveToFile("poly.txt");*/
		
		//Addition test
		double[] coeff1 = new double[] {4, 5, 7};
		int[] exp1 = new int[] {0, 1, 2};
		Polynomial p1 = new Polynomial(coeff1, exp1);
		
		double[] coeff2 = new double[] {6, -2, 5};
		int[] exp2 = new int[] {0, 1, 3};
		Polynomial p2 = new Polynomial(coeff2, exp2);
		
		Polynomial sum = p1.add(p2);
		System.out.println("Sum coefficients:");
		for(int i = 0; i < sum.coeff.length; i++) {
			System.out.print(sum.coeff[i] + " ");
		}
		System.out.println();
		System.out.println("Sum exponents:");
		for(int i = 0; i < sum.exp.length; i++) {
			System.out.print(sum.exp[i] + " ");
		}
		System.out.println();
		
		//Multiply test
		Polynomial prod = p1.multiply(p2);
		System.out.println("Product coefficients:");
		for(int i = 0; i < prod.coeff.length; i++) {
			System.out.print(prod.coeff[i] + " ");
		}
		System.out.println();
		System.out.println("Product exponents:");
		for(int i = 0; i < prod.exp.length; i++) {
			System.out.print(prod.exp[i] + " ");
		}
		System.out.println();
		
		//Evaluate test
		System.out.println("Evaluation of p1 with x=1: " + p1.evaluate(1));

	}

}
