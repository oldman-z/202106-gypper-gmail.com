package kakaopay.investing.product.service;

import java.util.List;

import kakaopay.investing.model.Product;

public interface ProductService {

	public List<Product> getAllProducts() throws Exception;
	
	public void checkProductStatus(Product product) throws Exception;   
}
