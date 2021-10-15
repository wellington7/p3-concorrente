package Main;

import GoFsckYourself.Fsck;
import TokenBucket.Bucket;
import TokenBucket.Request;

public class Main {

	public static void main(String[] args) {
		
		//*****TOKEN BUCKET*****
		System.out.println("* TOKEN BUCKET - Iniciando a execucao");
		Bucket bucket = new Bucket(36L);
		//Cenário onde o Request pede uma quantidade menor do que a disponível no Bucket
		System.out.println("* TOKEN BUCKET - CENARIO A - Requisicao de uma quantidade inferior de Tokens disponiveis no Bucket");
		Request requestA = new Request(35L);
		bucket.run(requestA.getSize());
		//Cenário onde o Request pede uma quantidade igual a disponível no Bucket
		System.out.println("* TOKEN BUCKET - CENARIO B - Requisicao de uma quantidade igual de Tokens disponiveis no Bucket");
		Request requestB = new Request(36L);
		bucket.run(requestB.getSize());
		//Cenário onde o Request pede uma quantidade maior que a disponível no Bucket
		System.out.println("* TOKEN BUCKET - CENARIO C - Requisicao de uma quantidade superior de Tokens disponiveis no Bucket");
		Request requestC = new Request(37L);
		bucket.run(requestC.getSize());
		
		//*****FSCK*****
		System.out.println("");
		System.out.println("* FSCK - Iniciando a execucao");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		Fsck fsck = new Fsck();
	}

}
