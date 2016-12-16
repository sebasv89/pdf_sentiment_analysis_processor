package com.pdfsentimentanalysis.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdfsentimentanalysis.controller.dto.ApplicationDocumentSearchDto;
import com.pdfsentimentanalysis.model.ApplicationDocument;
import com.pdfsentimentanalysis.persistence.ApplicationDocumentRepository;

@RestController
@RequestMapping("/rest")
public class SearchController {

	@Autowired
	private ApplicationDocumentRepository applicationDocumentRepository;

	@RequestMapping(path = "/search/{textToSearch}", method = RequestMethod.GET)
	public List<ApplicationDocumentSearchDto> getPeople(@PathVariable String textToSearch) {
		List<ApplicationDocument> results = applicationDocumentRepository.searchByKeyword(textToSearch);
		List<ApplicationDocumentSearchDto> dtos = results.stream().map(this::toDto).collect(Collectors.toList());
		dtos.sort(ApplicationDocumentSearchDto::compareProjectToIgnoreCase);
		return dtos;
	}

	public ApplicationDocumentSearchDto toDto(ApplicationDocument appDocument) {
		ApplicationDocumentSearchDto dto = new ApplicationDocumentSearchDto();
		dto.setCanonicalPath(appDocument.getCanonicalPath());
		dto.setFileName(appDocument.getFileName());
		dto.setProject(appDocument.getProject());
		return dto;
	}
}
