package com.blue.blueplanserviceapplicationpc.Model;

public class Store {

    private Integer commodityId;

    private Integer relevanceId;

    private String commodityName;

    private String commodityImage;


    private String commodityPrice;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityImage() {
        return commodityImage;
    }

    public void setCommodityImage(String commodityImage) {
        this.commodityImage = commodityImage;
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    @Override
    public String toString() {
        return "Store{" +
                "commodityId=" + commodityId +
                ", relevanceId=" + relevanceId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityImage='" + commodityImage + '\'' +
                ", commodityPrice='" + commodityPrice + '\'' +
                '}';
    }


}
