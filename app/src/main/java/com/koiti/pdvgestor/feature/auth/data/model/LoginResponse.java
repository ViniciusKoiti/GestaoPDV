package com.koiti.pdvgestor.feature.auth.data.model;

public class LoginResponse {
    private String tipo;
    private String mensagem;
    private String erro;
    private Usuario usuarioVo;

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getErro() { return erro; }
    public void setErro(String erro) { this.erro = erro; }

    public Usuario getUsuarioVo() { return usuarioVo; }
    public void setUsuarioVo(Usuario usuarioVo) { this.usuarioVo = usuarioVo; }

    // Métodos auxiliares
    public boolean isSuccess() {
        return "success".equals(tipo);
    }

    public boolean isError() {
        return !isSuccess();
    }
}