/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import view.HomeWindow;

/**
 *
 * @author Gabriel
 */
public class HomeController {
    
    private HomeWindow view;
    private User user;

    public HomeController(HomeWindow view, User user) {
        this.view = view;
        this.user = user;
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
        view.getLbl_welcome().setText("Bem-vindo(a), " + user.getName());
    }

    public User getUser() {
        return user;
    }
}
