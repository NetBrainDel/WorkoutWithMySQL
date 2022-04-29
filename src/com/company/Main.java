package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/mysql";
        String user = "*";
        String password = "*";

        try {
            Connection connection = DriverManager
                     .getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("use overone107");
            ResultSet resultSet = statement.executeQuery("select * from product where id>=1");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+" "+
                        resultSet.getString("name"));
            }
            Scanner scanner = new Scanner(System.in);

//            String name = scanner.nextLine();
//            int cost = scanner.nextInt(), weight = scanner.nextInt();
//            statement.executeUpdate("insert product(name, cost, weight) values " +
//                    "('"+name+"', "+
//                    cost+", "+
//                    weight+");");
            String x = scanner.nextLine();
          /*Можно взломать*/
//            resultSet = statement.executeQuery("select * from product where " +
//                    "name = '"+x+"';");
          /*Можно взломать*/
        /*Защита от взлома*/
            PreparedStatement st = connection.prepareStatement("select *"+
                    "from product where name = ?");
            st.setString(1,x);
            resultSet = st.executeQuery();
        /*Защита от взлома*/
            while (resultSet.next())
                System.out.println(resultSet.getInt("id")+" "+
                        resultSet.getString("name"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
