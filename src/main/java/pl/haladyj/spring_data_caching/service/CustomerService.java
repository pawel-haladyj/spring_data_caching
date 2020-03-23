package pl.haladyj.spring_data_caching.service;

import pl.haladyj.spring_data_caching.domain.Customer;
import pl.haladyj.spring_data_caching.model.Customers;
import pl.haladyj.spring_data_caching.service.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> getCustomerById(Long id);
    public List<Customer> getAllCustomers();
    public Customer createCustomer(CustomerDto customerDto);
    public Optional<Customer> updateCustomer(CustomerDto customerDto, Long id);
    public void deleteCustomer(Long id);

}
