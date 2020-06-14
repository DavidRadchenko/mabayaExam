package mabaya.assignment.dto;



public class Product {

    private long serialNum;
    private String title;
    private long price;
    private String category;
    private long sellerId;

    public Product(long serialNum, String title, long price, String category, long sellerId) {
        this.serialNum = serialNum;
        this.title = title;
        this.price = price;
        this.category = category;
        this.sellerId = sellerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(long sreialNum) {
        this.serialNum = sreialNum;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNum=" + serialNum +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", sellerId=" + sellerId +
                '}';
    }
}
