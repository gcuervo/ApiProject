package com.kiritsu.debts.domain.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Person extends PersistentEntity {

	private String _name;
	private List<Debt> _debts = Lists.newLinkedList();

	public Person(String name) {
		_name = name;
	}

	public String name() {
		return _name;
	}

	public List<Debt> debts() {
		return _debts;
	}

	public Person addDebt(Debt debt) {
		debts().add(debt);
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		return name().equals(other.name());
	}

	@Override
	public int hashCode() {
		return name().hashCode();
	}
}
