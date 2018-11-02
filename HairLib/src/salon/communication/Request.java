package salon.communication;

public class Request implements java.io.Serializable {
	//stores method/procedure to be called
    private String action;
    //data to send
    private Object object;
    
    public Request() {
    	action=""; object =null;
    }
    
    public Request(String action) {
    	this.action = action; object = null;
    }
    
    public Request(String action, Object object) {
    	this.action = action;
    	this.object = object;
    }

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Request [action=" + action + ", object=" + object + "]";
	}
    
    
}
