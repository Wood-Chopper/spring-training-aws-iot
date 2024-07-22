package be.navez.training.aws.iot.domain.exception;

public class ConfigurationNotFoundException extends RuntimeException {
	public ConfigurationNotFoundException(String message) {
		super(message);
	}
}
