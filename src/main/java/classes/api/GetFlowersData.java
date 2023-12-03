package classes.api;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// custom class
import com.example.FlowersData;
public class GetFlowersData {
    // just a path to the json file
    private static final String DATABASE_FILE_PATH = "src/main/resources/com/example/data/stock/FlowersDb.json";
    private List<FlowersData> flowersList;

    public GetFlowersData(){
        this.flowersList = loadDatabase();
    }
    
    public List<FlowersData> getFlowersList(){
        return flowersList; // return list of users
    }
    
 

    public void addUser(FlowersData flowerData){
        flowersList.add(flowerData); // add user to list in arrayList
        saveDatabase(); // revokes saveDatabase method
    } 
   
    public void saveDatabase(){
        // map object to json file
          ObjectMapper objectMapper = new ObjectMapper();
          try {
                objectMapper.writeValue(new File(DATABASE_FILE_PATH), flowersList);
          } catch (IOException e) {
                e.printStackTrace();
          }
    }
    

    private List<FlowersData> loadDatabase(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(DATABASE_FILE_PATH), new TypeReference<List<FlowersData>>() {});
        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<>();
        }
    }
    

    
}
