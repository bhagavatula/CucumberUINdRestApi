package com.apitest.automation.compare;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CompareSummary {

    private List<CompareDetails> fail  = new ArrayList<CompareDetails>();
    private List<CompareDetails> pass = new ArrayList<CompareDetails>();
    private String description;

    public CompareSummary (String description){
        this.description = description;
    }
    private  final static Logger LOGGER = Logger.getLogger(String.valueOf(CompareSummary.class));

    public void addDetail(Object actual, Object expected, String message, boolean pass){
        if(pass){
            this.pass.add(new CompareDetails(actual,expected,message, pass));
        }else{
            fail.add(new CompareDetails(actual,expected, message, pass));
        }
    }

    public boolean isEmpty(){
        return fail.isEmpty();
    }
    @Override
    public String toString(){
        String comparesummary  = "CompareSummary "+ description+ " [fail = "+fail+",\n pass="+pass+"]";
        return comparesummary;
    }
    public String report(){
        String report = new String();
        for(CompareDetails compareDetails : fail){

        }
        return report;
    }
}
