package com.koiti.pdvgestor.feature.auth.data.model;

public class Usuario {
    private Long id;
    private String nomeUsuario;
    private String email;
    private Integer sessaoUsuarioLogado;
    private String locale;

    // Construtores
    public Usuario() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getSessaoUsuarioLogado() { return sessaoUsuarioLogado; }
    public void setSessaoUsuarioLogado(Integer sessaoUsuarioLogado) {
        this.sessaoUsuarioLogado = sessaoUsuarioLogado;
    }

    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }
}