package ice.cream.shop.controller.model;

import ice.cream.shop.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IceCreamShopEmployeeData {
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeJobTitle;
	private String employeePhone;
	

	public IceCreamShopEmployeeData(Employee employee) {
		this.employeeId = employee.getEmployeeId();
		this.employeeFirstName = employee.getEmployeeFirstName();
		this.employeeLastName = employee.getEmployeeLastName();
		this.employeePhone = employee.getEmployeePhone();
		this.employeeJobTitle = employee.getEmployeeJobTitle();
	}
}
