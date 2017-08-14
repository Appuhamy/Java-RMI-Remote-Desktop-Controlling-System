/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.dao.custom.impl;

import java.awt.AWTException;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLSocket;
import lk.ilemo.viewer.dao.custom.UserDAO;
import lk.ilemo.viewer.dao.other.Generator;
import lk.ilemo.viewer.dto.ConnectDetailDTO;
import lk.ilemo.viewer.dto.UserDTO;
import lk.ilemo.viewer.observer.UserObserver;
import lk.ilemo.viewer.other.Encryption;
import lk.ilemo.viewer.service.custom.Broker;
import lk.ilemo.viewer.service.other.ViewerFileWriter;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class UserDAOImpl implements UserDAO {

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private UserDTO dto = null;
    public static HashMap<String, UserObserver> observerList = new HashMap<>();

    @Override
    public String loginToViewerServer(UserDTO t, UserObserver observer) throws RemoteException, IOException {
        List<String> readAllLines = Files.readAllLines(Paths.get("ViewerLoggers.txt"));
        for (String readAllLine : readAllLines) {
            String decrypting = Encryption.decrypting(readAllLine);
            String[] split = decrypting.split(":");
            //System.out.println(split.length);
            if (split[0].equals(t.getUserComputerName()) && split[1].equals(t.getIPAddress())) {
                //System.out.println("Im Already In..");
                dto = new UserDTO(t.getUserComputerName(), t.getIPAddress(), split[2]);
                observerList.put(split[2], observer);
                return split[2];
            }
        }
        Generator generator = new Generator();//Generate UserPassword
        String generateUserPassowrd = generator.generateUserPassowrd(t);
        String line=t.getUserComputerName() + ":" + t.getIPAddress() + ":" + generateUserPassowrd + ":" + t.getScreenZize().width + ":" + t.getScreenZize().height;
        
        boolean ViewerLoggerWriter = ViewerFileWriter.ViewerLoggerWriter("ViewerLoggers.txt", line);
        
        dto = new UserDTO(t.getUserComputerName(), t.getIPAddress(), generateUserPassowrd);
        observerList.put(generateUserPassowrd, observer);
        return generateUserPassowrd;
    }

    @Override
    public UserDTO getLoginDetails() throws RemoteException {
        return dto;//Return Login Details
    }

    @Override
    public Broker connectToPartner(ConnectDetailDTO t) throws RemoteException, IOException {
        //System.out.println("DaoConnect");
        List<String> readAllLines = Files.readAllLines(Paths.get("ViewerLoggers.txt"));
        //System.out.println(readAllLines);
        if (readAllLines.isEmpty()) {
            return null;
        }
        for (String readAllLine : readAllLines) {
            String decrypting = Encryption.decrypting(readAllLine);
            //Decrypting
            String[] split = decrypting.split(":");
            if (split[0].equals(t.getPartnerId()) && split[2].equals(t.getPartnerPassword())) {//Compare Partner id is Exists
                //System.out.println("Im in Broker Field");
                Dimension partnerScreensize = new Dimension(Integer.parseInt(split[3]), Integer.parseInt(split[4]));
                String[] parts = split[1].split("/");
                UserDTO partnerDto = new UserDTO(split[0], parts[1], partnerScreensize, t.getPartnerPassword());
                //if(split[0].equals(t.getMyId()) && split[2].equals(t.getMyPassword())){
                Dimension myScreensize = new Dimension(Integer.parseInt(split[3]), Integer.parseInt(split[4]));
                UserDTO myDto = new UserDTO(t.getMyId(), t.getMyIPAddress(), myScreensize, t.getMyPassword());
                Broker b = new Broker(partnerDto, myDto);
                UserObserver get = observerList.get(t.getMyPassword());
                b.registerObserver(get);//Regsiter Controlller Client to the broker
                get = observerList.get(t.getPartnerPassword());
                b.registerObserver(get);//Register Server Client to the Broker
                //System.out.println(observerList.keySet());
                boolean containsKey = observerList.containsKey(t.getPartnerPassword());//Check the partner is Exists
                //System.out.println(""+containsKey);
                //System.out.println(""+b.getControllerClientDto()+" "+b.getServerClientDto());
                // System.out.println("Broker");
                return b;//Return the broker to the Server Client and Controller Client
                //}
            }
        }
        //System.out.println("return null");
        return null;
    }

    @Override
    public boolean driverUpload(HashMap<String, byte[]> files, String details) throws RemoteException, AWTException, IOException {
        return ViewerFileWriter.infoFilesWriter("", details, files);
    }

    @Override
    public HashMap<String, byte[]> driverDownload(String details) throws RemoteException, AWTException, IOException {
        HashMap<String, byte[]> files = new HashMap<>();
        String[] detailSplit = details.split("/");
        String lockerPath = "C:\\Users\\Chamindu_Appuhamy\\Desktop\\ViewerDriver";
        File locker = new File(lockerPath);
        String[] subLockers = locker.list();
        for (String subLocker : subLockers) {
            String path = lockerPath + "\\" + subLocker + "\\" + "info.txt";
            List<String> readAllLines = Files.readAllLines(Paths.get(path));
            for (String line : readAllLines) {
                if (!line.isEmpty()) {
                    String decrypting = Encryption.decrypting(line);
                    String[] lineSplit = decrypting.split("/");
                    if (lineSplit[0].equals(detailSplit[0]) && lineSplit[2].equals(detailSplit[2])) {
                        File tempSubLocker = new File(lockerPath + "\\" + subLocker);
                        File[] listFiles = tempSubLocker.listFiles();
                        for (File file : listFiles) {
                            BufferedInputStream input = null;
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            byte[] buffer = new byte[(int) file.length()];
                            input = new BufferedInputStream(new FileInputStream(file));
                            input.read(buffer, 0, buffer.length);
                            input.close();
                            files.put(file.getName(), buffer);
                            if (lineSplit[3].equals("1")) {
                                String tempPath = lockerPath + "\\" + subLocker;
                                File tempFile = new File(tempPath);
                                boolean delete = file.delete();
                                if (delete) {
                                    tempFile = new File(lockerPath + "\\" + subLocker);
                                    boolean mkdir1 = tempFile.mkdir();
                                    //if(mkdir1){
                                    tempFile = new File(tempFile.getPath() + "\\" + "info.txt");
                                    boolean createNewFile = tempFile.createNewFile();
                                    if (createNewFile) {
                                        System.out.println("File Created..");
                                    } else {
                                        System.out.println("File not created..");
                                    }
                                    //}else{
                                    //  System.out.println("Folder not created..");
                                    //} 
                                } else {
                                    System.out.println("Folder not Deleted..");
                                }
                            } else {
                                int parseInt = Integer.parseInt(lineSplit[3]);
                                String encrypting = Encryption.encrypting(new String(lineSplit[0] + "/" + lineSplit[1] + "/" + lineSplit[2] + "/" + (parseInt - 1)));
                                File updateFile = new File(lockerPath + "\\" + subLocker + "\\" + "info.txt");
                                PrintWriter pw = new PrintWriter(file);
                                pw.write(encrypting);
                                pw.flush();
                                pw.close();
                            }
                            //return files;
                        }
                        return files;
                    }
                    //return files;
                }
                //return files;
            }
            //return files;
        }
        return null;
    }

}
