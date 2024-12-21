public class IllegalMoveException extends Exception{
    public IllegalMoveException(){
        super("Make a normal move you idiot!");
    }

    public IllegalMoveException(String message){
        super(message);
    }
}
