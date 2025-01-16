package com.alura.challengeAlura.service;

import com.alura.challengeAlura.model.BookData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatData implements IDataFormatter{
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> clase) {
        
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public BookData getBook(String json) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            
            JsonNode root = mapper.readTree(json);
    
            JsonNode resultsNode = root.get("results");
    
            
            if (resultsNode != null && resultsNode.isArray() && resultsNode.size() > 0) {
                
                JsonNode firstBookNode = resultsNode.get(0);
                
                BookData book = mapper.treeToValue(firstBookNode, BookData.class);
    
                return book;
            }
    
            return null;
    
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el JSON", e);
        }
    }


}

