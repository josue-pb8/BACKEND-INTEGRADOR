package com.boutique.services;

import com.boutique.models.Descuento;
import com.boutique.repositories.DescuentoRepository;
import java.util.List;
import java.util.Optional;

public class DescuentoService {

    private final DescuentoRepository descuentoRepository = new DescuentoRepository();

    public List<Descuento> listarTodos() {
        return descuentoRepository.listarTodos();
    }

    public List<Descuento> listarActivos() {
        return descuentoRepository.listarActivos();
    }

    public Optional<Descuento> buscarPorId(int id) {
        return descuentoRepository.buscarPorId(id);
    }

    public Descuento guardar(Descuento descuento) {
        return descuentoRepository.guardar(descuento);
    }

    public Optional<Descuento> actualizar(int id, Descuento datos) {
        Optional<Descuento> existente = descuentoRepository.buscarPorId(id);
        if (existente.isEmpty()) return Optional.empty();

        Descuento descuento = existente.get();
        if (datos.getNombre() != null) descuento.setNombre(datos.getNombre());
        if (datos.getPorcentaje() != null) descuento.setPorcentaje(datos.getPorcentaje());
        if (datos.getFechaInicio() != null) descuento.setFechaInicio(datos.getFechaInicio());
        if (datos.getFechaFin() != null) descuento.setFechaFin(datos.getFechaFin());

        return Optional.of(descuentoRepository.actualizar(descuento));
    }

    public Optional<Descuento> finalizar(int id) {
        Optional<Descuento> existente = descuentoRepository.buscarPorId(id);
        if (existente.isEmpty()) return Optional.empty();

        Descuento descuento = existente.get();
        descuento.setActivo(false);
        return Optional.of(descuentoRepository.actualizar(descuento));
    }
}
