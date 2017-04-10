package org.cron;
import java.io.InputStream;
import java.util.Scanner;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class SSHConnApp {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the host");
	    String host=in.nextLine();
	    System.out.println("Enter the ssh user");
	    String user=in.nextLine();
	    System.out.println("Enter the password");
	    String password=in.nextLine();
	    System.out.println("Enter the command");
	    String command1=in.nextLine();//"ls -ltr";
	    try{
	    	
	    	java.util.Properties config = new java.util.Properties(); 
	    	//config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	//session.setConfig(config);
	    	session.connect();
	    	System.out.println("Connected");
	    	
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command1);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream inn=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(inn.available()>0){
	            int i=inn.read(tmp, 0, 1024);
	            if(i<0)break;
	            System.out.print(new String(tmp, 0, i));
	          }
	          if(channel.isClosed()){
	            System.out.println("exit-status: "+channel.getExitStatus());
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
	        session.disconnect();
	        System.out.println("DONE");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }

	}

}