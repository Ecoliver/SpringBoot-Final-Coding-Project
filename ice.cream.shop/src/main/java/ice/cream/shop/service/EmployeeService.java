package ice.cream.shop.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.NoArgsConstructor;
import ice.cream.shop.controller.model.IceCreamShopEmployeeData;
import ice.cream.shop.entity.Employee;
import ice.cream.shop.entity.IceCreamShop;
import ice.cream.shop.repository.EmployeeDao;
import ice.cream.shop.repository.IceCreamShopDao;

@Data
@Service
@NoArgsConstructor
public class EmployeeService {

    @Autowired
    private IceCreamShopDao iceCreamShopDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(readOnly = false)
    public IceCreamShopEmployeeData saveEmployee(Long shopId, IceCreamShopEmployeeData iceCreamShopEmployee) {
        IceCreamShop shop = findIceCreamShopById(shopId);
        Employee employee = findOrCreateEmployee(iceCreamShopEmployee.getEmployeeId(), shopId);
        Employee newEmployee = transformEmployee(employee, iceCreamShopEmployee);
        newEmployee.setIceCreamShop(shop);
        shop.getEmployees().add(newEmployee);

        iceCreamShopDao.save(shop);
        employeeDao.save(newEmployee);
        return new IceCreamShopEmployeeData(newEmployee);
    }

    public Employee transformEmployee(Employee employee, IceCreamShopEmployeeData iceCreamShopEmployee) {
        employee.setEmployeeFirstName(iceCreamShopEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(iceCreamShopEmployee.getEmployeeLastName());
        employee.setEmployeePhone(iceCreamShopEmployee.getEmployeePhone());
        employee.setEmployeeJobTitle(iceCreamShopEmployee.getEmployeeJobTitle());
        return employee;
    }

    private IceCreamShop findIceCreamShopById(Long shopId) {
        return iceCreamShopDao.findById(shopId)
                .orElseThrow(() -> new NoSuchElementException("Invalid ice cream shop id"));
    }

    private Employee findEmployeeById(Long shopId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        if (!employee.getIceCreamShop().getIceCreamShopId().equals(shopId)) {
            throw new IllegalArgumentException("Employee does not belong to the specified ice cream shop");
        }
        return employee;
    }

    private Employee findOrCreateEmployee(Long employeeId, Long shopId) {
        if (Objects.isNull(employeeId)) {
            return new Employee();
        }
        return findEmployeeById(shopId, employeeId);
    }
    
    @Transactional
    public void deleteEmployee(Long employeeId) {
        if (!employeeDao.existsById(employeeId)) {
            throw new NoSuchElementException("Employee not found with ID: " + employeeId);
        }
        employeeDao.deleteById(employeeId);
    }
}
