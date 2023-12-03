package classes.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonDatabaseV2<T> {
    private final String DB_FILE_PATH;
    private final Class<T> elementType;
    private List<T> entityList;

    public JsonDatabaseV2(String dataBaseFilePath, Class<T> elementType) {
        DB_FILE_PATH = dataBaseFilePath;
        this.elementType = elementType;
        this.entityList = loadDatabase();
    }

    public List<T> getEntityList() {
        return entityList;
    }

  /*   // check if the entity exists in the database
    public Optional<T> authenticateEntity(String propertyName, String propertyValue) {
        return entityList.stream()
                .filter(entity -> getPropertyValue(entity, propertyName).equals(propertyValue))
                .findFirst();
    } */

    public void addEntity(T entity) {
        entityList.add(entity);
        saveDatabase();
    }

    public void saveDatabase() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(DB_FILE_PATH), entityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<T> loadDatabase() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // return objectMapper.readValue(new File(DB_FILE_PATH), new TypeReference<List<T>>() {});

            //
            return objectMapper.readValue(new File(DB_FILE_PATH), objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
            
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private String getPropertyValue(T entity, String propertyName) {
        try {
            // Use reflection to get the property value dynamically
            return entity.getClass().getMethod("get" + capitalize(propertyName)).invoke(entity).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
