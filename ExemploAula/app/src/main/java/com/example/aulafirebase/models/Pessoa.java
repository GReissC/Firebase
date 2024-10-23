package com.example.aulafirebase.models;

import java.util.ArrayList;

public class Pessoa {
    private String id;
    private String nome;
    private String email;
    private String celular;

    public Pessoa(String id, String nome, String email, String celular) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
    }

    public Pessoa() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Pessoa(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static ArrayList<Pessoa> getPessoas(){
        ArrayList<Pessoa>pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("1","Rafael Moreno"));
        pessoas.add(new Pessoa("2","José da Silva"));
        pessoas.add(new Pessoa("3","Maria das Dores"));

        return pessoas;
    }
}