/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */

public class TestInputOutputStream{
public static void main(String[] args){
    String pathFile = "C:/test.txt";
    Runnable r = new ThreadInputOutputStream(pathFile, "r");
    Runnable w = new ThreadInputOutputStream(pathFile, "w");
    Runnable o = new ThreadInputOutputStream(pathFile, "o");
    Runnable b = new ThreadInputOutputStream(pathFile, "b");
       new Thread(b).start();
      // new Thread(w).start();
       new Thread(o).start();// передаём объект r как параметр создаваемого потоку
       // теперь код из run() может работать в отдельном потоке + 
       // обрабатывать переданные параметры! =)) о как)}
}
}

class ThreadInputOutputStream implements Runnable {

      public String path;// параметр 
      char CaseWR;
      public ThreadInputOutputStream(String ... path) {// через конструтор передадим параметр
           // передаём в конструктор все параметры, которые могут пигодится потоку
       this.path=path[0]; // сохраняем параметры как поля - мне нужен только один =))
       switch(path[1]){
           case "w": 
               CaseWR = 'w';
               break;
            case "r": 
               CaseWR = 'r';
               break;
            case "o": 
               CaseWR = 'o';
               break;
            case "b": 
               CaseWR = 'b';
               break;
            default:
                CaseWR = 'r';
                break;
          }
      }
    @Override
    public void run() {
// здесь пишем код, который будет исполняться в отдельном потоке
         // далее я вызываю два статических метода одного из своих классов (самописный класс)))
         if (CaseWR == 'w')
         {
          System.out.println("Start Thread write!");
             try {
                 methodWrite();
             } catch (InterruptedException ex) {
                 Logger.getLogger(ThreadInputOutputStream.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         if (CaseWR == 'o')
         {
          System.out.println("Thread Object write!");
             try {
                 methodWriteObj();
             } catch (InterruptedException ex) {
                 Logger.getLogger(ThreadInputOutputStream.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         if (CaseWR == 'b')
         {
          System.out.println("Thread Object LISTarray!");
             try {
                 methodReadObj();
             } catch (InterruptedException ex) {
                 Logger.getLogger(ThreadInputOutputStream.class.getName()).log(Level.SEVERE, null, ex);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(ThreadInputOutputStream.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(ThreadInputOutputStream.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(ThreadInputOutputStream.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else{
         System.out.println("Start Thread methodRead!");
             try {
                 methodRead();
             } catch (InterruptedException ex) {
                 Logger.getLogger(ThreadInputOutputStream.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
    }
    //метод чтения файла
     public void methodRead() throws InterruptedException{    
         while (true){ 
             synchronized(path){
        try {
        BufferedReader in = new BufferedReader(new FileReader(path));
        String str;
        while ((str = in.readLine()) != null)
            System.out.println(str);
        in.close();
    } catch (IOException e) {
    }
sleep(1000);
    }
         }
     }
     //метод записи файла
     public synchronized void  methodWrite()throws InterruptedException{   
         while (true){
             synchronized(path){
        try {
        Double str =  Math.random();
        long position = (long) (str*10);
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        //writer.write(str); // это походу переписать
        writer.append('\n' );        // это добавить
        writer.append(str.toString());
        writer.close();
       // хер знает для чего и как работать
       /*
    RandomAccessFile writer = new RandomAccessFile(path, "rw");
    writer.seek(position);
    writer.(str);
    writer.close();
    */
    } catch (IOException e) {
    }
      sleep(3000);  
         }
         }
    }
     
          //метод записи объекта
     public synchronized void  methodWriteObj()throws InterruptedException{   
         while (true){
             synchronized(path){
        try {
        Double str =  Math.random();
        long position = (long) (str*10);
                ArrayList listanimal = new  ArrayList();
        listanimal.add(new Dog());
        listanimal.add(new Fish());
        listanimal.add(new Fish());
        ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(path)); // запись 
        //serial.writeObject(new Dog());
        serial.writeObject(listanimal);
        serial.flush();
        
        
    } catch (IOException e) {
    }
      sleep(3000);  
         }
         }
    }
              //метод чтения объекта
     public synchronized void  methodReadObj()throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException{   
         while (true){
             synchronized(path){
        ObjectInputStream serial = new ObjectInputStream(new FileInputStream(path));
        //Animal Dog1 = (Animal) serial.readObject();
        ArrayList listreadanimal = (ArrayList) serial.readObject();
        serial.close();
        System.out.println(listreadanimal.size());
        for (int i = 0; i < listreadanimal.size(); i++){
          System.out.println(listreadanimal.get(i)); 
        }
         }
        
              sleep(2000);  
    } 

         }
         }
    

    

