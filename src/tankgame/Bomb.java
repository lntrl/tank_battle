package tankgame;

//炸弹
public class Bomb {
    private int x,y;
    private int life = 9;           //炸弹周期

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getLife() {
        int i = 0;
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void lowerLife() { //配合爆炸的动画效果，让图片有适当的停滞
        if (life > 0) {
            life--;
        }
    }
}
