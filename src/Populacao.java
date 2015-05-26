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
		this.atualizarMelhor();
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
	 * quando a populacao fica presa em um m�nimo local
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
		this.melhorPosicaoGlobal = tempList.get(0).posicaoAtual;
		this.melhorFitnessGlobal = tempList.get(0).fitnessAtual;
		this.indexMelhor = this.enxame.indexOf(tempList.get(0));
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
