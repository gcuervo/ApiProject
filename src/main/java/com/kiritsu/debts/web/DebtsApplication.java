package com.kiritsu.debts.web;

import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.string.StringValue;
import org.springframework.stereotype.Component;

import com.kiritsu.debts.web.page.HomePage;
import com.kiritsu.debts.web.page.res.ApplicationResources;
import com.kiritsu.debts.web.rest.DebtsRestResource;
import com.kiritsu.debts.web.rest.PersonRestResource;
import com.kiritsu.debts.web.rest.SimpleResourceReference;

@SuppressWarnings("serial")
@Component
public class DebtsApplication extends WebApplication {

	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		mountResource("/persons", new SimpleResourceReference("persons", new PersonRestResource()));
		mountResource("/debts", new SimpleResourceReference("rests", new DebtsRestResource()));
		// Css
		mountResource("/st/css/exmaple.css", new JavaScriptResourceReference(ApplicationResources.class, "css/example.css"));
		// Js
		mountResource("/st/js/jquery-1.11.1.min.js", new JavaScriptResourceReference(ApplicationResources.class, "js/jquery-1.11.1.min.js"));
		mountResource("/st/js/example.js", new JavaScriptResourceReference(ApplicationResources.class, "js/example.js"));
		// All images
		mountResource("/st/img/${name}", new DynamicResourceReference(ApplicationResources.class, "img/"));
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	private class DynamicResourceReference extends ResourceReference {

		public DynamicResourceReference(Class<?> scope, String name) {
			super(scope, name);
		}

		@Override
		public IResource getResource() {
			return new DynamicResource(getScope(), getName());
		}

	}

	private class DynamicResource extends DynamicImageResource {

		private final Class<?> _scope;
		private final String _folder;

		public DynamicResource(Class<?> scope, String folder) {
			_scope = scope;
			_folder = folder;
		}

		@Override
		protected byte[] getImageData(Attributes attributes) {
			PageParameters parameters = attributes.getParameters();
			StringValue name = parameters.get("name");
			byte[] imageBytes = null;
			if (!name.isEmpty()) {
				try {
					InputStream is = _scope.getResourceAsStream(_folder + name);
					if (is != null) {
						imageBytes = IOUtils.toByteArray(is);
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return imageBytes;
		}

		@Override
		public boolean equals(Object that) {
			return that instanceof DynamicResourceReference;
		}

	}

}
