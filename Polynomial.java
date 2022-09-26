public class Polynomial {
	double[] coeff;

	public Polynomial() {
		//Initialize array [0]
		coeff = new double[]{0};
	}

	public Polynomial(double[] p) {
		//Initialize array with the elements of p
		coeff = p;
	}

	public Polynomial add(Polynomial p) {
		
		int len;
		Polynomial sum;
		
		//Case 1: The passed-in polynomial is longer
		if(this.coeff.length < p.coeff.length) {
			sum = new Polynomial(p.coeff);
			len = this.coeff.length;
		}
		//Case 2: The passed-in polynomial is not longer
		else {
			sum = new Polynomial(this.coeff);
			len = p.coeff.length;
		}
		
		//Add the shorter polynomial's coefficients to sum, which is the polynomial with the coefficients of the longer one
		for(int i = 0; i < len; i++) {
			sum.coeff[i] = this.coeff[i] + p.coeff[i];
		}
		
		return sum;
		
	}

	public double evaluate(double x) {
		double evaluation = 0;

		//Evaluate by summing up the product of the coefficient and the passed-in value raised to the exponent i
		for(int i = 0; i < this.coeff.length; i++) {
			evaluation += this.coeff[i] * Math.pow(x, i);
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
}