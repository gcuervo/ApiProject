package com.kiritsu.debts.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

import com.kiritsu.debts.web.page.HomePage;
import com.kiritsu.debts.web.rest.DebtsRestResource;
import com.kiritsu.debts.web.rest.PersonRestResource;
import com.kiritsu.debts.web.rest.SimpleResourceReference;

@Component
public class DebtsApplication extends WebApplication {

	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		mountResource("/persons", new SimpleResourceReference("persons", new PersonRestResource()));
		mountResource("/debts", new SimpleResourceReference("rests", new DebtsRestResource()));
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

}
