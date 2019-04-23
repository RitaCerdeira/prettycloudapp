package com.projetotqs.prettycloud;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 */

@RestController
public class ClientController {

    private final ClientRepository repository;


    /**
     * @param repository
     */

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }


    /**
     * @return
     */

    @GetMapping("/clients")
    List<Client> all(){
        return repository.findAll();
    }


    /**
     * @param newClient
     * @return
     */

    @PostMapping("/clients")
    Client newClient(@RequestBody Client newClient){
        return repository.save(newClient);
    }


    /**
     * @param id
     * @return
     */

    @GetMapping("/clients/{id}")
    Client one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    /**
     * @param newClient
     * @param id
     * @return
     */

    @PutMapping("/client/{id}")
    Client replaceClient(@RequestBody Client newClient, @PathVariable Long id){
        return repository.findById(id)
                .map(client -> {
                    client.setName(newClient.getName());
                    return repository.save(client);
                })
                .orElseGet(() -> {
                   newClient.setId(id);
                   return repository.save(newClient);
                });
    }


    /**
     * @param id
     */

    @DeleteMapping("/client/{id}")
    void deleteClient(@PathVariable Long id){
        repository.deleteById(id);
    }
}
