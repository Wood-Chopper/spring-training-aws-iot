package be.navez.training.aws.iot.persistence.mapper;

import be.navez.training.aws.iot.domain.model.MyConfiguration;
import be.navez.training.aws.iot.inbound.dto.ConfigurationDto;
import be.navez.training.aws.iot.persistence.entity.ConfigurationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConfigurationEntityMapper {

	ConfigurationEntityMapper INSTANCE = Mappers.getMapper(ConfigurationEntityMapper.class);

	@Mapping(target = "networkConfigs", source = "networks")
	@Mapping(target = "dataConfig", source = "data")
	ConfigurationEntity toEntity(MyConfiguration configuration);

	@Mapping(target = "networks", source = "networkConfigs")
	@Mapping(target = "data", source = "dataConfig")
	MyConfiguration toModel(ConfigurationEntity configuration);

}
