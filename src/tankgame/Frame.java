package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Frame extends JFrame {
    MyPanel myPanel;
    Scanner scanner = new Scanner(System.in);

    public Frame() {
        System.out.println("开始新游戏(1),继续上把(2)");
        String next = scanner.next();
        myPanel = new MyPanel(next);
        add(myPanel);
        setSize(1200,950);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(myPanel);
        new Thread(myPanel).start();
        //关闭窗口时,将数据写入
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.setEnemies(myPanel.enemies);
                Recorder.setHero(myPanel.hero);
                Recorder.writeRecord();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new Frame();
    }
}
