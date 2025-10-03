package sptech.projeto09.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projeto09.dto.PetSimplesResponse;
import sptech.projeto09.model.Pet;
import sptech.projeto09.service.PetService;

import java.util.List;
import java.util.Optional;

// cria o construtor com os atributos final
@RequiredArgsConstructor
@Slf4j // anotação de log do Lombok
@RestController
@RequestMapping("/pets")
// ou  public class PetResource { // é muito usado esse padrão de nome também
public class PetController {

    private final PetService service;

    @GetMapping("/simples")
    public ResponseEntity<List<PetSimplesResponse>> getSimples() {
        var lista = service.findAllSimples();
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getPets(
            @RequestParam(required = false) String pesquisa
    ) {
        List<Pet> pets = service.pesquisar(pesquisa);
        log.debug("A lista veio com {} pets", pets.size());

        return pets.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(pets);
    }

    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody @Valid Pet pet) {

        Pet petSalvo = service.salvar(pet);

        return ResponseEntity.status(201).body(petSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetPorId(@PathVariable Integer id) {
        Optional<Pet> petEncontrado = service.findById(id);
        /*
        ResponseEntity.of() -> versão especial para Optional
        Se o Optional estiver vazio, retorna 404
        Se o Optional tiver valor, retorna 200 com o valor
         */
        return ResponseEntity.of(petEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetPorId(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> putPet(
            @PathVariable Integer id,
            @RequestBody @Valid Pet pet
    ) {
        Pet petSalvo = service.salvar(pet);
        return ResponseEntity.status(200).body(petSalvo);
    }

    @PatchMapping("/desativacao/{id}")
    public ResponseEntity<Void> desativarPet(
            @PathVariable Integer id) {
        boolean atualizou = service.desativarPorId(id);
        return atualizou
                ? ResponseEntity.status(200).build()
                : ResponseEntity.status(404).build();
    }
}



