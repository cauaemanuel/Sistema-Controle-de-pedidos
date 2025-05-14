package com.deloitte.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(@NotBlank String nome, @NotNull Double valor) {
}
