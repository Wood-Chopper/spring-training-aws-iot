package be.navez.training.aws.iot.persistence.repository;

import be.navez.training.aws.iot.persistence.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigurationEntity, String> {

	// SELECT * FROM CONFIGURATION WHERE poll_interval = ?1
	List<ConfigurationEntity> findByDataConfig_PollInterval(int pollInterval);

	// SELECT * FROM CONFIGURATION LEFT JOIN NETWORK WHERE NETWORK.ADDRESS = ?1
	List<ConfigurationEntity> findByNetworkConfigs_Address(String address);

	List<ConfigurationEntity> findByDataConfig_PollIntervalGreaterThan(int pollInterval);

	// SELECT * FROM CONFIGURATION LEFT JOIN NETWORK WHERE NETWORK.ADDRESS = ?1 AND poll_interval = ?1
	List<ConfigurationEntity> findByDataConfig_PollIntervalAndNetworkConfigs_Address(int pollInterval, String address);

	// SELECT * FROM CONFIGURATION LEFT JOIN NETWORK WHERE NETWORK.ADDRESS = ?1 AND poll_interval = ?1
	List<ConfigurationEntity> findByDataConfig_PollIntervalOrNetworkConfigs_AddressAndField(int pollInterval, String address, String field);

	// JPQL Java Persistence Query Language
	@Query("SELECT c FROM ConfigurationEntity c JOIN NetworkEntity n ON n.configuration = c WHERE n.address = :address OR ( c.field = :field AND c.dataConfig.pollInterval = :poll )")
	List<ConfigurationEntity> getSomeConfigsBasedOnStuffs(@Param("address") String address, @Param("field") String field, @Param("poll") int fieldr);


	@Query(value = "SELECT C FROM CONFIGURATIONS C", nativeQuery = true)
	List<ConfigurationEntity> getSomeConfigsBasedOnStuffs2();

	

	default List<ConfigurationEntity> myOwnMethodName(int pollInterval, String address) {
		return this.findByDataConfig_PollIntervalAndNetworkConfigs_Address(pollInterval, address);
	}
}
