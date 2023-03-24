package tankgame;

import java.io.*;
import java.util.Vector;

//记录游戏数据
public class Recorder {
    private static int destoryEnemyNum;
    private static Vector<Enemy> enemies = new Vector<>();
    private static Hero hero;
    private static String filePath = "src\\myRecord.txt";

    public static int getDestoryEnemyNum() {
        return destoryEnemyNum;
    }

    public static void setDestoryEnemyNum(int destoryEnemyNum0) {
        destoryEnemyNum = destoryEnemyNum0;
    }

    public static void addNum() {
        ++destoryEnemyNum;
    }

    public static Vector<Enemy> getEnemies() {
        return enemies;
    }

    public static void setEnemies(Vector<Enemy> enemies) {
        Recorder.enemies = enemies;
    }

    public static Hero getHero() {
        return hero;
    }

    public static void setHero(Hero hero) {
        Recorder.hero = hero;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        Recorder.filePath = filePath;
    }

    /*
        游戏结束,数据存盘(叉掉窗口时)
        第一行为存储摧毁敌人坦克数
        第二行为 hero 坦克的 x y direct(并且以空格隔开)
        第三行及以后为敌方坦克的 x y direct(并且以空格隔开,如果还存在的化)
     */
    public static void writeRecord() {
        BufferedWriter bw = null;
        String info = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(destoryEnemyNum + "");
            bw.newLine();
            info = hero.getX() + " " + hero.getY() + " " + hero.getDirect();
            bw.write(info);
            bw.newLine();
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                info = enemy.getX() + " " + enemy.getY() + " " + enemy.getDirect();
                bw.write(info);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //若选择继续上把,则从存盘文件中读取出数据
    public static void readRecord() {
        BufferedReader br = null;
        String line;
        int[] a = new int[3];
        try {
            br = new BufferedReader(new FileReader(filePath));
            destoryEnemyNum = Integer.parseInt(br.readLine());
            String[] s1 = br.readLine().split(" ");
            for (int i = 0; i < s1.length; i++) {
                a[i] = Integer.parseInt(s1[i]);
            }
            hero = new Hero(a[0],a[1]);
            hero.setDirect(a[2]);
            hero.setSpeed(10);
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                for (int i = 0; i < s.length; i++) {
                    a[i] = Integer.parseInt(s[i]);
                }
                Enemy enemy = new Enemy(a[0], a[1]);
                new Thread(enemy).start();
                enemy.setSpeed(10);
                enemy.setDirect(a[2]);
                enemies.add(enemy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
