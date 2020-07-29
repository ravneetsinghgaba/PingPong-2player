package MiniProject;

import java.awt.*;

public class HumanPaddle implements Paddle {
    double y, yVel;
    boolean upAcc, downAcc;
    final double GRAVITY= 0.94;
    int player, x;
    //x and y represent position of the actual paddle in the applet
    //player tells player1 or player2, so that we can decide to give it the left side
    //of the applet or the right side.
    //upAccelerating and downAccelerating to see where it is moving faster.


    public HumanPaddle(int player) {
        upAcc= false;
        downAcc= false;
        y= 210;
        yVel= 0;
        if(player == 1){
            x= 10;
        }
        else{
            x= 670;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int)y, 20, 80);
    }


    public void move() {
        //moving pallet by pressing keys
        if(upAcc){
            yVel -= 2;
        }else if(downAcc){
            yVel += 2;
        }else if(!upAcc && !downAcc){
            yVel *= GRAVITY;
        }

        //speed of the pallet
        if(yVel >= 5){
            yVel= 5;
        }else if(yVel <= -5){
            yVel= -5;
        }
        y += yVel;

        //keeping pallet in the screen
        if(y < 0){
            y=0;
        }else if(y > 420){
            y=420;
        }
    }

    public void setUpAcc(boolean input){
        upAcc= input;
    }

    public void setDownAcc(boolean input){
        downAcc= input;
    }


    public int getY() {
        return (int)y;
    }
}
