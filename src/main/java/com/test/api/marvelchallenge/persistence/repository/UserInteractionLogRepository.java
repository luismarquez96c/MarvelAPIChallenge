package com.test.api.marvelchallenge.persistence.repository;

import com.test.api.marvelchallenge.persistence.entity.UserInteractionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {

    Page<UserInteractionLog> findByUsername(Pageable pageable, String username);
}
