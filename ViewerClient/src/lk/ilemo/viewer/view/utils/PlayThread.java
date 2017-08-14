package lk.ilemo.viewer.view.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.SourceDataLine;
import lk.ilemo.viewer.view.ViewerVideoChatController;
import lk.ilemo.viewer.view.ViewerVideoChatServer;
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
public class PlayThread extends Thread {

    public DatagramSocket din;
    public SourceDataLine audio_out;
    public byte[] buffer = new byte[512];
    public static boolean Server_Voice = false;
    public ViewerVoiceChatController VChat = null;
    public ViewerVoiceChatServer VchatServer = null;
    
    public ViewerVideoChatController VideoController=null;
    public ViewerVideoChatServer VideoServer=null;

    @Override
    public void run() {
        int i = 0;
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        while (Server_Voice) {
            try {
                System.out.println("Voice Data jdakfjdkjfkldj fjsdaklfjkldjafkljdf jsdfjlksdj " + incoming.getLength());
                if (incoming.getLength() != 0) {
                    System.out.println("Length:"+incoming.getLength());
                    din.receive(incoming);
                    buffer = incoming.getData();
                    audio_out.write(buffer, 0, buffer.length);
                    System.out.println(din.getRemoteSocketAddress());
                    System.out.println("#" + i++);
                } else if (incoming.getLength() == 0) {
                    if (VChat != null) {
                        VChat.disposeMe();
                        VchatServer.Signal=2;
                    } else if(VchatServer!=null) {
                        VchatServer.disposeMe();
                    }else if(VideoController!=null){
                        VideoController.disposeMe();
                    }else if(VideoServer!=null){
                        VideoServer.disposeMe();
                        //VideoServer.ThreadSignal=0;
                        
                    }
                    
                    Server_Voice = false;
                }
            } catch (IOException ex) {
                Logger.getLogger(PlayThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (VideoServer != null){
            VideoServer.disposeMe();
            
            System.gc();
        }
        din.close();
        //din.disconnect();
        audio_out.stop();
        audio_out.close();
        audio_out.drain();
        System.out.println("Stop..");
    }
}
