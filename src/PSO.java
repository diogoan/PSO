import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PSO {

	final static int NUM_DIMENSOES = 2;
	final static int TAM_POPULACAO = 10;
	final static int LIM_ITERACOES = 10000;
	final static int INTERVALO_ALEATORIO = 30;

	final static double PESO_INERCIA = 1;
	final static double PARAM_COGN = 2;
	final static double PARAM_SOCIAL = 2;

	public static void main(String[] args) {

		try {
			PrintWriter vel_arq = new PrintWriter("output_velocidades.txt");
			PrintWriter pos_arq = new PrintWriter("output_posicoes.txt");
			PrintWriter fit_arq = new PrintWriter("output_fitness.txt");

			Populacao a = new Populacao(NUM_DIMENSOES, TAM_POPULACAO, INTERVALO_ALEATORIO);

			int geracoes = 0;

			while(geracoes < LIM_ITERACOES){
				//atualizar a velocidade e posicoes da populacao
				//printar os valores das posicoes, ou seja, ver se as solucoes 
				//vão se aproximando do minimo da funcao de ackley
				
				pos_arq.println("Geração " + geracoes);
				vel_arq.println("Geração " + geracoes);
				fit_arq.println("Geração " + geracoes);
				
				pos_arq.println(a.posicoesString());
				vel_arq.println(a.velocidadesString());
				fit_arq.println(a.fitnessString());

				a.atualizarVelocidades(PESO_INERCIA, PARAM_COGN, PARAM_SOCIAL);
				a.atualizarPosicoes();
				a.atualizarMelhorGlobal();
				System.out.println("Gen " + geracoes + ": " + a.melhorFitnessGlobal + " | " + a.toString());
				geracoes++;
			}
			
			pos_arq.close();
			vel_arq.close();
			fit_arq.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
