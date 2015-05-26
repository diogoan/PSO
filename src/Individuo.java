import java.util.Arrays;

public class Individuo implements Comparable<Individuo>{

	double[] posicaoAtual;
	double fitnessAtual;

	double[] velocidadeAtual;

	double[] melhorPosicao;
<<<<<<< HEAD
	double melhorFitness = Double.MAX_VALUE;

=======
	double melhorFitness;
	
	int numDimensoes;
	int intervalo;
	boolean mutante;
	
>>>>>>> origin/iss1
	public Individuo(int numDimensoes, int intervalo){
		super();
		this.numDimensoes = numDimensoes;
		this.intervalo = intervalo;
		this.posicaoAtual = this.initPos(numDimensoes, intervalo);
		this.fitnessAtual = this.calcularFitness();

		this.velocidadeAtual = this.initVel(numDimensoes);
		
<<<<<<< HEAD
		this.atualizarMelhorLocal();
=======
		this.melhorPosicao = this.posicaoAtual;
		this.melhorFitness = this.fitnessAtual;
		this.mutante = false;
>>>>>>> origin/iss1
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
<<<<<<< HEAD

	void atualizarMelhorLocal(){
		if(this.fitnessAtual < this.melhorFitness){
			this.melhorPosicao = this.posicaoAtual;
			this.melhorFitness = this.fitnessAtual;
		}
	}

=======
	
	void atualizarVelocidade(double[] posicao){
		
	}
	
	double[] atualizarPosicao(){
		return ArrayUtils.sum(posicaoAtual, velocidadeAtual);
	}
	
>>>>>>> origin/iss1
	public String toString(){
		return ("Posição: " + Arrays.toString(posicaoAtual) + " | Fitness: " + Double.toString(fitnessAtual));
	}

	@Override
	public int compareTo(Individuo ind) {
		return Double.compare(this.fitnessAtual, ind.fitnessAtual);
	}

}
