package com.example.tictactoe.Model;

import jakarta.websocket.Session;

public class Player {

    private Session session;
    private String gameID;

    public Player(Session session){
        this.session=session;
    }

    public Session getSession(){
        return session;
    }

    public String getGameId(){
        return gameID;
    }

    public void setGameID(String gameID){
        this.gameID=gameID;
    }
    
}
