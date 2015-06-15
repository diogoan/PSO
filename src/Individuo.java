import java.util.Arrays;

public class Individuo implements Comparable<Individuo>{

	double[] posicaoAtual;
	double fitnessAtual;

	double[] velocidadeAtual;

	double[] pBest;
	double[] gBest;
	
	
	double melhorFitness = Double.MAX_VALUE;

	int numDimensoes;
	int intervalo;
	boolean mutante = false;

	public Individuo(int numDimensoes, int intervalo){
		super();
		this.numDimensoes = numDimensoes;
		this.intervalo = intervalo;
		this.posicaoAtual = this.initPos(numDimensoes, intervalo);
		this.fitnessAtual = this.calcularFitness();
		this.velocidadeAtual = this.initVel(numDimensoes);
		this.atualizarMelhorLocal();
		this.mutante = false;
	}

	/**
	 * Inicializa o array de posições aleatoriamente.
	 * @param numDimensoes O número de dimensões.
	 * @param intervalo O intervalo máximo ao qual os números aleatórios serão gerados.
	 * @return O array inicializado com valores aleatórios.
	 */
	double[] initPos(int numDimensoes, int intervalo){
		double[] posArray = new double[numDimensoes];

		for(int i = 0; i < numDimensoes; i++){
			posArray[i] = (Math.random() - 0.5) * intervalo * 2;
		}
		return posArray;
	}

	double[] initVel(int numDimensoes){
		double[] velArray = new double[numDimensoes];

		for(int i = 0; i < numDimensoes; i++){
			velArray[i] = 0;
		}
		return velArray;
	}

	double calcularFitness(){
		return Ackley.calculate(this.posicaoAtual);
	}

	void atualizarMelhorLocal(){
		if(this.fitnessAtual < this.melhorFitness){
			this.pBest = this.posicaoAtual;
			this.melhorFitness = this.fitnessAtual;
		}
	}

	void atualizarVelocidade(double[] melhorPosicaoGlobal, double inercia, double paramCog, double paramSoc){
		double[] xi = posicaoAtual;
		double[] vi = velocidadeAtual;
		
		double[] melhorPosicaoParcial = pBest;
		
		double r1 = Math.random();
		double r2 = Math.random();
		
		double[] in = ArrayUtils.mult(inercia, vi);
		
		double[] pbest = ArrayUtils.dif(melhorPosicaoParcial, xi);
		double[] gbest = ArrayUtils.dif(melhorPosicaoGlobal, xi);
		
		double[] term1 = ArrayUtils.mult(paramCog * r1, pbest);
		double[] term2 = ArrayUtils.mult(paramSoc * r2, gbest);
		
		double[] params = ArrayUtils.sum(term1, term2);
		
		velocidadeAtual = ArrayUtils.sum(in, params);
	}

	double[] atualizarPosicao(){
		return ArrayUtils.sum(posicaoAtual, velocidadeAtual);
	}

	public String toString(){
		return ("Posição: " + Arrays.toString(posicaoAtual) + " | Fitness: " + Double.toString(fitnessAtual));
	}

	@Override
	public int compareTo(Individuo ind) {
		return Double.compare(this.fitnessAtual, ind.fitnessAtual);
	}
	
	public static void main(String[] args) {
		Individuo ind = new Individuo(30,30);
		ind.atualizarVelocidade(ind.posicaoAtual, 0.5, 0.5, 0.5);
		ind.atualizarPosicao();
	}

}
