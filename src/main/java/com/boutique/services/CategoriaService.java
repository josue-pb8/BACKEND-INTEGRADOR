package com.boutique.services;

import com.boutique.models.Categoria;
import com.boutique.repositories.CategoriaRepository;
import java.util.List;
import java.util.Optional;

public class CategoriaService {

    private final CategoriaRepository categoriaRepository = new CategoriaRepository();

    public List<Categoria> listarTodas() {
        return categoriaRepository.listarTodas();
    }

    public Optional<Categoria> buscarPorId(int id) {
        return categoriaRepository.buscarPorId(id);
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.guardar(categoria);
    }

    public Optional<Categoria> actualizar(int id, Categoria datos) {
        Optional<Categoria> existente = categoriaRepository.buscarPorId(id);
        if (existente.isEmpty()) return Optional.empty();

        Categoria categoria = existente.get();
        if (datos.getNombre() != null) categoria.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) categoria.setDescripcion(datos.getDescripcion());

        return Optional.of(categoriaRepository.actualizar(categoria));
    }

    public boolean eliminar(int id) {
        Optional<Categoria> existente = categoriaRepository.buscarPorId(id);
        if (existente.isEmpty()) return false;
        categoriaRepository.eliminar(id);
        return true;
    }
}
