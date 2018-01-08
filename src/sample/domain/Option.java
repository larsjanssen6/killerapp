package sample.domain;

import java.io.Serializable;

public class Option implements Serializable {
    private int id;
    private int poll_id;
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPollId()
    {
        return poll_id;
    }

    public void setPollId(int poll_id)
    {
        this.poll_id = poll_id;
    }
}
