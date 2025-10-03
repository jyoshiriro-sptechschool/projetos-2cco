package sptech.projeto09.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Regras {

    @Id // do pacokage jakarta.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer minimoPets;

    private Integer maximoPets;

    // criem só os getters
    // criem um insert para o data.sql
    // que tenha só 1 minha (o mínimo_pets=1 e o maximo_pets=4)

    public Integer getId() {
        return id;
    }

    public Integer getMinimoPets() {
        return minimoPets;
    }

    public Integer getMaximoPets() {
        return maximoPets;
    }
}
