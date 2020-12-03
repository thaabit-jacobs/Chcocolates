package org.chocolates.model;

public class Chocolate {
    private int id;
    private String name;
    private int qty;

    public Chocolate(){}

    public Chocolate(int id, String name, int qty){
        this.id = id;
        this.name = name;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public Chocolate setId(int id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return name;
    }

    public Chocolate setName(String name) {
        this.name = name;

        return this;
    }

    public int getQty() {
        return qty;
    }

    public Chocolate setQty(int qty) {
        this.qty = qty;

        return this;
    }
}
