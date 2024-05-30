package ice.cream.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "employee_first_name")
	private String employeeFirstName;
	@Column(name = "employee_last_name")
	private String employeeLastName;
	@Column(name = "employee_job_title")
	private String employeeJobTitle;
	@Column(name = "employee_phone")
	private String employeePhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "ice_cream_shop_id, nullable = false")
	private IceCreamShop iceCreamShop;

}
