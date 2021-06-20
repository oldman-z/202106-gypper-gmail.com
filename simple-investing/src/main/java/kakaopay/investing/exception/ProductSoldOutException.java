package kakaopay.investing.exception;

import kakaopay.investing.common.code.ResultCode;

public class ProductSoldOutException extends BaseException {

	private static final long serialVersionUID = 175342477692280475L;

	public ProductSoldOutException() {
		super(ResultCode.PRODUCT_SOLD_OUT);
	}
}
