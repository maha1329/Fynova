package tn.fynova.spring;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

    private int id;


    public Product() {}

    public Product(int id) {
        this.id = id;
        
    }

    public Product clone() {
    	return new Product(getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
