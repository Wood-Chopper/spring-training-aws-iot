package be.navez.training.aws.iot.configuration.security;

import lombok.Data;

@Data
public class ApplicationUser {
    private String username;
    private String password;
    private String[] authorities;
}
