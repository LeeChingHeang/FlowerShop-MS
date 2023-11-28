package classes.api;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// custom class
import com.example.User;
public class JsonDatabase {
    // just a path to the json file
    // TODO: change path to argument so make our class recursive
    private static final String DATABASE_FILE_PATH = "src/main/resources/com/example/data/login/admin.json";
    private List<User> userList;

    public JsonDatabase(){
        this.userList = loadDatabase();
    }
    
    public List<User> getUserList(){
        return userList; // return list of users
    }
    
    public Optional<User> authenticateUser(String username, String password) {
        // Start streaming the elements of userList
        return userList.stream()
                // Filter the stream based on the provided username and password
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                // Return an Optional containing the first user that satisfies the filter condition
                .findFirst();
    }

    public void addUser(User user){
        userList.add(user); // add user to list in arrayList
        saveDatabase(); // revokes saveDatabase method
    } 
   
    public void saveDatabase(){
        // map object to json file
          ObjectMapper objectMapper = new ObjectMapper();
          try {
                objectMapper.writeValue(new File(DATABASE_FILE_PATH), userList);
          } catch (IOException e) {
                e.printStackTrace();
          }
    }
    

    private List<User> loadDatabase(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(DATABASE_FILE_PATH), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<>();
        }
    }
    

    
}
