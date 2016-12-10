package com.pdfsentimentanalysis.analysis.watson;

import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;

public interface WatsonAlchemyService {

	Entities getEntitiesFromText(String text);
}
