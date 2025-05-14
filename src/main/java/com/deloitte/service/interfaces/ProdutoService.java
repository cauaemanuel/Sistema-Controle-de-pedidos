package com.deloitte.service.interfaces;

import com.deloitte.model.Produto;
import com.deloitte.model.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoService {

    Produto create(ProdutoDTO dto);

    List<ProdutoDTO> findAll();

    ProdutoDTO findById(Integer id);

    void deleteById(Integer id);

    ProdutoDTO updateProduto(ProdutoDTO dto, Integer id);
}
