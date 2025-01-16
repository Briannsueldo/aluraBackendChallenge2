package com.alura.challengeAlura.service;

import com.alura.challengeAlura.model.BookData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatData implements IDataFormatter{

    // Deserealizacion del Json
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> clase) {
        
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    /* public List<BookData> getBooks(String json) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Leer el JSON como un árbol
            JsonNode root = mapper.readTree(json);

            // Extraer el nodo "results"
            JsonNode resultsNode = root.get("results");

            // Convertir "results" en una lista de BookData
            List<BookData> books = new ArrayList<>();
            if (resultsNode != null && resultsNode.isArray()) {
                for (JsonNode bookNode : resultsNode) {
                    BookData book = mapper.treeToValue(bookNode, BookData.class);
                    books.add(book);
                }
            }

            return books;
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el JSON", e);
        }
    } */

    public BookData getBook(String json) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Leer el JSON como un árbol
            JsonNode root = mapper.readTree(json);
    
            // Extraer el nodo "results"
            JsonNode resultsNode = root.get("results");
    
            // Verificar si el nodo "results" contiene elementos
            if (resultsNode != null && resultsNode.isArray() && resultsNode.size() > 0) {
                // Obtener el primer libro de la lista
                JsonNode firstBookNode = resultsNode.get(0);
                
                // Convertir el primer nodo en un objeto BookData
                BookData book = mapper.treeToValue(firstBookNode, BookData.class);
    
                return book;
            }
    
            return null;  // Retornar null si no hay libros
    
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el JSON", e);
        }
    }


}

