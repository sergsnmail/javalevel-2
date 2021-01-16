import entities.*;

public class Main {
    public static void main(String[] args) {

        Participator[] participants = getParticipator();

        Obstacle[] obstacles = getObstacle();

        /**
         * Начинаем соревнования
         */
        for (Participator participator: participants) {
            System.out.printf("Участник %s на старте\n",participator.toString());
            for (int i = 0; i < obstacles.length; i++) {
                if (!obstacles[i].pass(participator)){
                    System.out.printf("Участник %s выбывает из состязания\n",participator.toString());
                    break;
                }
                if (i == obstacles.length -1){
                    System.out.printf("Участник %s прошел все препрятствия\n",participator.toString());
                }
            }
            System.out.println("--------------------------\n");
        }
    }

    public static Participator[] getParticipator(){
        return new Participator[] {new Human("Человек", 100, 10),
                                   new Cat("Кот", 150, 25),
                                   new Robot("Робот", 350, 30)};
    }

    public static Obstacle[] getObstacle(){
        return new Obstacle[] {new Wall("Стена-1", 5),
                               new Wall("Стена-2", 10),
                               new Wall("Стена-3", 15),
                               new RunningTrack("Дорожка-1",50),
                               new RunningTrack("Дорожка-2",100),
                               new Wall("Стена-4", 20),
                               new RunningTrack("Дорожка-3",150),
                               new Wall("Стена-5", 26),
                               new RunningTrack("Дорожка-4",250),
                               new RunningTrack("Дорожка-5",300)};
    }
}
