package kakaopay.investing.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import kakaopay.investing.common.code.ResultCode;
import kakaopay.investing.exception.AuthenticationException;
import kakaopay.investing.exception.BaseException;
import kakaopay.investing.exception.InvalidParameterException;
import kakaopay.investing.exception.error.ResponseError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ControllerErrorHandler {
	
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ResponseError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	
    	log.error("handleHttpRequestMethodNotSupportedException", e);
    	
        final ResponseError response = ResponseError
                        .create()
                        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .message(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
	@ExceptionHandler(InvalidParameterException.class)
	protected ResponseEntity<ResponseError> handleInvalidParameterException(InvalidParameterException e) {

		log.error("handleInvalidParameterException", e);

		ResultCode resultCode = e.getResultCode();

		final ResponseError response = ResponseError
				.create()
				.status(resultCode.getStatus())
				.code(resultCode.getCode())
				.message(resultCode.getMessage())
				.errors(e.getErrors());

		return new ResponseEntity<>(response, HttpStatus.resolve(resultCode.getStatus()));
	}

	@ExceptionHandler(AuthenticationException.class)
	protected ResponseEntity<ResponseError> handleAuthenticationException(AuthenticationException e) {
		
		log.error("handleAuthenticationException", e);
		
		ResultCode resultCode = e.getResultCode();
		
		final ResponseError response = ResponseError
				.create()
				.status(resultCode.getStatus())
				.code(resultCode.getCode())
				.message(resultCode.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.resolve(resultCode.getStatus()));
	}

	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<ResponseError> handleBaseException(BaseException e) {

		log.error("handleBaseException", e);

		ResultCode resultCode = e.getResultCode();

		ResponseError response = ResponseError
				.create()
				.status(resultCode.getStatus())
				.code(resultCode.getCode())
				.message(resultCode.getMessage()==null?e.toString():resultCode.getMessage());

		return new ResponseEntity<>(response, HttpStatus.resolve(resultCode.getStatus()));
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ResponseError> handleException(Exception e) {

		log.error("handleException", e);

		ResponseError response = ResponseError
				.create().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(e.toString());

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
}
