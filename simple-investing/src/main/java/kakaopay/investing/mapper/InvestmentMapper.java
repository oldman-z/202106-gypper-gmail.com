package kakaopay.investing.mapper;

import java.util.List;

import kakaopay.investing.model.Investment;
import kakaopay.investing.model.Product;

public interface InvestmentMapper {

	public List<Investment> selectInvestments(Investment searchVO);
	
	public int insertInvestmentTransaction(Investment investment);
	
	public int selectNewTid();

	public int selectInvestorCount(Product searchVO);
}
