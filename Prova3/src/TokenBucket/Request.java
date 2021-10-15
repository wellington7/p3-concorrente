package TokenBucket;

public class Request {
	
	private Long size;
	
	public Request(Long size) {
		this.size = size;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
}
