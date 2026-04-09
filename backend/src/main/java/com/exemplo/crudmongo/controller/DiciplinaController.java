package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Diciplina;
import com.exemplo.crudmongo.service.DiciplinaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Diciplinas")
@CrossOrigin(origins = "*")
public class DiciplinaController {
    private final DiciplinaService service;

    public DiciplinaController(DiciplinaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Diciplina> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Diciplina criar(@RequestBody Diciplina diciplina) {
        return service.salvar(diciplina);
    }

    @PutMapping("/{id}")
    public Diciplina atualizar(@PathVariable Long id, @RequestBody Diciplina diciplina) {
        return service.atualizar(id, diciplina);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
