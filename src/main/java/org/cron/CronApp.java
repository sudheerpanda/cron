package org.cron;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class CronApp {
	public static void main(String[] args) throws IOException {

		System.out
				.println("=======================================================");
		System.out
				.println("Enter the ssh command to connect remote machine         :");
		Scanner in = new Scanner(System.in);
		System.out
				.println("********************Conecting to the host**************");
		String host = in.nextLine();
		try {
			Process process = Runtime.getRuntime().exec(host);
			PrintStream out=new PrintStream(process.getOutputStream());
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
			System.out.println(bufferedReader.readLine()+"************");
			//out.println("ls -l /home/me");
			while(bufferedReader.ready()){
				String s=bufferedReader.readLine();
				System.out.println(s);
			}
			//out.print("exit");
		} catch (IOException e) {

			System.out.println("Error while connecting to host     :" + host);
			throw e;
		}

	}
}
