package ichat.ichat;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.scene.control.Label;
import org.bson.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.gte;

public class MongoDB {
    public void InsertToMongo(String usuari, String MensajeTotal) {
        LocalDateTime locaDate = LocalDateTime.now();
        int hours = locaDate.getHour();
        int minutes = locaDate.getMinute();
        int seconds = locaDate.getSecond();
        String hora = hours + ":" + minutes + ":" + seconds;
        LocalDate todaysDate = LocalDate.now();
        int dia = todaysDate.getDayOfMonth();
        int mes = todaysDate.getMonthValue();
        int any = todaysDate.getYear();
        String data = dia + "/" + mes + "/" + any;
        try {
            MongoClient mongodb = new MongoClient("127.0.0.1", 27017);
            MongoDatabase db = mongodb.getDatabase("ixat");
            MongoCollection<Document> coleccio = db.getCollection("missatges");
            Document document = new Document();
            document.append("usuari", usuari);
            document.append("missatge", MensajeTotal);
            document.append("data", data);
            document.append("hora", hora);
            coleccio.insertOne(document);
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }


    public void Find(String date, Label lblMissatges){

        ManagementController manager = new ManagementController();
    try{
        MongoClient mongodb = new MongoClient("127.0.0.1", 27017);
        MongoDatabase db = mongodb.getDatabase("ixat");
        MongoCollection<Document> coleccio = db.getCollection("missatges");
        List<Document> missatges = coleccio.find(gte("data", date)).into(new ArrayList<>());
        for (Document linia : missatges) {
            System.out.println(linia.toJson());
        }

    }catch(MongoException e){
        e.printStackTrace();
    }

    }
}


