package com.pdfsentimentanalysis.model;

public class Entity {

	private String name;

	private String type;

	private Sentiment sentiment;
	private double sentimentRelevance;

	private double entityRelevance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

}
