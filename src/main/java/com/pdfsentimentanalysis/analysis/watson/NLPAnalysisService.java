package com.pdfsentimentanalysis.analysis.watson;

public interface NLPAnalysisService {

	EntityListResponse getEntitiesFromText(String text);
}
