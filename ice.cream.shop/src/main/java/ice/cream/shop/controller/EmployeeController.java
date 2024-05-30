package ice.cream.shop.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ice.cream.shop.controller.model.IceCreamShopEmployeeData;
import ice.cream.shop.service.EmployeeService;

@RestController
@RequestMapping("/ice_cream_shop_employee")
@Slf4j
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/{iceCreamShopId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public IceCreamShopEmployeeData addEmployee(@PathVariable Long iceCreamShopId,
			@RequestBody IceCreamShopEmployeeData iceCreamShopEmployeeData) {
		log.info("Adding employee to ice cream shop {}: {}", iceCreamShopId, iceCreamShopEmployeeData);
		return employeeService.saveEmployee(iceCreamShopId, iceCreamShopEmployeeData);
	}
	
	@DeleteMapping("/{employeeId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Map<String, String> deleteEmployee(@PathVariable Long employeeId) {
        log.info("Deleting employee with ID {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        return Collections.singletonMap("message", "Deletion successful");
    }
}


