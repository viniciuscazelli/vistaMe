package bt.unicamp.ft.m183711_v188110.vista_me.entities;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private ArrayList<Product> itens;
    private int divider;
    private Card card;
    private Date date;
    private String number;
    private String status;
    private double frete;

    public Order(ArrayList<Product> itens, int divider, Card card, Date date, String number,String status, double frete) {

        this.itens = itens;
        this.divider = divider;
        this.card = card;
        this.date = date;
        this.number = number;
        this.status = status;
        this.frete = frete;

    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Product> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Product> itens) {
        this.itens = itens;
    }

    public int getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double Total(){
        double total = 0;
        for (Product p:itens) {
            total += p.getValor();
        }

        total += getFrete();
        return total;
    }



}
