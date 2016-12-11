package com.pdfsentimentanalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileAnalysisRunner {

	private static final String BASE_PATH = "/home/sebastian/Documents/reviews";

	@Autowired
	private FileProcessorManager manager;

	@PostConstruct
	public void init() throws IOException {
		Files.find(Paths.get(BASE_PATH), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile())
				.forEach(filePath -> manager.processFile(filePath));

	}
}
