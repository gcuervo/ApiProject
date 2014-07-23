package com.kiritsu.debts.domain.entity;

import java.math.BigDecimal;

import com.google.common.base.Preconditions;
import com.kiritsu.util.BigDecimals;

public class Debt extends PersistentEntity {

	private Person _debtor;
	private BigDecimal _amount;

	public Debt(Person debtor, BigDecimal amount) {
		_debtor = Preconditions.checkNotNull(debtor);
		_amount = Preconditions.checkNotNull(amount);
		Preconditions.checkArgument(BigDecimals.isPositive(amount()));
	}

	public BigDecimal amount() {
		return _amount;
	}

	public Person debtor() {
		return _debtor;
	}
}
