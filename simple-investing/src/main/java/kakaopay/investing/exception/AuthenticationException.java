package kakaopay.investing.exception;

import kakaopay.investing.common.code.ResultCode;

public class AuthenticationException extends BaseException {

	private static final long serialVersionUID = 175342477692280475L;

	public AuthenticationException() {
		super(ResultCode.AUTHENTICATION_ERROR);
	}
}
