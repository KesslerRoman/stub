package db;

import com.example.stub.models.User;

import java.sql.*;

public class connection {

    public final static String db_url = "jdbc:postgresql://192.168.0.119:5432/mydatabase";
    public final static String db_user = "admin";
    public final static String db_password = "admin";

    public static User select(String condition) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        final String query = "SELECT public.user.login, public.email.email, public.user.password, public.user.date\n" +
                             "FROM public.user\n" +
                             "JOIN public.email\n" +
                             "ON public.user.login = public.email.login\n" +
                             "WHERE public.user.login = '" + condition + "';";

        try {
            Connection db = DriverManager.getConnection(db_url, db_user, db_password);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3));

                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
                System.out.println(user.getDate());

                rs.close();
                st.close();
                db.close();
                return user;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int insert(User user) {

        final String query = "INSERT INTO public.email VALUES (?,?);\n" +
                             "INSERT INTO public.user VALUES (?,?,?);";

        try (Connection db = DriverManager.getConnection(db_url, db_user, db_password);
             PreparedStatement ps = db.prepareStatement(query);
        ) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setDate(5, user.getDate());

            int row = ps.executeUpdate();

            System.out.println("Записалось " + row + "строк");

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
