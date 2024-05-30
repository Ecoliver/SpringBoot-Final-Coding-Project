package ice.cream.shop.controller.model;

import java.util.HashSet;
import java.util.Set;

import ice.cream.shop.entity.Customer;
import ice.cream.shop.entity.Employee;
import ice.cream.shop.entity.IceCreamShop;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IceCreamShopData {
    private Long shopId;

    private String shopName;
    private String shopAddress;
    private String shopCity;
    private String shopState;
    private String shopZip;
    private String shopPhone;
    private Set<IceCreamShopCustomerData> customers = new HashSet<>();
    private Set<IceCreamShopEmployeeData> employees = new HashSet<>();

    public IceCreamShopData(IceCreamShop iceCreamShop) {
        this.shopId = iceCreamShop.getIceCreamShopId();
        this.shopName = iceCreamShop.getIceCreamShopName();
        this.shopAddress = iceCreamShop.getIceCreamShopAddress();
        this.shopCity = iceCreamShop.getIceCreamShopCity();
        this.shopState = iceCreamShop.getIceCreamShopState();
        this.shopZip = iceCreamShop.getIceCreamShopZip();
        this.shopPhone = iceCreamShop.getIceCreamShopPhone();

        for (Customer customer : iceCreamShop.getCustomer()) {
            this.customers.add(new IceCreamShopCustomerData(customer));
        }

        for (Employee employee : iceCreamShop.getEmployees()) {
            this.employees.add(new IceCreamShopEmployeeData(employee));
        }
    }
}

