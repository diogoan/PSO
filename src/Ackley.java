
public final class Ackley {

	public static double calculate(double[] xx){

		double n = xx.length;

		// Parâmetros da função
		double c1 = 20;
		double c2 = 0.2;
		double c3 = 2 * Math.PI;

		// Somatório 1
		double som1 = 0;
		for (double i : xx) som1 = som1 + Math.pow(i, 2);

		// Somatório 2
		double som2 = 0;
		for (double i : xx) som2 = som2 + Math.cos(c3 * i);

		// Termo 1
		double t1 = -c1 * Math.exp(-c2 * Math.sqrt(som1/n));

		// Termo 2
		double t2 = -Math.exp(som2/n);

		return t1 + t2 + c1 + Math.exp(1);
	}

}
