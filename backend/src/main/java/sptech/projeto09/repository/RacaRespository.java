package sptech.projeto09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto09.model.Raca;

public interface RacaRespository extends
        JpaRepository<Raca, String> {
}
