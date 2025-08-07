package com.koiti.pdvgestor.feature.auth.data.model;

public class LoginRequest {
    private String email;
    private String senha;
    private String fluxoLogin;
    private String locale;
    private Integer sessaoUsuarioLogado;

    public LoginRequest() {}

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
        this.fluxoLogin = "APP";
    }

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getFluxoLogin() { return fluxoLogin; }
    public void setFluxoLogin(String fluxoLogin) { this.fluxoLogin = fluxoLogin; }

    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }

    public Integer getSessaoUsuarioLogado() { return sessaoUsuarioLogado; }
    public void setSessaoUsuarioLogado(Integer sessaoUsuarioLogado) {
        this.sessaoUsuarioLogado = sessaoUsuarioLogado;
    }
}
