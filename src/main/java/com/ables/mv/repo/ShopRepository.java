/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ables.mv.repo;

import com.ables.mv.model.Shop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ablesn
 */
public interface ShopRepository extends JpaRepository<Shop, Integer>{
}
