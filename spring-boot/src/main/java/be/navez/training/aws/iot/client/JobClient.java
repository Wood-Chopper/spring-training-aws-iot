package be.navez.training.aws.iot.client;

import be.navez.training.aws.iot.aop.Timed;
import be.navez.training.aws.iot.configuration.AwsConfigurationProperties;
import be.navez.training.aws.iot.domain.model.MyConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.CreateJobRequest;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobClient {

	private final IotClient iotClient;
	private final AwsConfigurationProperties awsConfigurationProperties;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Timed(34)
	public String sendConfiguration(MyConfiguration configuration) {

		final String jsonJob;
		try {
			jsonJob = objectMapper.writeValueAsString(configuration);
		} catch (JsonProcessingException e) {
			log.warn("Error while transforming to JSON");
			throw new RuntimeException(e);
		}

		final UUID uuid = UUID.randomUUID();
		final String template = "arn:aws:iot:{region}:{accountId}:thing/JeromeDevice";
		final String interpolated = template
				.replace("{region}", awsConfigurationProperties.getRegion())
				.replace("{accountId}", awsConfigurationProperties.getAccount());
		iotClient.createJob(CreateJobRequest.builder()
									.jobId(uuid.toString())
									.document(jsonJob)
									.targets(interpolated)
									.build());

		return uuid.toString();
	}
}
