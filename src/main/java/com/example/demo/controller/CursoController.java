package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
   /* @Autowired
    private  CursoService service;*/
   //private final CursoService service;
   @Autowired
   private  CursoService service;
   /* public CursoController(CursoService service) {
        this.service = service;
    }*/

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso buscarDetalhes(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // HTTP 201
    public Curso criar(@RequestBody Curso curso) {
        return service.cadastrar(curso);
    }
}
