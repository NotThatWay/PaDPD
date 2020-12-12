package lab4package;

public class Message {
    public String id;
    public Result result;

    public Message(String id, Result result) {
        this.id = id;
        this.result = result;
    }

    public String toString() {
        return String.format("(%s - %s)", this.id, this.result);
    }
}
