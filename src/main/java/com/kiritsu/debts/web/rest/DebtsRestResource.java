package com.kiritsu.debts.web.rest;

import java.math.BigDecimal;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.resource.gson.GsonRestResource;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.kiritsu.debts.domain.entity.Debt;
import com.kiritsu.debts.domain.entity.Person;
import com.kiritsu.debts.domain.service.PersonService;
import com.kiritsu.debts.web.rest.pojo.DebtResume;
import com.kiritsu.debts.web.rest.pojo.PersonResume;
import com.kiritsu.util.BigDecimals;

@SuppressWarnings("serial")
public class DebtsRestResource extends GsonRestResource {

	@SpringBean
	private PersonService persons;

	public DebtsRestResource() {
		Injector.get().inject(this);
	}

	@MethodMapping("/all")
	public List<PersonResume> findAll() {
		return Lists.transform(persons.findAll(), new BuildPersonResume());
	}

	@MethodMapping("/find/{name}")
	public Optional<PersonResume> find(String name) {
		return persons.findByName(name).transform(new BuildPersonResume());
	}

	@MethodMapping("/create/{name}/{amount}/{debtorName}")
	public int create(String name, BigDecimal amount, String debtorName) {
		Optional<Person> person = persons.findByName(name);
		Optional<Person> debtor = persons.findByName(debtorName);
		if (amount == null || BigDecimals.isNegative(amount)) {
			return 1;
		}
		if (!person.isPresent()) {
			return 2;
		}
		if (!debtor.isPresent()) {
			return 3;
		}
		person.get().addDebt(new Debt(debtor.get(), amount));
		return 0;
	}

	@MethodMapping("/clear/{name}")
	public int clear(String name) {
		Optional<Person> person = persons.findByName(name);
		if (!person.isPresent()) {
			return 1;
		}
		person.get().debts().clear();
		return 0;
	}

	private static class BuildPersonResume implements Function<Person, PersonResume> {

		@Override
		public PersonResume apply(Person input) {
			PersonResume person = new PersonResume();
			person.setName(input.name());
			for (Debt debt : input.debts()) {
				person.getDebts().add(new DebtResume(debt.debtor().name(), debt.amount()));
			}
			return person;
		}

	}
}
