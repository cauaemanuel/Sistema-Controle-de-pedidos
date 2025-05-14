package com.deloitte.model.dto;

public record PedidoResponse(
        int pedidoId,
        int clientId,
        int produtoId,
        String nomeCliente,
        String emailCliente,
        String nomeProduto,
        double precoProduto,
        int quantidade,
        double valorTotal
) {}
