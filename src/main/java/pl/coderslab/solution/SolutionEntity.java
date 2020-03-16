package pl.coderslab.solution;

import java.time.LocalDateTime;

public class SolutionEntity {

    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String description;
    private int exerciseId;
    private int usersId;

    public SolutionEntity(LocalDateTime created, LocalDateTime updated, String description, int exerciseId, int usersId) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exerciseId = exerciseId;
        this.usersId = usersId;
    }

    public SolutionEntity() {
    }

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

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exerciseId=" + exerciseId +
                ", usersId=" + usersId +
                '}';
    }
}
