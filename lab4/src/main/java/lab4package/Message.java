package lab4package;

public class Message {
    public String id;
    public Results results;

    public Message(String id, Results results) {
        this.id = id;
        this.results = results;
    }

    public String toString() {
        return String.format("(%s - %s)", this.id);
    }
}
