package MiniProject;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
  final int WIDTH= 700, HEIGHT= 500;
  Thread thread;
  HumanPaddle p1, p2;
  Ball b1;
  boolean gameStarted;
  Graphics gfx;
  Image img;

  public void init(){
    this.resize(WIDTH, HEIGHT);
    gameStarted= false;
    this.addKeyListener(this);
    p1= new HumanPaddle(1);
    p2= new HumanPaddle(2);
    b1= new Ball();
    img= createImage(WIDTH, HEIGHT);
    gfx= img.getGraphics();
    thread= new Thread(this);
    thread.start();
  }

  public void paint(Graphics g){
    gfx.setColor(Color.BLACK);
    gfx.fillRect(0, 0, WIDTH, HEIGHT);
    if(b1.getX() < -10 || b1.getX() > 710){
      gfx.setColor(Color.cyan);
      gfx.drawString("Game Over", 350, 250);
    }
    else{
      p1.draw(gfx);
      p2.draw(gfx);
      b1.draw(gfx);
    }

    if(!gameStarted){
      gfx.setColor(Color.white);
      gfx.drawString("Ping Pong", 340, 100);
      gfx.drawString("Press Enter to Begin", 310, 130 );
    }
    g.drawImage(img, 0, 0, this);

  }

  public void update(Graphics g){
    paint(g);
  }

  public void run() {
    for(;;){

      if(gameStarted){
        p1.move();
        p2.move();
        b1.move();
        b1.checkPaddleCollision(p1,p2);

      }

      repaint();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
  
  public void keyTyped(KeyEvent e) {

  }

  public void keyPressed(KeyEvent e) {
    //for player 1
    if(e.getKeyCode() == KeyEvent.VK_W){
      p1.setUpAcc(true);
    }else if(e.getKeyCode() == KeyEvent.VK_S){
      p1.setDownAcc(true);
    }

    //for player 2
    if(e.getKeyCode() == KeyEvent.VK_UP){
      p2.setUpAcc(true);
    }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
      p2.setDownAcc(true);
    }

    //to start game
    if(e.getKeyCode() == KeyEvent.VK_ENTER){
      gameStarted= true;
    }

  }

  public void keyReleased(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_W){
      p1.setUpAcc(false);
    }else if(e.getKeyCode() == KeyEvent.VK_S){
      p1.setDownAcc(false);
    }

    //for player 2
    if(e.getKeyCode() == KeyEvent.VK_UP){
      p2.setUpAcc(false);
    }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
      p2.setDownAcc(false);
    }
  }
}
