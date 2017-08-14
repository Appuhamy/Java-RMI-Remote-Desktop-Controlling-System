/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.service.other;

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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class ViewerFileWriter {

    public enum callType {
        MISSCALL, ANSWERCALL, REJECTCALL, GETCALL, ANSWERVIDEOCALL, GETVIDEOCALL, PARTNERLOGIN
    }

    public ViewerFileWriter() {
    }

    public static boolean ViewerLoggerWriter(String path, String line) throws IOException {
        String encrypting = Encryption.encrypting(line);
        //Encrypting.......
        FileWriter fileWriter = new FileWriter(path, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(encrypting);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        return true;
    }

    public static boolean UploadDetailWriter(String path, String line) throws IOException {
        String encrypting = Encryption.encrypting(line);
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bf = new BufferedWriter(fw);
        bf.write(encrypting);
        bf.newLine();
        bf.flush();
        bf.close();
        return true;
    }

    public static boolean infoFilesWriter(String path, String line, HashMap<String, byte[]> files) throws IOException {
        String encrypting = Encryption.encrypting(line);
        //Encrypting
        String driverLockerPath = "C:\\Users\\Chamindu_Appuhamy\\Desktop\\ViewerDriver";
        File driverLocker = new File(driverLockerPath);
        File[] subLockers = driverLocker.listFiles();
        for (File subLocker : subLockers) {
            String[] list = subLocker.list();
            if (list.length == 1) {
                Set<String> keySet = files.keySet();
                String infoPath = subLocker.getPath() + "\\" + "info.txt";
                File infoFile = new File(infoPath);
                if (infoFile.exists()) {
                    FileWriter writer = new FileWriter(infoFile);
                    BufferedWriter bf = new BufferedWriter(writer);
                    bf.write(encrypting);
                    bf.newLine();
                    bf.flush();
                    bf.close();
                    for (String key : keySet) {
                        byte[] get = files.get(key);
                        String subLockerpath = subLocker.getPath();
                        File savePath = new File(subLockerpath + "\\" + key);
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath));
                        bos.write(get);
                        bos.flush();
                        bos.close();

                    }
                    return true;
                }

            }
            //return false;
        }
        return false;
    }

    public static boolean callLoggerFileWriter(String path, String line, callType type) throws IOException {

        int status;
        switch (type) {
            case ANSWERCALL:
                status = 2;
                break;
            case GETCALL:
                status = 4;
                break;
            case REJECTCALL:
                status = 3;
                break;
            case MISSCALL:
                status = 1;
                break;
            case ANSWERVIDEOCALL:
                status = 5;
                break;
            case PARTNERLOGIN:
                status = 0;
                break;
            default:
                status = 6;
                break;
        }
        Date date = new Date();
        File file = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd/HH.mm.ss");
        String dateandtime = format.format(date);
        String home = System.getProperty("user.home") + "\\Desktop\\ViewerClient";
        file = new File(home);
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
            if (mkdir) {
                String encrypting = Encryption.encrypting(line + ":" + dateandtime + ":" + status);
                String subPath = home + "\\" + "callLoggers.txt";
                file = new File(subPath);
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(encrypting);
                bf.newLine();
                bf.flush();
                bf.close();
            }
        } else {
            String encrypting = Encryption.encrypting(line + ":" + dateandtime + ":" + status);
            String subPath = home + "\\" + "callLoggers.txt";
            file = new File(subPath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bf = new BufferedWriter(fw);
            bf.write(encrypting);
            bf.newLine();
            bf.flush();
            bf.close();
        }

        return true;
    }
}
