package com.template.v1.repository;

import com.template.v1.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientDao extends JpaRepository<Client, Long> {
}
