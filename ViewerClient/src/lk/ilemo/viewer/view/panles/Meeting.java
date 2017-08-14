/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.view.panles;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lk.ilemo.viewer.view.DriverDownloader;
import lk.ilemo.viewer.view.DriverUploader;
import lk.ilemo.viewer.view.other.Encryption;
import lk.ilemo.viewer.view.other.SuraTable;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class Meeting extends javax.swing.JPanel {

    private enum LogType {
        CALLLOG, VIDEOCALLLOG, PARTNERLOG
    }
    private SuraTable suraTable;
    private SuraTable suraTabel2;
    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;

    /**
     * Creates new form Meeting
     */
    public Meeting() throws IOException {
        initComponents();
        suraTable = new SuraTable(tbl);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            suraTable.setHeaderAlignment(i, SwingConstants.CENTER);
        }
        suraTabel2 = new SuraTable(logsDetailTbl);
        for (int i = 0; i < logsDetailTbl.getColumnCount(); i++) {
            suraTabel2.setHeaderAlignment(i, SwingConstants.CENTER);
        }
        tbl.setFillsViewportHeight(true);
        logsDetailTbl.setFillsViewportHeight(true);
        tbl.setBorder(new MatteBorder(0, 1, 0, 1, tbl.getBackground()));
        logsDetailTbl.setBorder(new MatteBorder(0, 1, 0, 1, logsDetailTbl.getBackground()));
        dtm = (DefaultTableModel) tbl.getModel();
        dtm2 = (DefaultTableModel) logsDetailTbl.getModel();
        String home = System.getProperty("user.home") + "\\Desktop\\ViewerClient";
        File file = new File(home);
        if (file.exists()) {
            file = new File(home + "\\" + "UploadDetails.txt");
            if (file.exists()) {
                List<String> readAllLines = Files.readAllLines(Paths.get(home + "\\" + "UploadDetails.txt"));
                if (readAllLines.isEmpty()) {
                    Object[] rw = {"Nope", "Nope"};
                    dtm.addRow(rw);
                } else {
                    for (String line : readAllLines) {
                        if (line.isEmpty()) {
                            //String[] split = line.split("/");
                            Object[] rw = {"Nope", "Nope", "Nope"};
                            dtm.addRow(rw);
                        } else {
                            tbl.removeAll();
                            String decrypting = Encryption.decrypting(line);
                            String[] split = decrypting.split("/");
                            Object[] rw = {split[2], split[0], split[4] + "/" + split[5]};
                            dtm.addRow(rw);
                        }
                    }
                }
            } else {
                Object[] rw = {"Nope", "Nope"};
                dtm.addRow(rw);
            }
        }

        setLogsDetailTable(LogType.CALLLOG);
    }

    private void setLogsDetailTable(LogType type) throws IOException {
        try {
            List<String> readAllLines = Files.readAllLines(Paths.get(System.getProperty("user.home") + "\\Desktop\\ViewerClient\\callLoggers.txt"));
            ArrayList<String> allLines = new ArrayList<>();
            ArrayList<String> list = new ArrayList<>();
            if (readAllLines.isEmpty()) {
                return;
            } else {
                for (String line : readAllLines) {
                    String decrypting = Encryption.decrypting(line);
                    allLines.add(decrypting);
                }
            }

            switch (type) {
                case CALLLOG:
                    callLogPnl.setBackground(new java.awt.Color(153, 153, 153));
                    VideCallPnl.setBackground(new java.awt.Color(51, 158, 231));
                    PartnerLogPnl.setBackground(new java.awt.Color(51, 158, 231));
                    for (String line : allLines) {
                        String[] split = line.split(":");
                        if (split[2].equals("1") || split[2].equals("2") || split[2].equals("3") || split[2].equals("4")) {
                            list.add(line);
                        }
                    }
                    ;
                    break;
                case VIDEOCALLLOG:
                    callLogPnl.setBackground(new java.awt.Color(51, 158, 231));
                    VideCallPnl.setBackground(new java.awt.Color(153, 153, 153));
                    PartnerLogPnl.setBackground(new java.awt.Color(51, 158, 231));
                    for (String line : allLines) {
                        String[] split = line.split(":");
                        if (split[2].equals("5") || split[2].equals("6")) {
                            list.add(line);
                        }
                    }
                    ;
                    break;
                case PARTNERLOG:
                    callLogPnl.setBackground(new java.awt.Color(51, 158, 231));
                    VideCallPnl.setBackground(new java.awt.Color(51, 158, 231));
                    PartnerLogPnl.setBackground(new java.awt.Color(153, 153, 153));
                    for (String line : allLines) {
                        String[] split = line.split(":");
                        if (split[2].equals("0")) {
                            list.add(line);
                        }
                    }
                    ;
                    break;
            }
            logsDetailTbl.removeAll();
            dtm2.setRowCount(0);
            for (String line : list) {
                String[] split = line.split(":");
                if (split[2].equals("1")) {
                    Object[] rw = {split[0], split[1], "Miss Call.."};
                    dtm2.addRow(rw);
                }
                if (split[2].equals("2")) {
                    Object[] rw = {split[0], split[1], "Answered.."};
                    dtm2.addRow(rw);
                }
                if (split[2].equals("3")) {
                    Object[] rw = {split[0], split[1], "Rejected..."};
                    dtm2.addRow(rw);
                }
                if (split[2].equals("4")) {
                    Object[] rw = {split[0], split[1], "Got.."};
                    dtm2.addRow(rw);
                }
                if (split[2].equals("5")) {
                    Object[] rw = {split[0], split[1], "Video Answered.."};
                    dtm2.addRow(rw);
                }
                if (split[2].equals("6")) {
                    Object[] rw = {split[0], split[1], "Video Rejected..."};
                    dtm2.addRow(rw);
                }
            }
            list.clear();
        } catch (NoSuchFileException e) {
            File file = new File(System.getProperty("user.home") + "\\Desktop\\ViewerClient\\callLoggers.txt");
            if (!file.exists()) {
                new File(System.getProperty("user.home") + "\\Desktop\\ViewerClient").mkdir();
                file.createNewFile();
            }
        }
//       logsDetailTbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                ArrayList<Integer> missCalls = missCalls();
//                ArrayList<Integer> answerCalls = answerCalls();
//                ArrayList<Integer> rejectCalls = rejectCalls();
//                ArrayList<Integer> getcalls = getCalls();
//                ArrayList<Integer> answerVideoCalls = answerVideoCalls();
//                ArrayList<Integer> getvideoCalls = getVideoCalls();
//                ArrayList<Integer> partnerLogs = partnerLogs();
//                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
//
////                if (!isSelected) {
////                    c.setBackground(Color.white);
////                }
//                if (!missCalls.isEmpty()) {
//                    for (Object i : missCalls) {
//                        if (!missCalls.isEmpty()) {
//                            if (row == Integer.parseInt(i.toString())) {
//                                c.setBackground(new java.awt.Color(255, 0, 100));
//
//                            } else {
//                                //c.setBackground(Color.green);
//                            }
//                        }
//
//                    }
//                }
//                if (!answerCalls.isEmpty()) {
//                    for (Object i : answerCalls) {
//                        if (!answerCalls.isEmpty()) {
//                            if (row == Integer.parseInt(i.toString())) {
//                                c.setBackground(new java.awt.Color(51, 158, 231));
//
//                            } else {
//                                //c.setBackground(Color.green);
//                            }
//                        }
//                    }
//                }
//                if (!rejectCalls.isEmpty()) {
//                    for (Object i : rejectCalls) {
//                        if (!rejectCalls.isEmpty()) {
//                            if (row == Integer.parseInt(i.toString())) {
//                                c.setBackground(new java.awt.Color(255, 0, 51));
//
//                            } else {
//                                //c.setBackground(Color.green);
//                            }
//                        }
//                    }
//                }
//                if (!getcalls.isEmpty()) {
//                    for (Object i : getcalls) {
//                        if (!getcalls.isEmpty()) {
//                            if (row == Integer.parseInt(i.toString())) {
//                                c.setBackground(new java.awt.Color(50, 255, 110));
//
//                            } else {
//                                //c.setBackground(Color.green);
//                            }
//                        }
//                    }
//                }
//                if (!answerVideoCalls.isEmpty()) {
//                    for (Object i : answerVideoCalls) {
//                        if (!answerVideoCalls.isEmpty()) {
//                            if (row == Integer.parseInt(i.toString())) {
//                                c.setBackground(new java.awt.Color(51, 158, 231));
//
//                            } else {
//                                //c.setBackground(Color.green);
//                            }
//                        }
//                    }
//                }
//                if (!getvideoCalls.isEmpty()) {
//                    for (Object i : getvideoCalls) {
//                        if (!getvideoCalls.isEmpty()) {
//                            if (row == Integer.parseInt(i.toString())) {
//                                c.setBackground(new java.awt.Color(51, 255, 110));
//
//                            } else {
//                                //c.setBackground(Color.green);
//                            }
//                        }
//                    }
//                }
//
//                if (!partnerLogs.isEmpty()) {
//                    for (Object i : partnerLogs) {
//                        if (!partnerLogs.isEmpty()) {
//                            if (row == Integer.parseInt(i.toString())) {
//                                c.setBackground(new java.awt.Color(255, 155, 0));
//
//                            } else {
//                                //c.setBackground(Color.green);
//                            }
//                        }
//                    }
//                }
//                return c;
//
//            }
//
//        });
    }

    private ArrayList<Integer> missCalls() {
        ArrayList<Integer> chrows = new ArrayList<>();
        for (int i = 0; i < logsDetailTbl.getRowCount(); i++) {
            String res = logsDetailTbl.getValueAt(i, 2).toString();
            if (res.equals("1")) {
                chrows.add(i);
            }
        }
        return chrows;
    }

    private ArrayList<Integer> answerCalls() {
        ArrayList<Integer> chrows = new ArrayList<>();
        for (int i = 0; i < logsDetailTbl.getRowCount(); i++) {
            String res = logsDetailTbl.getValueAt(i, 2).toString();
            if (res.equals("2")) {
                chrows.add(i);
            }
        }
        return chrows;
    }

    private ArrayList<Integer> rejectCalls() {
        ArrayList<Integer> chrows = new ArrayList<>();
        for (int i = 0; i < logsDetailTbl.getRowCount(); i++) {
            String res = logsDetailTbl.getValueAt(i, 2).toString();
            if (res.equals("3")) {
                chrows.add(i);
            }
        }
        return chrows;
    }

    private ArrayList<Integer> getCalls() {
        ArrayList<Integer> chrows = new ArrayList<>();
        for (int i = 0; i < logsDetailTbl.getRowCount(); i++) {
            String res = logsDetailTbl.getValueAt(i, 2).toString();
            if (res.equals("4")) {
                chrows.add(i);
            }
        }
        return chrows;
    }

    private ArrayList<Integer> answerVideoCalls() {
        ArrayList<Integer> chrows = new ArrayList<>();
        for (int i = 0; i < logsDetailTbl.getRowCount(); i++) {
            String res = logsDetailTbl.getValueAt(i, 2).toString();
            if (res.equals("5")) {
                chrows.add(i);
            }
        }
        return chrows;
    }

    private ArrayList<Integer> getVideoCalls() {
        ArrayList<Integer> chrows = new ArrayList<>();
        for (int i = 0; i < logsDetailTbl.getRowCount(); i++) {
            String res = logsDetailTbl.getValueAt(i, 2).toString();
            if (res.equals("6")) {
                chrows.add(i);
            }
        }
        return chrows;
    }

    private ArrayList<Integer> partnerLogs() {
        ArrayList<Integer> chrows = new ArrayList<>();
        for (int i = 0; i < logsDetailTbl.getRowCount(); i++) {
            String res = logsDetailTbl.getValueAt(i, 2).toString();
            if (res.equals("0")) {
                chrows.add(i);
            }
        }
        return chrows;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        driverDownloader = new javax.swing.JLabel();
        driverUplaoder = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        logsDetailTbl = new javax.swing.JTable();
        VideCallPnl = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        callLogPnl = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PartnerLogPnl = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        driverDownloader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/driverDownloder.png"))); // NOI18N
        driverDownloader.setText("Viewer Downloader");
        driverDownloader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                driverDownloaderMouseClicked(evt);
            }
        });

        driverUplaoder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/upload_to_cloud.png"))); // NOI18N
        driverUplaoder.setText("Viewer Uploader");
        driverUplaoder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                driverUplaoderMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("My Upload Details");

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserId", "LockerId", "Date & Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl.setRowHeight(40);
        jScrollPane2.setViewportView(tbl);

        logsDetailTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Caller Name", "Date & Time", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        logsDetailTbl.setRowHeight(40);
        jScrollPane3.setViewportView(logsDetailTbl);

        VideCallPnl.setBackground(new java.awt.Color(51, 158, 231));
        VideCallPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VideCallPnlMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Video Call Logs");

        javax.swing.GroupLayout VideCallPnlLayout = new javax.swing.GroupLayout(VideCallPnl);
        VideCallPnl.setLayout(VideCallPnlLayout);
        VideCallPnlLayout.setHorizontalGroup(
            VideCallPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VideCallPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        VideCallPnlLayout.setVerticalGroup(
            VideCallPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VideCallPnlLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        callLogPnl.setBackground(new java.awt.Color(51, 158, 231));
        callLogPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                callLogPnlMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Call Logs");

        javax.swing.GroupLayout callLogPnlLayout = new javax.swing.GroupLayout(callLogPnl);
        callLogPnl.setLayout(callLogPnlLayout);
        callLogPnlLayout.setHorizontalGroup(
            callLogPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(callLogPnlLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        callLogPnlLayout.setVerticalGroup(
            callLogPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, callLogPnlLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        PartnerLogPnl.setBackground(new java.awt.Color(51, 158, 231));
        PartnerLogPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PartnerLogPnlMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Partner Logs");

        javax.swing.GroupLayout PartnerLogPnlLayout = new javax.swing.GroupLayout(PartnerLogPnl);
        PartnerLogPnl.setLayout(PartnerLogPnlLayout);
        PartnerLogPnlLayout.setHorizontalGroup(
            PartnerLogPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PartnerLogPnlLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel4)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        PartnerLogPnlLayout.setVerticalGroup(
            PartnerLogPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PartnerLogPnlLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(driverUplaoder, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(74, 74, 74)
                            .addComponent(driverDownloader, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(callLogPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(VideCallPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PartnerLogPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(driverDownloader, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(driverUplaoder, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VideCallPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(callLogPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PartnerLogPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void driverDownloaderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driverDownloaderMouseClicked
        DriverDownloader dd = new DriverDownloader();
        dd.setVisible(true);
    }//GEN-LAST:event_driverDownloaderMouseClicked

    private void driverUplaoderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driverUplaoderMouseClicked
        DriverUploader du = new DriverUploader();
        du.setVisible(true);
    }//GEN-LAST:event_driverUplaoderMouseClicked

    private void VideCallPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VideCallPnlMouseClicked
        logsDetailTbl.removeAll();
        try {
            setLogsDetailTable(LogType.VIDEOCALLLOG);
        } catch (IOException ex) {
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_VideCallPnlMouseClicked

    private void PartnerLogPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PartnerLogPnlMouseClicked
        logsDetailTbl.removeAll();
        try {
            setLogsDetailTable(LogType.PARTNERLOG);
        } catch (IOException ex) {
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PartnerLogPnlMouseClicked

    private void callLogPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_callLogPnlMouseClicked
        logsDetailTbl.removeAll();
        try {
            setLogsDetailTable(LogType.CALLLOG);
        } catch (IOException ex) {
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_callLogPnlMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PartnerLogPnl;
    private javax.swing.JPanel VideCallPnl;
    private javax.swing.JPanel callLogPnl;
    private javax.swing.JLabel driverDownloader;
    private javax.swing.JLabel driverUplaoder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable logsDetailTbl;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
