package com.pdfsentimentanalysis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfiguration {

	// // @Bean
	// // public NodeBuilder nodeBuilder() {
	// // return new NodeBuilder();
	// // }
	// //
	// // @Bean
	// // public NodeClient nodeClient() {
	// // return (NodeClient)
	// //
	// nodeBuilder().clusterName("svelez_cluster").local(true).node().client();
	// //
	// // }
	// //
	// // @Bean
	// // public ElasticsearchOperations elasticsearchTemplate() {
	// // // Settings.Builder elasticsearchSettings =
	// // // Settings.settingsBuilder().put("http.enabled", "false") // 1
	// // // .put("path.data", "/home/sebastian/elastic_search_data") //
	// // // TODO-PARAMETER!
	// // // .put("path.home", "/home/sebastian/Downloads/elasticsearch-5.1.1");
	// // // // TODO-PARAMETER!
	// // return new ElasticsearchTemplate(nodeClient());
	// // }
	//
	// @Bean
	// public ElasticsearchTemplate elasticsearchTemplate() throws
	// UnknownHostException {
	// String server = "localhost";
	// Integer port = 9300;
	// Settings settings = Settings.settingsBuilder().put("cluster.name",
	// "svelez_cluster").build();
	// TransportClient client =
	// TransportClient.builder().settings(settings).build()
	// .addTransportAddress(new
	// InetSocketTransportAddress(InetAddress.getByName(server), port));
	// return new ElasticsearchTemplate(client);
	//
	// }
	//
	// // @Bean
	// // public ElasticsearchOperations elasticsearchTemplate() {
	// // return new ElasticsearchTemplate(client());
	// // }
	//
	// // @Bean
	// // public Client client() {
	// // TransportClient client = new TransportClient();
	// // TransportAddress address = new InetSocketTransportAddress("localhost",
	// // 9300);
	// // client.addTransportAddress(address);
	// // return client;
	// // }

}