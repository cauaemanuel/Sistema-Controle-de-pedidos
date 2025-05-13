package com.deloitte.service.interfaces;


import com.deloitte.model.Cliente;
import com.deloitte.model.dto.ClientDTO;

import java.util.List;


public interface ClientService {

    Cliente create(ClientDTO dto);

    List<ClientDTO> findAll();

    ClientDTO findById(Integer id);

    void deleteById(Integer id);

    ClientDTO updateCliente(ClientDTO dto, Integer id);
}
