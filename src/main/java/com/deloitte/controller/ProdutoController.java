package com.deloitte.controller;

import com.deloitte.model.Cliente;
import com.deloitte.model.Produto;
import com.deloitte.model.dto.ClientDTO;
import com.deloitte.model.dto.ProdutoDTO;
import com.deloitte.service.interfaces.ClientService;
import com.deloitte.service.interfaces.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        var list = produtoService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id){
        var Cliente = produtoService.findById(id);
        return ResponseEntity.ok(Cliente);
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid ProdutoDTO dto){
        var Cliente = produtoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@RequestBody ProdutoDTO dto,@PathVariable Integer id){
        var Cliente = produtoService.updateProduto(dto, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        produtoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
