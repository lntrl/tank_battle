package tankgame;

import java.util.HashMap;
import java.util.Vector;

//敌方坦克[线程]
public class Enemy extends Tank implements Runnable {
    private Bullet bullet;
    Vector<Bullet> bullets = new Vector<>();  //子弹集合

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    //敌方坦克之间是否有接触
    public boolean isTouchEnemy(Vector<Enemy> enemies) {
        switch (getDirect()) {
            case 0:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //同一个坦克必然重叠,无比较必要
                    if (enemy == this) {
                        continue;
                    }
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断该敌方坦克左上角是否进入 enemy 坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断该敌方坦克右上角是否进入 enemy 坦克领域
                        if (this.getX() + 40 >= enemy.getX() && this.getX() + 40 <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                    }
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
            case 1:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if (enemy == this) {
                        continue;
                    }
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断该敌方坦克右上角是否进入 enemy 坦克领域
                        if (this.getX() + 60 >= enemy.getX() && this.getX() + 60 <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断该敌方坦克右下角是否进入 enemy 坦克领域
                        if (this.getX() + 60 >= enemy.getX() && this.getX() + 60 <= enemy.getX() + 40
                                && this.getY() + 40 >= enemy.getY()
                                && this.getY() + 40 <= enemy.getY() + 60) {
                            return true;
                        }
                    }
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
            case 2:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if (enemy == this) {
                        continue;
                    }
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断该敌方坦克左下角是否进入 enemy 坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() + 60 >= enemy.getY()
                                && this.getY() + 60 <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断该敌方坦克右下角是否进入 enemy 坦克领域
                        if (this.getX() + 40 >= enemy.getX() && this.getX() + 40 <= enemy.getX() + 40
                                && this.getY() + 60 >= enemy.getY()
                                && this.getY() + 60 <= enemy.getY() + 60) {
                            return true;
                        }
                    }
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
            case 3:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if (enemy == this) {
                        continue;
                    }
                    if (enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                        //判断该敌方坦克左上角是否进入 enemy 坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() >= enemy.getY()
                                && this.getY() <= enemy.getY() + 60) {
                            return true;
                        }
                        //判断该敌方坦克左下角是否进入 enemy 坦克领域
                        if (this.getX() >= enemy.getX() && this.getX() <= enemy.getX() + 40
                                && this.getY() + 40 >= enemy.getY()
                                && this.getY() + 40 <= enemy.getY() + 60) {
                            return true;
                        }
                    }
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

    //敌方坦克是否接触到我方坦克(同上方法)
    public boolean isTouchHero(Hero hero) {
        switch (getDirect()) {
            case 0:
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() >= hero.getX() && this.getX() <= hero.getX() + 40
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() + 40 >= hero.getX() && this.getX() + 40 <= hero.getX() + 40
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 60) {
                        return true;
                    }
                }
                if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() >= hero.getX() && this.getX() <= hero.getX() + 60
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 40) {
                        return true;
                    }
                    if (this.getX() + 40 >= hero.getX() && this.getX() + 40 <= hero.getX() + 60
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 40) {
                        return true;
                    }
                }
                break;
            case 1:
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() + 60 >= hero.getX() && this.getX() + 60 <= hero.getX() + 40
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() + 60 >= hero.getX() && this.getX() + 60 <= hero.getX() + 40
                            && this.getY() + 40 >= hero.getY()
                            && this.getY() + 40 <= hero.getY() + 60) {
                        return true;
                    }
                }
                if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() + 60 >= hero.getX() && this.getX() + 60 <= hero.getX() + 60
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 40) {
                        return true;
                    }
                    if (this.getX() + 60 >= hero.getX() && this.getX() + 60 <= hero.getX() + 60
                            && this.getY() + 40 >= hero.getY()
                            && this.getY() + 40 <= hero.getY() + 40) {
                        return true;
                    }
                }
            case 2:
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() >= hero.getX() && this.getX() <= hero.getX() + 40
                            && this.getY() + 60 >= hero.getY()
                            && this.getY() + 60 <= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() + 40 >= hero.getX() && this.getX() + 40 <= hero.getX() + 40
                            && this.getY() + 60 >= hero.getY()
                            && this.getY() + 60 <= hero.getY() + 60) {
                        return true;
                    }
                }
                if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() >= hero.getX() && this.getX() <= hero.getX() + 60
                            && this.getY() + 60 >= hero.getY()
                            && this.getY() + 60 <= hero.getY() + 40) {
                        return true;
                    }
                    if (this.getX() + 40 >= hero.getX() && this.getX() + 40 <= hero.getX() + 60
                            && this.getY() + 60 >= hero.getY()
                            && this.getY() + 60 <= hero.getY() + 40) {
                        return true;
                }
           }
            case 3:
                if (hero.getDirect() == 0 || hero.getDirect() == 2) {
                    if (this.getX() >= hero.getX() && this.getX() <= hero.getX() + 40
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 60) {
                        return true;
                    }
                    if (this.getX() >= hero.getX() && this.getX() <= hero.getX() + 40
                            && this.getY() + 40 >= hero.getY()
                            && this.getY() + 40 <= hero.getY() + 60) {
                        return true;
                    }
                }
                if (hero.getDirect() == 1 || hero.getDirect() == 3) {
                    if (this.getX() >= hero.getX() && this.getX() <= hero.getX() + 60
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 40) {
                        return true;
                    }
                    if (this.getX() + 40 >= hero.getX() && this.getX() + 40 <= hero.getX() + 60
                            && this.getY() >= hero.getY()
                            && this.getY() <= hero.getY() + 40) {
                        return true;
                    }
                }
                break;
             }
             return false;
        }

    //每启动一个敌方坦克线程,就会调用这个方法,直到这个坦克死亡
    @Override
    public void run() {
        while (isLive) {
            /*
                为了使坦克的运动稍微符合惯性,在确定某一方向后，
                应在此方向行驶一段,又为了让其不至于瞬移,每移动一次,就让线程休眠一段时间
             */
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    bullet = new Bullet(getX() + 20, getY(), getDirect());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    bullet = new Bullet(getX() + 60, getY() + 20, getDirect());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    bullet = new Bullet(getX() + 20, getY() + 60, getDirect());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    bullet = new Bullet(getX(), getY() + 20, getDirect());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            bullets.add(bullet);
            //启动子弹线程,让其自行飞
            new Thread(bullet).start();
            //随机选择一个方向
            setDirect((int) (Math.random() * 4));
        }

    }

}
