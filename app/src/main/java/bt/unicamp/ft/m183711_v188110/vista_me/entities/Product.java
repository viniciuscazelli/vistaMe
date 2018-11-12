package bt.unicamp.ft.m183711_v188110.vista_me.entities;

import android.graphics.Bitmap;

import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.e_Sexo;

public class Product {
    private String id;
    private String nome;
    private String descricao;
    private Bitmap foto ;
    private String url;
    private double valor;
    private int maxDivider;
    private e_Sexo sexo;

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String nome, String descricao, Bitmap foto, double valor, int maxDivider, e_Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.valor = valor;
        this.maxDivider = maxDivider;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getMaxDivider() {
        return maxDivider;
    }

    public void setMaxDivider(int maxDivider) {
        this.maxDivider = maxDivider;
    }

    public e_Sexo getSexo() {
        return sexo;
    }

    public void setSexo(e_Sexo sexo) {
        this.sexo = sexo;
    }

    public double getDividerValue(){
        return getValor()/getMaxDivider();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
