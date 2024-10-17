package com.example.tictactoe.GameManager;

import jakarta.websocket.Session;
import org.springframework.web.socket.TextMessage;

public class GameManager {

    //waiting players
    private Map <String, Player> waitingPlayers= new HashMap<>();
    //active games
    private Map <String, Game> activeGames=new HashMaps<>();

    //add Player.
    public void addPlayer(Session session){
        Player player=new Player(session);
        if(waitingPlayers.isEmpty()){
            waitingPlayers.put(session.getId(),player);
        }else{
            Player opponent=waitingPlayers.values().iterator().next();
            Game game=new Game(player,opponent);
            player.setGameId(game.getGameID());
            opponent.setGameId(game.getGameID());
            activeGames.put(game.getGameId(),game);
            waitingPlayers.remove(opponent.getsession().getId());
            game.startGame();
        }
    }

    //Process player Move
    //we have a player session representing the current player.
    //we also have that player move which is represented by a string
    //We have to first find an active game by session(player)
    //once that is found we have to process the players move in that active game
    public void processMessage(Session session, String message){
        //players have a gameid
        Game game= findGameBySession(session);

        if(game != null){
            game.processMessage(session,message);
        }
    }



    //disconnetplayer or end game
    //we firt find an active game for the session or the player.
    //if a game is present end the game and remove it from the games list.
    //if no games are present for the session meaning the player must be in the waiting list.
    //remove the player from the waiting list.
    public void disconnectPlayer(Session session){
        Game game=containsSession(session);
        if(game!=null){
            game.endGame();
            activeGames.remove(game.getGameId());
        }
    }

    //find games by players(Sessions)
    //session is provided
    //we need to iterate the active games list and find
    //if any games has the session or player.
    private Game findGameBySession(Session session){
        for(Game game: activeGames.values()){
            if(game.containsSession(session)){
                return game;
            }
        }
        return null;
    }




}
