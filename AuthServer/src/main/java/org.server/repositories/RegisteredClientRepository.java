package org.server.repositories;

import jakarta.annotation.Nullable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface RegisteredClientRepository {
    @Nullable
    RegisteredClient findByLogin(String login);
}
