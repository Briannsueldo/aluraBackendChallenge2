package com.alura.challengeAlura.service;

public interface IDataFormatter {
    
    <T> T getData(String json, Class <T> clase);
    
}
