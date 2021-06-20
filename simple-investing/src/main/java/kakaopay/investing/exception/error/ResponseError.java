package kakaopay.investing.exception.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseError {

	private int    status;
	private String code;
	private String message;
	
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
	private List<ParameterError> parameterErrors;
	
	public ResponseError() {
	};
	
	public static ResponseError create() {
		return new ResponseError();
	}
	
    public ResponseError code(String code) {
        this.code = code;
        return this;
    }

    public ResponseError status(int status) {
        this.status = status;
        return this;
    }

    public ResponseError message(String message) {
        this.message = message;
        return this;
    }

    public ResponseError errors(Errors errors) {
        setParameterErrors(errors.getFieldErrors());
        return this;
    }
    
    public void setParameterErrors(List<FieldError> list) {
    	parameterErrors = new ArrayList<>();
    	
    	list.forEach(error -> {
    		parameterErrors.add(new ParameterError(
    				error.getCodes()[0],
    				error.getRejectedValue(),
    				error.getDefaultMessage()
    				));
    	});
    }
}
