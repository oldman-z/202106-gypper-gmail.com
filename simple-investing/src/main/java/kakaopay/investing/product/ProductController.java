package kakaopay.investing.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kakaopay.investing.model.Product;
import kakaopay.investing.product.service.ProductService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/investment/products")
	public List<Product> getAllProducts(HttpServletRequest request) throws Exception {
		
		
		return productService.getAllProducts();
	}
}
