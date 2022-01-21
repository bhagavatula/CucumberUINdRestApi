//package com.apitest.UIAutomation.utils;
//
//public class dontuseDifference {
//    private int id;
//    private final String description;
//    private boolean recoverable;
//    private NodeDetail controlNodeDetail;
//    private NodeDetail testNodeDetail;
//
//    protected dontuseDifference(int id, String description){this(id, description, false);  }
//    protected dontuseDifference(int id, String description, boolean recoverable){
//        this.controlNodeDetail = null;
//        this.testNodeDetail = null;
//        this.id = id;
//        this.description = description;
//        this.recoverable = recoverable;
//    }
//
//    protected dontuseDifference(dontuseDifference prototype, NodeDetail controlNodeDetail, NodeDetail testNodeDetail){
//        this(prototype.getId(), prototype.getDescription(), prototype.isRecoverable());
//        this.controlNodeDetail = controlNodeDetail;
//        this.testNodeDetail = testNodeDetail;
//    }
//
//    public int getId(){
//        return this.id;
//    }
//    public String getDescription(){ return this.description;}
//    public boolean isRecoverable(){ return this.recoverable;}
//    protected  void setRecoverable(boolean overrideValue){this.recoverable = overrideValue;}
//    public NodeDetail getControlNodeDetail(){ return this.controlNodeDetail;}
//    public NodeDetail getTestNodeDetail(){ return this.testNodeDetail;}
//
//    public boolean equals(Object other) {
//        if (other == null) {
//            return false;
//        } else if (other instanceof dontuseDifference) {
//            dontuseDifference otherDifference = (dontuseDifference) other;
//            this.id = otherDifference.getId();
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//
//
//
//}
