package com.pdfsentimentanalysis;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.pdfsentimentanalysis.persistence")
@ComponentScan
public class AppConfiguration {

	@Bean
	public NodeBuilder nodeBuilder() {
		return new NodeBuilder();
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		Settings.Builder elasticsearchSettings = Settings.settingsBuilder().put("http.enabled", "false") // 1
				.put("path.data", "/home/sebastian/elastic_search_data") // TODO-PARAMETER!
				.put("path.home", "/home/sebastian/Downloads/elasticsearch-5.1.1"); // TODO-PARAMETER!

		return new ElasticsearchTemplate(
				nodeBuilder().local(true).settings(elasticsearchSettings.build()).node().client());
	}
}