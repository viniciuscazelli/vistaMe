package bt.unicamp.ft.m183711_v188110.vista_me.entities;

import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.e_Sexo;

public class Product {
    private String nome;
    private String descricao;
    private int foto ;
    private double valor;
    private int maxDivider;
    private e_Sexo sexo;

    public Product(String nome, String descricao, int foto, double valor, int maxDivider,e_Sexo sexo) {
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

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
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
}
