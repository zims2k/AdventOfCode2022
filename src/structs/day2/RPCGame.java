package structs.day2;

public class RPCGame {

    private static final int VALUE_WON = 6;
    private static final int VALUE_DRAW = 3;
    private static final int VALUE_LOST = 0;

    private final RPCEnum player1Choice;
    private final RPCEnum player2Choice;

    public RPCGame(RPCEnum player1Choice, RPCEnum player2Choice) {
        this.player1Choice = player1Choice;
        this.player2Choice = player2Choice;
    }

    private static int getPoints(RPCEnum player1Choice, RPCEnum player2Choice){
        int gamePoints = VALUE_LOST;

        if (player1Choice.equals(player2Choice)){
            gamePoints = VALUE_DRAW;
        }
        else{
            switch(player1Choice){
                case ROCK -> {
                    if (player2Choice.equals(RPCEnum.SCISSOR)) gamePoints = VALUE_WON;
                }
                case PAPER -> {
                    if (player2Choice.equals(RPCEnum.ROCK)) gamePoints = VALUE_WON;
                }
                case SCISSOR -> {
                    if (player2Choice.equals(RPCEnum.PAPER)) gamePoints = VALUE_WON;
                }
            }
        }

        return gamePoints + player1Choice.getValue();
    }

    private static int getPointsPerfectStrategy(RPCEnum player1Choice, RPCEnum player2Choice){
        int gamePoints = 0;

        switch (player1Choice){
            case ROCK -> { // lose
                return getPoints(RPCFactory.enforceLost(player2Choice), player2Choice);
            }
            case PAPER -> { // draw
                return getPoints(player2Choice, player2Choice);
            }
            case SCISSOR -> { // win
                return getPoints(RPCFactory.enforceWon(player2Choice), player2Choice);
            }
        }

        return gamePoints + player1Choice.getValue();
    }

    @SuppressWarnings("unused") // future use...
    public int getPointsPlayer1OwnPlayer(){
        return getPoints(player1Choice, player2Choice);
    }

    public int getPointsPlayer2OwnPlayer(){
        return getPoints(player2Choice, player1Choice);
    }

    @SuppressWarnings("unused") // future use...
    public int getPointsPlayer1OwnPlayerPerfectStrategy(){
        return getPointsPerfectStrategy(player1Choice, player2Choice);
    }

    public int getPointsPlayer2OwnPlayerPerfectStrategy(){
        return getPointsPerfectStrategy(player2Choice, player1Choice);
    }
}
