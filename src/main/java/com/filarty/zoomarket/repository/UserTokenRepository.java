package com.filarty.zoomarket.repository;

import com.filarty.zoomarket.models.EmailToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<EmailToken, Long> {
    EmailToken findEmailTokenByToken(String token);
}
