package com.deloitte.controller;

import com.deloitte.model.Pedido;
import com.deloitte.model.dto.PedidoDTO;
import com.deloitte.service.PedidoServiceImple;
import com.deloitte.service.interfaces.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoServiceImple pedidoServiceImple) {
        this.pedidoService = pedidoServiceImple;
    }

    @PostMapping
    public ResponseEntity<Void> realizarPedido(@RequestBody @Valid PedidoDTO dto){
        pedidoService.realizarPedido(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
