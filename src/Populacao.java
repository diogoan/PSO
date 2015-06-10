import java.util.ArrayList;
import java.util.Collections;

public class Populacao {

	ArrayList<Individuo> enxame;

	double[] melhorPosicaoGlobal;
	double melhorFitnessGlobal = Double.MAX_VALUE;

	int indexMelhor;

	public Populacao(int numDimensoes, int tamPopulacao, int intervalo) {
		this.enxame = this.initPop(numDimensoes, tamPopulacao, intervalo);
		this.atualizarMelhorGlobal();
	}

	/**
	 * Inicializa cada individuo da população com os parâmetros fornecidos.
	 * @param numDimensoes O número de dimensoes das coordenadas.
	 * @param tamPopulacao O tamanho inicial da população.
	 * @return A população com cada indivíduo inicializado.
	 */
	ArrayList<Individuo> initPop(int numDimensoes, int tamPopulacao, int intervalo){
		ArrayList<Individuo> populacao = new ArrayList<Individuo>();

		for(int i = 0; i < tamPopulacao; i++){
			populacao.add(new Individuo(numDimensoes, intervalo));	
		}

		return populacao;
	}

	/**
	 * Atualiza as propriedades melhorPosicaoGlobal e melhorFitnessGlobal
	 * com os melhores valores encontrados na população no momento que o
	 * método é executado.
	 */
	void atualizarMelhorGlobal(){
		ArrayList<Individuo> tempList = new ArrayList<Individuo>(this.enxame);
		Collections.sort(tempList);

		Individuo melhorDessaGen = tempList.get(0);

		if(melhorDessaGen.fitnessAtual < this.melhorFitnessGlobal)
		{
			this.melhorPosicaoGlobal = melhorDessaGen.posicaoAtual;
			this.melhorFitnessGlobal = melhorDessaGen.fitnessAtual;
			this.indexMelhor = this.enxame.indexOf(melhorDessaGen);
		}
	}

	void atualizarVelocidades(double inercia, double paramCog, double paramSoc){
		for (int i = 0; i < enxame.size(); i++) {

			double[] xi = enxame.get(i).posicaoAtual;
			double[] vi = enxame.get(i).velocidadeAtual;

			double[] melhorPosicaoParcial = enxame.get(i).melhorPosicao;

			double r1 = Math.random();
			double r2 = Math.random();

			double[] in = ArrayUtils.mult(inercia, vi);

			double[] pbest = ArrayUtils.dif(melhorPosicaoParcial, xi);
			double[] gbest = ArrayUtils.dif(melhorPosicaoGlobal, xi);

			double[] term1 = ArrayUtils.mult(paramCog * r1, pbest);
			double[] term2 = ArrayUtils.mult(paramSoc * r2, gbest);

			double[] params = ArrayUtils.sum(term1, term2);

			enxame.get(i).velocidadeAtual = ArrayUtils.sum(in, params);
		}
	}

	void atualizarPosicoes(){
		for (int i = 0; i < enxame.size(); i++) {
			Individuo ind_i = enxame.get(i);

			ind_i.posicaoAtual = ArrayUtils.sum(ind_i.posicaoAtual, ind_i.velocidadeAtual);
			ind_i.fitnessAtual = Ackley.fitness(ind_i.posicaoAtual);

			ind_i.atualizarMelhorLocal();
		}
	}

	/**
	 * Fornece a posição e o fitness de todos os indivíduos (mas não a velocidade)
	 * e o informa qual o indivíduo que possui o melhor fitness.
	 */
	public String toString(){
		StringBuilder descPop = new StringBuilder();
		for(int i = 0; i < enxame.size(); i++){
			descPop.append(enxame.get(i) + "; ");
		}
		return descPop.toString();
	}

}
