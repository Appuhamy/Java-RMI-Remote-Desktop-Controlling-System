/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.dao.other;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lk.ilemo.viewer.dto.UserDTO;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class Generator {
    public String generateUserPassowrd(UserDTO dto) throws IOException{
        String ipAddress = dto.getIPAddress();
        String[] split = ipAddress.split("[.]");
        return split[3]+split[1]+split[2];
    }
}
