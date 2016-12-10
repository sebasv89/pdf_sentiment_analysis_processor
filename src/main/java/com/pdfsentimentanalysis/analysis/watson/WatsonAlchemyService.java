package com.pdfsentimentanalysis.analysis.watson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;
import com.pdfsentimentanalysis.model.Entity;
import com.pdfsentimentanalysis.model.Sentiment;

@Component
public class WatsonAlchemyService implements NLPAnalysisService {

	// TODO - parameterize variable
	private static final String WATSON_API_KEY = "8d9f2f4716eff8c06a3be47e877ff9baaeb292ca";

	@Override
	public EntityListResponse getEntitiesFromText(String text) {
		AlchemyLanguage service = getService();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.TEXT, text);
		params.put(AlchemyLanguage.SENTIMENT, 1);

		Entities entities = service.getEntities(params).execute();

		EntityListResponse response = new EntityListResponse();
		response.setLanguage(entities.getLanguage());
		List<Entity> responseEntities = new ArrayList<Entity>();
		if (entities != null && CollectionUtils.isEmpty(entities.getEntities()) == false) {

			for (com.ibm.watson.developer_cloud.alchemy.v1.model.Entity watsonEntity : entities.getEntities()) {
				Entity entity = new Entity();
				entity.setName(watsonEntity.getText());
				if (watsonEntity.getSentiment() != null) {
					entity.setSentiment(Sentiment.valueOf(watsonEntity.getSentiment().getType().toString()));
					if (watsonEntity.getSentiment().getScore() != null) {
						entity.setSentimentRelevance(watsonEntity.getSentiment().getScore());
					}
				}
				entity.setType(watsonEntity.getType());
				entity.setEntityRelevance(watsonEntity.getRelevance());
				responseEntities.add(entity);
			}
		}
		response.setEntities(responseEntities);
		return response;
	}

	private AlchemyLanguage getService() {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey(WATSON_API_KEY);
		return service;
	}

}
