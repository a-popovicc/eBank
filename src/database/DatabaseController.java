package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import user.User;


import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class DatabaseController {
    // Metoda koja upisuje objekat u JSON fajl
    public static void writeObjectToJson(Object obj, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // lep format JSON-a

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(obj, writer);
            //System.out.println("Data saved to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<User> loadUsersFromJson(String filePath, List<User> users) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> loadedUsers = gson.fromJson(reader, userListType);

            if (loadedUsers != null) {
                users.clear();          // obriši eventualni sadržaj
                users.addAll(loadedUsers); // popuni listu sa podacima iz fajla
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

}
