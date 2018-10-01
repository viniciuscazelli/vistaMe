package bt.unicamp.ft.m183711_v188110.vista_me;

import java.util.ArrayList;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.CartNotificChangeListProducts;

public class Cart {

    public  ArrayList<Product> itens;
    private CartNotificChangeListProducts cartNotificChangeListProducts;

    public void setCartNotificChangeListProducts(CartNotificChangeListProducts cartNotificChangeListProducts) {
        this.cartNotificChangeListProducts = cartNotificChangeListProducts;
    }

    public Cart(){
        itens = new ArrayList<>();
    }

    public void Add(Product p){
        itens.add(p);
        if(cartNotificChangeListProducts != null)
            cartNotificChangeListProducts.notificChangeList();
    }

    public void Remove(Product p){
        itens.remove(p);
        if(cartNotificChangeListProducts != null)
            cartNotificChangeListProducts.notificChangeList();
    }

    public void Remove(int position){
        itens.remove(position);
        if(cartNotificChangeListProducts != null)
            cartNotificChangeListProducts.notificChangeList();
    }

    public double Total(){
        double total = 0;
        for (Product p:itens) {
            total += p.getValor();
        }
        return total;
    }

    public double Frete(){
        return (10+(itens.size()*2.37));
    }

    public double TotalGeral(){
        return Total()+Frete();
    }
}
