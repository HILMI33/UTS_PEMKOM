package com.mycompany.p_uts_23090069_d_2025;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.Arrays;

public class CRUD_UTS_23090069_D_2025 {

    static MongoCollection<Document> collection;

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("uts_23090069_D_2025");
        collection = database.getCollection("coll_23090069_D_2025");

        createData();
        readData();
        updateData();
        deleteData();
        searchData("nama");
    }

    static void createData() {
        Document doc1 = new Document("nama", "Andi").append("umur", 20).append("kelas", "D");
        Document doc2 = new Document("nama", "Budi").append("umur", 21).append("kelas", "D");
        Document doc3 = new Document("nama", "Citra").append("umur", 22).append("kelas", "D");
        collection.insertMany(Arrays.asList(doc1, doc2, doc3));
        System.out.println("Data berhasil ditambahkan.");
    }

    static void readData() {
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }
    }

    static void updateData() {
        collection.updateOne(new Document("nama", "Budi"),
                new Document("$set", new Document("umur", 23)));
        System.out.println("Data berhasil diupdate.");
    }

    static void deleteData() {
        collection.deleteOne(new Document("nama", "Andi"));
        System.out.println("Data berhasil dihapus.");
    }

    static void searchData(String key) {
        FindIterable<Document> docs = collection.find(new Document(key, new Document("$exists", true)));
        for (Document doc : docs) {
            System.out.println("Hasil pencarian: " + doc.toJson());
        }
    }
}
