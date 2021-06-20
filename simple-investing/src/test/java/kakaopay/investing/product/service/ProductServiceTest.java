package kakaopay.investing.product.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kakaopay.investing.common.code.CodeMap;
import kakaopay.investing.common.code.ProductStatus;
import kakaopay.investing.model.Product;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	ProductService productService;
	
	@Test
	public void getAllProducts() throws Exception {
		List<Product> products = productService.getAllProducts();
		Assert.assertEquals("", products.size(), 2);
	}
	
	@Test
	public void investingProduct() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		System.out.println(ProductStatus.PROCEEDING.name());
//		System.out.println(ProductStatus.PROCEEDING.getStatusName());
		
		Map<String, String> map = CodeMap.getCodeMap(ProductStatus.class);
		System.out.println(map.get(ProductStatus.PROCEEDING.name()));
	}
}
