package com.blue.blueplanserviceapplicationpc.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentText {
    private int contentId;
    private String contentName;
    private int contentAge;
    private String contentDescribe;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public int getContentAge() {
        return contentAge;
    }

    public void setContentAge(int contentAge) {
        this.contentAge = contentAge;
    }

    public String getContentDescribe() {
        return contentDescribe;
    }

    public void setContentDescribe(String contentDescribe) {
        this.contentDescribe = contentDescribe;
    }

    @Override
    public String toString() {
        return "ContentText{" +
                "contentId=" + contentId +
                ", contentName='" + contentName + '\'' +
                ", contentAge=" + contentAge +
                ", contentDescribe='" + contentDescribe + '\'' +
                '}';
    }

}
