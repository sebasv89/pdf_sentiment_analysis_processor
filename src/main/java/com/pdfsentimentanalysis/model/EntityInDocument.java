package com.pdfsentimentanalysis.model;

public class EntityInDocument {

	private Entity entity;

	private Sentiment sentiment;
	private double sentimentRelevance;

	private double entityRelevance;

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}

	public double getSentimentRelevance() {
		return sentimentRelevance;
	}

	public void setSentimentRelevance(double sentimentRelevance) {
		this.sentimentRelevance = sentimentRelevance;
	}

	public double getEntityRelevance() {
		return entityRelevance;
	}

	public void setEntityRelevance(double entityRelevance) {
		this.entityRelevance = entityRelevance;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}
