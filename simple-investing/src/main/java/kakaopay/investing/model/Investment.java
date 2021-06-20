package kakaopay.investing.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Investment {

	@JsonIgnore
	private int    customerId;
	
	@NonNull
	private int    productId;
	
	private int    tid;
	
	private String title;
	
	private long   totalInvestingAmount;
	
	private long   currentInvestingAmount;

	@NonNull
	@Min(value=1, message="최소값 1")
	@Positive
	private long   investingAmount;
	
	private String investingDate;
	
	@JsonIgnore
	private String resultCode;
	
	@JsonIgnore
	private String resultMessage;
	
}
