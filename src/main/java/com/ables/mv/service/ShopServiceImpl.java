/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ables.mv.service;

import com.ables.mv.model.Shop;
import com.ables.mv.repo.ShopRepository;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ables
 */
@Service
public class ShopServiceImpl implements ShopService{
    
	@Resource
	private ShopRepository shopRepository;

	@Override
	@Transactional
	public Shop create(Shop shop) {
		Shop createdShop = shop;
		return shopRepository.save(createdShop);
	}

	@Override
	@Transactional
	public Shop findById(int id) {
		return shopRepository.findOne(id);
	}

	@Override
	@Transactional
	public Shop delete(int id){
		Shop deletedShop = shopRepository.findOne(id);

                Objects.requireNonNull(deletedShop, "deletedShop cant be null");
		shopRepository.delete(deletedShop);
		return deletedShop;
	}

	@Override
	@Transactional
	public List findAll() {
		return shopRepository.findAll();
	}

	@Override
	@Transactional()
	public Shop update(Shop shop) {
		Shop updatedShop = shopRepository.findOne(shop.getId());

		Objects.requireNonNull(updatedShop, "deletedShop cant be null");

		updatedShop.setName(shop.getName());
		updatedShop.setEmplNumber(shop.getEmplNumber());
		return updatedShop;
	}
}
