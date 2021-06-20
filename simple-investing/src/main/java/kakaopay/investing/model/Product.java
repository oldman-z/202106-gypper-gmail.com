package kakaopay.investing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kakaopay.investing.common.code.CodeMap;
import kakaopay.investing.common.code.ProductStatus;
import lombok.Data;

@Data
public class Product {

	private int    productId;
	
	private String title;
	
	private long   totalInvestingAmount;
	
	private long   currentInvestingAmount;
	
	private long   investingAmount;
	
	private int    investorCount;
	
	@JsonIgnore
	private String status;
	
	private String startedAt;
	
	private String finishedAt;
	
	@JsonIgnore
	private int    lastTid;
	
	public String getStatusName() {
		return CodeMap.getCodeMap(ProductStatus.class).get(status);
	}
}
