package com.journeyjoy.service;

import com.journeyjoy.model.Transport;
import java.util.List;

public interface TransportService {
    Transport saveTransport(Transport transport);
    List<Transport> getAllTransport();
}
