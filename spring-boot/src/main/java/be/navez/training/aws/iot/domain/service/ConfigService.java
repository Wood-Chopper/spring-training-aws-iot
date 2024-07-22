package be.navez.training.aws.iot.domain.service;

import be.navez.training.aws.iot.client.JobClient;
import be.navez.training.aws.iot.domain.exception.ConfigurationNotFoundException;
import be.navez.training.aws.iot.domain.model.MyConfiguration;
import be.navez.training.aws.iot.inbound.dto.ConfigurationDto;
import be.navez.training.aws.iot.persistence.entity.ConfigurationEntity;
import be.navez.training.aws.iot.persistence.mapper.ConfigurationEntityMapper;
import be.navez.training.aws.iot.persistence.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfigService {
	private final ConfigRepository configRepository;
	private final JobClient jobClient;
	private ConfigurationEntityMapper configurationEntityMapper = ConfigurationEntityMapper.INSTANCE;

	public boolean hasAccess(String s) {
		return true;
	}

	public String postConfig(MyConfiguration config) {
		final ConfigurationEntity entity = configurationEntityMapper.toEntity(config);

		final String id = UUID.randomUUID().toString();
		entity.setId(id);

		configRepository.save(entity);

		return id;
	}

	public Optional<MyConfiguration> getConfig(String id) {
		return configRepository.findById(id)
				.map(configurationEntityMapper::toModel);
	}

	@Transactional
	public void updateConfig(String id, final MyConfiguration config) {
		final ConfigurationEntity entity = configurationEntityMapper.toEntity(config);

		entity.setId(id);

		configRepository.save(entity);
	}

	public String apply(final String id) {
		final MyConfiguration myConfiguration = configRepository.findById(id)
				.map(configurationEntityMapper::toModel)
				.orElseThrow(() -> new ConfigurationNotFoundException(id + " not found"));
		return jobClient.sendConfiguration(myConfiguration);
	}
}
