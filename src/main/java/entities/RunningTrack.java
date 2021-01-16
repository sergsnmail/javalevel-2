package entities;

public class RunningTrack implements Obstacle {
    private int distance;
    private String name;

    public RunningTrack(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public boolean pass(Participator participator) {
        return participator.run(this);
    }

    @Override
    public int getDifficulty() {
        return distance;
    }

    @Override
    public String toString() {
        return name;
    }
}
