package com.example.demo.api.Customer;

import com.example.demo.model.Customer.*;
import com.example.demo.model.Person.Person;
import com.example.demo.model.Product.Product_Details;
import com.example.demo.service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@RequestMapping("api/v1/")
@RestController
public class CustomerController {

    @Autowired
    private  final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    //Registering A new Customer
    @PostMapping(path="/registerNewCustomer")
    public  String addCustomer (@Valid @NotNull @RequestBody CustomerDetails customerDetails){
        System.out.println("Adding People");
        //security check class
//        apiValidation.personValidator(person);
        return customerService.addCustomer(customerDetails);
    }

    //list of customer
    @GetMapping(path="/allCustomer")
    public List<Customer_Username> getALlCustomer_Username(){
        return customerService.allCustomerUsername();
    }

    // Validating A Existing Customer
    @PostMapping(path = "/ValidateUser")
    public  void ValidateCustomer(@Valid @NotNull @RequestBody CustomerValidation customerValidation )
    {
        System.out.println("Validating User");
        customerService.ValidateCustomer(customerValidation);
    }


    // Updating Customer Details
    @PutMapping(path ="{customer_username}" )
    public void updateCustomer(@PathVariable("customer_username") String customer_username, @Valid @NotNull @RequestBody CustomerDetails customerDetails){
        customerService.updateCustomer(customer_username,customerDetails);
    }


    //Getting Customer Details by Username
    @GetMapping(path="{customer_username}")
    public CustomerDetails GetCustomerDetails(@PathVariable("customer_username")String customer_username)
    {
        return customerService.getCustomerDetails(customer_username);
    }

    // All products of Cart
    @GetMapping(path = "/cart")
    public List<Customer_Cart> allCart(@Valid @NotNull @RequestBody Customer_Username customer_username)
    {
        return customerService.getUserCart(customer_username);
    }


    //Add to Cart
    @GetMapping(path = "/add_to_cart")
    public void AddToCart(@Valid @NotNull @RequestBody Customer_Cart customer_cart)
    {
        customerService.Insert_to_Cart(customer_cart);
    }

    //Delete from Cart
    @GetMapping(path = "/Delete_From_Cart/{customer_username}")
    public void DeleteFromCart(@PathVariable("customer_username") String customer_username,@Valid @NotNull @RequestBody  Product_Details product_details)
    {
        customerService.Delete_from_Cart(customer_username,product_details);
    }

    //All orders
    @GetMapping(path = "/orders")
    public List<Customer_Orders> allOrders(@Valid @NotNull @RequestBody Customer_Username customer_username)
    {
       return customerService.getallOrders(customer_username);
    }


}
