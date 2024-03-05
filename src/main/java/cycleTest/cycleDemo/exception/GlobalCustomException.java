package cycleTest.cycleDemo.exception;

import cycleTest.cycleDemo.payloads.ApiErrorResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalCustomException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> globalException(Exception exception){
        ApiErrorResponse response= ApiErrorResponse.builder().error(true).
                message(exception.getLocalizedMessage()).build();
        exception.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestException.class})
    public  ResponseEntity<Object> badRequestException(BadRequestException badRequestException){
        ApiErrorResponse response=ApiErrorResponse.builder().error(true).message(badRequestException.getLocalizedMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ApiErrorResponse response=ApiErrorResponse.builder().error(true).message(resourceNotFoundException.getLocalizedMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InternalServerException.class})
    public  ResponseEntity<Object> internalServerException (InternalServerException internalServerException){
        ApiErrorResponse response= ApiErrorResponse.builder().error(true).message(internalServerException.getLocalizedMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> authenticationException(AuthenticationException authenticationException){
        ApiErrorResponse response= ApiErrorResponse.builder().error(true).message(authenticationException.getLocalizedMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }
}
