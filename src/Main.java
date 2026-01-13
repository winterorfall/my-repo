package com.student;

import com.student.entity.Student;
import com.student.manager.StudentManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建StudentManager实例（调用业务功能）
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // 循环显示菜单，实现持续交互
        while (isRunning) {
            // 打印交互菜单
            System.out.println("====================学生管理系统====================");
            System.out.println("1. 添加学生信息");
            System.out.println("2. 根据ID查询学生信息");
            System.out.println("3. 显示所有学生信息");
            System.out.println("4. 计算各科目平均分");
            System.out.println("5. 退出系统");
            System.out.print("请输入操作序号：");

            // 获取用户输入的操作序号
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消费换行符，避免后续输入异常

            // 根据选择执行对应功能
            switch (choice) {
                case 1:
                    // 1. 添加学生信息
                    System.out.print("请输入学生姓名：");
                    String name = scanner.nextLine();
                    System.out.print("请输入学生性别：");
                    String gender = scanner.nextLine();
                    System.out.print("请输入学生班级：");
                    String className = scanner.nextLine();
                    System.out.print("请输入高数成绩：");
                    double mathScore = scanner.nextDouble();
                    System.out.print("请输入Java成绩：");
                    double javaScore = scanner.nextDouble();
                    scanner.nextLine();

                    // 封装为Student对象，调用添加方法
                    Student student = new Student();
                    student.setName(name);
                    student.setGender(gender);
                    student.setClassName(className);
                    student.setMathScore(mathScore);
                    student.setJavaScore(javaScore);
                    boolean addSuccess = studentManager.addStudent(student);
                    System.out.println(addSuccess ? "添加学生成功！" : "添加学生失败！");
                    break;

                case 2:
                    // 2. 根据ID查询学生信息
                    System.out.print("请输入要查询的学生ID：");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Student queryStudent = studentManager.getStudentById(id);
                    if (queryStudent != null) {
                        System.out.println("查询到的学生信息：" + queryStudent);
                    } else {
                        System.out.println("未找到该ID对应的学生！");
                    }
                    break;

                case 3:
                    // 3. 显示所有学生信息
                    List<Student> studentList = studentManager.showAllStudents();
                    if (studentList.isEmpty()) {
                        System.out.println("当前无学生信息！");
                    } else {
                        System.out.println("所有学生信息：");
                        for (Student s : studentList) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 4:
                    // 4. 计算各科目平均分
                    double[] averages = studentManager.calculateAverageScores();
                    System.out.println("高数科目平均分：" + averages[0]);
                    System.out.println("Java科目平均分：" + averages[1]);
                    break;

                case 5:
                    // 5. 退出系统
                    System.out.println("系统已退出，感谢使用！");
                    isRunning = false;
                    break;

                default:
                    System.out.println("输入的操作序号无效，请重新输入！");
                    break;
            }
            System.out.println("====================================================");
        }
        scanner.close();
    }
}