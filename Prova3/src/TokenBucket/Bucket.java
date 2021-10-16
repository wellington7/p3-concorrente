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
	private Semaphore semaphore = new Semaphore(1);
	
	public Bucket(Integer capacity, Integer timeToAddTokens) {
		this.capacity = capacity;
		this.timeToAddTokens = timeToAddTokens;
//		bucketSlots = new CopyOnWriteArrayList<Token>();
		
		System.out.println("Preenchendo Bucket com Tokens");
		add(capacity);
		System.out.println("Bucket Preenchido");
		
		startInterval();
	}
	
	public void run(Integer requestSize) {
		System.out.println("Verificando Requisicao de tamanho " + requestSize);
		
		if (!limitCapWait(requestSize)) {
			System.out.println("Requisicao de tamanho " + requestSize + " bloqueada");
			blockBucket(requestSize);
		}
		
		System.out.println("Requisicao de tamanho " + requestSize + " sendo efetuada");
		remove(requestSize);
		System.out.println("Requisicao de tamanho " + requestSize + " efetuada com sucesso");
		System.out.println(tokensOnBucket + "/" + capacity);
	}
	
	private boolean limitCapWait(Integer requestSize) {
		if (requestSize > tokensOnBucket) {
			return false;
		}
		return true;
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
				if (tokensOnBucket >= neededToUnblock && isBlocked) {
					semaphore.release();
					isBlocked = false;
					neededToUnblock = 0;
				}
			}
		}, 0, this.timeToAddTokens);
	}
	
	private void blockBucket(Integer requestSize) {
		neededToUnblock = requestSize;
		isBlocked = true;
		
		semaphore.acquireUninterruptibly();
	}
	
	private void remove(Integer requestSize) {
//		for (int i = 0; i < requestSize; i++){
//			bucketSlots.remove(i);
//		}
		tokensOnBucket -= requestSize;
    }
	private void add(Integer quantity) {
//	    for (int i = 0; i < quantity; i++){
//			bucketSlots.add(new Token());
//		}
	    tokensOnBucket += quantity;
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
