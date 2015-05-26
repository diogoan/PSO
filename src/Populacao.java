import java.util.ArrayList;
import java.util.Collections;

public class Populacao {

	ArrayList<Individuo> enxame;

	double[] melhorPosicaoGlobal;
	double melhorFitnessGlobal = Double.MAX_VALUE;
	
	int indexMelhor;

	public Populacao(int numDimensoes, int tamPopulacao, int intervalo) {
		this.enxame = this.initPop(numDimensoes, tamPopulacao, intervalo);
		this.atualizarMelhor();
	}
	
	/**
	 * Inicializa cada individuo da popula��o com os par�metros fornecidos.
	 * @param numDimensoes O n�mero de dimensoes das coordenadas.
	 * @param tamPopulacao O tamanho inicial da popula��o.
	 * @return A popula��o com cada indiv�duo inicializado.
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
	 * com os melhores valores encontrados na popula��o no momento que o
	 * m�todo � executado.
	 */
	void atualizarMelhor(){
		ArrayList<Individuo> tempList = new ArrayList<Individuo>(this.enxame);
		Collections.sort(tempList);
		
		if(tempList.get(0).fitnessAtual < this.melhorFitnessGlobal){
			this.melhorPosicaoGlobal = tempList.get(0).posicaoAtual;
			this.melhorFitnessGlobal = tempList.get(0).fitnessAtual;
			this.indexMelhor = this.enxame.indexOf(tempList.get(0));
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
	
	/**
	 * Fornece a posi��o e o fitness de todos os indiv�duos (mas n�o a velocidade)
	 * e o informa qual o indiv�duo que possui o melhor fitness.
	 */
	public String toString(){
		StringBuilder descPop = new StringBuilder();
		for(int i = 0; i < enxame.size(); i++){
			descPop.append("Individuo " + i + " - ");
			descPop.append(enxame.get(i) + "\n");
		}
		descPop.append("Melhor: " + indexMelhor + " | Fitness: " + melhorFitnessGlobal);
		return descPop.toString();
	}
	
}
