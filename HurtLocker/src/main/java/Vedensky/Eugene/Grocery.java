package Vedensky.Eugene;

/**
 * Created by eugenevendensky on 2/9/17.
 */
public class Grocery{

    public Grocery(){}


    String type = null;
    Double price = null;

    public Grocery(String type, double price){
        this.type = type;
        this.price = price;

    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

}
