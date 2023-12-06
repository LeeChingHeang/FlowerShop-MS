package classes.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JsonLoaderV3<T> {
    private final String DB_FILE_PATH;
    private final Class<T> elementType;
    private List<T> entityList;

    public JsonLoaderV3(String jsonFilePath, Class<T> elementType) {
        DB_FILE_PATH = jsonFilePath;
        this.elementType = elementType;
        this.entityList = loadDatabase();
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public Optional<T> checkEntity(String propertyName, Object propertyValue) {
        return entityList.stream()
                .filter(entity -> getPropertyValue(entity, propertyName).equals(propertyValue))
                .findFirst();
    }

    public void addEntity(T entity) {
        entityList.add(entity);
        saveDatabase();
    }

    public void deleteEntity(String propertyName, Object propertyValue) {
        Optional<T> entityToDelete = checkEntity(propertyName, propertyValue);
        entityToDelete.ifPresent(entity -> {
            entityList.remove(entity);
            saveDatabase();
        });
    }

    public void updateEntity(T updatedEntity, String propertyName, Object propertyValue) {
        Optional<T> entityToUpdate = checkEntity(propertyName, propertyValue);
        entityToUpdate.ifPresent(entity -> {
            int index = entityList.indexOf(entity);
            entityList.set(index, updatedEntity);
            saveDatabase();
        });
    }

    public List<T> fetchEntities(String arrayName) {
        return (List<T>) getProperty(entityList, arrayName);
    }

    public Map<String, Object> fetchMap(String mapName) {
        return (Map<String, Object>) getProperty(entityList, mapName);
    }

    public void addMapEntry(String mapName, String key, Object value) {
        Map<String, Object> map = fetchMap(mapName);
        if (map != null) {
            map.put(key, value);
            saveDatabase();
        }
    }

    public void updateMapEntry(String mapName, String key, Object value) {
        Map<String, Object> map = fetchMap(mapName);
        if (map != null && map.containsKey(key)) {
            map.put(key, value);
            saveDatabase();
        }
    }

    public void deleteMapEntry(String mapName, String key) {
        Map<String, Object> map = fetchMap(mapName);
        if (map != null && map.containsKey(key)) {
            map.remove(key);
            saveDatabase();
        }
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
            return objectMapper.readValue(new File(DB_FILE_PATH), objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Object getPropertyValue(T entity, String propertyName) {
        try {
            return entity.getClass().getMethod("get" + capitalize(propertyName)).invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private Object getProperty(List<T> entityList, String propertyName) {
        return entityList.stream()
                .findFirst()
                .map(entity -> getPropertyValue(entity, propertyName))
                .orElse(null);
    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
