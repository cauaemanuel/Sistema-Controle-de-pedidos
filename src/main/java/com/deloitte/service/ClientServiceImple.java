package com.deloitte.service;

import ch.qos.logback.core.net.server.Client;
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
        var client = ClientFactory.fromDTO(dto);
        return clientRepository.save(client);
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
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Cliente não foi encontrada; Verifique o ID"));
        return ClientFactory.fromClient(client);
    }

    @Transactional
    public void deleteById(Integer id){
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Cliente não foi encontrada; Verifique o ID"));
        clientRepository.deleteById(id);
    }

    public ClientDTO updateCliente(ClientDTO dto, Integer id){
        var client = clientRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id invalido"));

        var updateSala = ClientFactory.updateFromDTO(client, dto);
        return ClientFactory.fromClient(clientRepository.save(updateSala));
    }
}
