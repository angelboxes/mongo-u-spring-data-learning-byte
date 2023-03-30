package com.example.demo;

import com.example.demo.model.TVItem;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class DemoApplication implements CommandLineRunner {

    @Autowired
    ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public void run(String... args) {
        itemRepository.deleteAll();
        createTVItems();
        showAllTVItems();
        getItemsByCategory("comedy");
        findCountOfTVItems();
    }

    public void createTVItems() {
        itemRepository.save(new TVItem("1", "How I Met your Mother", 1, "comedy"));
        itemRepository.save(new TVItem("2", "How I Met your Father", 3, "comedy"));
        itemRepository.save(new TVItem("3", "Friends", 3, "comedy"));
        itemRepository.save(new TVItem("4", "CSI", 1, "drama"));
        itemRepository.save(new TVItem("5", "Greys Anatomy", 1, "drama"));
    }

    public void showAllTVItems() {
        List<TVItem> result = itemRepository.findAll();
        result.forEach(tvItem -> System.out.println(getItemDetails(tvItem)));
    }

    public void getTVItemByName(String name) {
        System.out.println("Getting TVItem by name" + name);
        TVItem result = itemRepository.findTVItemByName(name);
        System.out.println(getItemDetails(result));
    }

    public void getItemsByCategory(String category) {
        System.out.println("Getting items for category" + category);
        List<TVItem> result = itemRepository.findAll(category);
        result.forEach(tvItem -> System.out.println("Name: " + tvItem.getName() + ", Quantity: " + tvItem.getQuantity()));
    }

    public String getItemDetails(TVItem tvItem) {
        return "Name: " + tvItem.getName() + ", Quantity: " + tvItem.getQuantity();
    }

    public void findCountOfTVItems() {
        long count = itemRepository.count();
        System.out.println("Number of documents in the collection: " + count);
    }

}
