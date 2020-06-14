package mabaya.assignment.dto;

import java.util.List;


public class Campaign {

    private String name;
    private CampaignStatus status;
    private long bid;
    private long sellerId;
    private List<Long> productSerialNums;

    public Campaign(String name, long bid, long sellerId) {
        this.name = name;
        this.bid = bid;
        this.sellerId = sellerId;
        this.status = CampaignStatus.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public List<Long> getProductSerialNums() {
        return productSerialNums;
    }

    public void setProductSerialNums(List<Long> productIds) {
        this.productSerialNums = productIds;
    }

    public void addProduct(Long productId) {
        this.productSerialNums.add(productId);
    }

    public boolean removeProduct(Long productId) {
        return this.productSerialNums.remove(productId);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", bid=" + bid +
                ", sellerId=" + sellerId +
                ", productSerialNums=" + productSerialNums +
                '}';
    }
}
