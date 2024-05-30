package ice.cream.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ice.cream.shop.controller.model.IceCreamShopData;
import ice.cream.shop.entity.IceCreamShop;
import ice.cream.shop.repository.IceCreamShopDao;

@Service
public class IceCreamShopService {
    @Autowired
    private IceCreamShopDao iceCreamShopDao;

    @Transactional(readOnly = false)
    public IceCreamShopData saveIceCreamShop(IceCreamShopData iceCreamShopData) {
        Long shopId = iceCreamShopData.getShopId();
        IceCreamShop iceCreamShop = findOrCreateIceCreamShop(shopId);

        copyIceCreamShopFields(iceCreamShop, iceCreamShopData);
        return new IceCreamShopData(iceCreamShopDao.save(iceCreamShop));
    }

    private void copyIceCreamShopFields(IceCreamShop iceCreamShop, IceCreamShopData iceCreamShopData) {
        iceCreamShop.setIceCreamShopAddress(iceCreamShopData.getShopAddress());
        iceCreamShop.setIceCreamShopCity(iceCreamShopData.getShopCity());
        iceCreamShop.setIceCreamShopId(iceCreamShopData.getShopId());
        iceCreamShop.setIceCreamShopName(iceCreamShopData.getShopName());
        iceCreamShop.setIceCreamShopPhone(iceCreamShopData.getShopPhone());
        iceCreamShop.setIceCreamShopState(iceCreamShopData.getShopState());
        iceCreamShop.setIceCreamShopZip(iceCreamShopData.getShopZip());
    }

    public IceCreamShop findOrCreateIceCreamShop(Long shopId) {
        if (Objects.isNull(shopId)) {
            return new IceCreamShop();
        } else {
            return findIceCreamShopById(shopId);
        }
    }

    @Transactional(readOnly = true)
    public IceCreamShopData retrieveIceCreamShopById(Long shopId) {
        IceCreamShop iceCreamShop = findIceCreamShopById(shopId);
        return new IceCreamShopData(iceCreamShop);
    }

    public IceCreamShop createIceCreamShop(IceCreamShopData iceCreamShopData) {
        if (iceCreamShopData == null) {
            throw new IllegalArgumentException("Ice cream shop data cannot be null");
        }

        IceCreamShop iceCreamShop = new IceCreamShop();
        setIceCreamShopFields(iceCreamShopData, iceCreamShop);
        iceCreamShopDao.save(iceCreamShop);

        return iceCreamShop;
    }

    @Transactional(readOnly = true)
    public List<IceCreamShopData> findAllIceCreamShops() {
        List<IceCreamShop> iceCreamShops = iceCreamShopDao.findAll();
        List<IceCreamShopData> iceCreamShopDataList = new ArrayList<>();
        for (IceCreamShop iceCreamShop : iceCreamShops) {
            IceCreamShopData data = new IceCreamShopData(iceCreamShop);
            data.setEmployees(Collections.emptySet());
            data.setCustomers(Collections.emptySet());
            iceCreamShopDataList.add(data);
        }
        return iceCreamShopDataList;
    }

    private void setIceCreamShopFields(IceCreamShopData iceCreamShopData, IceCreamShop iceCreamShop) {
        iceCreamShop.setIceCreamShopName(iceCreamShopData.getShopName());
        iceCreamShop.setIceCreamShopAddress(iceCreamShopData.getShopAddress());
        iceCreamShop.setIceCreamShopCity(iceCreamShopData.getShopCity());
        iceCreamShop.setIceCreamShopState(iceCreamShopData.getShopState());
        iceCreamShop.setIceCreamShopZip(iceCreamShopData.getShopZip());
        iceCreamShop.setIceCreamShopPhone(iceCreamShopData.getShopPhone());
    }

    public void deleteIceCreamShopById(Long shopId) {
        IceCreamShop iceCreamShop = findIceCreamShopById(shopId);
        if (iceCreamShop != null) {
            iceCreamShopDao.delete(iceCreamShop);
        } else {
            throw new NoSuchElementException("Ice cream shop with ID " + shopId + " does not exist.");
        }
    }

    public IceCreamShop findIceCreamShopById(Long shopId) {
        return iceCreamShopDao.findById(shopId)
                .orElseThrow(() -> new NoSuchElementException("Ice cream shop with ID =" + shopId + " was not found."));
    }
}
