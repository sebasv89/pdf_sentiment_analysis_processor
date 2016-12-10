package com.pdfsentimentanalysis.reader.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

@Component
public class DefaultPdfReader implements PdfReader {

	@Override
	public String getContentFromPdf(String path) throws IOException {
		File pdfFile = new File(path);
		if (!pdfFile.isFile()) {
			System.err.println("File " + path + " does not exist.");
			throw new IOException("file does not exist");
		}
		PDDocument doc = PDDocument.load(pdfFile);
		return new PDFTextStripper().getText(doc);
	}
}