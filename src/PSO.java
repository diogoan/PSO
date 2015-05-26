
public class PSO {
	
	final static int NUM_DIMENSOES = 30;
	final static int TAM_POPULACAO = 30;
	final static int LIM_ITERACOES = 1000;
	final static int INTERVALO_ALEATORIO = 50;
		
	public static void main(String[] args) {
		
		Populacao a = new Populacao(NUM_DIMENSOES, TAM_POPULACAO, INTERVALO_ALEATORIO);
		System.out.println(a.toString());
		int geracoes = 0;
		
		while(geracoes < LIM_ITERACOES){
			//atualizar a velocidade e posicoes da populacao
			//printar os valores das posicoes, ou seja, ver se as solucoes 
			//vão se aproximando do minimo da funcao de ackley
		}
	}
}
