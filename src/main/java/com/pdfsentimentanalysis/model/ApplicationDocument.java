package com.pdfsentimentanalysis.model;

import java.util.List;

public class ApplicationDocument {

	private String project;

	private String fileName;

	private String canonicalPath;

	private String completeText;

	private List<EntityInDocument> entities;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCanonicalPath() {
		return canonicalPath;
	}

	public void setCanonicalPath(String canonicalPath) {
		this.canonicalPath = canonicalPath;
	}

	public List<EntityInDocument> getEntities() {
		return entities;
	}

	public void setEntities(List<EntityInDocument> entities) {
		this.entities = entities;
	}

	public String getCompleteText() {
		return completeText;
	}

	public void setCompleteText(String completeText) {
		this.completeText = completeText;
	}

}
