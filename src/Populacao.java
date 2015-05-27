import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Populacao {

	ArrayList<Individuo> enxame;

	double[] melhorPosicaoGlobal;
	double melhorFitnessGlobal;

	int indexMelhor;

	public Populacao(int numDimensoes, int tamPopulacao, int intervalo) {
		this.enxame = this.initPop(numDimensoes, tamPopulacao, intervalo);
		this.atualizarMelhorGlobal();
	}
	
	/**
	 * Atualiza as posicoes de todo o enxame
	 */
	void atualizarPosicoes(){
		for (int i = 0; i < enxame.size(); i++) {
			enxame.get(i).atualizarPosicao();
		}
	}

	/**
	 * Metodo que faz mutacao em um certo numero de individuos da populacao
	 * quando a populacao fica presa em um mínimo local
	 * @param numeroDeIndividuosAMutar
	 */
	void mutacaoEnxame(int numeroDeIndividuosAMutar){
		Random ran = new Random(enxame.size());

		//Atencao para o numero de individuos a mutar menor que a populacao, se nao pode
		//entrar em loop infinito
		
		int contador = 0;
		while (contador < numeroDeIndividuosAMutar) {
			int index = ran.nextInt();	
			Individuo atual = enxame.get(index);
			if(atual.mutante == false){
				int dimensao = atual.numDimensoes;
				int intervalo = atual.intervalo;

				Individuo novo = new Individuo(dimensao, intervalo);
				novo.mutante = true;
				enxame.set(index, novo);
				contador++;
			}
		}
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
		this.melhorPosicaoGlobal = tempList.get(0).posicaoAtual;
		this.melhorFitnessGlobal = tempList.get(0).fitnessAtual;
		this.indexMelhor = this.enxame.indexOf(tempList.get(0));
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
	
	void atualizarPosicoes2(){
		for (int i = 0; i < enxame.size(); i++) {
			Individuo ind_i = enxame.get(i);
			
			ind_i.posicaoAtual = ArrayUtils.sum(ind_i.posicaoAtual, ind_i.velocidadeAtual);
			ind_i.fitnessAtual = ind_i.calcularFitness();
			
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
			descPop.append("Individuo " + i + " - ");
			descPop.append(enxame.get(i) + "\n");
		}
		descPop.append("Melhor: " + indexMelhor + " | Fitness: " + melhorFitnessGlobal);
		return descPop.toString();
	}

}
