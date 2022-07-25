package com.huawei.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
 * JDBC工具类
 * */
public class JDBCUtils {
    //创建全局属性
    private static String className;
    private static String url;
    private static String username;
    private static String password;

    static {
        //加载配置文件，获取连接信息
        Properties properties = new Properties();
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
            //读取配置文件，获取信息
            properties.load(in);
            //根据key获取value
            className = properties.getProperty("className");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        //加载驱动程序
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接对象
    public static Connection getConn() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接错误！");
        }
    }

    //关闭连接，释放资源
    public static void closeAll(Connection conn, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //方法重载
    public static void closeAll(Connection conn, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static ResultSet executeQuery(String preparedSql, Object... param) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        /*处理SQL，执行SQL*/
        try {
            conn = getConn();//得到数据库连接
            pstmt = conn.prepareStatement(preparedSql);//得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]);//为预编译sql设置参数
                }
            }
            res = pstmt.executeQuery();//执行SQL语句
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAll(conn, pstmt, res);
        }
        return res;
    }

    public static int executeUpdate(String preparedSql, Object... param) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int key = 0;//主键
        /*处理SQL，执行SQL*/
        try {
            conn = getConn();//得到数据库连接
            //注意：MySQL5.1.7需要显式添加一个参数Statement.RETURN_GENERATED_KEYS
            pstmt = conn.prepareStatement(preparedSql, Statement.RETURN_GENERATED_KEYS);//得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]);//为预编译SQL设置参数
                }
            }
            pstmt.executeUpdate();//执行SQL语句
            ResultSet resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getInt(1);//获取主键返回
            }
        } catch (SQLException e) {
            e.printStackTrace();//处理异常
        } finally {
            JDBCUtils.closeAll(conn, pstmt);
        }
        return key;
    }
}
