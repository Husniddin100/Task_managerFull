package com.company;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

import static com.company.TaskStatus.ACTIVE;
import static com.company.TaskStatus.DONE;

public class TaskReposity {
    public void createTask(){
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Enter id");
        int id=scanner1.nextInt();
        System.out.println("Enter title");
        String title=scanner.nextLine();
        System.out.println("Enter content");
        String content=scanner.nextLine();

        Task task1=new Task();
        task1.setId(id);
        task1.setTitle(title);
        task1.setContent(content);
        task1.setTaskStatus(ACTIVE);
        task1.setCreated_date(LocalDateTime.now());
        task1.setFinished_date(LocalDateTime.now());

        try {
            Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                    "jdbc_user", "123456");
            Statement statement=connection.createStatement();
            String sql="insert into Task (id,title,content,task_status,created_date,finished_date)" +
                    "values("+task1.getId()+",'"+task1.getTitle()+"','"+task1.getContent()+"','"+task1.getTaskStatus()+"'," +
                    "'"+task1.getCreated_date()+"','"+task1.getFinished_date()+"')";
            int effectiveRows=statement.executeUpdate(sql);
            if (effectiveRows!=0){
                System.out.println("Create Task succesfully");
            }else {
                System.err.println("Create Task Error");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedList<Task> ActiveTaskList(){
        LinkedList<Task>taskLinkedList=new LinkedList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                    "jdbc_user", "123456");
            Statement statement = con.createStatement();
            ResultSet rs=statement.executeQuery("select * from Task");
            while (rs.next()){
                Task task=new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setContent(rs.getString("content"));
                task.setTaskStatus(TaskStatus.valueOf(rs.getString("task_status")));
                task.setCreated_date(LocalDateTime.parse(rs.getString("created_date")));
                task.setFinished_date(LocalDateTime.parse(rs.getString("finished_date")));
                taskLinkedList.add(task);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskLinkedList;
    }

    public void tasklist(){
        LinkedList<Task>list=ActiveTaskList();
        for (Task task:list) {
            if (task!=null && task.getTaskStatus() == ACTIVE) {
                System.out.println(task.getId() + " " + task.getTitle() + " " + task.getContent() + " " + task.getTaskStatus() + " "
                        + task.getCreated_date() + " ");
            }
        }
    }

    public void FinishedTaskList(){
        LinkedList<Task>list=ActiveTaskList();
        for (Task task:list) {
            if (task!=null && task.getTaskStatus() == DONE) {
                System.out.println(task.getId() + " " + task.getTitle() + " " + task.getContent() + " " + task.getTaskStatus() + " "
                        + task.getCreated_date() + " || "+task.getFinished_date());
            }
        }
    }
    public void UpdateTask(){
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Enter task id");
        int taskId=scanner.nextInt();
        System.out.println("Enter title ");
        String title=scanner1.nextLine();
        System.out.println("Enter content");
        String content=scanner1.nextLine();
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                    "jdbc_user", "123456");
            Statement statement=con.createStatement();
            String sql="update Task set title='%s',content='%s' where id=%d";
            sql=String.format(sql,title,content,taskId);
            int effectiveRows=statement.executeUpdate(sql);
            if (effectiveRows!=0){
                System.out.println("Update Task succesfully");
            }else {
                System.err.println("Update Task Error");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteTask(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter id");
        int id=scanner.nextInt();
        try {
            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                    "jdbc_user", "123456");
            Statement statement=con.createStatement();
            String sql="delete from Task where id="+id;
            int effectiveRows=statement.executeUpdate(sql);
            if (effectiveRows!=0){
                System.out.println("Delete Task succesfully");
            }else {
                System.err.println("Delete Task Error");
            }
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Mark_isDone(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter id");
        int id=scanner.nextInt();
        String taskstatus="DONE";
        Task task=new Task();
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                    "jdbc_user", "123456");
            Statement statement=con.createStatement();
            task.setFinished_date(LocalDateTime.now());
            String sql="update Task set task_status='%s' ,finished_date='%s' where id=%d";
            sql=String.format(sql ,taskstatus,task.getFinished_date(),id);
            int effectiveRows=statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}