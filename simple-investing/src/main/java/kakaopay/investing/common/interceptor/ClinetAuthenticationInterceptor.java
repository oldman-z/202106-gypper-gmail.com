package kakaopay.investing.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import kakaopay.investing.exception.AuthenticationException;

/**
 * 사용자 검증을 위한 interceptor 로 오류가 있을 경우 @ControllerAdvice 에서 처리함
 * Http Header 의 "X-USER-ID" 을 검사하고, 정상이면 RequestBody 로 받을 수 있도록 attribute 로 등록함
 */
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
