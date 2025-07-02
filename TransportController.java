package com.journeyjoy.controller;

import com.journeyjoy.model.Transport;
import com.journeyjoy.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transport")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @PostMapping("/add")
    public Transport addTransport(@RequestBody Transport transport) {
        return transportService.saveTransport(transport);
    }

    @GetMapping("/all")
    public List<Transport> getAllTransport() {
        return transportService.getAllTransport();
    }
}
