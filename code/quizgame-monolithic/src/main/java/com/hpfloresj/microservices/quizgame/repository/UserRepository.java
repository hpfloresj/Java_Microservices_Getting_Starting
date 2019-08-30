package com.hpfloresj.microservices.quizgame.repository;

import com.hpfloresj.microservices.quizgame.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAlias(final String alias);

}
