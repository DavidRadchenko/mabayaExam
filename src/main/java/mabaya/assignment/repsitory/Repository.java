package mabaya.assignment.repsitory;

import mabaya.assignment.dto.Campaign;
import mabaya.assignment.dto.CampaignStatus;
import mabaya.assignment.dto.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Repository {

    private List<Product> products;
    private List<Campaign> campaigns;

    public Repository() {
        this.campaigns = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public void addCampaign(Campaign campaign) {
        this.campaigns.add(campaign);
    }

    public boolean removeCampaign(Campaign campaign) {
        return this.campaigns.remove(campaign);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public boolean removeProduct(Campaign product) {
        return this.products.remove(product);
    }


    public List<Product> getProductsBySellerId(long sellerId) {
        return products
                .stream()
                .filter(product -> product.getSellerId() == sellerId)
                .collect(Collectors.toList());
    }

    public List<Campaign> getCampaignsByStatus(CampaignStatus status) {
        return campaigns
                .stream()
                .filter(campaign -> campaign.getStatus().equals(status))
                .collect(Collectors.toList());
    }
}
