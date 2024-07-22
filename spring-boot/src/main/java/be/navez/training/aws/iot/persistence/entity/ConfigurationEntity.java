package be.navez.training.aws.iot.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "CONFIGURATIONS")
@NoArgsConstructor
public class ConfigurationEntity {

	@Id
	private String id;

	@OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<NetworkEntity> networkConfigs;

	@Embedded
	private DataEntity dataConfig;

	private String field;

	public void setNetworkConfigs(List<NetworkEntity> networkConfigs) {
		this.networkConfigs = networkConfigs;
		this.networkConfigs.forEach(config -> config.setConfiguration(this));
	}
}

