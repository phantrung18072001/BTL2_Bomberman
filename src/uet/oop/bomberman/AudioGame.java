package uet.oop.bomberman;

public class AudioGame {
    private static String PATH_EXPLOSION = "/sound/Bomb_Explodes.mp3";
    private static String PATH_END = "/sound/Ending.mp3";
    private static String PATH_GAME_OVER = "/sound/Death.mp3";
    private static String PATH_GET_ITEM = "/sound/Get_Item.mp3";
    private static String PATH_KILL_ENEMY = "/sound/Kill_Enemy.mp3";


    private static Thread threadExplosion;
    private static Thread threadGameOver;
    private static Thread threadEnd;
    private static Thread threadGetItem;
    private static Thread threadKillEnemy;

    public static void playExplosion() {
        threadExplosion = new Thread(new ThreadAudio(PATH_EXPLOSION));
        threadExplosion.start();
    }
    public static void playEnd() {
        threadEnd = new Thread(new ThreadAudio(PATH_END));
        threadEnd.start();
    }
    public static void playGetItem() {
        threadGetItem = new Thread(new ThreadAudio(PATH_GET_ITEM));
        threadGetItem.start();
    }
    public static void playKillEnemy(){
        threadKillEnemy = new Thread(new ThreadAudio(PATH_KILL_ENEMY));
        threadKillEnemy.start();
    }

    public static void playGameOver() {
        if (threadGameOver == null || !threadGameOver.isAlive()) {
            threadGameOver = new Thread(new ThreadAudio(PATH_GAME_OVER));
            threadGameOver.start();
        }
    }

}
