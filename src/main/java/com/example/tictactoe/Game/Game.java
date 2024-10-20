package com.example.tictactoe.Game;

import jakarta.websocket.Session;

import java.util.UUID;

public class Game {
        //2players.
        //currentplayer
        //game board
        //gameiD
        private String GameId;
        private Player player1;
        private Player player2;
        private char[][] board= new char[3][3];
        private Player currentPlayer;

        //constructor to initialize a game.
        public Game(Player player1, Player player2){
            this.GameId= UUID.randomUUID().toString();
            this.player1=player1;
            this.player2=player2;
            this.currentPlayer=player2;
        }

        //function to get gameid
        public String getGameId(){
            return GameId;
        }
        //function to startGame
        public void startGame(){
            sendMessage(player1.getSession(),"Game Started. You are Player 1.");
            sendMessage(player2.getSession(),"Game Started. You are Player 2.");
            sendGameState();
        }
        //function to find game with a player
        public boolean containsSession(Session session){
            return player1.getSession().equals(session) || player2.getSession().equals(session);
        }

        //function to processmove
        public void processMove(Session session, String move){
            //find the location// i will try to make it such that if the user taps on
            //a location on the board it will convert that location to the coordinate and send
            //here we check if the position is empty if so place the target there.
            //afer which we check if the user has won
            //stop the game if they won
            //if not continue
            sendGameState();
        }

        //function to send game state to each player
        public void sendGameState(){

        }
        //function to notify end of game.
        public void endGame(String message){
            sendMessage(player1.getSession(),"Game has ended "+message);
            sendMessage(player2.getSession(),"Game has ended "+message);
        }


        //helper for the sending message
        //session remote.sendText

        public void sendMessage(Session session,String message){
            try{
                session.getBasicRemote().sendText(message);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
}
