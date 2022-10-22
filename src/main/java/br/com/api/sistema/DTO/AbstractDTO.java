package br.com.api.sistema.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public abstract class AbstractDTO {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate criado;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate modificado;

    public LocalDate getCriado() {
        return criado;
    }

    public void setCriado(LocalDate criado) {
        this.criado = criado;
    }

    public LocalDate getModificado() {
        return modificado;
    }

    public void setModificado(LocalDate modificado) {
        this.modificado = modificado;
    }
}
