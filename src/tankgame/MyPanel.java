package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

/*
    浅谈为什么 MYPanel,Bullet 要设置成线程，其实我认为 run 方法不过是一个普通方法而已，它并不特别，
    我认为线程的休眠机制是实施线程的原因
    如果只是单纯的使用 while 死循环,达到的效果只能是子弹一瞬间就到达边界了(因为程序运行的太快了,这时就可以利用线程的休眠机制来完善这一点)
 */
public class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero hero;              //我方坦克
    Vector<Enemy>  enemies;  //多线程安全,敌方坦克集合
    Vector<Bullet> heroBullets;     //我方坦克子弹集合
    int enenySize = 10;      //敌方坦克数量
    Vector<Bomb> bombs;     //炸弹集合
    Image image1;           //炸弹阶段1图片
    Image image2;           //炸弹阶段2图片
    Image image3;           //炸弹阶段3图片

    //初始化敌我坦克
    public MyPanel(String next) {
        heroBullets = new Vector<>();
        bombs = new Vector<>();
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
        File file = new File(Recorder.getFilePath());
        //如果要存盘的文件不存在,则证明是还没有新开过游戏,直接继续上把会报错,所有让其强制新开游戏
        if (!file.exists()) {
            System.out.println("文件不存在开启新游戏");
            next = "1";
        }
        switch (next) {
            //新开游戏
            case "1":
                hero = new Hero(400,300);
                hero.setSpeed(10);
                this.enemies = new Vector<>();
                for (int i = 0; i < enenySize; i++) {
                    Enemy enemy = new Enemy(100 * (i + 1), 0);
                    new Thread(enemy).start();
                    enemy.setDirect(2);
                    enemy.setSpeed(10);
                    this.enemies.add(enemy);
                }
                new AePlayWave("src\\111.wav").start();
                break;
            case "2":
                //继续上把,将数据从存盘文件中读出,hero 和 enemie 的初始化也在该方法中进行
                Recorder.readRecord();
                hero = Recorder.getHero();
                enemies = Recorder.getEnemies();
                heroBullets = new Vector<>();
                break;
            default:
                System.out.println("输入值有误");
        }
    }

    //绘制右边旁白部分
    public void drawBlank(Graphics g) {
        Font font = new Font("宋体",Font.BOLD, 15);
        g.setFont(font);
        g.drawString("您累计击毁的坦克数",1020,30);
        g.drawString(Recorder.getDestoryEnemyNum() + "",1110,80);
        drawTank(1040, 50, g, 0, 0);
    }

    /*
        MyPanel 每刷新一次就会执行该方法
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);   //默认为黑色[游戏区域]
        drawBlank(g);
        for (int j = 0; j < bombs.size(); j++) {        //绘制炸弹
            Bomb bomb = bombs.get(j);
            if (bomb.getLife() > 6) {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            else if (bomb.getLife() > 3) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            else {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            bomb.lowerLife();       //炸弹寿命减少
            if (bomb.getLife() == 0) {
                bombs.remove(bomb);
                j--;
            }
        }
        for (int i = 0; i < enemies.size(); i++) {          //绘制敌方坦克
            Enemy enemy = enemies.get(i);
            //首先检查坦克是否接触到其他坦克,如果接触到了,则限制其继续行进的能力,直到他们不接触
            enemy.isOverlap = (enemy.isTouchEnemy(enemies) || enemy.isTouchHero(hero));
            //如果用户已经按下子弹且子弹尚未越界,就判断我方坦克子弹是否击中敌方坦克
            for (int j = 0; j < heroBullets.size(); j++) {
                Bullet bullet = heroBullets.get(j);
                hitEnemy(enemy, bullet);
            }
            Vector<Bullet> enemeyBullets;          //敌方[各个]坦克的子弹集合
            Bullet bullet;
            drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 0);
            for (int j = 0; j < (enemeyBullets = enemy.bullets).size(); j++) {//绘制敌方坦克子弹
                g.setColor(Color.cyan);
                //如果子弹尚存活,就画出来
                if ((bullet = enemeyBullets.get(j)).isLive()) {
                    //判断这枚子弹是否击中我方坦克[我方坦克存活情况下,才能调用这个方法]
                    if (hero.isLive) {
                        hitHero(hero, bullet, enemy.bullets);
                    }
                    g.draw3DRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), false);
                }
                //子弹存活情况为 false,将子弹从 vector 删除
                else {
                    enemeyBullets.remove(bullet);
                    j--;            //remove 之后,vector 的大小减一，后一位移到此位，为使后一位也正常进入循环,j 要减一
                }
            }
        }
        //判断我方坦克是否与敌方坦克接触,如果接触到了,则限制其继续行进的能力,直到他们不接触
        hero.isOverlap = hero.isTouchEnemy(enemies);
        heroBullets = hero.bullets;
        /*
            1.若尚未按下 t 键，则尚未坦克尚未发出子弹，不需要将子弹画出
            2.如果子弹不存在了[越界],也不需要再画了
         */
        for (int i = 0; i < heroBullets.size(); i++) {
            Bullet bullet = heroBullets.get(i);
            g.setColor(Color.yellow);
            if (!(bullet.isLive())) {
                heroBullets.remove(bullet);
                i--;
            }
            else {
                g.draw3DRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(),
                        false);
            }
        }
        //我方坦克未被击中
        if (hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);    //绘制我方坦克
        }
    }

    //判定是否击中敌方坦克
    public void hitEnemy(Enemy enemy, Bullet bullet) {
        switch (enemy.getDirect()) {
            case 0:
            case 2:
                if (bullet.getX() > enemy.getX() && bullet.getX() < enemy.getX() + 40 &&
                        bullet.getY() > enemy.getY() && bullet.getY() < enemy.getY() + 60) {
                    Recorder.addNum();          //记录敌方坦克被击毁数
                    enemy.isLive = false;       //该敌方坦克存活情况设置为 false
                    enemies.remove(enemy);      //将该敌方坦克从敌方坦克集合中除去
                    bullet.setLive(false);      //子弹存活情况设置为 false
                    heroBullets.remove(bullet); //将该子弹从我方坦克的子弹集合中除去
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());   //坦克被击毁,新建一个炸弹对象
                    bombs.add(bomb);    //将该炸弹加入到炸弹集合中
                }
                break;
            case 1:
            case 3:
                if (bullet.getX() > enemy.getX() && bullet.getX() < enemy.getX() + 60 &&
                        bullet.getY() > enemy.getY() && bullet.getY() < enemy.getY() + 40) {
                    Recorder.addNum();          //记录敌方坦克被击毁数
                    enemy.isLive = false;
                    enemies.remove(enemy);
                    bullet.setLive(false);
                    heroBullets.remove(bullet);
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                }
        }
    }

    //判断是否击中我方坦克
    public void hitHero(Hero hero, Bullet bullet, Vector<Bullet> bullets) {
        switch (hero.getDirect()) {
            case 0:
            case 2:
                if (bullet.getX() > hero.getX() && bullet.getX() < hero.getX() + 40 &&
                        bullet.getY() > hero.getY() && bullet.getY() < hero.getY() + 60) {
                    hero.isLive = false;        //我方坦克存活情况设置为 false
                    bullet.setLive(false);      //子弹存活情况设置为 false
                    bullets.remove(bullet);     //将子弹从其坦克对应的子弹集合中除去
                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (bullet.getX() > hero.getX() && bullet.getX() < hero.getX() + 60 &&
                        bullet.getY() > hero.getY() && bullet.getY() < hero.getY() + 40) {
                    hero.isLive = false;
                    bullet.setLive(false);
                    bullets.remove(bullet);
                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
                    bombs.add(bomb);
                }
        }
    }

    /**
     *
     * @param x 坦克左上角 x 坐标
     * @param y 坦克左上角 y 坐标
     * @param g 画笔
     * @param direct 坦克方向(0上 1右 2下 3左)
     * @param type 坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据坦克类型，设置不同颜色
        switch (type) {
            case 0://敌方坦克
                g.setColor(Color.cyan);
                break;
            case 1://我方坦克
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {
            case 0://表示向上
                g.fill3DRect(x, y, 10 ,60 ,false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10 ,60 ,false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20 ,40 ,false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1://表示向右
                g.fill3DRect(x, y, 60 ,10 ,false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60 ,10 ,false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40 ,20 ,false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
                break;
            case 2://表示向下
                g.fill3DRect(x, y, 10 ,60 ,false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10 ,60 ,false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20 ,40 ,false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3://表示向左
                g.fill3DRect(x, y, 60 ,10 ,false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60 ,10 ,false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40 ,20 ,false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下键盘的 w、d、s、a,则对应坦克向上、右、下、左移动(顺时针)
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }
        //按下 j 键,且在我方坦克存活的情况下,发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            if (hero.isLive) {
                hero.shoot();
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    //MyPanel 隔 50ms 重绘一次
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
