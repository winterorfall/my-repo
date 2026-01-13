package com.student.manager;

import com.student.entity.Student;
import com.student.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    // 1. 添加学生信息到数据库
    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 获取数据库连接
            conn = DatabaseConnection.getConnection();
            // SQL插入语句（与students表字段对应）
            String sql = "INSERT INTO students(name, gender, class_name, math_score, java_score) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            // 设置SQL参数（与Student对象的属性对应）
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());
            // 执行插入，返回影响行数（>0则成功）
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 关闭资源（先关Statement，再关Connection）
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 2. 根据ID查询学生信息
    public Student getStudentById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM students WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            // 解析结果集为Student对象
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class_name"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭资源（先关ResultSet，再关Statement，最后关Connection）
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 3. 显示所有学生信息
    public List<Student> showAllStudents() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM students";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // 遍历结果集，封装为Student列表
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class_name"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
            return studentList;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 4. 根据ID删除学生信息
    public boolean deleteStudentById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM students WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            // 执行删除，返回影响行数（>0则成功）
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 5. 计算学生各科目平均分数
    public double[] calculateAverageScores() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        double[] averages = new double[2]; // 0:高数平均分；1:Java平均分
        try {
            conn = DatabaseConnection.getConnection();
            // SQL聚合函数计算平均分
            String sql = "SELECT AVG(math_score) AS math_avg, AVG(java_score) AS java_avg FROM students";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                averages[0] = rs.getDouble("math_avg");
                averages[1] = rs.getDouble("java_avg");
            }
            return averages;
        } catch (SQLException e) {
            e.printStackTrace();
            return averages;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}