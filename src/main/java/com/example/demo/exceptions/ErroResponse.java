package com.example.demo.exceptions;

public class ErroResponse {
    private String erro;
    private int status;

    public ErroResponse(String erro, int status) {
        this.erro = erro;
        this.status = status;
    }

    public String getErro() { return erro; }
    public int getStatus() { return status; }
}