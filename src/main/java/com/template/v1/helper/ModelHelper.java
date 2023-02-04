package com.template.v1.helper;

import com.template.v1.domain.model.Person;
import com.template.v1.domain.vo.PersonRequest;
import org.jetbrains.annotations.NotNull;

public class ModelHelper {

    private ModelHelper() {

    }

    public static @NotNull Person setModel(PersonRequest.@NotNull CreateRequest requestBody) {
        Person person = new Person();
        person.setMsisdn(requestBody.getMsisdn());
        person.setEmail(requestBody.getEmail());
        person.setFirstName(requestBody.getFirstName());
        person.setLastName(requestBody.getLastName());
        return person;
    }
}
