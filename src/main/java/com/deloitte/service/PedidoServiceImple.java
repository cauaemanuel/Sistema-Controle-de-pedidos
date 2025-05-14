package com.deloitte.service;

import com.deloitte.model.Cliente;
import com.deloitte.model.Pedido;
import com.deloitte.model.dto.PedidoDTO;
import com.deloitte.model.dto.PedidoResponse;
import com.deloitte.repository.ClientRepository;
import com.deloitte.repository.PedidoRepository;
import com.deloitte.repository.ProdutoRepository;
import com.deloitte.service.interfaces.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImple implements PedidoService {

    private ClientRepository clientRepository;
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    public PedidoServiceImple(ClientRepository clientRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.clientRepository = clientRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void realizarPedido(PedidoDTO pedidoDTO){

        Cliente client = clientRepository.findById(pedidoDTO.ClientId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O Cliente não foi encontrado; Verifique o ID"));

        List<Pedido> pedidos = new ArrayList<>();

        pedidoDTO.produtos().stream().forEach(item -> {

            var prod = produtoRepository.findById(item.produtoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "O Id: "+ item.produtoId() +" não foi encontrado;"));

            Pedido pedido = new Pedido();
            pedido.setCliente(client);
            pedido.setProduto(prod);
            pedido.setQuantidade(item.quantidade());
            pedido.setValorTotal(item.quantidade() * prod.getValor());
            pedidos.add(pedido);
        });
        pedidoRepository.saveAll(pedidos);
    }

    public PedidoResponse consultarPorClienteId(Integer id){
        return null;
    }

}
