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

    public static boolean writeObjectToJson(Object obj, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(obj, writer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<User> loadUsersFromJson(String filePath, List<User> users) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> loadedUsers = gson.fromJson(reader, userListType);

            if (loadedUsers != null) {
                users.clear();
                users.addAll(loadedUsers);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

}
