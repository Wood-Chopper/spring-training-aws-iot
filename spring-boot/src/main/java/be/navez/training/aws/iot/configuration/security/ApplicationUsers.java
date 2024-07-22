package be.navez.training.aws.iot.configuration.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("application")
@Getter
public class ApplicationUsers {
    private final List<ApplicationUser> users = new ArrayList<>();
}
