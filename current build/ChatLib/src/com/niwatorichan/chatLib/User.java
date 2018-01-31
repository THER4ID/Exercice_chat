package com.niwatorichan.chatLib;

/**
 *
 * @author NiwatoriChan
 */
public class User {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    String username;

    public User(){}
    
    public User(int id, String Username){
        this.id=id;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = username;
    }
}
