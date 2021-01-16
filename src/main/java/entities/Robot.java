package entities;

public class Robot implements Participator {

    private int runningDistanceLimit;
    private int jumpHeightLimits;
    private String name;

    public Robot(String name, int runningDistanceLimit, int jumpHeightLimits) {
        this.name = name;
        this.runningDistanceLimit = runningDistanceLimit;
        this.jumpHeightLimits = jumpHeightLimits;
    }

    @Override
    public boolean run(Obstacle obstacle) {
        if (obstacle.getDifficulty() <= runningDistanceLimit) {
            System.out.printf("%s пробежал препятствие %s длиной %s\n", name, obstacle.toString(), obstacle.getDifficulty());
            return true;
        } else {
            System.out.printf("%s не смог пробежать препятствие %s длиной %s\n", name, obstacle.toString(), obstacle.getDifficulty());
            return false;
        }
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        if (obstacle.getDifficulty() <= jumpHeightLimits) {
            System.out.printf("%s перепрыгнул препятствие %s высотой %S\n", name, obstacle.toString(), obstacle.getDifficulty());
            return true;
        } else {
            System.out.printf("%s не смог перепрыгнуть препятствие %s высотой %s\n", name, obstacle.toString(), obstacle.getDifficulty());
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
