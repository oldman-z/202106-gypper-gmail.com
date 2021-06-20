# 개 요
 
* project   : 카카오페이 서버 개발 과제
* framework : Spring Boot 2.4.7
* sdk       : JDK 1.8
* database  : h2 Database

# 프로그램 시작

### 실행
`kakaopay.investing.SimpleInvestingApplication`

### DB 생성 및 기본 자료 등록
최초 실행 시 initialization-mode: always 로 설정하여 DB 를 설정함

    datasource:
      initialization-mode: never
      schema: classpath:h2/schema.sql
      data: classpath:h2/data.sql
      hikari:
        jdbc-url: jdbc:h2:~/testdb
        driver-class-name: org.h2.Driver
        username: sa
        password:

# DB 정보

#### DB 생성 스크립트 참조

     schema: classpath:h2/schema.sql
     data: classpath:h2/data.sql


### 테이블 레이아웃

1. product : 투자상품

| column name | type | comments |
|-------------------------|-------------|-------------------------------------------|
| product_id | int | 상품ID |
| title | varchar(50) | 투자명 |
| total_investing_amount | decimal | 총 투자 모집금액 |
| currnt_investing_amount | decimal | 현재까지 모집금액 |
| status | varchar(10) | 모집상태(PROCEEDING:모집중, COMPLETED:모집완료) |
| started_at | timestamp | 투자시작일시 |
| finished_at | timestamp | 투자종료일시 |
| last_tid | int | 마지막 트랜젝션ID |

2. investment_transaction : 투자내역

| column name      | type         | comments        |
|------------------|--------------|-----------------|
| tid              | int          | 거래 트랜젝션ID |
| transaction_date | varchar(8)   | 거래일자        |
| transaction_time | varchar(6)   | 거래시간        |
| customer_id      | int          | 사용자 식별값   |
| product_id       | int          | 상품ID          |
| investing_amount | decimal      | 투자금액        |
| result_code      | varchar(4)   | 결과코드        |
| result_message   | varchar(100) | 결과메세지      |

# API 정보
### API 목록

| No. | API name | URI | method | remark |
|-----|----------|-----|--------|--------|
| 1   |전체상품목록  |/investment/products     |GET        |기간 내 상품만 조회|
| 2   |상품투자     |/investment/invest     |PUT        |        |
| 3   |내투자내역조회 |/investment/invest     |GET        |        |

### API 상세
| No. |   |   |   |   |
|-----|---|---|---|---|
| 1   |   |   |   |   |
| 2   |   |   |   |   |
| 3   |   |   |   |   |

# 코드 내용
### 사용자 인증
Interceptor 로 처리하여 http header 값을 인증함
`ClinetAuthenticationInterceptor`

```java
@Component
public class ClinetAuthenticationInterceptor implements HandlerInterceptor, AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String customerId = request.getHeader("X-USER-ID");
		
		/**
		 * 사용자 테이블을 별도 생성하지 않아
		 * 사용자 식별값이 있고, 숫자인가만을 검증함
		 */
		if(customerId == null || customerId.isEmpty() || 
				!customerId.chars().allMatch( Character::isDigit )) {
			
			throw new AuthenticationException();
		}
		
		request.setAttribute("customerId", customerId);
		
		return true;
	}

}
```

### 동시 처리
Product 테이블에 Row Lock 시간을 최소화 하기 위해 transaction 시작 전 유효성 검증을 끝낸 후 투자금액을 계산 업데이트함
```java
		productService.checkProductStatus(product); //<-- 투자 가능 상태 확인
		
		ResultCode resultCode = investmentService.investProduct(investment);
```

```java
@Override
	@Transactional
	public ResultCode investProduct(Investment investment) throws Exception {
 
 //...
 int result = productMapper.updateProduct(product);
 
 //...
 investmentMapper.insertInvestmentTransaction(investment);
```
