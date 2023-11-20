package com.example.demo.dto;

import io.micrometer.common.lang.NonNull;

public record CredentialsDto( @NonNull String login, @NonNull String password) {

}
