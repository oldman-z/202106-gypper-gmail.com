package kakaopay.investing.product.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kakaopay.investing.common.code.ProductStatus;
import kakaopay.investing.common.code.ResultCode;
import kakaopay.investing.exception.BaseException;
import kakaopay.investing.exception.NotFoundException;
import kakaopay.investing.exception.OverAmountException;
import kakaopay.investing.mapper.ProductMapper;
import kakaopay.investing.model.Product;
import kakaopay.investing.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired 
	private ProductMapper productMapper;

	@Override
	public List<Product> getAllProducts() throws Exception {
		
		return productMapper.selectAllProducts();
	}

	@Override
	public void checkProductStatus(Product searchVO) throws Exception {
		
		Product product = productMapper.selectProduct(searchVO);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		Date now = new Date();
		Date startedAt  = sdf.parse(product.getStartedAt());
		Date finishedAt = sdf.parse(product.getFinishedAt());
		
		if(product == null || now.before(startedAt) || now.after(finishedAt)) {
			throw new NotFoundException();
			
		} else if(product.getTotalInvestingAmount() < searchVO.getInvestingAmount() + product.getCurrentInvestingAmount()) {
			throw new OverAmountException();
			
		}  else if(product.getStatus().equals(ProductStatus.COMPLETED.name())) {
			throw new BaseException(ResultCode.PRODUCT_SOLD_OUT);
			
		}
	}
}
