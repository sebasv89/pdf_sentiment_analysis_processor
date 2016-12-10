package com.pdfsentimentanalysis;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.analysis.watson.EntityListResponse;
import com.pdfsentimentanalysis.analysis.watson.NLPAnalysisService;
import com.pdfsentimentanalysis.model.ApplicationDocument;
import com.pdfsentimentanalysis.persistence.ApplicationDocumentRepository;
import com.pdfsentimentanalysis.reader.pdf.PdfReader;

@Component
public class AnalysisManager {

	@Autowired
	private PdfReader reader;

	@Autowired
	private NLPAnalysisService watsonService;

	@Autowired
	private ApplicationDocumentRepository applicationDocumentRepository;

	@PostConstruct
	public void test() throws IOException {
		String fileName = "Deloitte - Assessment Mala Ramsaroop 09 12 2016.pdf";
		String projectName = "Deloitte";
		String path = "/home/sebastian/Documents/reviews/" + projectName + "/" + fileName;
		String content = reader.getContentFromPdf(path);
		EntityListResponse entities = watsonService.getEntitiesFromText(content);

		ApplicationDocument appDocument = new ApplicationDocument();
		appDocument.setCanonicalPath(path);
		appDocument.setEntities(entities.getEntities());
		appDocument.setFileName(fileName);
		appDocument.setProject(projectName);

		applicationDocumentRepository.save(appDocument);

	}
}
