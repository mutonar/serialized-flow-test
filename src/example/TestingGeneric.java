/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// статья http://www.quizful.net/post/java-generics-tutorial

package example;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

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
    public void doSomethingAdd(List<? super Animal> list)
{
 list.add(new Animal()); //ошибка! если будет extends а не super
 list.add(new Fish()); 
}
    
    public static void main(String[] args){
    ArrayList listanimal1 = new  ArrayList();
     listanimal1.add(new Fish());
     listanimal1.add(new Fish());
     listanimal1.add(new Dog());
     listanimal1.add(new Pudel());
    TestingGeneric run = new TestingGeneric();
    run.doSomething(listanimal1);
    // два переменных в Дженерике
    IndependensMath<Integer, String> pair = new IndependensMath<Integer, String>(6, " Apr"); 
    //Алмазный синтаксис (Diamond syntax)
    IndependensMath<Integer, String> pair1 = new IndependensMath<>(6, " Apr"); 
    //без <> приведет к простому типу  (raw type)
    IndependensMath<Integer, String> pair2 = new IndependensMath(6, " Apr"); 
    //Wildcards (Маски)
    List<?> pair4 = new ArrayList<>(); 
    List<?> intList = new ArrayList<Integer>(); 
    //intList.add(new Integer(10));  // не проходит такое что бы разные типы не пихать




    }
    
}

class IndependensMath<T1, T2> {
        T1 object1; 
        T2 object2; 
        
IndependensMath(T1 one, T2 two) { 
        object1 = one; 
        object2 = two; 
    } 
    double koren3 (double a, double b){
    return Math.pow(a, 1.0/ b );}
    
}
