package com.daw.daw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import com.daw.daw.dto.ReservaDTO;
import com.daw.daw.dto.ReservaMapper;
import com.daw.daw.model.Reserva;
import com.daw.daw.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reserva;

    @Autowired
    private ReservaMapper reservaMapper;

    public Collection<ReservaDTO> findAll() {
        return reservaMapper.toDTOs(reserva.findAll());
    }

    public ReservaDTO findById(Long id) {
        return reservaMapper.toDTO(reserva.findById(id).orElseThrow());
    }

    public ReservaDTO createReserva(ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toDomain(reservaDTO);
        return reservaMapper.toDTO(this.reserva.save(reserva));
    }

    public void deleteReserva(Long id) {
        reserva.deleteById(id);
    }

    public ReservaDTO updateReserva(Long id, ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toDomain(reservaDTO);
        reserva.setId(id);
        return reservaMapper.toDTO(this.reserva.save(reserva));
    }
}
