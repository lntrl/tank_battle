package tankgame;

import java.util.Vector;

//我方坦克
public class Hero extends Tank {
    Bullet bullet;
    Vector<Bullet> bullets = new Vector<>();        //我方坦克子弹集合

    public Hero(int x, int y) {
        super(x, y);
    }

    public boolean isTouchEnemy(Vector<Enemy> enemies) {
        switch (getDirect()) {
            case 0://向上
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //敌方坦克向(上/下)
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断我方坦克左上角是否进入敌方坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断我方坦克右上角是否进入敌方坦克领域
                        if (this.getX() + 40 >= enemy.getX() && this.getX() + 40 <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                    }
                    //敌方坦克向(右/左)
                    if (enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 60
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 40 >= enemy.getX() && this.getX() + 40 <= enemy.getX() + 60
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 1://向右
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //敌方坦克向(上/下)
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断我方坦克的右上角是否进入敌方坦克领域
                        if (this.getX() + 60 >= enemy.getX() && this.getX() + 60 <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断我方坦克的右下角是否进入敌方坦克领域
                        if (this.getX() + 60 >= enemy.getX() && this.getX() + 60 <= enemy.getX() + 40
                                && this.getY() + 40 >= enemy.getY()
                                && this.getY() + 40 <= enemy.getY() + 60) {
                            return true;
                        }
                    }
                    //敌方坦克向(右/左)
                    if (enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                        if (this.getX() + 60 >= enemy.getX() && this.getX() + 60 <= enemy.getX() + 60
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 60 >= enemy.getX() && this.getX() + 60 <= enemy.getX() + 60
                                && this.getY() + 40 >= enemy.getY()
                                && this.getY() + 40 <= enemy.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 2://向下
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //敌方坦克向(上/下)
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断我方坦克左下角是否进入敌方坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() + 60 >= enemy.getY()
                                && this.getY() + 60 <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断我方坦克的右下角是否进入敌方坦克领域
                        if (this.getX() + 40 >= enemy.getX() && this.getX() + 40 <= enemy.getX() + 40
                                && this.getY() + 60 >= enemy.getY()
                                && this.getY() + 60 <= enemy.getY() + 60) {
                            return true;
                        }
                    }
                    //敌方坦克向(右/左)
                    if (enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 60
                                && this.getY() + 60 >= enemy.getY()
                                && this.getY() + 60 <= enemy.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 40 >= enemy.getX() && this.getX() + 40 <= enemy.getX() + 60
                                && this.getY() + 60 >= enemy.getY()
                                && this.getY() + 60 <= enemy.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 3://向左
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //敌方坦克向(上/下)
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断我方坦克左上角是否进入敌方坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断我方坦克左下角是否进入敌方坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() + 40 >= enemy.getY()
                                && this.getY() + 40 <= enemy.getY() + 60) {
                            return true;
                        }
                    }
                    //敌方坦克向(右/左)
                    if (enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 60
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 40 >= enemy.getX() && this.getX() + 40 <= enemy.getX() + 60
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }

    //我方坦克射击
    public void shoot() {
        //如果存活子弹数达到 5,则不能继续发射
        if (bullets.size() == 5) {
            System.out.println("子弹数目不能超过5");
            return;
        }
        switch (getDirect()) {
            case 0:
                bullet = new Bullet(getX() + 20, getY(), getDirect());
                break;
            case 1:
                bullet = new Bullet(getX() + 60, getY() + 20, getDirect());
                break;
            case 2:
                bullet = new Bullet(getX() + 20, getY() + 60, getDirect());
                break;
            case 3:
                bullet = new Bullet(getX(), getY() + 20, getDirect());
                break;
        }
        bullets.add(bullet);
        //启动子弹线程
        new Thread(bullet).start();
    }
}
