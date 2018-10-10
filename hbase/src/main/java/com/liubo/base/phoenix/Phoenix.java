package com.liubo.base.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Phoenix {
    public static void main(String[] args) throws Throwable {
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");

            String url = "jdbc:phoenix:cdh-node1,cdh-node2,cdh-node3:2181";
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            long time = System.currentTimeMillis();
//            String sql = "select *  from face_table where ROW =" + "'" + "0f1b4cf4c8d83fedcf8980b80f544a47" + "'";
            String sql = "select *  from " + "\"" + "face_table" + "\"" + "where " + "\"" + "ROW" + "\"" + " = " + "'" + "0f1b4cf4c8d83fedcf8980b80f544a47" + "'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ROW = " + rs.getString("ROW"));
                System.out.println("access-control = " + rs.getString("access-control"));
                System.out.println("cameraID = " + rs.getString("cameraID"));
                System.out.println("faceData = " + rs.getString("faceData"));
                System.out.println("faceImage = " + rs.getString("faceImage"));
                System.out.println("frameImage = " + rs.getString("frameImage"));
                System.out.println("id = " + rs.getString("id"));
                System.out.println("lat = " + rs.getString("lat"));
                System.out.println("lon = " + rs.getString("lon"));
                System.out.println("matcherThreshold = " + rs.getString("matcherThreshold"));
            }

            long timeUsed = System.currentTimeMillis() - time;

            System.out.println("time " + timeUsed + "mm");

            // 关闭连接
            rs.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
