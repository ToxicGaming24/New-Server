package de.ToxicGaming.Cloud.Utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import lombok.Getter;

public class MongoDB {
	//INFO: mongo-java-driver2 bei fehlern in den Plugins oder lib ordner packen
	@Getter
	private static MongoClient client;
	@Getter
	private static DB cloud;
	@Getter
	private static DBCollection players;
	@Getter
	private static DBCollection banned;
	@Getter
	private static DBCollection nicks;
	@Getter
	private static List<String> nickname = new ArrayList<>();
	public MongoDB() {
		try{
			client = new MongoClient("localhost", 27017);
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		cloud = client.getDB("cloud");
		players = cloud.getCollection("players");
		banned = cloud.getCollection("banned");
		nicks = cloud.getCollection("nicknames");
	}
	public void disconnect() {
		client.close();
	}
	public void storePlayer(UUID uuid, String name, String rank, String ip) {
		DBObject obj = new BasicDBObject("uuid", uuid);
		obj.put("name", name);
		obj.put("rank", rank);
		obj.put("ip", ip);
		players.insert(obj);
	}
//	public void readPlayer(UUID uuid) {
//		DBObject readObject = new BasicDBObject("uuid", uuid);
//		DBObject found = players.findOne(readObject);
//		if(found == null) {
//			return;
//		}
//		String name = (String) found.get("name");
//		String rank = (String) found.get("rank");
//	}
	public void updatePlayer(UUID uuid, String name, String rank, String ip) {
		DBObject object = new BasicDBObject("uuid", uuid);
		DBObject found = players.findOne(object);
		if(found==null){
			return;
		}
		DBObject obj = new BasicDBObject("uuid",uuid);
		obj.put("name", name);
		obj.put("rank", rank);
		obj.put("ip", ip);
		players.update(found, obj);
	}
	public Rank getPlayerRank(UUID uuid) {
		DBObject readObject = new BasicDBObject("uuid", uuid);
		DBObject found = players.findOne(readObject);
		if(found == null) {
			return null;
		}
		String rankName = (String) found.get("rank");
		Rank rank = Rank.fromStringExact(rankName);
		return rank;
	}
	public void insertToBan(UUID uuid, String name, String reason) {
		DBObject object = new BasicDBObject();
		object.put("uuid", uuid);
		object.put("name", name);
		object.put("reason", reason);
		banned.insert(object);
	}
	public void removeFromBan(UUID uuid, String name) {
		DBObject object = new BasicDBObject();
		object.put("uuid", uuid);
		object.put("name", name);
		banned.remove(object);
	}
	public void addNickName(String name) {
		DBObject object = new BasicDBObject("nickname",name);
		nicks.insert(object);
	}
	@SuppressWarnings("static-access")
	public void getNickNames(){
		BasicDBObject query = new BasicDBObject("nickname", null);
		DBCursor cursor = nicks.find(query);
		if(cursor.hasNext()) {
			JSON json = new JSON();
			String s = json.serialize(cursor.next());
			nickname.add(s);
			cursor.close();
		}
	}
}
