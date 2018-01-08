package sample.domain;

import java.io.Serializable;

public class Poll implements Serializable {
    private int id;
    private String name;
    private String question;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getQuestion() { return this.question; }

    public void setQuestion(String question) { this.question = question; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }
}
