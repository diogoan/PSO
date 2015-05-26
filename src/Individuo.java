import java.util.Arrays;

public class Individuo implements Comparable<Individuo>{
	
	double[] posicaoAtual;
	double fitnessAtual;
	
	double[] velocidadeAtual;
	
	double[] melhorPosicao;
	double melhorFitness;
	
	public Individuo(int numDimensoes, int intervalo){
		super();
		this.posicaoAtual = this.initPos(numDimensoes, intervalo);
		this.fitnessAtual = this.calcularFitness();
		
		this.velocidadeAtual = this.initVel(numDimensoes);
		
		this.melhorPosicao = this.posicaoAtual;
		this.melhorFitness = this.fitnessAtual;
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
	
	double calcularFitness(){
		return Ackley.calculate(this.posicaoAtual);
	}
	
	public String toString(){
		return ("Posi��o: " + Arrays.toString(posicaoAtual) + " | Fitness: " + Double.toString(fitnessAtual));
	}
	
	@Override
	public int compareTo(Individuo ind) {
		return Double.compare(this.fitnessAtual, ind.fitnessAtual);
	}

}
