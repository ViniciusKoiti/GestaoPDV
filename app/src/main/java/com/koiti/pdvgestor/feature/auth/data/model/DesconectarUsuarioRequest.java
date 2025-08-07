package com.koiti.pdvgestor.feature.auth.data.model;

import java.util.List;

public class DesconectarUsuarioRequest {
    private List<Usuario> listUsuarioVo;
    private Usuario usuarioLogado;

    public DesconectarUsuarioRequest() {}

    public DesconectarUsuarioRequest(List<Usuario> usuarios, Usuario usuarioLogado) {
        this.listUsuarioVo = usuarios;
        this.usuarioLogado = usuarioLogado;
    }

    // Getters e Setters
    public List<Usuario> getListUsuarioVo() { return listUsuarioVo; }
    public void setListUsuarioVo(List<Usuario> listUsuarioVo) {
        this.listUsuarioVo = listUsuarioVo;
    }

    public Usuario getUsuarioLogado() { return usuarioLogado; }
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}