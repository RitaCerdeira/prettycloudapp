package com.projetotqs.prettycloud;

/**
 * Exception when client is not found
 */

public class ClientNotFoundException extends RuntimeException {

    /**
     * Exception when client is not found
     *
     * @param id client's id
     */
    public ClientNotFoundException(Long id) {
        super("Could not find client " + id);
    }
}
