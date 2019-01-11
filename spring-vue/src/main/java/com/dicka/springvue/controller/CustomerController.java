package com.dicka.springvue.controller;

import com.dicka.springvue.entity.Customer;
import com.dicka.springvue.exception.ResourceNotFound;
import com.dicka.springvue.exception.ResponseDelete;
import com.dicka.springvue.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/customer/")
@CrossOrigin(origins = {"*"})
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping(value = "/find")
    public List<Customer> getByAge(@RequestParam("age") int age){
        return customerRepository.findByAge(age);
    }

    @PostMapping(value = "/create")
    public Customer newCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDelete deleteCustomer(@PathVariable("id") Long id){
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null){
            throw new ResourceNotFound("sorry id with : "+id+" not found.");
        }else{
            customerRepository.delete(customer);

            List<Customer> customers = new ArrayList<>();
            for (Customer custom : customerRepository.findAll()){
                customers.add(custom);
            }

            return ResponseDelete.builder()
                    .code(200)
                    .message("success")
                    .currentList(customers)
                    .build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public Customer updateCustomer(@PathVariable("id") Long id,
                                   @RequestBody Customer customer){
        return customerRepository.findById(id)
                .map(currentData -> {
                    currentData.setName(customer.getName());
                    currentData.setAge(customer.getAge());
                    currentData.setActive(customer.isActive());
                    return customerRepository.save(currentData);
                }).orElseThrow(() ->
                        new ResourceNotFound("sorry id : "+id+" not found."));
    }

}
