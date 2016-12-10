package com.pdfsentimentanalysis;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;
import com.pdfsentimentanalysis.analysis.watson.WatsonAlchemyService;
import com.pdfsentimentanalysis.reader.pdf.PdfReader;

@Component
public class AnalysisManager {

	@Autowired
	private PdfReader reader;

	@Autowired
	private WatsonAlchemyService watsonService;

	@PostConstruct
	public void test() throws IOException {
		String content = reader
				.getContentFromPdf("/home/sebastian/Downloads/Deloitte - Assessment Mala Ramsaroop 09 12 2016.pdf");
		Entities entities = watsonService.getEntitiesFromText(content);
		System.out.println(entities);
	}
}
