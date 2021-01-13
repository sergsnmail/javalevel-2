package entities;

public interface Obstacle {
    boolean pass(Participator participator);
    int getDifficulty();
}
