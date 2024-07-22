package be.navez.training.aws.iot.domain.model;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@lombok.Data
@Builder
public class MyConfiguration {

	private String id;

	@Singular
	private List<MyNetwork> networks;

	private MyData data;
}
