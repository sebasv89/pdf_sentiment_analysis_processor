package com.pdfsentimentanalysis.controller.dto;

public class ApplicationDocumentSearchDto {

	private String project;

	private String fileName;

	private String canonicalPath;

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

	public int compareProjectToIgnoreCase(ApplicationDocumentSearchDto toDto) {
		return project.compareToIgnoreCase(toDto.getProject());
	}

}
