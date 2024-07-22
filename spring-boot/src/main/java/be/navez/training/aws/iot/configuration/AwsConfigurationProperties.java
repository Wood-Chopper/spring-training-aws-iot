package be.navez.training.aws.iot.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "config.aws")
public class AwsConfigurationProperties {
	private String account;

	private String region;
}
