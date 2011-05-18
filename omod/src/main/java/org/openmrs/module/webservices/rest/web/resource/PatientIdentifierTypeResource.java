/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.webservices.rest.web.resource;

import java.util.List;

import org.openmrs.PatientIdentifierType;
import org.openmrs.annotation.Handler;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

/**
 * Allows standard CRUD for the {@link PatientIdentifierType} domain object
 */
@Resource("patientidentifiertype")
@Handler(supports=PatientIdentifierType.class, order=0)
public class PatientIdentifierTypeResource extends MetadataDelegatingCrudResource<PatientIdentifierType> {

	private PatientService service() {
		return Context.getPatientService();
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getRepresentationDescription(org.openmrs.module.webservices.rest.web.representation.Representation)
	 */
	@Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof DefaultRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("uri", findMethod("getUri"));
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("format");
			description.addProperty("formatDescription");
			description.addProperty("required");
			description.addProperty("checkDigit");
			description.addProperty("validator");
			//description.addProperty("locationBehavior");
			return description;
		} else if (rep instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("uri", findMethod("getUri"));
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("format");
			description.addProperty("formatDescription");
			description.addProperty("required");
			description.addProperty("checkDigit");
			description.addProperty("validator");
			//description.addProperty("locationBehavior");
			description.addProperty("auditInfo", findMethod("getAuditInfo"));
			return description;
		}
		return null;
    }
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getByUniqueId(java.lang.String)
	 */
	@Override
    public PatientIdentifierType getByUniqueId(String uniqueId) {
	    return service().getPatientIdentifierTypeByUuid(uniqueId);
    }
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#doGetAll(org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	protected List<PatientIdentifierType> doGetAll(RequestContext context) throws ResponseException {
	    return service().getAllPatientIdentifierTypes(true);
	}

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#newDelegate()
	 */
	@Override
    protected PatientIdentifierType newDelegate() {
	   return new PatientIdentifierType();
    }

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#save(java.lang.Object)
	 */
	@Override
    protected PatientIdentifierType save(PatientIdentifierType delegate) {
	    return service().savePatientIdentifierType(delegate);
    }

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#purge(java.lang.Object, org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
    public void purge(PatientIdentifierType delegate, RequestContext context) throws ResponseException {
	    service().purgePatientIdentifierType(delegate);
    }

}