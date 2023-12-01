package classes.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonDatabaseV2<T> {
    private final String databaseFilePath;
    private final Class<T> entityType;
    private List<T> entityList;

    public JsonDatabaseV2(String databaseFilePath, Class<T> entityType) {
        this.databaseFilePath = databaseFilePath;
        this.entityType = entityType;
        this.entityList = loadDatabase();
    }

    public List<T> getEntityList() {
        return new ArrayList<>(entityList);
    }

    // check if the entity exists in the database
    public Optional<T> authenticateEntity(String propertyName, String propertyValue) {
        return entityList.stream()
                .filter(entity -> getPropertyValue(entity, propertyName).equals(propertyValue))
                .findFirst();
    }

    public void addEntity(T entity) {
        entityList.add(entity);
        saveDatabase();
    }

    public void saveDatabase() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(databaseFilePath), entityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<T> loadDatabase() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(databaseFilePath), new TypeReference<List<T>>() {
            });
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
