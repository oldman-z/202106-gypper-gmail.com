package kakaopay.investing.common.code;

import lombok.Getter;

@Getter
public enum ResultCode {

	RESULT_OK            (200, "0000", "정상"),
	VALIDATION_ERROR     (400, "E000", "요청 인자 유효성 오류"),
	PRODUCT_NOT_FOUND    (404, "E001", "요청한 상품정보 없음"),
	PRODUCT_SOLD_OUT     (410, "E002", "sold-out"),
	PRODUCT_OVER_AMOUNT  (413, "E003", "투자금액이 모집금액을 초과함"),
	AUTHENTICATION_ERROR (401, "E004", "사용자 인증 오류");

	private final int    status;
	private final String code;
	private final String message;
	
	ResultCode(final int status, final String code, final String message) {
		this.status  = status;
		this.code    = code;
		this.message = message;
	}
}
