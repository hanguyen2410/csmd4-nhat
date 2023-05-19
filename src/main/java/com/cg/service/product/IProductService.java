package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductCreateReqDTO;
import com.cg.model.dto.ProductCreateResDTO;
import com.cg.model.dto.ProductResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product, Long> {

    ProductCreateResDTO create(ProductCreateReqDTO productCreateReqDTO);

    List<ProductResDTO> getAllProductResDTO();
}
