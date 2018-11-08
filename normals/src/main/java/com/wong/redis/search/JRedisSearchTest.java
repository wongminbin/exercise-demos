package com.wong.redis.search;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.wong.redis.search.annotation.RedisEntity;
import com.wong.redis.search.annotation.RedisField;

import io.redisearch.Document;
import io.redisearch.Query;
import io.redisearch.Schema;
import io.redisearch.SearchResult;
import io.redisearch.client.AddOptions;
import io.redisearch.client.Client;

/**
 * @author HuangZhibin
 * 
 *         2018年8月25日 下午3:04:30
 */
public class JRedisSearchTest {

	private static final String CHINESE = "chinese";
	
	private String host;
	private int port = 6380;
	private Client client;

	private String index;
	private Map<Field, RedisField> fieldOpt;

	@Before
	public void init() {
		Class<Person> person = Person.class;
		RedisEntity entity = person.getAnnotation(RedisEntity.class);

		index = StringUtils.defaultIfEmpty(entity.index(), person.getName());

		fieldOpt = new HashMap<>();

		Field[] fields = person.getDeclaredFields();
		for (Field field : fields) {
			RedisField redisField = field.getDeclaredAnnotation(RedisField.class);
			if (redisField != null) {
				fieldOpt.put(field, redisField);
			}
		}
		
		host = "10.10.0.205";
		
		client = new Client(index, host, port);
	}
	
	@Test
	public void getInfo() {
		Map<String, Object> info = client.getInfo();
		info.forEach((k, v) -> {
			System.out.println("key: " + k + "  value: " + v);
		});
	}
	
	@Test
	public void drop() {
		client.dropIndex(true);
	}

	@Test
	public void createIndex() {
		Schema sc = new Schema();
		fieldOpt.forEach((k, v) -> {
			Type type = k.getType();
			if (type == String.class) {
				sc.addTextField(k.getName(), v.weight());
			}
			if (type == int.class || type == long.class || type == float.class || type == double.class
					|| type == Integer.class || type == Long.class || type == Float.class || type == Double.class) {
				sc.addNumericField(k.getName());
			}
		});

		boolean create = client.createIndex(sc, Client.IndexOptions.Default());
		
		System.out.println("=============="+create);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void addDocument() {
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = (Map<String, Object>) JSON.toJSON(new Person("张三" + i, "GZ", i));
			Document doc = new Document(StringUtils.leftPad(String.valueOf(i), 6, "0"), map);
			boolean add = client.addDocument(doc, new AddOptions().setLanguage(CHINESE));
			System.out.println(i + "============" + add);
		}
	}

	@Test
	public void query() {
		// Creating a complex query
		Query q = new Query("张三14").addFilter(new Query.NumericFilter("age", 10, 18)).limit(0, 10).setLanguage(CHINESE);

		// actual search
		SearchResult res = client.search(q);

		System.out.println("totalResults================="+res.totalResults);
		res.docs.forEach(doc->{
			System.out.println("id=========="+doc.getId());
			Person person = JSON.parseObject(doc.toString()).getObject("properties", Person.class);
			System.out.println("person=========="+person.toString());
		});
		
	}
}
