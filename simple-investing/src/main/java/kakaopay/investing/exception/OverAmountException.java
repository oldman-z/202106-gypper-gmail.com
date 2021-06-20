package kakaopay.investing.exception;

import kakaopay.investing.common.code.ResultCode;

public class OverAmountException extends BaseException {

	private static final long serialVersionUID = 908996481049141339L;

	public OverAmountException() {
		super(ResultCode.PRODUCT_SOLD_OUT);
	}
}
