package com.bms.pharmacymanagementsystem.service;

import com.bms.pharmacymanagementsystem.dto.CustomerDto;
import com.bms.pharmacymanagementsystem.dto.converter.CustomerDtoConverter;
import com.bms.pharmacymanagementsystem.exception.CustomerAlreadyExistException;
import com.bms.pharmacymanagementsystem.exception.CustomerListEmptyException;
import com.bms.pharmacymanagementsystem.exception.CustomerNotFoundException;
import com.bms.pharmacymanagementsystem.model.Customer;
import com.bms.pharmacymanagementsystem.repository.CustomerRepository;
import com.bms.pharmacymanagementsystem.request.customer.CreateCustomerRequest;
import com.bms.pharmacymanagementsystem.request.customer.UpdateCustomerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public void createCustomer(final CreateCustomerRequest request) {

        checkIfCustomerExist(request.getEmail());

        Customer customer = new Customer(
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getAge(),
                request.getAddress(),
                request.getEmail()
        );

        customerRepository.save(customer);
    }

    public void updateCustomer(final String id, final UpdateCustomerRequest request) {
        Customer customer = getCustomerById(id);

        if (!customer.getEmail().equals(request.getEmail())) {
            checkIfCustomerExist(request.getEmail());
        }

        Customer updatedCustomer = new Customer(
                customer.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getAge(),
                request.getAddress(),
                request.getEmail(),
                customer.getSales(),
                customer.getPurchasers(),
                customer.getReports()
        );

        customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(final String id) {
        customerRepository.delete(getCustomerById(id));
    }

    public CustomerDto findCustomerById(final String id) {
        return converter.convert(getCustomerById(id));
    }

    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            throw new CustomerListEmptyException("Customer list is empty.");
        }

        return converter.convert(customers);
    }

    protected Customer getCustomerById(final String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found by id: " + id));
    }

    private void checkIfCustomerExist(final String email) {
        if (customerRepository.existsByEmailIgnoreCase(email)) {
            throw new CustomerAlreadyExistException("Customer already exist by email: " + email);
        }
    }
}
