package entities;

public class Wall implements Obstacle {

    private int height;
    private String name;

    public Wall(String name, int height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public boolean pass(Participator participator) {
        return participator.jump(this);
    }

    @Override
    public int getDifficulty() {
        return height;
    }

    @Override
    public String toString() {
        return name;
    }
}
