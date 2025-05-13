package com.deloitte.controller;

import com.deloitte.model.Cliente;
import com.deloitte.model.dto.ClientDTO;
import com.deloitte.service.interfaces.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        var list = clientService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
        var Cliente = clientService.findById(id);
        return ResponseEntity.ok(Cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody @Valid ClientDTO dto){
        var Cliente = clientService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto,@PathVariable Integer id){
        var Cliente = clientService.updateCliente(dto, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
