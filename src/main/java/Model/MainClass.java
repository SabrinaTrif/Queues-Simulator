package Model;

import Controller.InputController;
import View.InputFrame;

public class MainClass {
    public static void main(String[] args) {
        InputFrame view=new InputFrame();
        new InputController(view);
    }
}
