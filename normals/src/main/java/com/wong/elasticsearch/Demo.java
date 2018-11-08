package com.wong.elasticsearch;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.Sniffer;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.wong.lombok.Person;
import com.wong.lombok.Person.Gender;

/**
 * @author HuangZhibin
 * 
 *         2018年6月15日 上午9:59:03
 */
public class Demo {
	
	private volatile RestHighLevelClient client;
	
	private volatile Sniffer sniffer;
	
	@Before
	public void init() {
		
		RestClientBuilder builder = RestClient.builder(new HttpHost("10.10.0.198", 9200, "http"))
				.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
					
		            @Override
		            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
		            	// 连接密码
		            	CredentialsProvider auth = new BasicCredentialsProvider();
		            	auth.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "xGXZubzTI7BGTJ7SOfto"));
		            	
		            	httpClientBuilder
		            		.setDefaultCredentialsProvider(auth)// 授权
		            		.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(32).build());// 处理线程数
		                
		            	return httpClientBuilder;
		            }
		        });
		
		// set configuration
		Header[] defaultHeaders = new Header[]{new BasicHeader("content-binven", "customer")};
		builder.setDefaultHeaders(defaultHeaders);
		
		builder.setMaxRetryTimeoutMillis(6000);
		
		builder.setFailureListener(new RestClient.FailureListener() {
			@Override
		    public void onFailure(Node node) {
		        // TODO
				
		    }
		});
		
		// high level client
		client = new RestHighLevelClient(builder);

		/*
		 * Once a RestClient instance has been created as shown in Initialization, a
		 * Sniffer can be associated to it. The Sniffer will make use of the provided
		 * RestClient to periodically (every 5 minutes by default) fetch the list of
		 * current nodes from the cluster and update them by calling
		 * RestClient#setHosts.
		 */
		sniffer = Sniffer.builder(client.getLowLevelClient()).build();
	}
	
	@After
	public void close() {
		try {
			sniffer.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 一个 索引 类似于传统关系数据库中的一个 数据库 ，是一个存储关系型文档的地方
	private String index = "test-index";
	
	// 索引类型类似于传统关系数据库的表
	private String type = "person";
	
	/**
	 * 创建索引文档
	 * 
	 * 一个 Elasticsearch 集群可以 包含多个 索引 ，相应的每个索引可以包含多个 类型 。
	 *  这些不同的类型存储着多个 文档 ，每个文档又有 多个 属性 。
	 * 
	 * @author HuangZhibin
	 * @throws IOException 
	 *
	 */
	@Test
	public void index() throws IOException {
		
		for (int i = 0; i < 10; i++) {
			IndexRequest indexReq = new IndexRequest(index, type);
			
			// 文档内容ID，对于同一个index、type唯一
			String id = UUID.randomUUID().toString();
			indexReq.id(id);
			
			Person person = Person.builder().name("张三").age(28).gender(Gender.MAIL)
					.address("中国广东省广州市天河区员村二横路牧业大厦").build();

			indexReq.source(JSON.toJSONString(person), XContentType.JSON);
			
			IndexResponse response = client.index(indexReq, RequestOptions.DEFAULT);
			
			System.out.println(response.toString());
		}
	}

	@Test
	public void get() throws IOException {
		GetRequest get = new GetRequest(index, type, "79dcf7d4-d054-4808-b8ac-2c64afa57913");
		
		// 指定返回字段
		String[] includes = new String[]{"name", "age"};
		String[] excludes = new String[]{};
		FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
		get.fetchSourceContext(fetchSourceContext);
		
		GetResponse response = client.get(get, RequestOptions.DEFAULT);
		
		System.out.println(response.getSourceAsString());
	}
	
	@Test
	public void exist() throws IOException {
		GetRequest get = new GetRequest(index, type, "79dcf7d4-d054-4808-b8ac-2c64afa57913");
		get.fetchSourceContext(new FetchSourceContext(false));
		get.storedFields("_none_");
		
		boolean exist = client.exists(get, RequestOptions.DEFAULT);
		
		System.out.println(exist);
	}
	
	@Test
	public void update() {
		
	}
	
	@Test
	public void upsert() {
		
	}
	
	@Test
	public void delete() throws IOException {
		DeleteRequest delete = new DeleteRequest(index, type, "de37f645-75d2-41bf-a18a-12a78c4e2595");
		
		DeleteResponse response = client.delete(delete, RequestOptions.DEFAULT);
		
		System.out.println(response.getResult().toString());
	}
	
	@Test
	public void searchAll() throws IOException {
		SearchRequest search = new SearchRequest(index);
		search.types(type);
		int from = 0, //查询开始的角标
			size = 25;//返回最多条数
		search.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).from(from).size(size));
		
		SearchResponse response = client.search(search, RequestOptions.DEFAULT);
		response.getHits().forEach(h -> {
			System.out.println(h.getSourceAsString());
		});
	}
	
	/**
	 * 词汇检索,需要把检索内容分词,分别查询,查询的内容分词等于检索词汇的分词<br>
	 * e.g. 中国广东省--> 中国、广东省
	 * @author HuangZhibin
	 *
	 * @throws IOException
	 */
	@Test
	public void search() throws IOException {
		SearchRequest search = new SearchRequest(index);
		search.types(type);
		search.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "四")));
		
		SearchResponse response = client.search(search, RequestOptions.DEFAULT);
		response.getHits().forEach(h -> {
			System.out.println(h.getSourceAsString());
		});
	}
	
	/**
	 * 全文检索,查询的内容包含完整或部分的检索全文内容
	 * @author HuangZhibin
	 *
	 * @throws IOException
	 */
	@Test
	public void search2() throws IOException {
		SearchRequest search = new SearchRequest(index);
		search.types(type);
		search.source(new SearchSourceBuilder().query(QueryBuilders.matchQuery("address", "牧业")));
		
		SearchResponse response = client.search(search, RequestOptions.DEFAULT);
		response.getHits().forEach(h -> {
			System.out.println(h.getSourceAsString());
		});
	}
	
	/**
	 * 短语检索,查询的内容需要包含完整的短语
	 * @author HuangZhibin
	 *
	 * @throws IOException
	 */
	@Test
	public void search3() throws IOException {
		SearchRequest search = new SearchRequest(index);
		search.types(type);
		search.source(new SearchSourceBuilder().query(QueryBuilders.matchPhraseQuery("address", "牧业")));
		
		SearchResponse response = client.search(search, RequestOptions.DEFAULT);
		response.getHits().forEach(h -> {
			System.out.println(h.getSourceAsString());
		});
	}
	
	@Test
	public void match() {
		SearchRequest search = new SearchRequest(index);
		search.types(type);
		
		SearchSourceBuilder builder = new SearchSourceBuilder();
		
		search.source(builder);
	}
	
}
