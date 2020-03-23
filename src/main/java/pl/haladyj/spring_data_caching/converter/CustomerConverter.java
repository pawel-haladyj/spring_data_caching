package pl.haladyj.spring_data_caching.converter;

import org.springframework.stereotype.Component;
import pl.haladyj.spring_data_caching.domain.Customer;
import pl.haladyj.spring_data_caching.service.dto.CustomerDto;

@Component
public class CustomerConverter implements Converter<Customer, CustomerDto> {
    @Override
    public Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        return customer;
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        return customerDto;
    }
}
