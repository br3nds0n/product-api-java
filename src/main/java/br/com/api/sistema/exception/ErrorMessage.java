package br.com.api.sistema.exception;

public class ErrorMessage {

    private int status;
    private String erro;
    private String descricao;

    public ErrorMessage(int status, String erro, String descricao) {
        this.status = status;
        this.erro = erro;
        this.descricao = descricao;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
