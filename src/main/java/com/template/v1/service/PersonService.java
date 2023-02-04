package com.template.v1.service;

import com.template.v1.domain.contract.TemplateContract;
import com.template.v1.domain.model.Person;
import com.template.v1.repository.IPersonDao;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class PersonService implements TemplateContract.IPersonService {

    private final IPersonDao iPersonDao;

    @Autowired
    public PersonService(IPersonDao iPersonDao) {
        this.iPersonDao = iPersonDao;
    }

    @Override
    public void save(Person data) {
        iPersonDao.save(data);
    }

    @Override
    @CachePut(value = "person", key = "#person.msisdn")
    public void update(Person person) {
        if (person.getId() == null) {
            Person updatedPerson = iPersonDao.findUserByMsisdn(person.getMsisdn());
            person.setId(updatedPerson.getId());
        }
        iPersonDao.save(person);
    }

    @Override
    @CacheEvict(value = "person", key = "#data.msisdn")
    public void delete(Person data) {
        if (!Strings.isEmpty(data.getMsisdn())) {
            iPersonDao.deleteUserByMsisdn(data.getMsisdn());
        }
        if (data.getId() != null) {
            iPersonDao.deleteById(data.getId());
        }
        iPersonDao.delete(data);
    }

    @Override
    @CacheEvict(value = "person", key = "#msisdn")
    public void delete(String msisdn) {
        Person deletedData = iPersonDao.findUserByMsisdn(msisdn);
        iPersonDao.deleteById(deletedData.getId());
    }

    @Override
    @Cacheable(value = "person", key = "#id")
    public Person getPerson(Long id) {
        return iPersonDao.getById(id);
    }

    @Override
    @Cacheable(value = "person", key = "#msisdn")
    public Person getPerson(String msisdn) {
        return iPersonDao.findUserByMsisdn(msisdn);
    }
}
