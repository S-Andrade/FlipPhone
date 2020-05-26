package com.ua.flipPhone;

import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlipPhoneApplication  {//implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(FlipPhoneApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception { //Populating DB with 10 Samsung phones
		for(int i=1; i<=10; i++) {
			Product phone = new Product();
			phone.setCpu_gpu("octacore");
	 		phone.setRam_rom("8_64 "+i);
			phone.setImage("4000dpi");
			phone.setScreen_size("5.5");
			phone.setScreen_type("led");
			phone.setBattery("4000mAH");
			phone.setOs("Android");
			phone.setSelfie_cam("12px");
			phone.setCamera("10px");
			phone.setProduct_name("Samsung" + i);

			productRepository.save(phone);
		}
	}*/

}
