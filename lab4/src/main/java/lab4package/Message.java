package lab4package;

public class Message {
    public String id;
//    public

    public Message(String id) {
        this.id = id;
    }

    public String toString() {
        return String.format("(%s - %s)", this.id);
    }
}
