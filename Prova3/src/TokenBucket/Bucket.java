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
		System.out.println("Executando Requisição");
		while (true) {
			if (limitCapWait(requestSize)) {
				System.out.println("Efetuando Requisição");
				bucketSlots.stream();
				System.out.println("Requisição efetuada com sucesso");
				break;
			} else {
				System.out.println("Requisição Bloqueada");
				break;
			}
		}
	}
	
	//Função que faz o controle da adição e remoção de tokens
	//Se conseguir efetuar requisição retorna true, se não retorna false
	private boolean limitCapWait(Long requestSize) {
		if (requestSize > capacity)
			return false;
			//Fazer Controle de Tempo --- Um token é adicionado ao bucket a cada 1/R segundos
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
