package be.navez.training.aws.iot.service;

import be.navez.training.aws.iot.domain.service.ConfigService;
import be.navez.training.aws.iot.inbound.dto.ConfigurationDto;
import be.navez.training.aws.iot.persistence.entity.ConfigurationEntity;
import be.navez.training.aws.iot.persistence.repository.ConfigRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ConfigServiceTest {

	private final ConfigRepository configRepository = Mockito.mock(ConfigRepository.class);
	private final ConfigService configService = new ConfigService(configRepository);

	@Test
	void getConfig() {
		// Arrange (given)
		final String someId = "Some id";
		final ConfigurationEntity value = new ConfigurationEntity();
		value.setId(someId);
		Mockito.when(configRepository.findById(someId)).thenReturn(Optional.of(value));

		// Act (when)
		final ConfigurationDto config = configService.getConfig(someId);

		// Assert (Then)
		assertEquals(someId, config.getId());
	}

	@Test
	void given_anUnknownId_whenGetConfig_then_returnNull() {
		String unknowId = "some id";
		Mockito.when(configRepository.findById(unknowId)).thenReturn(Optional.empty());

		final ConfigurationDto config = configService.getConfig(unknowId);

		assertNull(config);
	}
}