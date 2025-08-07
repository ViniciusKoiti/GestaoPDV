package com.koiti.pdvgestor.feature.auth.ui;

import com.koiti.pdvgestor.feature.auth.data.model.Usuario;

public class LoginViewState {

    public enum State {
        IDLE,
        LOADING,
        SUCCESS,
        ERROR
    }

    private final State state;
    private final String errorMessage;
    private final Usuario usuario;
    private final String emailError;
    private final String passwordError;

    private LoginViewState(State state, String errorMessage, Usuario usuario,
                           String emailError, String passwordError) {
        this.state = state;
        this.errorMessage = errorMessage;
        this.usuario = usuario;
        this.emailError = emailError;
        this.passwordError = passwordError;
    }

    // Estados estáticos para facilitar criação
    public static LoginViewState idle() {
        return new LoginViewState(State.IDLE, null, null, null, null);
    }

    public static LoginViewState loading() {
        return new LoginViewState(State.LOADING, null, null, null, null);
    }

    public static LoginViewState success(Usuario usuario) {
        return new LoginViewState(State.SUCCESS, null, usuario, null, null);
    }

    public static LoginViewState error(String message) {
        return new LoginViewState(State.ERROR, message, null, null, null);
    }

    public static LoginViewState validationError(String emailError, String passwordError) {
        return new LoginViewState(State.ERROR, null, null, emailError, passwordError);
    }

    // Getters
    public State getState() {
        return state;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    // Métodos auxiliares
    public boolean isLoading() {
        return state == State.LOADING;
    }

    public boolean isSuccess() {
        return state == State.SUCCESS;
    }

    public boolean isError() {
        return state == State.ERROR;
    }

    public boolean hasValidationErrors() {
        return emailError != null || passwordError != null;
    }
}
