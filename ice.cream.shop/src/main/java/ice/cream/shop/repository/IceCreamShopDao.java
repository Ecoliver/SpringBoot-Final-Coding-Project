package ice.cream.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ice.cream.shop.entity.IceCreamShop;

public interface IceCreamShopDao extends JpaRepository<IceCreamShop, Long> {
}
