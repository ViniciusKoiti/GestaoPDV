package com.koiti.pdvgestor.feature.auth.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.koiti.pdvgestor.core.common.model.Result;
import com.koiti.pdvgestor.core.network.ApiClient;
import com.koiti.pdvgestor.feature.auth.data.model.ApiResponse;
import com.koiti.pdvgestor.feature.auth.data.model.LoginRequest;
import com.koiti.pdvgestor.feature.auth.data.model.LoginResponse;
import com.koiti.pdvgestor.feature.auth.data.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final AuthApiService apiService;
    private final MutableLiveData<Result<Usuario>> loginResult = new MutableLiveData<>();

    public AuthRepository() {
        // TODO: Mover URL para configuração/constants
        ApiClient apiClient = new ApiClient("https://sua-api.com/api/");
        this.apiService = apiClient.createService(AuthApiService.class);
    }

    public LiveData<Result<Usuario>> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        request.setLocale("pt-BR");

        apiService.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.isSuccess()) {
                        loginResult.postValue(Result.success(loginResponse.getUsuarioVo()));
                    } else {
                        String error = loginResponse.getErro() != null ?
                                loginResponse.getErro() : "Credenciais inválidas";
                        loginResult.postValue(Result.error(error));
                    }
                } else {
                    loginResult.postValue(Result.error("Erro na comunicação com o servidor"));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResult.postValue(Result.error("Erro de conexão: " + t.getMessage()));
            }
        });
    }

    public void forgotPassword(String email) {
        LoginRequest request = new LoginRequest();
        request.setEmail(email);

        apiService.enviarEmailRecuperarSenha(request).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                // TODO: Implementar callback para recuperação de senha se necessário
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // TODO: Implementar tratamento de erro
            }
        });
    }

    public void clearLoginResult() {
        loginResult.setValue(null);
    }
}