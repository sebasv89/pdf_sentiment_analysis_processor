package com.pdfsentimentanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.pdfsentimentanalysis.persistence")
public class PdfSentimentAnalysisProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfSentimentAnalysisProcessorApplication.class, args);
	}
}
