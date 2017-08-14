/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.dto;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class ConnectDetailDTO extends SuperDTO{
    private String myId;
    private String myIPAddress;
    private String myPassword;
    private String partnerId;
    private String partnerPassword;

    public ConnectDetailDTO() {
    }

    public ConnectDetailDTO(String myId, String myIPAddress, String myPassword, String partnerId, String partnerPassword) {
        this.myId = myId;
        this.myIPAddress = myIPAddress;
        this.myPassword = myPassword;
        this.partnerId = partnerId;
        this.partnerPassword = partnerPassword;
    }

   

    /**
     * @return the myId
     */
    public String getMyId() {
        return myId;
    }

    /**
     * @param myId the myId to set
     */
    public void setMyId(String myId) {
        this.myId = myId;
    }

    /**
     * @return the myPassword
     */
    public String getMyPassword() {
        return myPassword;
    }

    /**
     * @param myPassword the myPassword to set
     */
    public void setMyPassword(String myPassword) {
        this.myPassword = myPassword;
    }

    /**
     * @return the partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId the partnerId to set
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * @return the partnerPassword
     */
    public String getPartnerPassword() {
        return partnerPassword;
    }

    /**
     * @param partnerPassword the partnerPassword to set
     */
    public void setPartnerPassword(String partnerPassword) {
        this.partnerPassword = partnerPassword;
    }

    /**
     * @return the myIPAddress
     */
    public String getMyIPAddress() {
        return myIPAddress;
    }

    /**
     * @param myIPAddress the myIPAddress to set
     */
    public void setMyIPAddress(String myIPAddress) {
        this.myIPAddress = myIPAddress;
    }
    
}
