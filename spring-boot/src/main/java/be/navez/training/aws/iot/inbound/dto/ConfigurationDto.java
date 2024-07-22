package be.navez.training.aws.iot.inbound.dto;

import lombok.Data;

import java.util.List;

@Data
public class ConfigurationDto {

	private String id;

	private List<NetworkDto> networks;

	private DataDto data;
}
