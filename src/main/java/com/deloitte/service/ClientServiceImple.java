package com.deloitte.service;

import com.deloitte.model.Cliente;
import com.deloitte.model.dto.ClientDTO;
import com.deloitte.model.map.ClientFactory;

import com.deloitte.repository.ClientRepository;
import com.deloitte.service.interfaces.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientServiceImple implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImple(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Cliente create(ClientDTO dto){
        var produto = ClientFactory.fromDTO(dto);
        return clientRepository.save(produto);
    }

    public List<ClientDTO> findAll(){
        var clients = clientRepository.findAll().stream()
                .map(p -> ClientFactory.fromClient(p)).toList();
        if(clients.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return clients;
    }

    @Transactional
    public ClientDTO findById(Integer id){
        var produto = findEntity(id);
        return ClientFactory.fromClient(produto);
    }

    @Transactional
    public void deleteById(Integer id){
        var produto = findEntity(id);
        clientRepository.deleteById(id);
    }

    public ClientDTO updateCliente(ClientDTO dto, Integer id){
        var produto = findEntity(id);

        var updateSala = ClientFactory.updateFromDTO(produto, dto);
        return ClientFactory.fromClient(clientRepository.save(updateSala));
    }

    private Cliente findEntity(Integer id){
        return clientRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id invalido"));
    }
}
