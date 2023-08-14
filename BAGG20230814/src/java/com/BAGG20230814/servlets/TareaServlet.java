package com.BAGG20230814.servlets;

import com.BAGG20230814.model.Tarea;
import com.BAGG20230814.Utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TareaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tarea> tasks = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            String query = "SELECT * FROM Tarea";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tarea task = new Tarea();
                task.setId(resultSet.getInt("ID"));
                task.setNombre(resultSet.getString("Nombre"));
                task.setDescripcion(resultSet.getString("Descripcion"));
                task.setCompletada(resultSet.getBoolean("Completada"));
                task.setFechaCreacion(resultSet.getDate("FechaCreacion"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        try (Connection connection = DBUtils.getConnection()) {
            String query = "INSERT INTO Tarea (Nombre, Descripcion, Completada, FechaCreacion) VALUES (?, ?, 0, GETDATE())";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/TaskServlet");
    }
}
