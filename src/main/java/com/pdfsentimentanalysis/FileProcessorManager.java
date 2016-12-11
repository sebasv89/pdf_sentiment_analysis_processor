package com.pdfsentimentanalysis;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.analysis.watson.EntityListResponse;
import com.pdfsentimentanalysis.analysis.watson.NLPAnalysisService;
import com.pdfsentimentanalysis.model.ApplicationDocument;
import com.pdfsentimentanalysis.persistence.ApplicationDocumentRepository;
import com.pdfsentimentanalysis.reader.pdf.PdfReader;

@Component
public class FileProcessorManager {

	// TODO: as we support new file formats we need to change this for a more
	// extensible strategy to validate file extensions.
	private static final String SUPPORTED_EXTENSION = "PDF";

	@Autowired
	private PdfReader reader;

	@Autowired
	private NLPAnalysisService watsonService;

	@Autowired
	private ApplicationDocumentRepository applicationDocumentRepository;

	public void processFile(Path path) {
		try {
			String fileName = path.getFileName().toString();
			String projectName = path.getParent().getFileName().toString();
			String content = reader.getContentFromPdf(path.toString());

			if (fileName.toUpperCase().endsWith(SUPPORTED_EXTENSION) == false) {
				// TODO-log non proccessed
				System.out.println("Invalid extension to process: " + path.toString());
				return;
			}

			if (applicationDocumentRepository.isFileAlreadyProcessed(path.toString())) {
				// TODO-log non proccessed
				System.out.println("Not processing file: " + path.toString());
				return;
			}

			EntityListResponse entities = watsonService.getEntitiesFromText(content);

			ApplicationDocument appDocument = new ApplicationDocument();
			appDocument.setCanonicalPath(path.toString());
			appDocument.setEntities(entities.getEntities());
			appDocument.setFileName(fileName);
			appDocument.setCompleteText(content);
			appDocument.setProject(projectName);

			applicationDocumentRepository.save(appDocument);
		} catch (Exception ex) {
			// TODO-LOGME error logging!!
			ex.printStackTrace();
		}
	}
}
