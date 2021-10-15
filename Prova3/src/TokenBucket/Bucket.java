package TokenBucket;

import java.util.concurrent.CopyOnWriteArrayList;

public class Bucket {
	
	private Long capacity = 0L;
	private CopyOnWriteArrayList<Token> bucketSlots;
	
	public Bucket(Long capacity) {
		this.capacity = capacity;
		bucketSlots = new CopyOnWriteArrayList<Token>();
		System.out.println("Preenchendo Bucket com Tokens");
		fillBucket();
		System.out.println("Bucket Preenchido");
	}
	
	public void run(Long requestSize) {
		System.out.println("Executando Requisi��o");
		while (true) {
			if (limitCapWait(requestSize)) {
				System.out.println("Efetuando Requisi��o");
				bucketSlots.stream();
				System.out.println("Requisi��o efetuada com sucesso");
				break;
			} else {
				System.out.println("Requisi��o Bloqueada");
				break;
			}
		}
	}
	
	//Fun��o que faz o controle da adi��o e remo��o de tokens
	//Se conseguir efetuar requisi��o retorna true, se n�o retorna false
	private boolean limitCapWait(Long requestSize) {
		if (requestSize > capacity)
			return false;
			//Fazer Controle de Tempo --- Um token � adicionado ao bucket a cada 1/R segundos
		return true;
	}
	
	private void fillBucket() {
		for (int i=0; i<capacity; i++){
			bucketSlots.add(new Token("xyz - " + i));
		}
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public CopyOnWriteArrayList<Token> getBucketSlots() {
		return bucketSlots;
	}

	public void setBucketSlots(CopyOnWriteArrayList<Token> bucketSlots) {
		this.bucketSlots = bucketSlots;
	}
	
}
