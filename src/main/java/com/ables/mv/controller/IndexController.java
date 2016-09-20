/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ables.mv.controller;

import com.ables.mv.model.Shop;
import com.ables.mv.service.ShopService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ables
 */
@Controller
//@RequestMapping(value="/shop")
public class IndexController {
    
    @Autowired
	private ShopService shopService;
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView("index");
        Shop shop = new Shop();
        shop.setEmplNumber(1);
        shop.setName("Awolaju Saheed");
        shopService.create(shop);
        System.out.println("create shop:"+shop.getName());
        return mv;
    }
    
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView newShopPage() {
		ModelAndView mav = new ModelAndView("shop-new", "shop", new Shop());
		return mav;
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createNewShop(@ModelAttribute Shop shop, 
			final RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		String message = "New shop "+shop.getName()+" was successfully created.";

		shopService.create(shop);
		mav.setViewName("redirect:/index.html");

		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView shopListPage() {
		ModelAndView mav = new ModelAndView("shop-list");
		List shopList = shopService.findAll();
		mav.addObject("shopList", shopList);
		return mav;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editShopPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("shop-edit");
		Shop shop = shopService.findById(id);
		mav.addObject("shop", shop);
		return mav;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editShop(@ModelAttribute Shop shop,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes){

		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Shop was successfully updated.";

		shopService.update(shop);

		redirectAttributes.addFlashAttribute("message", message);	
		return mav;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteShop(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes){

		ModelAndView mav = new ModelAndView("redirect:/index.html");		

		Shop shop = shopService.delete(id);
		String message = "The shop "+shop.getName()+" was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
}
