package com.example.tictactoe.Model;

import jakarta.websocket.Session;

public class Player {

    private Session session;

    public Player(Session session){
        this.session=session;
    }

    public Session getSession(){
        return session;
    }
    
}
