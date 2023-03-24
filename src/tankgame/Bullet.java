package tankgame;

//子弹[线程]
public class Bullet implements Runnable {
    private int x, y, direct;
    private int width = 2;                  //子弹宽
    private int height = 2;                 //子弹高[子弹实际上是一个比较小的矩形]
    private int speed = 20;                 //子弹速度
    private boolean isLive = true;          //子弹存活情况

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //每启动一个子弹线程,自行调动该方法,直到其死亡
    @Override
    public void run() {
        while (isLive) {
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
//            System.out.println("x=" + x + ",y=" + y);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //如果子弹不在窗口内,则无需画出
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {
                isLive = false;
            }
        }
    }
}
