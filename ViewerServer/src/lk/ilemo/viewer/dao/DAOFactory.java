/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.dao;

import lk.ilemo.viewer.dao.custom.impl.UserDAOImpl;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class DAOFactory {
    public enum DAOType{
        USERDAO;
    }
    private static DAOFactory factory;

    public DAOFactory() {
    }
    public static DAOFactory getInstance(){
        if(factory==null){
            factory=new DAOFactory();
        }
        return factory;
    }
    
    public SuperDAO getDAO(DAOType type){
        switch(type){
            case USERDAO:return new UserDAOImpl();
            default:return null;
        }
    }
    
}
