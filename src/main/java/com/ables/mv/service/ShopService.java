/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ables.mv.service;

import com.ables.mv.model.Shop;
import java.util.List;

/**
 *
 * @author ables
 */
public interface ShopService {
    public Shop create(Shop shop);
    public Shop  delete(int id);
    public List findAll();
    public Shop update(Shop shop);
    public Shop findById(int id);
}
