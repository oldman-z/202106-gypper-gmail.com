package kakaopay.investing.investment.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kakaopay.investing.model.Investment;

@SpringBootTest
public class InvestmentServiceTest {
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(100);

	@Autowired
	InvestmentService investmentService;
	
	@Test
	public void investProduct() throws Exception {
		
		CountDownLatch latch = new CountDownLatch(100);
		for(int i=0; i < 100; i++) {
			
			Investment investment = new Investment();
			
			investment.setProductId(1);
			investment.setCustomerId(i);
			investment.setTid(i);
			investment.setInvestingAmount(100);
			
			executor.execute(() -> {
				try {
					investmentService.investProduct(investment);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				latch.countDown();
			});
			
		}

		latch.await();
	}
	
}
