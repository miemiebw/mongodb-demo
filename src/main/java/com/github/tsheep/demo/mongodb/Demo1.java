package com.github.tsheep.demo.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Demo1 {

	/**
	 * @param args
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, MongoException {
		Mongo m = new Mongo("127.0.0.1", 27017);
		DB db = m.getDB("tsheep");
		
//		DBCollection coll = db.getCollection("15969884293");
//		
//		BasicDBObject doc = new BasicDBObject();
//		doc.put("lon", 123456789);
//		doc.put("lat", 987654321);
//		doc.put("time", System.currentTimeMillis());
//		coll.insert(doc);
//		DBObject myDoc = coll.findOne();
//		System.out.println(myDoc);
		
		DBCollection coll = db.getCollection("test");
		
		coll.createIndex(new BasicDBObject("i", 1));
		List<DBObject> list = coll.getIndexInfo();

        for (DBObject o : list) {
            System.out.println(o);
        }
		
		
		for (int i=0; i < 100; i++) {
		    coll.insert(new BasicDBObject().append("i", i));
		}
		System.out.println(coll.getCount());
		
		BasicDBObject query = new BasicDBObject();
        query.put("i", new BasicDBObject("$gt", 20).append("$lte", 30)); // i.e.   20 < i <= 30
		
		DBCursor cursor = coll.find(query);
		while(cursor.hasNext()){
			DBObject doc = cursor.next();
			System.out.println(doc);
		}
		coll.drop();
	}

}
