package com.company;

import java.util.Scanner;

public class Menu {
    public void start(){
        System.out.println("** Task Manager **");
        System.out.println("1.Create Task");
        System.out.println("2.Active TaskList");
        System.out.println("3.Finished TaskList");
        System.out.println("4.Update Task");
        System.out.println("5.Delete Task");
        System.out.println("6.Mark isDone");
        System.out.println("7.Exit");
    }
    public void controlMenu(){
        TaskReposity taskReposity=new TaskReposity();
        Scanner scanner=new Scanner(System.in);
        boolean t=true;
        while (t){
            start();
            int action=scanner.nextInt();
            switch (action){
                case 1:taskReposity.createTask();
                    break;
                case 2:taskReposity.tasklist();
                    break;
                case 3:taskReposity.FinishedTaskList();
                    break;
                case 4:taskReposity.UpdateTask();
                    break;
                case 5:taskReposity.DeleteTask();
                    break;
                case 6:taskReposity.Mark_isDone();
                    break;
                case 7:t=false;
                    break;
                default:
                    System.out.println("Invalid Action !");
            }
        }
    }
}