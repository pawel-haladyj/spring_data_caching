package pl.haladyj.spring_data_caching.converter;

public interface Converter<Customer, CustomerDto> {
    Customer toEntity(CustomerDto customerDto);
    CustomerDto toDto(Customer customer);
}
