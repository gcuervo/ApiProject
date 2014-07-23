package com.kiritsu.debts.web.rest.pojo;

import java.util.List;

import com.google.common.collect.Lists;

public class PersonResume {

	private String name;
	private List<DebtResume> debts = Lists.newLinkedList();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<DebtResume> getDebts() {
		return debts;
	}
}
