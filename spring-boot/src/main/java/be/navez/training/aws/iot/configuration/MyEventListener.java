package be.navez.training.aws.iot.configuration;

import be.navez.training.aws.iot.domain.model.MyConfiguration;
import be.navez.training.aws.iot.domain.model.MyData;
import be.navez.training.aws.iot.domain.model.MyNetwork;
import be.navez.training.aws.iot.domain.service.ConfigService;
import be.navez.training.aws.iot.persistence.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MyEventListener {

	private final ConfigRepository configRepository;
	private final ConfigService configService;

	//@EventListener(ApplicationStartedEvent.class)
	private void onApplicationStarted() {

		System.out.println("==================================");
		configRepository.findByDataConfig_PollInterval(60);
		System.out.println("==================================");

		System.out.println("==================================");
		configRepository.findByNetworkConfigs_Address("AZEAZE");
		System.out.println("==================================");

		System.out.println("==================================");
		configRepository.myOwnMethodName(60, "AZEAZE");
		System.out.println("==================================");

		System.out.println("==================================");
		configRepository.findByDataConfig_PollIntervalOrNetworkConfigs_AddressAndField(60, "AZEAZE", "field");
		System.out.println("==================================");
		System.out.println("==================================");

		System.out.println("==================================");
		configRepository.findByDataConfig_PollIntervalGreaterThan(60);
		System.out.println("==================================");

		System.out.println("==================================");
		configRepository.getSomeConfigsBasedOnStuffs("", "", 0);
		System.out.println("==================================");
	}

	@EventListener(ApplicationStartedEvent.class)
	public void applicationStarted() {
		final MyConfiguration configuration1 = MyConfiguration.builder()
				.id("1")
				.network(MyNetwork.builder().address("address1.1").build())
				.network(MyNetwork.builder().address("address1.2").build())
				.data(MyData.builder().pollInterval(12).build())
				.build();
		final MyConfiguration configuration2 = MyConfiguration.builder()
				.id("2")
				.network(MyNetwork.builder().address("address2.1").build())
				.network(MyNetwork.builder().address("address2.2").build())
				.network(MyNetwork.builder().address("address2.3").build())
				.data(MyData.builder().pollInterval(22).build())
				.build();
		final MyConfiguration configuration3 = MyConfiguration.builder()
				.id("3")
				.network(MyNetwork.builder().address("address3.1").build())
				.network(MyNetwork.builder().address("address3.2").build())
				.network(MyNetwork.builder().address("address3.3").build())
				.data(MyData.builder().pollInterval(32).build())
				.build();
		final MyConfiguration configuration4 = MyConfiguration.builder()
				.id("4")
				.network(MyNetwork.builder().address("address4.1").build())
				.network(MyNetwork.builder().address("address4.2").build())
				.network(MyNetwork.builder().address("address4.3").build())
				.network(MyNetwork.builder().address("address4.4").build())
				.data(MyData.builder().pollInterval(42).build())
				.build();
		final MyConfiguration configuration5 = MyConfiguration.builder()
				.id("6")
				.network(MyNetwork.builder().address("address4.1").build())
				.network(MyNetwork.builder().address("address4.2").build())
				.network(MyNetwork.builder().address("address4.3").build())
				.network(MyNetwork.builder().address("address4.4").build())
				.data(null)
				.build();

		final List<MyConfiguration> list = List.of(
				configuration1,
				configuration2,
				configuration3,
				configuration4
		);

		// Create a stream of those elements
		list.stream();

		// Returns all the ids in a list
		final List<String> list1 = list.stream().map(config -> config.getId()).toList();
		System.out.println(list1);

		final List<String> list2 = list.stream().map(MyConfiguration::getId).toList();
		System.out.println(list2);

		// "Traditional way" (before Java 8)
		List<String> traditionalList = new ArrayList<>();
		for (MyConfiguration myConfiguration: list) {
			traditionalList.add(myConfiguration.getId());
		}
		System.out.println(traditionalList);

		// Filter and print the configuration with ID "3"
		final List<MyConfiguration> list3 = list.stream().filter(configuration -> "3".equals(configuration.getId())).toList();
		System.out.println(list3);

		final Optional<MyConfiguration> optional1 = list.stream().filter(configuration -> "3".equals(configuration.getId())).findFirst();
		optional1.ifPresent(configuration -> System.out.println(configuration));

		// Before streams
		MyConfiguration config1 = null;

		for (MyConfiguration myConfiguration: list) {
			if (myConfiguration.getId().equals("3")) {
				config1 = myConfiguration;
				break; // Beurk!
			}
		}

		if (config1 != null) {
			System.out.println(config1);
		}

		// Get all the poll intervals as list
		final List<Integer> list4 = list.stream().map(configuration -> configuration.getData().getPollInterval()).toList();
		final List<Integer> list5 = list.stream()
				.map(MyConfiguration::getData)
				.filter(Objects::nonNull)
				.map(MyData::getPollInterval)
				.toList();

		// Get all configurations where poll interval is a multiple of 3 (x % 3 == 0)
		list.stream().filter(config -> config.getData() != null && config.getData().getPollInterval() % 3 == 0).toList();

		// Overkill
		list.stream().map(config -> Optional.of(config).filter(config2 -> config.getData() != null))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.filter(config -> config.getData().getPollInterval() % 3 == 0)
				.toList();

		// Find the configuration with ID "5" or return a default configuration object if not found
		list.stream().filter(config -> "5".equals(config.getId())).findFirst().orElseGet(() -> MyConfiguration.builder().id("5").build());

		// Return all the Network configurations as a single List
		list.stream().flatMap(config -> config.getNetworks().stream()).toList();
		list.stream().map(MyConfiguration::getNetworks)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.filter(Objects::nonNull)
				.toList();

		// Return the first network configuration where the address contains ".3"
		list.stream().map(MyConfiguration::getNetworks)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.filter(Objects::nonNull)
				.filter(net -> net.getAddress().contains(".3"))
				.findFirst();

		// Combine two streams
		final Stream<MyConfiguration> stream1 = Stream.of(configuration1, configuration2);
		final Stream<MyConfiguration> stream2 = Stream.of(configuration3, configuration4);

		// Good
		final Stream<MyConfiguration> concatStream = Stream.concat(stream1, stream2);
		//Less good but still good
		Stream.of(
				Stream.of(configuration1, configuration2),
				Stream.of(configuration3, configuration4)
		).flatMap(a -> a).toList();

		// Stream to Map<String, Config>
		final Map<String, MyConfiguration> map = concatStream.collect(Collectors.toMap(c -> c.getId(), c -> c));

		// skip, limit, count, distinct, sorted
		Stream.of(configuration1, configuration2).count();
		Stream.of(configuration3, configuration4).sorted(Comparator.comparing(MyConfiguration::getId).reversed());
	}
}
