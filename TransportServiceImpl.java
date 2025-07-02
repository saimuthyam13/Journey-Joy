package com.journeyjoy.service;

import com.journeyjoy.model.Transport;
import com.journeyjoy.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    @Override
    public List<Transport> getAllTransport() {
        return transportRepository.findAll();
    }
}
