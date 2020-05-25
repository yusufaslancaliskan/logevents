package com.example.accessingdatamongodb;

import com.example.accessingdatamongodb.model.Customer;
import com.example.accessingdatamongodb.model.Location;
import com.example.accessingdatamongodb.repository.CustomerRepository;
import com.example.accessingdatamongodb.repository.LocationRepository;
import com.example.accessingdatamongodb.utils.CClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AccessingDataMongodbApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    	locationRepository.save(new Location(30,30));
    	locationRepository.save(new Location(40,40));

    	List<Location> locationList = locationRepository.findAll();
    	locationRepository.saveAll(locationList);

    	for(Location t : locationList){
    	    locationRepository.delete(t);
        }

    	saveCustomer();

    }

    public void saveCustomer() {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        repository.saveAll(repository.findAll());

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }

}
