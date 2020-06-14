package mabaya.assignment.controller;

import mabaya.assignment.dto.Campaign;
import mabaya.assignment.dto.CampaignStatus;
import mabaya.assignment.dto.Product;
import mabaya.assignment.repsitory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    Repository repository;

    @RequestMapping(value = "/newCampaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campaign> CreateCampaign(@PathVariable("name") String name, @PathVariable("bid") long bid, @PathVariable("sellerId") long sellerId) {
        try {
            Campaign newCampaign = new Campaign(name, bid, sellerId);
            List<Long> productSerialNums = repository.getProductsBySellerId(sellerId)
                    .stream()
                    .map(Product::getSerialNum)
                    .collect(Collectors.toList());
            newCampaign.setProductSerialNums(productSerialNums);
            repository.addCampaign(newCampaign);
            return new ResponseEntity<>(newCampaign, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/product/{category}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> serveAd(@PathVariable("category") String category) {
        List<Campaign> activeCampaigns = repository.getCampaignsByStatus(CampaignStatus.ACTIVE)
                .stream()
                .sorted(Comparator.comparingLong(campaign -> campaign.getBid() * -1))
                .collect(Collectors.toList());

        boolean firstIteration = true;
        Product res = null;
        if (!activeCampaigns.isEmpty()) {
            for (Campaign campaign : activeCampaigns) {
                List<Product> productsBySeller = repository.getProductsBySellerId(campaign.getSellerId());
                if (firstIteration) {
                    res = getRandomProduct(productsBySeller);
                    firstIteration = false;
                }
                List<Product> productsByCategory = productsBySeller.stream()
                        .filter(product -> product.getCategory().equals(category))
                        .collect(Collectors.toList());
                if (!productsByCategory.isEmpty()) {
                    res = getRandomProduct(productsBySeller);
                }
            }
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/newProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> CreateProduct(@PathVariable("serialNum") long serialNum, @PathVariable("title") String title, @PathVariable("price") long price, @PathVariable("category") String category, @PathVariable("category") long sellerId) {
        try {
            Product newProduct = new Product(serialNum, title, price, category, sellerId);
            repository.addProduct(newProduct);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    private Product getRandomProduct(List<Product> listOfProducts) {
        Random r = new Random();
        return listOfProducts.get(r.nextInt(listOfProducts.size()));
    }

}