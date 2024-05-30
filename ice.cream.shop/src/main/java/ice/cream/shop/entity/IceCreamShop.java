package ice.cream.shop.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "icecreamshop")
public class IceCreamShop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ice_cream_shop_id")
	private Long iceCreamShopId;

	@Column(name = "shop_name")
	private String iceCreamShopName;
	@Column(name = "ice_cream_shop_address")
	private String iceCreamShopAddress;
	@Column(name = "ice_cream_shop_city")
	private String iceCreamShopCity;
	@Column(name = "ice_cream_shop_state")
	private String iceCreamShopState;
	@Column(name = "ice_cream_shop_zip")
	private String iceCreamShopZip;
	@Column(name = "ice_cream_shop_phone")
	private String iceCreamShopPhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "ice_cream_shop_customer", joinColumns = @JoinColumn(name = "ice_cream_shop_id"),
	inverseJoinColumns = @JoinColumn(name = "customer_Id"))
	private Set<Customer> customer = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "iceCreamShop", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employees = new HashSet<>();

}