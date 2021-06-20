package kakaopay.investing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages="kakaopay.investing.mapper")
@ComponentScan(basePackages="kakaopay.investing")
@Configuration
@SpringBootApplication
public class SimpleInvestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleInvestingApplication.class, args);
	}

}
