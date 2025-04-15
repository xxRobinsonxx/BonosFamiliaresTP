package com.bonos.bonosfamiliares.controller;

import com.bonos.bonosfamiliares.model.BonoFamiliar;
import com.bonos.bonosfamiliares.service.BonoFamiliarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/bonos")
public class BonoFamiliarController {

    private final BonoFamiliarService service;

    public BonoFamiliarController(BonoFamiliarService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BonoFamiliar> registrarBono(@Valid @RequestBody BonoFamiliar bonoFamiliar) {
        BonoFamiliar registrado = service.registrarBonoFamiliar(bonoFamiliar);
        return ResponseEntity.ok(registrado);
    }

    @GetMapping
    public ResponseEntity<List<BonoFamiliar>> listarBonos() {
        return ResponseEntity.ok(service.listarBonosFamiliares());
    }

    // Endpoint para importar datos desde Excel
    @PostMapping("/importar")
    public ResponseEntity<List<BonoFamiliar>> importarExcel(@RequestParam("archivo") MultipartFile archivo) throws Exception {
        List<BonoFamiliar> bonosImportados = service.importarExcel(archivo);
        return ResponseEntity.ok(bonosImportados);
    }
}
