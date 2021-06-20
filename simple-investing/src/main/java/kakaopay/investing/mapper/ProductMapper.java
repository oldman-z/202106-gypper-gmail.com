package kakaopay.investing.mapper;

import java.util.List;

import kakaopay.investing.model.Product;

public interface ProductMapper {

	public List<Product> selectAllProducts();
	
	public Product selectProduct(Product product);

	public Product selectProductForUpdate(Product product);

	public int updateProduct(Product product);

}
