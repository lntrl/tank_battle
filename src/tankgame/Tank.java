package tankgame;

//所有坦克的父类(罗列出所有坦克的共性)
public class Tank {
    private int x;                  //坦克左上角 x 坐标
    private int y;                  //坦克左上角 y 坐标
    private int direct;             //坦克方向
    private int speed;              //坦克速度
    boolean isLive = true;          //坦克存活情况
    boolean isOverlap = false;      //坦克重叠情况

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*
        move 系列方法的 if 语句,将坦克限制在窗口内
     */
    public void moveUp() {
        if (y <= 20 || isOverlap) {
//            System.out.println("不能继续向上...");
            return;
        }
        y -= speed;
    }

    public void moveRight() {
        if (x >= 900 || isOverlap) {
//            System.out.println("不能继续向右...");
            return;
        }
        x += speed;
    }

    public void moveDown() {
        if (y >= 640 || isOverlap) {
//            System.out.println("不能继续向下...");
            return;
        }
        y += speed;
    }

    public void moveLeft() {
        if (x <= 20 || isOverlap) {
//            System.out.println("不能继续向左");
            return;
        }
        x -= speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
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
}
