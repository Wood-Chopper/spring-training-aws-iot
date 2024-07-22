package be.navez.training.aws.iot.inbound.advisor;

import be.navez.training.aws.iot.domain.exception.ConfigurationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigurationControllerAdvisor {

	@ExceptionHandler(ConfigurationNotFoundException.class)
	public ErrorResponse handle(ConfigurationNotFoundException ex) {
		return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage()).build();
	}
}
