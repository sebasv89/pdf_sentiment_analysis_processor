package com.pdfsentimentanalysis;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.analysis.watson.WatsonAlchemyService;
import com.pdfsentimentanalysis.model.Article;
import com.pdfsentimentanalysis.reader.pdf.PdfReader;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;

@Component
public class AnalysisManager {

	@Autowired
	private PdfReader reader;

	@Autowired
	private WatsonAlchemyService watsonService;

	@PostConstruct
	public void test() throws IOException {
		// String content = reader
		// .getContentFromPdf("/home/sebastian/Downloads/Deloitte - Assessment
		// Mala Ramsaroop 09 12 2016.pdf");
		// Entities entities = watsonService.getEntitiesFromText(content);
		// System.out.println(entities);

		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200").multiThreaded(true).build());
		JestClient client = factory.getObject();
		client.execute(new CreateIndex.Builder("articles").build());

		// Article a = new Article();
		// a.setFirstName("Alejandra");
		// a.setLastName("Cruz");
		//
		// Index index = new
		// Index.Builder(a).index("articles").type("article").build();
		// client.execute(index);

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("firstName", "Sebastian"));

		Search search = new Search.Builder(searchSourceBuilder.toString())
				// multiple index or types can be added.
				.addIndex("articles").addType("article").build();

		SearchResult result = client.execute(search);

		List<SearchResult.Hit<Article, Void>> hits = result.getHits(Article.class);
		System.out.println(hits.size());
	}
}
