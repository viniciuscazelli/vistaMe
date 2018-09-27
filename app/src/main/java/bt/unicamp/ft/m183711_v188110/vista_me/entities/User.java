package bt.unicamp.ft.m183711_v188110.vista_me.entities;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String lastname;
    private String CPF;

    private String address;
    private String number;
    private String distric;
    private String city;
    private String country;
    private String CEP;

    private String username;
    private String password;

    public User(int id, String name, String lastname, String CPF, String address, String number, String distric, String city, String country, String CEP, String username, String password, ArrayList<Card> cards, ArrayList<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.CPF = CPF;
        this.address = address;
        this.number = number;
        this.distric = distric;
        this.city = city;
        this.country = country;
        this.CEP = CEP;
        this.username = username;
        this.password = password;
        this.cards = cards;
        this.contacts = contacts;
    }

    public String getNumber() {

        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistric() {
        return distric;
    }

    public void setDistric(String distric) {
        this.distric = distric;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    private ArrayList<Card> cards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    private ArrayList<Contact> contacts;

}
