package be.navez.training.aws.iot.domain.model;

import lombok.Builder;

@lombok.Data
@Builder
public class MyData {
	private int pollInterval;
}
