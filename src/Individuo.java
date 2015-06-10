import java.util.Arrays;

public class Individuo implements Comparable<Individuo>{
     
	double[] posicaoAtual;
	double fitnessAtual;
     
	double[] velocidadeAtual;
     
	double[] melhorPosicao;
	double melhorFitness = Double.MAX_VALUE;
	
	public Individuo(int numDimensoes, int intervalo){
		super();		
		this.posicaoAtual = this.initPos(numDimensoes, intervalo);
		this.fitnessAtual = Ackley.fitness(this.posicaoAtual);
		
		this.velocidadeAtual = this.initVel(numDimensoes);
		this.atualizarMelhorLocal();
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
	
	public String toString(){
		return (Double.toString(this.fitnessAtual) + " " + Arrays.toString(this.posicaoAtual) + " " + Arrays.toString(this.velocidadeAtual));
	}

	@Override
	public int compareTo(Individuo ind) {
		return Double.compare(this.fitnessAtual, ind.fitnessAtual);
	}

}
