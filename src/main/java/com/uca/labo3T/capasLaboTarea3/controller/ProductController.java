package com.uca.labo3T.capasLaboTarea3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.labo3T.capasLaboTarea3.domain.Product;

@Controller 
public class ProductController {
	private List<Product> ball = new ArrayList<Product>();
	
	@GetMapping("/fillProduct")
	public ModelAndView fillBall() {
		ModelAndView mav = new ModelAndView();
		ball.add(new Product(0, "Soccer Ball" , "10"));
		ball.add(new Product(1, "Bkb Ball" , "15"));
		ball.add(new Product(2, "PingPong Ball" , "5"));
		mav.setViewName("home");
		mav.addObject("producto", ball);
		return mav;
	}
	
	@PostMapping("/validate")
	public ModelAndView validate(Product ballP) {
		ModelAndView mav = new ModelAndView();
		Integer amount = Integer.parseInt(ballP.getCantidad());
		mav.addObject("producto", ball.get(ballP.getId()).getNombre());
		Integer ballAmount = Integer.parseInt(ball.get(ballP.getId()).getCantidad());
		if( amount > ballAmount) {
			mav.setViewName("error");
			
		}else {
			Integer total = ballAmount - amount;
			ballP.setCantidad(total.toString());
			ball.set(ballP.getId(), ballP);
			mav.setViewName("buy");
		}
		return mav;	
	}
	
}