package lk.ilemo.viewer.view.utils;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.TargetDataLine;
import lk.ilemo.viewer.view.ViewerVoiceChatController;
import lk.ilemo.viewer.view.ViewerVoiceChatServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Chamindu_Appuhamy
 */
public class RecordThread extends Thread {

    public TargetDataLine audio_in = null;
    public DatagramSocket dout;
    public byte byte_buffer[] = new byte[512];
    public InetAddress server_ip;
    public int server_port;
    public static boolean Client_Voice=false;
    private ViewerVoiceChatController Vchat=null;
    private ViewerVoiceChatServer VchatServer=null;
    @Override
    public void run() {
        int i = 0;
        while (Client_Voice) {
            audio_in.read(byte_buffer, 0, byte_buffer.length);
            DatagramPacket data = new DatagramPacket(byte_buffer, byte_buffer.length, server_ip, server_port);
            System.out.println("Send#" + i++);
            try {
                dout.send(data);
            } catch (IOException ex) {
                Logger.getLogger(RecordThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        dout.close();
        //dout.disconnect();
        audio_in.stop();
        audio_in.close();
        audio_in.drain();
        System.out.println("Thread Stop..");
    }
}
