package com.corderfromscratch.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;

public class Json {

    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static JsonNode parse(String jsonSrc) throws IOException {
       return myObjectMapper.readTree(jsonSrc);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException{
        return myObjectMapper.treeToValue(node, clazz);
   }

   public static  JsonNode ToJson(Object obj){
        return  myObjectMapper.valueToTree(obj);
   }

   public static String Stringify(JsonNode node) throws JsonProcessingException{
    return generateJson(node, false);
   }
    public static String StringifyPretty(JsonNode node) throws JsonProcessingException{
        return generateJson(node, true);
    }

   private static String generateJson(Object o, boolean prettry) throws JsonProcessingException{
       ObjectWriter objectWriter = myObjectMapper.writer();
       if (prettry){
           objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
       }
       return objectWriter.writeValueAsString(o);
   }

}
