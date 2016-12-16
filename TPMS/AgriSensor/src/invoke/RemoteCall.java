package invoke;

import java.io.File;
import java.io.IOException;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class RemoteCall {

	@SuppressWarnings("static-access")
	public void runScript(String ipaddress, String user_id, String sensor_id, String lat, String lon)
			throws IOException, JSchException, InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("remotecall");

		JSch jsch = new JSch();

		// change after deploying to ec2
		String currentDirectory;
		/*
		 * currentDirectory =
		 * System.getProperty("user.dir")+"\\WebContent\\KEYSENSOR.pem";
		 * 
		 */
		File file = new File(".");
		currentDirectory = file.getAbsolutePath() + "\\WebContent\\AWSKey_Sam.pem";

		// jsch.addIdentity(currentDirectory);
		// jsch.addIdentity("C:\\Users\\divya\\JAVAProject\\SensorCloud\\KEYSENSOR.pem");
		jsch.addIdentity("C:\\Users\\DELL\\Downloads\\cmpe272key.pem");
		jsch.setConfig("StrictHostKeyChecking", "no");

		// enter your own EC2 instance IP here
		Session session = jsch.getSession("windows", ipaddress, 22);

		int a = 0;

		while (a <= 10) {
			try {
				Thread.sleep(6000);
				session.connect();
				ChannelExec channel = (ChannelExec) session.openChannel("exec");
				System.out.println("remote call: " + user_id + "," + sensor_id + "," + lat + "," + lon);
				// BufferedReader in11=new BufferedReader(new
				// InputStreamReader(channel.getInputStream()));
				channel.setCommand("./sensor.sh " + user_id + " " + sensor_id + " " + lat + " " + lon + " &");
				channel.setErrStream(System.err);
				channel.connect();
				channel.disconnect();
				System.out.println("connected");
				a = 11;
			} catch (JSchException e) {
				a++;
				System.out.println("Reconnecting");

			}

		}
		session.disconnect();

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * try { runScript(null, null, null, null, null); } catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } catch
	 * (JSchException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

}
