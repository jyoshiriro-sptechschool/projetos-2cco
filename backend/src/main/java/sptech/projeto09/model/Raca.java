package sptech.projeto09.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor // cria um construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio
@Getter // cria os getters para todos os atributos
@Setter // cria os setters para todos os atributos

/*
@Data // cria os getters, setters, toString, equals e hashCode
 */
@Data@ToString // gera um método toString() com todos os atributos
@EqualsAndHashCode // gera os métodos equals() e hashCode() com todos os atributos
@Builder // cria um builder para a classe (segue o padrão de projeto Builder)
@Entity
public class Raca {
    // @Getter e @Setter podem ser usados em atributos específicos
    @Id // do pacokage jakarta.persistence
    private String codigo;

    @NotBlank
    private String nome;

    /*@OneToMany(mappedBy = "raca")
    private List<Pet> pets;*/
}

