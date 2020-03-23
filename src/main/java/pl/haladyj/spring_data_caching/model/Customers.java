package pl.haladyj.spring_data_caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.haladyj.spring_data_caching.domain.Customer;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customers {
    private List<Customer> customers;
}
