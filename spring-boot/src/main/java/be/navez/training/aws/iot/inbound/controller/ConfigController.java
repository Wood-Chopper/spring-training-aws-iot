package be.navez.training.aws.iot.inbound.controller;

import be.navez.training.aws.iot.domain.model.MyConfiguration;
import be.navez.training.aws.iot.inbound.dto.ConfigurationDto;
import be.navez.training.aws.iot.domain.service.ConfigService;
import be.navez.training.aws.iot.inbound.mapper.ConfigurationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

@RequestMapping("config")
@RestController
@RequiredArgsConstructor
public class ConfigController {
	private final ConfigService configService;
	private final WebApplicationContext webApplicationContext;
	private final ConfigurationDtoMapper configurationDtoMapper = ConfigurationDtoMapper.INSTANCE;

	@PreAuthorize("hasAuthority('WRITE') && @configService.hasAccess(#s)")//SpEL // RBAC
	@PostMapping
	public String postConfig(@RequestBody ConfigurationDto config, Principal principal, @RequestHeader("SomeHeader") String s) {
		System.out.println(principal.getName());
		final Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final MyConfiguration model = configurationDtoMapper.toModel(config);
		return configService.postConfig(model);
	}

	//@PreAuthorize("hasPermission(#id, 'READ')")//ReBAC
	@GetMapping("{id}")
	public ResponseEntity<ConfigurationDto> getConfig(@PathVariable String id) {
		return ResponseEntity.of(configService.getConfig(id).map(configurationDtoMapper::toDto));
	}

	@PutMapping("{id}")
	public void updateConfig(@PathVariable String id, @RequestBody ConfigurationDto config) {
		final MyConfiguration model = configurationDtoMapper.toModel(config);
		configService.updateConfig(id, model);
	}

	@PutMapping("{id}/apply")
	public String apply(@PathVariable String id) {
		return configService.apply(id);
	}
}
