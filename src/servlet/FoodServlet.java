package servlet;

import DBUtil.Db;
import entity.Food;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("gbk");
        List<Food> lists = new ArrayList<>();
        String num = req.getParameter("num");
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            if (num == null) {
                String sql = "select * from food";
                 preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Food food = new Food();
                        food.setId(resultSet.getInt("id"));
                        food.setName(resultSet.getString("name"));
                        food.setNumber(Integer.valueOf(resultSet.getString("num")));
                        food.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(food);
                    }
                }
            } else {
                String sql = "select * from food where num > ?";
                preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    preparedStatement.setObject(1,num);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Food food = new Food();
                        food.setId(resultSet.getInt("id"));
                        food.setName(resultSet.getString("name"));
                        food.setNumber(Integer.valueOf(resultSet.getString("num")));
                        food.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(food);
                    }
                }
            }
            Db.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("foods", lists);
        resp.sendRedirect("/food.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
