package com.deloitte.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoID {

    private Integer clientId;

    private Integer produtoId;
}
