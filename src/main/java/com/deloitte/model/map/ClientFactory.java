package com.deloitte.model.map;

import com.deloitte.model.Cliente;
import com.deloitte.model.dto.ClientDTO;

public class ClientFactory {
    public static Cliente fromDTO(ClientDTO dto) {
        var client = new Cliente();
        client.setEmail(dto.email());
        client.setNome(dto.nome());
        return client;
    }

    public static ClientDTO fromClient(Cliente p) {

        return new ClientDTO(p.getNome(),p.getEmail());
    }

    public static Cliente updateFromDTO(Cliente client, ClientDTO dto) {
        if(dto.email() != null){
            client.setEmail(dto.email());
        }
        if (dto.nome() != null){
            client.setNome(dto.nome());
        }
        return client;
    }
}
