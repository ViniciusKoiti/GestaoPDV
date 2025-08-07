package com.koiti.pdvgestor.feature.auth.data.model;

public class ApiResponse {
    private String tipo;
    private String mensagem;
    private String erro;
    private Object objeto;

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getErro() { return erro; }
    public void setErro(String erro) { this.erro = erro; }

    public Object getObjeto() { return objeto; }
    public void setObjeto(Object objeto) { this.objeto = objeto; }

    public boolean isSuccess() {
        return "success".equals(tipo);
    }

    public boolean isError() {
        return !isSuccess();
    }
}