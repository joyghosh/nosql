package com.tiatros.mongodb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoApp {

	public static void main(String[] args) {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Document> table = database.getCollection("myTestCollection");
		
		Document document1 = new Document("name", "Tom White")
								.append("age", 17)
								.append("createdAt", new Date());
		
		Document document2 = new Document("name", "John Allen")
				.append("age", 37)
				.append("createdAt", new Date());
		List<Document> documents = new ArrayList<Document>();
		documents.add(document1);
		documents.add(document2);
		table.insertMany(documents);
		
		Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(final Document document){
				System.out.println(document.toJson());
			}
		};
		
		table.find().forEach(printBlock);
		client.close();
	}
}
