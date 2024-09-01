package com.devsu.prueba.service.imp;

import com.devsu.prueba.dto.MovimientoRequestDto;
import com.devsu.prueba.dto.MovimientoResponseDTO;
import com.devsu.prueba.entity.Cuenta;
import com.devsu.prueba.entity.Movimiento;
import com.devsu.prueba.entity.Persona;
import com.devsu.prueba.exception.ModelNotFoundException;
import com.devsu.prueba.repository.ICuentaRepo;
import com.devsu.prueba.repository.IMovimientoRepo;
import com.devsu.prueba.repository.IPersonaRepo;
import com.devsu.prueba.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IMovimientoServiceImp implements IMovimientoService {
    @Autowired
    private ICuentaRepo repoCuenta;
    @Autowired
    private IMovimientoRepo repoMovimiento;

    @Autowired
    private IPersonaRepo repoPersona;

    @Override
    public Movimiento realizarMovimiento(Movimiento movimiento) throws ModelNotFoundException {
        Cuenta cuenta = this.repoCuenta.findByNumeroCuenta(movimiento.getNumeroCuenta());
        if (!Objects.isNull(cuenta)){
            if(cuenta.getSaldo()<=0 && movimiento.getValor() > 0){
                throw new ArithmeticException("Saldo no disponible");
            }
            if (movimiento.getValor() <= 0 && repoMovimiento.limitediario(cuenta.getNumeroCuenta()) <= -1000){
                throw new ArithmeticException("Cupo diario Excedido");
            }
            if (cuenta.getSaldo() >= movimiento.getValor() && Math.abs(movimiento.getValor()) <= cuenta.getSaldo()){
                Movimiento movimiento1 = new Movimiento( movimiento.getFecha(), cuenta.getNumeroCuenta(), movimiento.getTipoMovimiento(), movimiento.getValor(), cuenta.getSaldo());
                cuenta.setSaldo(cuenta.getSaldo() + movimiento.getValor());
                this.repoMovimiento.save(movimiento1);
                movimiento = movimiento1;
            }else{
                if (movimiento.getValor() > 0){
                    Movimiento movimiento1 = new Movimiento( movimiento.getFecha(), cuenta.getNumeroCuenta(), movimiento.getTipoMovimiento(), movimiento.getValor(), cuenta.getSaldo());
                    cuenta.setSaldo(cuenta.getSaldo() + movimiento.getValor());
                    this.repoMovimiento.save(movimiento1);
                    movimiento = movimiento1;
                }else
                    throw new ArithmeticException("Saldo no disponible");
            }
        } else
            throw new ModelNotFoundException("Cuenta no encontrada");
        return movimiento;
    }
    @Override
    public List<MovimientoResponseDTO> estadoCuenta(MovimientoRequestDto requestDto) throws ModelNotFoundException {
        Optional<Persona> persona = this.repoPersona.findById(requestDto.getClienteId());
        List<MovimientoResponseDTO> responseDTOS = new ArrayList<MovimientoResponseDTO>();
        if (!Objects.isNull(persona)){

            List<Movimiento> movimientoList = this.repoMovimiento.buscarEstadoCuenta(requestDto.getFecha(), requestDto.getFechaRango(), persona.get().getIdPersona());

            for(int i = 0; i < movimientoList.size(); i++){
                System.out.println(i);
                Cuenta cuenta = this.repoCuenta.findByNumeroCuenta(movimientoList.get(i).getNumeroCuenta());
                MovimientoResponseDTO movimientoResponseDTO = new MovimientoResponseDTO(movimientoList.get(i).getFecha(), persona.get().getNombre(), movimientoList.get(i).getNumeroCuenta(),
                        movimientoList.get(i).getTipoMovimiento(), movimientoList.get(i).getSaldoInicial(), cuenta.getEstado(), movimientoList.get(i).getValor(),
                        cuenta.getSaldo());
                responseDTOS.add(movimientoResponseDTO);
            }

        } else
            throw new ModelNotFoundException("Cuenta no encontrada");
        return responseDTOS;
    }
}
