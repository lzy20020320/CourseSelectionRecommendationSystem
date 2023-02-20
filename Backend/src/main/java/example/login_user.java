package example;

import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class login_user {
    private String name;  //为简化，使用id
    private String password;
    private String data_passward="12345";
    private int number;

    public login_user(String n,String p){
        name=n;
        password=p;
        number=0;
    }

    public String login(){
        Connection con = null;  // 定义Connection对象用于链接数据库
        Statement s;  // 定义Statement对象用于执行SQL语句
        String res = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 链接数据库java_hs_database
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_recommendations?severTimezone=GMT",
                    "root", data_passward);
            s = con.createStatement();

            // 查询操作，查询是否存在此用户
            // 需要执行的SQL语句用string对象保存
            String sql = "SELECT user_id,password" +
                    " FROM user WHERE user_id=?;";
            PreparedStatement psql= con.prepareStatement(sql);
            psql.setString(1,name);
            ResultSet r = psql.executeQuery();
            String us_id ;
            if(r.next()){
                us_id = r.getString("user_id");
                System.out.println(us_id);
                String pswd;
                pswd = r.getString("password");
                if(Objects.equals(pswd, password)){
                    res = "登录成功！";
                }
                else{
                    res = "密码错误，请重试！";
                }
            }
            else res = "账户不存在，请注册";

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(con!=null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                con=null;
            }
        }
        return res;
    }

    public String Registration(){
        Connection con = null;  // 定义Connection对象用于链接数据库
        Statement s;  // 定义Statement对象用于执行SQL语句
        String res = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 链接数据库java_hs_database
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_recommendations?severTimezone=GMT",
                    "root", data_passward);
            s = con.createStatement();

            //查询操作，判断是否重名
            String sql = "SELECT user_id,password" +
                    " FROM user WHERE user_id=?;";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet r = ps.executeQuery();
            String us_id ;
            if(r.next()){
                res = "账户名重复，请重新注册";
            }
            else {
                // 数据插入,用于用户注册
                PreparedStatement psql;
                psql = con.prepareStatement( "insert into user(user_id, password, number) values (?,?,?)" );
                int x =0;
                psql.setString( 1 ,name );
                psql.setString( 2 , password );
                psql.setInt( 3 ,x);
                psql.executeUpdate();
                res = "注册成功，请登录";
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(con!=null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                con=null;
            }
        }
        return res;
    }

    public List<JSONObject> Recommend_c(){
        Connection con = null;  // 定义Connection对象用于链接数据库
        Statement s;  // 定义Statement对象用于执行SQL语句
        List<JSONObject> list = new LinkedList<JSONObject>();   //实现传表
        List<User> users = new ArrayList<>();
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 链接数据库java_hs_database
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course_recommendations?severTimezone=GMT",
                    "root", data_passward);
            s = con.createStatement();

            // 查询操作，按number降序输出
            // 需要执行的SQL语句用string对象保存
            String sql = "SELECT user_id" +
                    " FROM student";
            PreparedStatement psql= con.prepareStatement(sql);
            ResultSet r = psql.executeQuery();
            String user_id ;
            int num;
            while(r.next()){
                user_id = r.getString("user_id");
                User now_user = new User(user_id);
                String sql2 = "SELECT c_id,appraise" +
                        " FROM course_selection WHERE s_id=?";
                PreparedStatement psql2= con.prepareStatement(sql2);
                psql2.setString(1,user_id);
                ResultSet r2 = psql2.executeQuery();
                String c_name;
                int features;
                while(r2.next()){
                    c_name = r2.getNString("c_id");
                    features = r2.getInt("appraise");
                    now_user.set(c_name,features);
                }
                users.add(now_user);
            }
            Recommend recommend = new Recommend();
            List<Course> recommendationMovies = recommend.recommend(name, users);
            System.out.println("-----------------------");
            System.out.println("推荐结果如下：");
            for (Course course : recommendationMovies) {
                System.out.println("课程："+course.cName+" ,评分："+course.appraise);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(con!=null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                con=null;
            }
        }
        return list;
    }
}
