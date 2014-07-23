package com.kiritsu.debts.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.kiritsu.debts.domain.entity.Debt;
import com.kiritsu.debts.domain.entity.Person;

@Service
public class PersonService {

	private List<Person> persons = Lists.newLinkedList();
	
	public PersonService() {
		Person juan = new Person("Juan");
		Person gonzalo = new Person("Gonzalo");
		persons.add(juan);
		persons.add(gonzalo
			.addDebt(new Debt(juan, new BigDecimal(100)))
		);
		persons.add(new Person("Marcelo")
			.addDebt(new Debt(juan, new BigDecimal(38.5f)))
			.addDebt(new Debt(gonzalo, new BigDecimal(22.5f)))
		);
	}

	public Optional<Person> findByName(final String name) {
		return Iterables.tryFind(findAll(), new Predicate<Person>() {
			@Override
			public boolean apply(Person input) {
				return input.name().equals(name);
			}
		});
	}

	public List<Person> findAll() {
		return persons;
	}

	public void save(Person person) {
		persons.add(person);
	}

	public void delete(Person person) {
		findAll().remove(person);
	}
}
