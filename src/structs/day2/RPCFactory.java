package structs.day2;

public class RPCFactory {
    public static RPCEnum create(String inp) {
        return switch (inp) {
            case "A", "X" -> RPCEnum.ROCK;
            case "B", "Y" -> RPCEnum.PAPER;
            case "C", "Z" -> RPCEnum.SCISSOR;
            default -> throw new IllegalArgumentException(String.format("No such tool to play with: '%s'...", inp));
        };
    }

    public static RPCEnum enforceLost(RPCEnum opponentVal){
        switch(opponentVal){
            case ROCK -> {
                return RPCEnum.SCISSOR;
            }
            case PAPER -> {
                return RPCEnum.ROCK;
            }
            case SCISSOR -> {
                return RPCEnum.PAPER;
            }
        }

        throw new IllegalStateException("WTF!! Could not enforce LOST!!");
    }

    public static RPCEnum enforceWon(RPCEnum opponentVal){
        switch(opponentVal){
            case ROCK -> {
                return RPCEnum.PAPER;
            }
            case PAPER -> {
                return RPCEnum.SCISSOR;
            }
            case SCISSOR -> {
                return RPCEnum.ROCK;
            }
        }

        throw new IllegalStateException("WTF!! Could not enforce WON!!");
    }
}
