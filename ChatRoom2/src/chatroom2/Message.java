package chatroom2;

import java.io.Serializable;

public class Message implements Serializable{
    String User="";
    String message="";
    String destiny="";
    String type="";
    
    //Public message
    public Message(String User, String message, String type) {
        this.User = User;
        this.message = message;
        this.type = type;
    }
    
    //Private message
    public Message(String User, String message, String destiny, String type) {
        this.User = User;
        this.message = message;
        this.destiny = destiny;
        this.type = type;
    }

    public String getUser() {
        return User;
    }

    public String getMessage() {
        return message;
    }

    public String getDestiny() {
        return destiny;
    }

    public String getType() {
        return type;
    }
}
