package TokenBucket;

public class Request {
	
	private Integer size;
	
	public Request(Integer size) {
		this.size = size;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
