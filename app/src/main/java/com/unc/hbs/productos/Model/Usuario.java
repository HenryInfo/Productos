package com.unc.hbs.productos.Model;

import android.content.ContentValues;

import java.util.UUID;

/**
 * Created by hbs on 2/10/16.
 */

public class Usuario {
    private String id;
    private String user;
    private String pass;
    private boolean state;

    public Usuario(String user, String pass, boolean state) {
        this.id =  UUID.randomUUID().toString();;
        this.user = user;
        this.pass = pass;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public boolean isState() {
        return state;
    }

}
