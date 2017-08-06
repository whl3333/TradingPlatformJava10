package model;


public class Trade {

    private final long id;
    private final String content;

    public Trade(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}