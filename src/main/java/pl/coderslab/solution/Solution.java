package pl.coderslab.solution;

import pl.coderslab.exercise.Exercise;
import pl.coderslab.users.User;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Solution implements Comparator<Solution> {

    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String description;
    private Exercise exercise;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compare(Solution o1, Solution o2) {
        return o1.created.compareTo(o2.created);
    }
}
