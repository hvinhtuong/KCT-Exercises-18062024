package vn.edu.likelion.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vn.edu.likelion.demo.entity.ProductEntity;
import vn.edu.likelion.demo.entity.WarehouseEntity;
import vn.edu.likelion.demo.model.ProductDTO;
import vn.edu.likelion.demo.model.WarehouseDTO;
import vn.edu.likelion.demo.service.ProductService;
import vn.edu.likelion.demo.service.WarehouseService;

@Controller
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    public Iterable<WarehouseEntity> findAll() {
        return warehouseService.findAll();
    }

    public WarehouseEntity create(WarehouseDTO warehouseDTO) {
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setWarehouseName(warehouseDTO.getWarehouseName());
        warehouseEntity.setWarehouseAddress(warehouseDTO.getWarehouseAddress());

        return warehouseService.save(warehouseEntity);
    }
}
