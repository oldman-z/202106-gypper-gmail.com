package kakaopay.investing.investment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kakaopay.investing.common.ResponseData;
import kakaopay.investing.common.code.ResultCode;
import kakaopay.investing.exception.InvalidParameterException;
import kakaopay.investing.investment.service.InvestmentService;
import kakaopay.investing.model.Investment;
import kakaopay.investing.model.Product;
import kakaopay.investing.product.service.ProductService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class InvestmentCotroller {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private InvestmentService investmentService;

	@GetMapping(value = "/investment/invest")
	public List<Investment> getMyInvestments(Investment investment, 
			HttpServletRequest request) throws Exception {
		
		return investmentService.getMyInvestments(investment);
		
	}

	@PutMapping(value = "/investment/invest")
	public ResponseEntity<ResponseData> invest(@Valid Investment investment
			, BindingResult result, HttpServletRequest request) throws Exception {
		
		if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }
		
		Product product = new Product();
		product.setProductId(investment.getProductId());
		
		productService.checkProductStatus(product);
		
		
		ResultCode resultCode = investmentService.investProduct(investment);
		
		final ResponseData response = ResponseData
				.create()
				.status(resultCode.getStatus())
				.code(resultCode.getCode())
				.message(resultCode.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.resolve(resultCode.getStatus()));
	}
}
