package ice.cream.shop.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ice.cream.shop.controller.model.IceCreamShopData;
import ice.cream.shop.entity.IceCreamShop;
import ice.cream.shop.service.IceCreamShopService;

@RestController
@RequestMapping("/ice_cream_shop")
@Slf4j
public class IceCreamShopController {
	@Autowired
	private IceCreamShopService iceCreamShopService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public IceCreamShop createIceCreamShop(@RequestBody IceCreamShopData iceCreamShopData) {
		log.info("Creating ice cream shop {}", iceCreamShopData);
		return iceCreamShopService.createIceCreamShop(iceCreamShopData);
	}

	@PutMapping("/{iceCreamShopId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public IceCreamShopData updateIceCreamShop(@PathVariable Long iceCreamShopId,
			@RequestBody IceCreamShopData iceCreamShopData) {
		iceCreamShopData.setShopId(iceCreamShopId);
		log.info("Updating shop {}", iceCreamShopData);
		return iceCreamShopService.saveIceCreamShop(iceCreamShopData);
	}

	@GetMapping("/{shopId}")
	public IceCreamShopData retrieveIceCreamShopById(@PathVariable Long shopId) {
		log.info("Retrieving ice cream shop {}", shopId);
		return iceCreamShopService.retrieveIceCreamShopById(shopId);
	}

	@GetMapping
	public List<IceCreamShopData> getAllIceCreamShops() {
		log.info("Retrieving all ice cream shops");
		return iceCreamShopService.findAllIceCreamShops();
	}

	@DeleteMapping("/{shopId}")
	public Map<String, String> deleteIceCreamShopById(@PathVariable Long shopId) {
		log.info("Deleting ice cream shop {}", shopId);
		iceCreamShopService.deleteIceCreamShopById(shopId);
		return Collections.singletonMap("message", "Deletion successful");
	}
}
