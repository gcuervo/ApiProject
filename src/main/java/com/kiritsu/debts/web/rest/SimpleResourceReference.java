package com.kiritsu.debts.web.rest;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

import com.google.common.base.Preconditions;

@SuppressWarnings("serial")
public class SimpleResourceReference extends ResourceReference {

	private final IResource _reference;

	public SimpleResourceReference(String name, IResource reference) {
		super(name);
		_reference = Preconditions.checkNotNull(reference);
	}

	@Override
	public IResource getResource() {
		return _reference;
	}
}
