package Main;

import java.util.ArrayList;

import GoFsckYourself.Fsck;
import TokenBucket.Bucket;
import TokenBucket.Request;

public class Main {

	public static void main(String[] args) {
		
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
		
		// *****TOKEN BUCKET*****
		System.out.println("");
		System.out.println("* TOKEN BUCKET - Iniciando a execucao");

		Integer bucketSize = 100;
		Integer bucketTimeToAddToken = 100;
		ArrayList<Request> requests = new ArrayList();

		// Gerando 100 requisicoes
		for (int i = 0; i < 20; i++) {
			Request request = new Request(getRandomIntegerBetweenRange(bucketSize));
			requests.add(request);
		}

		Bucket bucket = new Bucket(bucketSize, bucketTimeToAddToken);

		for (Request request : requests) {
			new Thread() {
				@Override
				public void run() {
					bucket.run(request.getSize());
				}
			}.start();
		}
			
	}
	
	public static int getRandomIntegerBetweenRange(double max){
	    int x = (int)(Math.random()*((max-1)+1))+1;
	    return x;
	}

}
