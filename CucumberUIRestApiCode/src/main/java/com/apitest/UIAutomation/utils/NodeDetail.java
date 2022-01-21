package com.apitest.UIAutomation.utils;

import org.w3c.dom.Node;

public class NodeDetail {
    private final String value;
    private final Node node;
    private final String xpathLocation;

    public  NodeDetail(String value, Node node, String xpathLocation){
        this.value = value;
        this.node = node;
        this.xpathLocation = xpathLocation;
    }
    public Node getNode(){
        return this.node;
    }
    public String getValue(){
        return this.value;
    }
    public String getXpathLocation(){
        return this.xpathLocation;
    }
}
