package com.boutique.services;

import com.boutique.models.*;
import com.boutique.repositories.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ApartadoService {

    private final ApartadoRepository apartadoRepository = new ApartadoRepository();
    private final AbonoRepository abonoRepository = new AbonoRepository();

    public List<Apartado> listarTodos() {
        return apartadoRepository.listarTodos();
    }

    public Optional<Apartado> buscarPorId(int id) {
        return apartadoRepository.buscarPorId(id);
    }

    public List<Apartado> buscarPorCliente(int clienteId) {
        return apartadoRepository.buscarPorCliente(clienteId);
    }

    public List<Apartado> buscarPorEstado(Apartado.EstadoApartado estado) {
        return apartadoRepository.buscarPorEstado(estado);
    }

    public Apartado registrarApartado(Apartado apartado, List<DetalleApartado> detalles) {
        for (DetalleApartado detalle : detalles) {
            detalle.setApartado(apartado);
        }
        apartado.setDetalles(detalles);
        return apartadoRepository.guardar(apartado);
    }

    public Optional<Apartado> registrarAbono(int apartadoId, BigDecimal monto) {
        Optional<Apartado> existente = apartadoRepository.buscarPorId(apartadoId);
        if (existente.isEmpty()) return Optional.empty();

        Apartado apartado = existente.get();

        Abono abono = new Abono(apartado, monto);
        abonoRepository.guardar(abono);

        apartado.getAbonos().add(abono);

        if (apartado.getPendiente().compareTo(BigDecimal.ZERO) <= 0) {
            apartado.setEstado(Apartado.EstadoApartado.PAGADO);
            apartadoRepository.actualizar(apartado);
        }

        return Optional.of(apartado);
    }

    public Optional<Apartado> cancelar(int apartadoId) {
        Optional<Apartado> existente = apartadoRepository.buscarPorId(apartadoId);
        if (existente.isEmpty()) return Optional.empty();

        Apartado apartado = existente.get();
        if (apartado.getEstado() == Apartado.EstadoApartado.CANCELADO) {
            return Optional.of(apartado);
        }
        apartado.setEstado(Apartado.EstadoApartado.CANCELADO);
        return Optional.of(apartadoRepository.actualizar(apartado));
    }
}
