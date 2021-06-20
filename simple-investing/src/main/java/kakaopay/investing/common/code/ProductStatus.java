package kakaopay.investing.common.code;

import lombok.Getter;

@Getter
public enum ProductStatus {

	PROCEEDING("모집중"),
	COMPLETED("모집완료");

	private final String statusName;
	
	ProductStatus(final String statusName) {
		this.statusName  = statusName;
	}
	
	@Override
	public String toString() {
		return statusName;
	}
}
