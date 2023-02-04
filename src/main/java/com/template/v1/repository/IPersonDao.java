package com.template.v1.repository;

import com.template.v1.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonDao extends JpaRepository<Person, Long> {

    Person findUserByMsisdn(String msisdn);

    void deleteUserByMsisdn(String msisdn);

}
