package ice.cream.shop.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ice.cream.shop.controller.model.IceCreamShopCustomerData;
import ice.cream.shop.entity.Customer;
import ice.cream.shop.entity.IceCreamShop;
import ice.cream.shop.repository.CustomerDao;
import ice.cream.shop.repository.IceCreamShopDao;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private IceCreamShopDao iceCreamShopDao;
    
    @Autowired
    private IceCreamShopService iceCreamShopService;

    @Transactional(readOnly = false)
    public IceCreamShopCustomerData postCustomer(Long shopId, IceCreamShopCustomerData iceCreamShopCustomer) {
        IceCreamShop shop = iceCreamShopService.findOrCreateIceCreamShop(shopId);

        if (shop.equals(null)) {
            throw new NoSuchElementException("Invalid ice cream shop id");
        }

        Customer customer = findOrCreateCustomer(shopId, iceCreamShopCustomer.getCustomerId());
        transformCustomer(customer, iceCreamShopCustomer);
        
        shop.getCustomer().add(customer);
        customer.getIceCreamShop().add(shop);
        return new IceCreamShopCustomerData(customerDao.save(customer));
    }
    
    private Customer findOrCreateCustomer(Long shopId, Long customerId) {
        if (Objects.isNull(customerId)) {
            return new Customer();
        }
        return findCustomerById(shopId, customerId);
    }

    private Customer findCustomerById(Long shopId, Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID =" + customerId + " was not found"));
        boolean found = false;
        for (IceCreamShop shop : customer.getIceCreamShop()) {
            if (shop.getIceCreamShopId() == shopId) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Customer with the ID =" + customerId + " not a member of the ice cream shop with ID");
        }
        return customer;
    }

    public Customer transformCustomer(Customer customer, IceCreamShopCustomerData iscd) {
        customer.setCustomerFirstName(iscd.getCustomerFirstName());
        customer.setCustomerLastName(iscd.getCustomerLastName());
        customer.setCustomerEmail(iscd.getCustomerEmail());
        customer.setCustomerPhone(iscd.getCustomerPhone());
        return customer;
    }

    @SuppressWarnings("unused")
    private Customer findCustomerById(Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        boolean found = customer.getIceCreamShop().stream().anyMatch(shop -> shop.getIceCreamShopId().equals(customerId));
        if (!found) {
            throw new IllegalArgumentException("Customer does not belong to the specified ice cream shop");
        }
        return customer;
    }
}


