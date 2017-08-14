/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.dto;

import java.awt.Dimension;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class UserDTO extends SuperDTO{
    private String userComputerName;
    private String IPAddress;
    private Dimension screenZize;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userComputerName, String IPAddress, Dimension screenZize) {
        this.userComputerName = userComputerName;
        this.IPAddress = IPAddress;
        this.screenZize = screenZize;

    }

    public UserDTO(String userComputerName,String password) {
        this.userComputerName = userComputerName;
        this.password = password;
    }

    

    public UserDTO(String userComputerName, String IPAddress,String password) {
        this.userComputerName = userComputerName;
        this.IPAddress = IPAddress;
        this.password = password;
    }

    public UserDTO(String userComputerName, String IPAddress, Dimension screenZize, String password) {
        this.userComputerName = userComputerName;
        this.IPAddress = IPAddress;
        this.screenZize = screenZize;
        this.password = password;
    }
    

    /**
     * @return the userComputerName
     */
    public String getUserComputerName() {
        return userComputerName;
    }

    /**
     * @param userComputerName the userComputerName to set
     */
    public void setUserComputerName(String userComputerName) {
        this.userComputerName = userComputerName;
    }

    /**
     * @return the IPAddress
     */
    public String getIPAddress() {
        return IPAddress;
    }

    /**
     * @param IPAddress the IPAddress to set
     */
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

 
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the screenZize
     */
    public Dimension getScreenZize() {
        return screenZize;
    }

    /**
     * @param screenZize the screenZize to set
     */
    public void setScreenZize(Dimension screenZize) {
        this.screenZize = screenZize;
    }
    
    
}
