package com.kiritsu.debts.web.rest.pojo;

import java.math.BigDecimal;

public class DebtResume {

	private String name;
	private BigDecimal amount;

	public DebtResume(String name, BigDecimal amount) {
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}
