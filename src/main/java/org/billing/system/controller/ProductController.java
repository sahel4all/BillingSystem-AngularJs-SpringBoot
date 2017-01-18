package org.billing.system.controller;

import org.billing.system.model.Product;
import org.billing.system.service.ProductService;
import org.billing.system.service.InvoiceService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by msahel on 8/7/2016.
 */
@RestController
@Component
public class ProductController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value="/product/{id}",method = RequestMethod.GET)
    public ResponseEntity getProducts(@PathVariable final long id){
    //public ResponseEntity getProducts(){
        try{
            System.out.println("From Controller");
            Product product=productService.getProductDetails(id);
            return new ResponseEntity(product,HttpStatus.OK);
        }
        catch(Exception e){
            //LOGGER.error(" Error occurred while fetching patient id {} : {} ",id, e.getMessage());
            return new ResponseEntity("No product found:", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/products", method=RequestMethod.GET)
    public List<Product> getAllProducts(){
        List<Product> products=productService.getAllProducts();
        return products;
    }

    @RequestMapping(value="/product",method = RequestMethod.PUT)
    public ResponseEntity addProduct(@RequestBody final Product product){
        System.out.println(product);
        Product productNew=productService.insertProduct(product);
        System.out.println("productNew::"+productNew);
        return new ResponseEntity(product,HttpStatus.OK);
    }

    @RequestMapping(value="/getNextSeqVal",method = RequestMethod.GET)
    public ResponseEntity getNextSeqVal(){
        BigInteger invoiceSeq=invoiceService.getNextSeqVal();
        //Invoice invoice=new Invoice();
        System.out.println("invoice::"+invoiceSeq);
        return new ResponseEntity(invoiceSeq,HttpStatus.OK);
    }
}
