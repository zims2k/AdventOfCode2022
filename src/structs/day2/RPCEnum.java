package structs.day2;

public enum RPCEnum {
    ROCK, PAPER, SCISSOR;

    public int getValue(){
        switch (this){
            case ROCK -> {
                return 1;
            }
            case PAPER -> {
                return 2;
            }
            case SCISSOR -> {
                return 3;
            }
        }

        throw new IllegalStateException("WTF!! This cannot be true to be true...");
    }
}
