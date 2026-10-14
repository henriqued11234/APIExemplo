package com.example.demo.service;


import com.example.demo.exceptions.RecursoNaoEncontradoException;
import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
@Autowired
    private  CursoRepository repository;

    public CursoService(CursoRepository repository) {

        this.repository = repository;
    }

    public List<Curso> listarTodos() {

        return repository.buscarTodos();
    }

    public Curso buscarPorId(Long id) {
        Optional<Curso> cursoEncontrado = repository.buscarPorId(id);
        if (cursoEncontrado.isPresent()) {
            return cursoEncontrado.get();
        } else {
            throw new RecursoNaoEncontradoException("Curso não encontrado para o ID: " + id);
        }
    }

    public Curso cadastrar(Curso curso) {

        return repository.salvar(curso);
    }
}
