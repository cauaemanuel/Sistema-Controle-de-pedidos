package com.deloitte.model.map;

import com.deloitte.model.Cliente;
import com.deloitte.model.Produto;
import com.deloitte.model.dto.ClientDTO;
import com.deloitte.model.dto.ProdutoDTO;

public class ProdutoFactory {
    public static Produto updateFromDTO(Produto produto, ProdutoDTO dto) {
        if(dto.valor() != null){
            produto.setValor(dto.valor());
        }
        if (dto.nome() != null){
            produto.setNome(dto.nome());
        }
        return produto;
    }

    public static ProdutoDTO fromProduto(Produto p) {
        return new ProdutoDTO(p.getNome(), p.getValor());
    }

    public static Produto fromDTO(ProdutoDTO dto) {
        var prod = new Produto();
        prod.setNome(dto.nome());
        prod.setValor(dto.valor());
        return prod;
    }

}
