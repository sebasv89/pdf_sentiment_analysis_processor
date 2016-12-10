package com.pdfsentimentanalysis.analysis.watson;

import java.util.List;

import com.pdfsentimentanalysis.model.Entity;

public class EntityListResponse {

	private List<Entity> entities;

	private String language;

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
