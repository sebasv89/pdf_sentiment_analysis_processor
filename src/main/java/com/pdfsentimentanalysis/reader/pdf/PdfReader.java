package com.pdfsentimentanalysis.reader.pdf;

import java.io.IOException;

public interface PdfReader {

	String getContentFromPdf(String path) throws IOException;
}
