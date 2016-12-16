package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import VO.UDVO;
public class UserDAO{

	public List<UDVO> display(int i){
		List<UDVO> ls = new ArrayList<UDVO>();
		try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://system272.cjzcfzcb1use.us-west-2.rds.amazonaws.com:3306/system272?useSSL=false", "system272","system123");
				//iaasdb.chzfjyaimyou.us-west-2.rds.amazonaws.com:3306
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT s.sensor_id,t.temp,s.sensor_tag_value,s.sensor_status,max(t.time_stamp) as creation_time FROM system272.temp_data t join system272.sensor_master s on (s.sensor_id=t.sensor_id) where t.user_id="+i+" group by t.sensor_id");
				//+" group by t.sensor_id"
				while(rs.next()){
					UDVO udvo = new UDVO();
					udvo.setSensor_id(rs.getString("sensor_id"));
					System.out.println(rs.getString("sensor_id"));
					udvo.setTemp(rs.getInt("temp"));
					System.out.println(rs.getInt("temp"));
					udvo.setSensor_tag_value(rs.getString("sensor_tag_value"));
					System.out.println(rs.getString("sensor_tag_value"));
					udvo.setSensor_status(rs.getString("sensor_status"));
					System.out.println(rs.getString("sensor_status"));
					ls.add(udvo);
				}
		}
		catch(Exception e){
				e.printStackTrace();
		}
		return ls;		
	}

	
public List<UDVO> display1(int i){
	List<UDVO> ls = new ArrayList<UDVO>();
	try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://system272.cjzcfzcb1use.us-west-2.rds.amazonaws.com:3306/system272?useSSL=false", "system272","system123");
			//iaasdb.chzfjyaimyou.us-west-2.rds.amazonaws.com:3306
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT s.sensor_id,t.temp,s.sensor_tag_value,s.sensor_status,t.time_stamp as creation_time FROM system272.temp_data t join system272.sensor_master s on (s.sensor_id=t.sensor_id) where t.user_id="+i+"");
			//+" group by t.sensor_id"
			while(rs.next()){
				UDVO udvo = new UDVO();
				udvo.setSensor_id(rs.getString("sensor_id"));
				System.out.println(rs.getString("sensor_id"));
				udvo.setTemp(rs.getInt("temp"));
				System.out.println(rs.getInt("temp"));
				udvo.setSensor_tag_value(rs.getString("sensor_tag_value"));
				System.out.println(rs.getString("sensor_tag_value"));
				udvo.setSensor_status(rs.getString("sensor_status"));
				System.out.println(rs.getString("sensor_status"));
				ls.add(udvo);
			}
	}
	catch(Exception e){
			e.printStackTrace();
	}
	return ls;		
}
}
		
	



