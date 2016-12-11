package com.pdfsentimentanalysis.persistence;

import java.util.List;

import com.pdfsentimentanalysis.model.ApplicationDocument;

public interface ApplicationDocumentRepository {

	void save(ApplicationDocument document);

	List<ApplicationDocument> searchByKeyword(String keyword);

	boolean isFileAlreadyProcessed(String completeFilePath);
}
