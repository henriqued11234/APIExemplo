package com.example.demo.repository;


import com.example.demo.model.Curso;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepository {

    private final DataSource dataSource;

    public CursoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Curso> buscarTodos() {
        String sql = "SELECT id, nome, carga_horaria FROM cursos";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getLong("id"));
                curso.setNome(rs.getString("nome"));
                curso.setCargaHoraria(rs.getInt("carga_horaria"));

                cursos.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os cursos", e);
        }

        return cursos;
    }

    public Optional<Curso> buscarPorId(Long id) {
        String sql = "SELECT id, nome, carga_horaria FROM cursos WHERE id = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = new Curso();
                    curso.setId(rs.getLong("id"));
                    curso.setNome(rs.getString("nome"));
                    curso.setCargaHoraria(rs.getInt("carga_horaria"));

                    return Optional.of(curso);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o curso por ID", e);
        }

        return Optional.empty();
    }

    public Curso salvar(Curso curso) {
        String sql = "INSERT INTO cursos (nome, carga_horaria) VALUES (?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());

            stmt.executeUpdate();

            try (ResultSet chavesGeradas = stmt.getGeneratedKeys()) {
                if (chavesGeradas.next()) {
                    curso.setId(chavesGeradas.getLong(1));
                }
            }

            return curso;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o curso", e);
        }
    }
}