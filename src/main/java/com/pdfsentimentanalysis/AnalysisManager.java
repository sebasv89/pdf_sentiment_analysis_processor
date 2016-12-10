package com.pdfsentimentanalysis;

import java.io.IOException;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.analysis.watson.WatsonAlchemyService;
import com.pdfsentimentanalysis.persistence.Article;
import com.pdfsentimentanalysis.persistence.ArticleRepository;
import com.pdfsentimentanalysis.persistence.Author;
import com.pdfsentimentanalysis.reader.pdf.PdfReader;

@Component
public class AnalysisManager {

	@Autowired
	private PdfReader reader;

	@Autowired
	private WatsonAlchemyService watsonService;

	@Autowired
	private ArticleRepository articleRepository;

	@PostConstruct
	public void test() throws IOException {
		// String content = reader
		// .getContentFromPdf("/home/sebastian/Downloads/Deloitte - Assessment
		// Mala Ramsaroop 09 12 2016.pdf");
		// Entities entities = watsonService.getEntitiesFromText(content);
		// System.out.println(entities);
		Article article = new Article();
		article.setTitle("Spring Data ElasticSearch");
		article.setAuthors(Collections.singletonList(new Author()));
		articleRepository.save(article);
	}
}
