package be.navez.training.aws.iot.inbound.mapper;

import be.navez.training.aws.iot.domain.model.MyConfiguration;
import be.navez.training.aws.iot.inbound.dto.ConfigurationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConfigurationDtoMapper {

	ConfigurationDtoMapper INSTANCE = Mappers.getMapper(ConfigurationDtoMapper.class);

	MyConfiguration toModel(ConfigurationDto configurationDto);

	ConfigurationDto toDto(MyConfiguration configuration);

}
