import java.util.ArrayList;
import java.util.Collections;

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

		//Atencao para o numero de individuos a mutar menor que a populacao, se nao pode
		//entrar em loop infinito

		int contador = 0;
		while (contador < numeroDeIndividuosAMutar) {
			int index = (int) (Math.random()*(enxame.size()));	
			Individuo atual = enxame.get(index);
			//if(atual.mutante == false){
				int dimensao = atual.numDimensoes;
				int intervalo = atual.intervalo;

				Individuo novo = new Individuo(dimensao, intervalo);
				novo.mutante = true;
				enxame.set(index, novo);
				contador++;
			//}
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
			enxame.get(i).atualizarVelocidade(melhorPosicaoGlobal, inercia, paramCog, paramSoc);
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

	public static void main(String[] args) {
		Populacao pop = new Populacao(30, 30, 30);
		for (int i = 0; i < 5000; i++) {
			pop.atualizarPosicoes2();
			pop.atualizarVelocidades(0.9, 0.5, 0.5);
			pop.atualizarMelhorGlobal();
			pop.mutacaoEnxame(1);
			System.out.println(pop.melhorFitnessGlobal);
			
		}

	}
}
