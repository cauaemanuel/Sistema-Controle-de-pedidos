package com.deloitte.model.dto;

import java.util.List;

public record PedidoDTO(Integer ClientId, List<ProdutoItem> produtos) {
}
