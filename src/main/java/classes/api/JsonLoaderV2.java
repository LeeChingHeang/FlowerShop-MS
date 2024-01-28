package classes.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonLoaderV2<T> {
    private final String DB_FILE_PATH;
    private final Class<T> elementType;
    private List<T> entityList;

    // Constructor
    public JsonLoaderV2(String jsonFile_Path, Class<T> elementType) {
        DB_FILE_PATH = jsonFile_Path;

        this.elementType = elementType;
        this.entityList = loadDatabase();
    }

    public List<T> getEntityList() {
        return entityList;
    }

    // check if the entity exists in the database
    public Optional<T> checkEntity(String propertyName, String propertyValue) {
        return entityList.stream()
                .filter(entity -> getPropertyValue(entity, propertyName).equals(propertyValue))
                .findFirst();
    } 

    public void addEntity(T entity) {
        entityList.add(entity);
        saveDatabase();
    }
    
     public void deleteEntity(String propertyName, String propertyValue) {
        Optional<T> entityToDelete = checkEntity(propertyName, propertyValue);
        entityToDelete.ifPresent(entity -> {
            entityList.remove(entity);
            saveDatabase();
        });
    }
      public void updateEntity(T updatedEntity, String propertyName, String propertyValue) {
        // check each entity in the list
        Optional<T> entityToUpdate = checkEntity(propertyName, propertyValue);
        // if found, update the entity
        entityToUpdate.ifPresent(entity -> {
            // Update the existing entity with the new data
            int index = entityList.indexOf(entity);
            entityList.set(index, updatedEntity);
            saveDatabase();
        });
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
    File file = new File(DB_FILE_PATH);

    if (!file.exists()) {
        try {
            // Create a new empty file if it doesn't exist
            file.createNewFile();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    try {
        // Suggest the objects are instances of the default LinkedHashMap class
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
    } catch (IOException e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}
/*     private List<T> loadDatabase() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // return objectMapper.readValue(new File(DB_FILE_PATH), new TypeReference<List<T>>() {});

            // Suggest the objects are instances of the default LinkedHashMap class
            return objectMapper.readValue(new File(DB_FILE_PATH), objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
            
        } catch (IOException e) {
            return new ArrayList<>();
        }
    } */

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
