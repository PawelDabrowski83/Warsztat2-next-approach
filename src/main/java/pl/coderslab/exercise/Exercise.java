package pl.coderslab.exercise;

import java.util.Comparator;

public class Exercise implements Comparator<Exercise> {

    private int id;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compare(Exercise o1, Exercise o2) {
        return o1.title.compareToIgnoreCase(o2.title);
    }
}
