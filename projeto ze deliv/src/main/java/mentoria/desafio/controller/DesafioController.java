package mentoria.desafio.controller;

import mentoria.desafio.model.Parceiros;
import mentoria.desafio.repository.ParceirosRepository;
import mentoria.desafio.utils.ConversorHashMapObjetoParceiros;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;


@RestController
public class DesafioController {

    private static ParceirosRepository parceirosRepository;

    @Inject
    public DesafioController(ParceirosRepository parceirosRepository){
        this.parceirosRepository = parceirosRepository;
    }

    @PostMapping(consumes = "application/json")
    @RequestMapping("/cadastrar")
    public String cadastrar(@RequestBody HashMap body){

        ConversorHashMapObjetoParceiros conversor = new ConversorHashMapObjetoParceiros();
        Parceiros parceiros = conversor.converter(body);
        parceirosRepository.cadastrarParceiro(parceiros);
        System.out.println(parceiros.getAddressCoordinates());
        return "cadastrado";
    }

    @GetMapping
    @RequestMapping("/buscarPorId/{id}")
    public String buscarPorId(@PathVariable int id){

        var parceiros = parceirosRepository.buscarPorId(id);
        return parceiros.toString();
    }


    @PostMapping
    @RequestMapping("/buscarPorProximidade/{log}/{lat}")
    public String buscarPorProximidade(@PathVariable int log, @PathVariable int lat){

        var parceiros = parceirosRepository.buscarPorProximidade(log, lat);
        if(parceiros == null){

            return "Nenhum Parceiro próximo!";
        }
        return parceiros.toString();
    }
}
