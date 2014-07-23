package com.kiritsu.debts.web.rest;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.resource.gson.GsonRestResource;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kiritsu.debts.domain.entity.Person;
import com.kiritsu.debts.domain.service.PersonService;

@SuppressWarnings("serial")
public class PersonRestResource extends GsonRestResource {

	@SpringBean
	private PersonService persons;

	public PersonRestResource() {
		Injector.get().inject(this);
	}

	@MethodMapping("/all")
	public List<String> findAll() {
		return Lists.transform(persons.findAll(), new Function<Person, String>() {
			@Override
			public String apply(Person input) {
				return input.name();
			}
		});
	}

	@MethodMapping("/create/{name}")
	public int create(String name) {
		persons.save(persons.findByName(name).or(new Person(name)));
		return 0;
	}

	@MethodMapping("/delete/{name}")
	public int delete(String name) {
		persons.delete(persons.findByName(name).orNull());
		return 0;
	}
}
