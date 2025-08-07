package com.koiti.pdvgestor.feature.auth.ui;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koiti.pdvgestor.core.common.model.Result;
import com.koiti.pdvgestor.feature.auth.data.AuthRepository;
import com.koiti.pdvgestor.feature.auth.data.model.Usuario;

public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;
    private final MediatorLiveData<LoginViewState> viewState = new MediatorLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();

    public LoginViewModel() {
        this.authRepository = new AuthRepository();
        setupObservers();

        // Estado inicial
        viewState.setValue(LoginViewState.idle());
    }

    private void setupObservers() {
        viewState.addSource(authRepository.getLoginResult(), this::handleLoginResult);
    }

    private void handleLoginResult(Result<Usuario> result) {
        if (result == null) return;

        if (result.isSuccess()) {
            viewState.setValue(LoginViewState.success(result.getData()));
        } else {
            viewState.setValue(LoginViewState.error(result.getError()));
        }
    }

    // Public methods for UI
    public LiveData<LoginViewState> getViewState() {
        return viewState;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public void login() {
        String emailValue = email.getValue();
        String passwordValue = password.getValue();

        // Validação local
        String emailError = validateEmail(emailValue);
        String passwordError = validatePassword(passwordValue);

        if (emailError != null || passwordError != null) {
            viewState.setValue(LoginViewState.validationError(emailError, passwordError));
            return;
        }

        // Iniciando login
        viewState.setValue(LoginViewState.loading());
        authRepository.login(emailValue, passwordValue);
    }

    public void forgotPassword() {
        String emailValue = email.getValue();
        String emailError = validateEmail(emailValue);

        if (emailError != null) {
            viewState.setValue(LoginViewState.validationError(emailError, null));
            return;
        }

        // TODO: Implementar navegação para tela de recuperação
        authRepository.forgotPassword(emailValue);
    }

    public void clearState() {
        viewState.setValue(LoginViewState.idle());
        authRepository.clearLoginResult();
    }

    // Validation methods
    private String validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return "Email é obrigatório";
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Email inválido";
        }

        return null;
    }

    private String validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return "Senha é obrigatória";
        }

        if (password.length() < 3) {
            return "Senha deve ter pelo menos 3 caracteres";
        }

        return null;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Cleanup se necessário
    }
}