/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.io.Serializable;

/**
 *
 * @author admin
 */
 class Animal implements Serializable {
 
    public void eat() {
        System.out.println("animal eat");
        
    }
    public void method (String ... strings) {
        for (String s : strings) {
          System.out.println(s);
        }
    }
}
 
class Fish extends Animal {
 
    public void eat() {
        System.out.println("Fish eat");
    }
    public void thisEat() {
        System.out.println("Call Fish.eat()");
        this.eat();
    }
 
    public void superEat() {
        System.out.println("Call Animal.method()");
        super.method("Fish get param");
    }
 
}

class Dog extends Animal {
 
    public void eat() {
        System.out.println("Dog eat");
    }
    public void thisEat() {
        System.out.println("Call Dog.eat()");
        this.eat();
    }
 
    public void superEat() {
        System.out.println("Call Animal.eat()");
        super.eat();
    }
 
}
 
public class TestSuperThis  implements Serializable { // для сохранения в файл
    
    TestSuperThis(){}
    
    String caseAnimal;
    TestSuperThis(int animal){
     switch(animal){
           case 1: 
               caseAnimal = "Fish";
               break;
            case 2: 
               caseAnimal = "Dog";
               break;
            default:
                caseAnimal = "Animal";
                break;
          }
    }
    public static void main(String[] args) {
       // Animal <?> sdfs = new Animal<?>(new Animal());
        Dog dog = new Dog();
        dog.eat();
        dog.thisEat();
        dog.superEat();
        dog.method("sdfdsfsdfsdf", "sdfdsf", "sdfdsfds");
    }
}
/*Dog eat
Call Dog.eat()
Dog eat
Call Animal.eat()
animal eat
*/