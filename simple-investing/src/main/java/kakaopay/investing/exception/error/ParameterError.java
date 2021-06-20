package kakaopay.investing.exception.error;

import lombok.Getter;

@Getter
public class ParameterError {

	private String field;
	private Object value;
	private String reason;

    public ParameterError(String field, Object value, String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }
}
