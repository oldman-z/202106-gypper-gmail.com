package kakaopay.investing.exception;

import org.springframework.validation.Errors;

import kakaopay.investing.common.code.ResultCode;

public class InvalidParameterException extends BaseException {
	
	private static final long serialVersionUID = 4650940626623455099L;
	
	private final Errors errors;

	public InvalidParameterException(Errors errors) {
		super(ResultCode.VALIDATION_ERROR);
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
