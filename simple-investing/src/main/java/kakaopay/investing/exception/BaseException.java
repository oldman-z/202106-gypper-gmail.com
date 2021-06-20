package kakaopay.investing.exception;

import kakaopay.investing.common.code.ResultCode;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -895671669991100580L;
	
	private final ResultCode resultCode;
	
	public BaseException(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}
}
