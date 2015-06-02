import java.util.Arrays;

public class Individuo implements Comparable<Individuo>{
     
	double[] posicaoAtual;
	double fitnessAtual;
     
	double[] velocidadeAtual;
     
	double[] melhorPosicao;
	double melhorFitness = Double.MAX_VALUE;
     
//	int numDimensoes;
//	int intervalo;
//	boolean mutante = false;
	
	public Individuo(int numDimensoes, int intervalo){
		super();
//		this.numDimensoes = numDimensoes;
//		this.intervalo = intervalo;
		
		this.posicaoAtual = this.initPos(numDimensoes, intervalo);
		this.fitnessAtual = Ackley.fitness(this.posicaoAtual);
		
		this.velocidadeAtual = this.initVel(numDimensoes);
		this.atualizarMelhorLocal();
		
//		this.mutante = false;
	}

	/**
	 * Inicializa o array de posi��es aleatoriamente.
	 * @param numDimensoes O n�mero de dimens�es.
	 * @param intervalo O intervalo m�ximo ao qual os n�meros aleat�rios ser�o gerados.
	 * @return O array inicializado com valores aleat�rios.
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

	void atualizarMelhorLocal(){
		if(this.fitnessAtual < this.melhorFitness){
			this.melhorPosicao = this.posicaoAtual;
			this.melhorFitness = this.fitnessAtual;
		}
	}
	
	void atualizarPosicao(){
		this.posicaoAtual = ArrayUtils.sum(this.posicaoAtual, this.velocidadeAtual);
		this.fitnessAtual = Ackley.fitness(this.posicaoAtual);
		this.atualizarMelhorLocal();
	}
	
//	boolean isMutante(){
//		return this.mutante;
//	}
	
	public String toString(){
		return ("Posi��o: " + Arrays.toString(posicaoAtual) + " | Fitness: " + Double.toString(fitnessAtual));
	}

	@Override
	public int compareTo(Individuo ind) {
		return Double.compare(this.fitnessAtual, ind.fitnessAtual);
	}

}
