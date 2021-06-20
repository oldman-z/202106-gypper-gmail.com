package kakaopay.investing.investment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kakaopay.investing.common.code.ResultCode;
import kakaopay.investing.exception.OverAmountException;
import kakaopay.investing.investment.service.InvestmentService;
import kakaopay.investing.mapper.InvestmentMapper;
import kakaopay.investing.mapper.ProductMapper;
import kakaopay.investing.model.Investment;
import kakaopay.investing.model.Product;

@Service
public class InvestmentServiceImpl implements InvestmentService {
	
	@Autowired 
	private InvestmentMapper investmentMapper;
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	@Transactional
	public ResultCode investProduct(Investment investment) throws Exception {
		
		ResultCode resultCode = ResultCode.RESULT_OK;
		
		int tid = investmentMapper.selectNewTid();
		
		Product product = new Product();
		product.setProductId(investment.getProductId());
		product.setInvestingAmount(investment.getInvestingAmount());
		product.setLastTid(tid);
		
		int result = productMapper.updateProduct(product);
		
		if(result < 1) {
			resultCode = ResultCode.PRODUCT_OVER_AMOUNT;
		}
		
		investment.setTid(tid);
		investment.setInvestingAmount(investment.getInvestingAmount());
		investment.setResultCode(resultCode.getCode());
		investment.setResultMessage(resultCode.getMessage());

		investmentMapper.insertInvestmentTransaction(investment);
		
		if(resultCode.getCode().equals(ResultCode.PRODUCT_OVER_AMOUNT.getCode())) {
			throw new OverAmountException();
		}
		
		return resultCode;
	}

	@Override
	public List<Investment> getMyInvestments(Investment investment) throws Exception {
		
		return investmentMapper.selectInvestments(investment);
	}

}
