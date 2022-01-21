package com.apitest.automation.pages;

//import io.cucumber.gherkin.internal.com.eclipsesource.json.Json;
//import io.cucumber.messages.JSON;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.compress.utils.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class PrepareTestData{
    public static final String JSON = ".json";

    public String preparePayloadRequest(String baseFile, String fileExtension, Map<String,String> testData){
        String jsonTemplate = loadJsonTemplate(baseFile,fileExtension);
        if (JSON.equals(fileExtension)){
            return populateTestData(jsonTemplate,testData);
        }else{
            return populateXMLData(jsonTemplate,testData);
        }
    }

    public  String populateTestData(String jsonTemplate, Map<String,String> testData){
        final StringTemplate finalJson = new StringTemplate(jsonTemplate);
        for(String key: testData.keySet()){
            finalJson.setAttribute(key,testData.get(key));
        }
        return finalJson.toString();
    }

    public String populateXMLData(String inputXml, Map<String, String> testData){
        StringBuilder xmlFile = new StringBuilder((inputXml));
        if(testData!=null){
            for (String key: testData.keySet()){
                while(xmlFile.indexOf(key) >0){
                    int index = xmlFile.indexOf(key);
                    if(index >0){
                        xmlFile.replace(index -2, index+key.length()+2, testData.get(key));
                    }
                }
            }
        }
        return xmlFile.toString();
    }

    public String loadJsonTemplate(String baseFile, String fileExtension){
        BufferedReader reader = null;
        String jsonTemplate = null;
        try {
            reader = new BufferedReader(new FileReader("filepath"+fileExtension));
            StringBuilder dataString = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                dataString.append(line);
                dataString.append("\n");
                line = reader.readLine();
            }
            jsonTemplate = dataString.toString();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(reader);
        }
        return jsonTemplate;
    }
}
