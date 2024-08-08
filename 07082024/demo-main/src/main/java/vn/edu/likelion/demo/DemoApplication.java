package vn.edu.likelion.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import vn.edu.likelion.demo.controller.ProductController;
import vn.edu.likelion.demo.entity.ProductEntity;
import vn.edu.likelion.demo.model.ProductDTO;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		ProductController productController = context.getBean(ProductController.class);

		String[] beanName = context.getBeanDefinitionNames();
		for (String name: beanName) {
			System.out.println(name);
		}

//		// chuc nang tao product
//		ProductDTO productDTO = new ProductDTO();
//		productDTO.setProductName("Nuoc tang luc");
//		productDTO.setQuantity(10);
//		productController.create(productDTO);
//
//
//		ProductDTO productDTO2 = new ProductDTO();
//		productDTO2.setProductName("Nuoc loc");
//		productDTO2.setQuantity(5);
//		productController.create(productDTO2);
//
//		// chuc nang tim kiem toan bo product
//		productController.findAll().forEach(a -> System.out.println(a.toString()));
	}

}
