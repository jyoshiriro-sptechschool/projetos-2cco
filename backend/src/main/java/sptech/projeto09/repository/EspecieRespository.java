package sptech.projeto09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto09.model.Especie;
import sptech.projeto09.model.Raca;

public interface EspecieRespository extends
        JpaRepository<Especie, String> {
}
