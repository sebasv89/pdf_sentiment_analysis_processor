package com.pdfsentimentanalysis.analysis.watson;

import java.util.List;

import com.pdfsentimentanalysis.model.EntityInDocument;

public class EntityListResponse {

	private List<EntityInDocument> entities;

	private String language;

	public List<EntityInDocument> getEntities() {
		return entities;
	}

	public void setEntities(List<EntityInDocument> entities) {
		this.entities = entities;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
