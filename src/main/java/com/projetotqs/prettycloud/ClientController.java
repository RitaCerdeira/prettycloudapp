package com.projetotqs.prettycloud;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


/**
 * Client Controller
 * Manages insertion, deletion and edition of clients on repository
 */

@RestController
@Api(value = "client", description = "clients's details")
public class ClientController {

    private final ClientRepository repository;


    /**
     * Client Controller constructor
     *
     * @param repository client's repository
     */

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }


    /**
     * Get all clients from database
     *
     * @return all clients
     */

    @ApiOperation(value = "Gets all clients from repository")
    @GetMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Client> all(){
        return repository.findAll();
    }


    /**
     * Inserts in database a new client
     *
     * @param newClient, client to insert
     * @return client saved on repository
     */

    @ApiOperation(value = "Inserts new client in repository")
    @PostMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Client newClient(@RequestBody Client newClient){
        return repository.save(newClient);
    }


    /**
     * Gets from database client with id
     *
     * @param id client's id
     * @return client with id
     */

    @ApiOperation(value = "Gets details about client given his id")
    @GetMapping(value = "/clients/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Client one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }


    /**
     * Edits client or add it to the database
     *
     * @param newClient client to replace or add
     * @param id client's id
     * @return client saved in repository
     */

    @ApiOperation(value = "Replaces client in repository given its id or insert it into repository")
    @PutMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
     * Delete client from database
     *
     * @param id client's id
     */

    @ApiOperation(value = "Delete client from repository")
    @DeleteMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void deleteClient(@PathVariable Long id){
        repository.deleteById(id);
    }
}
