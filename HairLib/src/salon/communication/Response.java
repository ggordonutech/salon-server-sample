package salon.communication;

public class Response  implements java.io.Serializable{
	private boolean success;
	private String errorMessage;
	private Object data;

	public Response() {
		success = true;
		errorMessage = "";
		data = null;
	}

	public Response(boolean success) {
		this.success = success;
		errorMessage = "";
		data = null;
	}

	public Response(Object data) {
		this.data = data;
		success = true;
		errorMessage = "";
	}

	public Response(boolean success, String errorMessage, Object data) {
		this.success = success;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [success=" + success + ", errorMessage=" + errorMessage + ", data=" + data + "]";
	}

}
