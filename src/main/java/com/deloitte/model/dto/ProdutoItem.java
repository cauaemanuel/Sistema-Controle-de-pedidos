package com.deloitte.model.dto;

import jakarta.validation.constraints.NotNull;

public record ProdutoItem(@NotNull Integer produtoId,@NotNull Integer quantidade) {
}
