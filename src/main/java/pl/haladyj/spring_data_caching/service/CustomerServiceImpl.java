package pl.haladyj.spring_data_caching.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import pl.haladyj.spring_data_caching.converter.CustomerConverter;
import pl.haladyj.spring_data_caching.domain.Customer;
import pl.haladyj.spring_data_caching.exception.ResourceNotFoundException;
import pl.haladyj.spring_data_caching.repository.CustomerRepository;
import pl.haladyj.spring_data_caching.service.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = "customer")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }


    @Override
    @Cacheable(cacheNames = "getCustomerById")
    public Optional<Customer> getCustomerById(Long id) {
        log.info(String.format("Get customer, id: %d", id));
        return customerRepository.findById(id);
    }

    @Override
    @Cacheable(cacheNames = "getAllCustomers")
    public List<Customer> getAllCustomers() {
        log.info(String.format("get all customers"));
        return customerRepository.findAll();
    }

    @Override
    @Caching(
            evict = {@CacheEvict (cacheNames = "getCustomerById", allEntries = true),
                    @CacheEvict (cacheNames = "getAllCustomers", allEntries = true)}
    )
    public Customer createCustomer(CustomerDto customerDto) {
        log.info(String.format("Creating customer: %s", customerDto.toString()));
        Customer customer = customerConverter.toEntity(customerDto);
        return customerRepository.save(customer);
    }

    @Override
    @Caching(
            evict = {@CacheEvict (cacheNames = "getCustomerById", allEntries = true),
                    @CacheEvict (cacheNames = "getAllCustomers", allEntries = true)}
    )
    public Optional<Customer> updateCustomer(CustomerDto customerDto, Long id) {
        log.info(String.format("Updating customer, id: %d, data: %s", id, customerDto.toString()));
        Customer customer = customerConverter.toEntity(customerDto);
        Optional<Customer> optional;
        if(customerRepository.findById(id).isEmpty()){
            optional = Optional.ofNullable(null);
        } else {
            customer.setId(id);
            optional = Optional.of(customer);
            customerRepository.save(customer);
        }
        return optional;
    }

    @Override
    @Caching(
            evict = {@CacheEvict (cacheNames = "getCustomerById", allEntries = true),
                    @CacheEvict (cacheNames = "getAllCustomers", allEntries = true)}
    )
    public void deleteCustomer(Long id) {
        if(customerRepository.findById(id).isEmpty()){
            log.info(String.format("Customer, id: %d is not present in db, can not delete", id));
            throw new ResourceNotFoundException(String.format("Customer id: %d, is not present", id));
        }
        log.info(String.format("deleting Customer, id: %d", id));
        customerRepository.deleteById(id);
    }
}
