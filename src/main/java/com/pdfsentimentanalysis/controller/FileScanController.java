package com.pdfsentimentanalysis.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pdfsentimentanalysis.FileProcessorManager;

@RestController
@RequestMapping("/rest")
public class FileScanController {

	@Autowired
	private FileProcessorManager fileProcessorManager;

	@RequestMapping(path = "/scan", method = RequestMethod.POST)
	public boolean scan(@RequestParam String path) throws IOException {
		Files.find(Paths.get(path), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile())
				.forEach(filePath -> fileProcessorManager.processFile(filePath));
		return true;
	}

}
