package com.koiti.pdvgestor.feature.auth.data;

import com.koiti.pdvgestor.feature.auth.data.model.ApiResponse;
import com.koiti.pdvgestor.feature.auth.data.model.DesconectarUsuarioRequest;
import com.koiti.pdvgestor.feature.auth.data.model.LoginRequest;
import com.koiti.pdvgestor.feature.auth.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApiService {

    /**
     * Login do usuário
     */
    @POST("MobileUsuario/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    /**
     * Enviar email para recuperar senha
     */
    @POST("MobileUsuario/enviarEmailEsqueciMinhaSenha")
    Call<ApiResponse> enviarEmailRecuperarSenha(@Body LoginRequest request);

    /**
     * Salvar aceite dos termos de uso
     */
    @POST("MobileUsuario/salvarTermoUso")
    Call<ApiResponse> salvarTermoUso(@Body LoginRequest request);

    /**
     * Verificar se usuário está logado
     */
    @POST("MobileUsuario/verificaUsuarioLogado")
    Call<ApiResponse> verificarUsuarioLogado(@Body LoginRequest request);

    /**
     * Alterar sessão do usuário
     */
    @POST("MobileUsuario/alterarSessaoUsuario")
    Call<ApiResponse> alterarSessaoUsuario(@Body LoginRequest request);

    /**
     * Deslogar usuário
     */
    @POST("MobileUsuario/deslogarUsuario")
    Call<ApiResponse> deslogarUsuario(@Body LoginRequest request);

    /**
     * Deslogar lista de usuários
     */
    @POST("MobileUsuario/deslogarUsuarioList")
    Call<ApiResponse> deslogarUsuarios(@Body DesconectarUsuarioRequest request);
}
