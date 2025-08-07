package com.koiti.pdvgestor.feature.auth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.koiti.pdvgestor.MainActivity;
import com.koiti.pdvgestor.R;

public class LoginActivity extends AppCompatActivity {

    // Views
    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword;
    private ProgressBar progressBar;

    // ViewModel
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViewModel();
        initViews();
        setupObservers();
        setupListeners();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void initViews() {
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setupObservers() {
        viewModel.getViewState().observe(this, this::handleViewState);
    }

    private void setupListeners() {
        // Text watchers para atualizar ViewModel
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setEmail(s.toString());
                // Limpar erro ao digitar
                tilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setPassword(s.toString());
                // Limpar erro ao digitar
                tilPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Click listeners
        btnLogin.setOnClickListener(v -> viewModel.login());
        tvForgotPassword.setOnClickListener(v -> viewModel.forgotPassword());
    }

    private void handleViewState(LoginViewState state) {
        if (state == null) return;

        // Atualizar UI baseado no estado
        updateLoadingState(state.isLoading());

        if (state.hasValidationErrors()) {
            handleValidationErrors(state);
        }

        if (state.isSuccess()) {
            handleLoginSuccess(state);
        }

        if (state.isError() && !state.hasValidationErrors()) {
            handleLoginError(state.getErrorMessage());
        }
    }

    private void updateLoadingState(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setEnabled(false);
            btnLogin.setText("Entrando...");
        } else {
            progressBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnLogin.setText("Entrar");
        }
    }

    private void handleValidationErrors(LoginViewState state) {
        if (state.getEmailError() != null) {
            tilEmail.setError(state.getEmailError());
            etEmail.requestFocus();
        }

        if (state.getPasswordError() != null) {
            tilPassword.setError(state.getPasswordError());
            if (state.getEmailError() == null) { // Só foca se não há erro no email
                etPassword.requestFocus();
            }
        }
    }

    private void handleLoginSuccess(LoginViewState state) {
        Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

        // TODO: Salvar usuário em SharedPreferences/Room se necessário
        // UserSession.save(state.getUsuario());

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void handleLoginError(String errorMessage) {
        String message = errorMessage != null ? errorMessage : "Erro desconhecido";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Limpar estado para permitir nova tentativa
        viewModel.clearState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            viewModel.clearState();
        }
    }
}
