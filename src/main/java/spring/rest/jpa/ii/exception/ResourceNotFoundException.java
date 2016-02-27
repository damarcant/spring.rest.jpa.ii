package spring.rest.jpa.ii.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5259956525566807358L;
	
	private long resourceId;

	public ResourceNotFoundException(long resourceId) {
		this.resourceId = resourceId;
	}
		 
	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}
 
}
