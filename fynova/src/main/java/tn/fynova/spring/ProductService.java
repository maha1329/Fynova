package tn.fynova.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ProductService {

    List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
      
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }


}
