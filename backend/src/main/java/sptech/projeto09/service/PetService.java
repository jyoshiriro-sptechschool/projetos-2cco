package sptech.projeto09.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sptech.projeto09.dto.PetSimplesResponse;
import sptech.projeto09.exception.DependenciaNaoEncontradaException;
import sptech.projeto09.exception.PetProtegidoException;
import sptech.projeto09.exception.QuantidadeMaximaPetsException;
import sptech.projeto09.exception.QuantidadeMinimaPetsException;
import sptech.projeto09.model.Pet;
import sptech.projeto09.repository.EspecieRespository;
import sptech.projeto09.repository.PetRepository;
import sptech.projeto09.repository.RacaRespository;
import sptech.projeto09.repository.RegrasRepository;

import java.util.List;
import java.util.Optional;

@Service // por padrão, toda Service é singleton
@RequiredArgsConstructor
@Slf4j // injeção objeto de log via Lombok
public class PetService {

    private final PetRepository petRepository;

    private final RegrasRepository regrasRepository;

    private final RacaRespository racaRespository;

    private final EspecieRespository especieRespository;

    public void excluir(Integer id) {
        if (petRepository.count() == regrasRepository.findTop1By().getMinimoPets()) {
            throw new QuantidadeMinimaPetsException();
        }

        if (id == 2) {
            throw new PetProtegidoException(id);
        }

        validarIdPet(id);

        petRepository.deleteById(id);
    }

    public Pet salvar(Pet pet) {
        validarQuantidadeMaximaPets();

        if (!racaRespository.existsById(pet.getRaca().getCodigo())) {
            throw new DependenciaNaoEncontradaException("Raça");
        }

        if (!especieRespository.existsById(pet.getEspecie().getCodigo())) {
            throw new DependenciaNaoEncontradaException("Espécie");
        }

        Pet petSalvo = petRepository.save(pet);
        return petSalvo;
    }

    private void validarQuantidadeMaximaPets() {
        // regra de exemplo: a api só pode ter N pets
        // if (repository.count() == regrasRepository.findAll().getFirst().getMaximoPets()) {
        if (petRepository.count() == regrasRepository.findTop1By().getMaximoPets()) {
            log.error("Quantidade máxima de pets excedida");
            throw new QuantidadeMaximaPetsException();
        }
    }

    public List<PetSimplesResponse> findAllSimples() {
        return petRepository.findAllSimples();
    }

    public List<Pet> pesquisar(String pesquisa) {
        if (pesquisa == null) {
            return petRepository.findAll();
        }
        return petRepository
                    .findByNomeDonoContainsIgnoreCaseOrNomePetContainsIgnoreCase(pesquisa, pesquisa);
    }

    public Optional<Pet> findById(Integer id) {
        return petRepository.findById(id);
    }

    public boolean desativarPorId(Integer id) {
        validarIdPet(id);
        return petRepository.desativarPorId(id) == 1;
    }

    private void validarIdPet(Integer id) {
        if (!petRepository.existsById(id)) {
            throw new DependenciaNaoEncontradaException("Pet");
        }
    }
}
