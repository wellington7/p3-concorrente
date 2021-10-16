package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import GoFsckYourself.Fsck;
import TokenBucket.Bucket;
import TokenBucket.Request;

public class Main {

	public static void main(String[] args) {
		System.out.println("************************************************");
		System.out.println("***** PROGRAMAÇÃO CONCORRENTE - PROVA 3 ********");
		System.out.println("***** ALUNOS: WELLINGTON ARAUJO SILVA **********");
		System.out.println("************* GILMAR GONZAGA DA SILVA **********");
		System.out.println("************* GUSTAVO LUIZ BISPO DOS SANTOS*****");
		System.out.println("************************************************");
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		Scanner sc = new Scanner(System.in);
		Integer opcao;

		opcao = chamaMenu(sc);
		switch (opcao) {
			case (1):
				tokenBucket();
				break;
			case (2):
				fsckRun();
				break;
			case (0):
				break;
			default:
				System.out.println("OPÇÃO INVÁLIDA!");	
				break;
		}
	}
	
	public static void fsckRun() {
		// *****FSCK*****
		System.out.println("");
		System.out.println("* FSCK - Iniciando a execucao");
		// System.out.println("Working Directory = " + System.getProperty("user.dir"));
		Fsck fsck = new Fsck();
		new Thread() {
			@Override
			public void run() {
				fsck.run();
			}
		}.start();
	}
	
	public static void tokenBucket() {
		// *****TOKEN BUCKET*****
		System.out.println("");
		System.out.println("* TOKEN BUCKET - Iniciando a execucao");

		Semaphore bucketSemaphore = new Semaphore(1);
		Integer bucketSize = 100;
		Integer bucketTimeToAddToken = 100;
		ArrayList<Request> requests = new ArrayList();

		// Gerando 20 requisicoes
		for (int i = 0; i < 20; i++) {
			Request request = new Request(getRandomIntegerBetweenRange(bucketSize));
			requests.add(request);
		}
		

		Bucket bucket = new Bucket(bucketSize, bucketTimeToAddToken, bucketSemaphore);

		for (Request request : requests) {
			new Thread() {
				@Override
				public void run() {
					bucket.run(request.getSize());
				}
			}.start();
		}
		
		return;
	}
	
	
	public static int getRandomIntegerBetweenRange(double max){
	    int x = (int)(Math.random()*((max-1)+1))+1;
	    return x;
	}
	
	public static Integer chamaMenu(Scanner sc) {
		System.out.println("Selecione a função que deseja rodar:");
		System.out.println("[0] - Para finalizar o programa");
		System.out.println("[1] - Token Bucket");
		System.out.println("[2] - FSCK");
		
		System.out.print("Opção> ");
		Integer opcao = sc.nextInt();
		return opcao;
	}

}
