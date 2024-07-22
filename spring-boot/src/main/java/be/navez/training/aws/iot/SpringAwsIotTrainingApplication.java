package be.navez.training.aws.iot;

import be.navez.training.aws.iot.configuration.AwsConfigurationProperties;
import be.navez.training.aws.iot.configuration.AwsIotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringAwsIotTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAwsIotTrainingApplication.class, args);
	}

}
