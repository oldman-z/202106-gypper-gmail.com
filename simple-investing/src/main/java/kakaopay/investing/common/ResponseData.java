package kakaopay.investing.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ResponseData {

	private int    status;
	private String code;
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;
	
	public ResponseData() {
	};
	
	public static ResponseData create() {
		return new ResponseData();
	}
	
    public ResponseData code(String code) {
        this.code = code;
        return this;
    }

    public ResponseData status(int status) {
        this.status = status;
        return this;
    }

    public ResponseData message(String message) {
        this.message = message;
        return this;
    }

    public ResponseData message(Object data) {
    	this.data = data;
    	return this;
    }
}
