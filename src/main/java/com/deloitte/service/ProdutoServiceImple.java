package com.deloitte.service;

import com.deloitte.model.Cliente;
import com.deloitte.model.Produto;
import com.deloitte.model.dto.ProdutoDTO;
import com.deloitte.model.map.ProdutoFactory;
import com.deloitte.repository.ProdutoRepository;
import com.deloitte.service.interfaces.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoServiceImple implements ProdutoService {
    
    private ProdutoRepository produtoRepository;

    public ProdutoServiceImple(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto create(ProdutoDTO dto){
        var produto = ProdutoFactory.fromDTO(dto);
        return produtoRepository.save(produto);
    }

    public List<ProdutoDTO> findAll(){
        var produtos = produtoRepository.findAll().stream()
                .map(p -> ProdutoFactory.fromProduto(p)).toList();
        if(produtos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return produtos;
    }

    @Transactional
    public ProdutoDTO findById(Integer id){
        var produto = findEntity(id);
        return ProdutoFactory.fromProduto(produto);
    }

    @Transactional
    public void deleteById(Integer id){
        var produto = findEntity(id);
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO updateProduto(ProdutoDTO dto, Integer id){
        var produto = findEntity(id);

        var updateSala = ProdutoFactory.updateFromDTO(produto, dto);
        return ProdutoFactory.fromProduto(produtoRepository.save(updateSala));
    }

    private Produto findEntity(Integer id){
        return produtoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id invalido"));
    }
}
