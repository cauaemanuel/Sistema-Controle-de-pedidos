package com.deloitte.service;

import com.deloitte.model.Cliente;
import com.deloitte.model.Pedido;
import com.deloitte.model.dto.PedidoDTO;
import com.deloitte.model.dto.ProdutoItem;
import com.deloitte.repository.ClientRepository;
import com.deloitte.repository.PedidoRepository;
import com.deloitte.repository.ProdutoRepository;
import com.deloitte.service.interfaces.ClientService;
import com.deloitte.service.interfaces.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImple {

    private ClientRepository clientRepository;
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    @Transactional
    public void realizarPedido(PedidoDTO pedidoDTO){
        Cliente client = clientRepository.findById(pedidoDTO.ClientId()).get();

        List<Pedido> pedidos = new ArrayList<>();

        for (ProdutoItem itens: pedidoDTO.produtos()){
            var prod = produtoRepository.findById(itens.produtoId()).get();
            Pedido pedido = new Pedido();
            pedido.setCliente(client);
            pedido.setProduto(prod);
            pedido.setQuantidade(itens.quantidade());
            pedido.setValorTotal(itens.quantidade() * prod.getValor());

            pedidos.add(pedido);
        }

        pedidoRepository.saveAll(pedidos);
    }

}
