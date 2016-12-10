package com.pdfsentimentanalysis.analysis.watson;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;

@Component
public class DefaultWatsonAlchemyService implements WatsonAlchemyService {

	// TODO - parameterize variable
	private static final String WATSON_API_KEY = "8d9f2f4716eff8c06a3be47e877ff9baaeb292ca";

	@Override
	public Entities getEntitiesFromText(String text) {
		AlchemyLanguage service = getService();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.TEXT, text);

		Entities entities = service.getEntities(params).execute();
		return entities;
	}

	private AlchemyLanguage getService() {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey(WATSON_API_KEY);
		return service;
	}

}
