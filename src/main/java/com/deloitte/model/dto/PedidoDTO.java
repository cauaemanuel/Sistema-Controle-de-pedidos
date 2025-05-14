package com.deloitte.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoDTO(@NotNull Integer ClientId,
                        @NotNull List<ProdutoItem> produtos) {
}
