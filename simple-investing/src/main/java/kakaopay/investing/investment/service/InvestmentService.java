package kakaopay.investing.investment.service;

import java.util.List;

import kakaopay.investing.common.code.ResultCode;
import kakaopay.investing.model.Investment;

public interface InvestmentService {
	
	public ResultCode investProduct(Investment investment) throws Exception;

	public List<Investment> getMyInvestments(Investment investment) throws Exception;
}
