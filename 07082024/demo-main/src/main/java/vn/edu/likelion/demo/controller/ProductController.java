package vn.edu.likelion.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vn.edu.likelion.demo.entity.ProductEntity;
import vn.edu.likelion.demo.model.ProductDTO;
import vn.edu.likelion.demo.service.ProductService;
import vn.edu.likelion.demo.util.StringUtil;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StringUtil stringUtil;

    public Iterable<ProductEntity> findAll() {
        return productService.findAll();
    }

    public ProductEntity create(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        if (stringUtil.isNull(productDTO.getProductName())) {
            throw new ArithmeticException();
        }
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setIsDeleted(0);

        return productService.save(productEntity);
    }

    public void remove(int id) {
        productService.remove(id);
    }
}
