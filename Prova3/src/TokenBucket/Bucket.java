package TokenBucket;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Bucket {
	
	private Integer timeToAddTokens = 5000; 
	private Integer capacity = 0;
//	private CopyOnWriteArrayList<Token> bucketSlots;
	private Integer tokensOnBucket = 0;
	private Integer neededToUnblock = 0;
	private Boolean isBlocked = false;
	private Semaphore semaphore;
	private Semaphore someoneIsRunning = new Semaphore(1);
	
	public Bucket(Integer capacity, Integer timeToAddTokens, Semaphore semaphore) {
		this.capacity = capacity;
		this.timeToAddTokens = timeToAddTokens;
		this.semaphore = semaphore; 
//		bucketSlots = new CopyOnWriteArrayList<Token>();
		
		System.out.println("Preenchendo Bucket com Tokens");
		add(capacity);
		System.out.println("Bucket Preenchido");
		
		startInterval();
	}
	
	public void run(Integer requestSize) {
		while (true) {
			try {
				remove(requestSize);
				break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void startInterval() {
		new Timer().scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				//	Se tiver menos tokens do que a capacidade, adiciona			
				if (tokensOnBucket < capacity){
					  tokensOnBucket++;
					  System.out.println("Adicionou no bucket " + tokensOnBucket + "/" + capacity);
					  System.out.println(Thread.activeCount() - 2 + " Threads rodando");
				}
				
 				// Se a quantidade de tokens atingir o necessário para desbloquear, desbloqueia					  
				if (tokensOnBucket >= neededToUnblock && semaphore.availablePermits() == 0) {
					semaphore.release();
					isBlocked = false;
					neededToUnblock = 0;
				}
			}
		}, 0, this.timeToAddTokens);
	}
	
	private void remove(Integer requestSize) throws InterruptedException {
		while (!limitCapWait(requestSize)) {
			neededToUnblock = requestSize;
			isBlocked = true;
			
			System.out.println("BLOQUEOU>>>>>> Tamanho: " + requestSize + " - BucketTokens: " + tokensOnBucket);
			semaphore.acquire();
			System.out.println("DESBLOQUEOU>>>>>> Tamanho: " + requestSize + " - BucketTokens: " + tokensOnBucket);
		} 			

		System.out.println("PASSOU>>>>>> Tamanho: " + requestSize + " - BucketTokens: " + tokensOnBucket);
		System.out.println("Requisicao de tamanho " + requestSize + " sendo efetuada");
		tokensOnBucket -= requestSize;
		System.out.println("Requisicao de tamanho " + requestSize + " efetuada com sucesso");
		System.out.println(tokensOnBucket + "/" + capacity);
    }
	
	private void add(Integer quantity) {
	    tokensOnBucket += quantity;
    }
	
	private boolean limitCapWait(Integer requestSize) {
		if (requestSize > tokensOnBucket) {
			return false;
		}
		return true;
	}

 	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
//	public CopyOnWriteArrayList<Token> getBucketSlots() {
//		return bucketSlots;
//	}
	
//	public void setBucketSlots(CopyOnWriteArrayList<Token> bucketSlots) {
//		this.bucketSlots = bucketSlots;
//	}
	
}
