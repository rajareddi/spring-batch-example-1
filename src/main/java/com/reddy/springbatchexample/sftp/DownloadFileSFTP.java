package com.reddy.springbatchexample.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
/**
 * 
 * @author javagists.com
 *
 */
public class DownloadFileSFTP {
 
    public static void main(String[] args) throws Exception {

        JSch jsch = new JSch();
        Session session = null;
        try {
           session = jsch.getSession("adobeteam", "ftp.csg-cc.com", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("adobeteam@123");
            session.connect();
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get("/home/sftpusers/adobeteam/workforce2/decrypt/SampleUserRecords1000_Encrypted.csv", "./testDownload.txt");  
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }

   }

}