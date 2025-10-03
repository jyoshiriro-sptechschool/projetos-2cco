package sptech.projeto09.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class Pet {

    /*
    Id do package jakarta.persistence
    Indica qual atributo é o ID (chave primária)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
//    @NotNull(message = "Onde já se viu sem ID!")
//    @NotNull
    private Integer id;

    @NotBlank
    private String nomePet;

    @NotBlank
    @Size(min = 3, max = 20)
    private String nomeDono;

    @ManyToOne
    @NotNull
    private Especie especie;

    @ManyToOne
    @NotNull
    private Raca raca;

    private String emailDono;

    @Positive
//    @Negative
//    @PositiveOrZero
//    @NegativeOrZero
    @DecimalMax("95.0") // valor tem que ser entre aspas
    private Double peso;

    @DecimalMin("0.01") // valor tem que ser entre aspas
    @DecimalMax("1.5") // valor tem que ser entre aspas
    private Double altura;

    @PastOrPresent
    // @Past
    private LocalDate nascimento; // aceita formato ISO

    @Future
    // @FutureOrPresent
    private LocalDate validadeChip;

    public Integer getId() {
        return id;
    }

    public String getNomePet() {
        return nomePet;
    }

//    @CNPJ
//    @TituloEleitoral
    @CPF // NÃO IMPORTA se tem ou não pontos e traços
    private String cpfDono;

    // valicação com uso de Expressões Regulares (Regex)
    @Pattern(
        regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
        message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX"
    )
    private String telefoneDono;

    private boolean ativo = true;

    public String getNomeDono() {
        return nomeDono;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Raca getRaca() {
        return raca;
    }

    public String getEmailDono() {
        return emailDono;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public LocalDate getValidadeChip() {
        return validadeChip;
    }

    public String getCpfDono() {
        return cpfDono;
    }

    public String getTelefoneDono() {
        return telefoneDono;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
