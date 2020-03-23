package pl.haladyj.spring_data_caching.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.haladyj.spring_data_caching.domain.Customer;
import pl.haladyj.spring_data_caching.exception.ResourceNotFoundException;
import pl.haladyj.spring_data_caching.model.Customers;
import pl.haladyj.spring_data_caching.service.CustomerServiceImpl;
import pl.haladyj.spring_data_caching.service.dto.CustomerDto;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer
                .map(response -> (ResponseEntity.ok().body(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Customers> getAllCustomers(){
        Customers customers = new Customers(customerService.getAllCustomers());
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto){
        Customer customer = customerService.createCustomer(customerDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){

/*        return customerService
                .getCustomerById(id)
                .map(result -> (ResponseEntity.ok().body(result)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
        return customerService
                .updateCustomer(customerDto,id)
                .map(result -> (ResponseEntity.ok().body(result)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
        try{
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException exception){
            return ResponseEntity.badRequest().build();
        }
    }

}
