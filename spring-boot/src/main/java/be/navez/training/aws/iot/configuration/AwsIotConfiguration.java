package be.navez.training.aws.iot.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.iot.IotClient;

@Configuration
@EnableConfigurationProperties(value = { AwsConfigurationProperties.class })
public class AwsIotConfiguration {

	@Bean
	public IotClient iotClient() {
		return IotClient.create();
	}

}
