package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Person;

import java.util.Collection;

public interface UserWebClient {

    Collection<Person> getPersons();

    Person getPerson(long id);
}

