/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TestingGeneric {

 
    public void doSomething(List<? extends Animal> list)
{
 for(Animal object : list)
 {
  System.out.println(object.getStateHashCode()); //тут все работает отлично.
 }
}
    
    public static void main(String[] args){
    ArrayList listanimal1 = new  ArrayList();
     listanimal1.add(new Fish());
     listanimal1.add(new Fish());
     listanimal1.add(new Dog());
     listanimal1.add(new Pudel());
    TestingGeneric run = new TestingGeneric();
    run.doSomething(listanimal1);
    }
    
}
