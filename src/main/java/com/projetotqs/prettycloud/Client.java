/**
 *
 *
 */

package com.projetotqs.prettycloud;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Client {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private Date birthday;

    /**
     * Client's constructor with no parameters
     */

    Client(){}


    /**
     * Client's constructor with name
     *
     * @param name
     */

    public Client(String name){
        this.name = name;
    }


    /**
     * Client's constructor with all parameters
     *
     * @param name client's name
     * @param email client's email
     * @param birthday client's birthday
     */

    public Client(String name, String email, Date birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }
}
