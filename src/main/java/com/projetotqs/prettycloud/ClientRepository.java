package com.projetotqs.prettycloud;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Client's repository.
 * Domain type is a Client and its id type is a Long
 */

interface ClientRepository extends JpaRepository<Client, Long>{

}
