package kakaopay.investing.exception;

import kakaopay.investing.common.code.ResultCode;

public class NotFoundException extends BaseException {

	private static final long serialVersionUID = 175342477692280475L;

	public NotFoundException() {
		super(ResultCode.PRODUCT_NOT_FOUND);
	}
}
