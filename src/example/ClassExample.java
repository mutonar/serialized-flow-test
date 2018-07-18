/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;


interface Vehicle {
    void go();
}
class Automobile implements Vehicle {
    public Automobile() {
        System.out.println("Construction Automobile!");    }
    public void go() {
        System.out.println("\tавтомобиль поехал!");
    }
}
class Truck extends Automobile {
    
    public Truck() {
        super();
    }
    public Truck(int i) {
        super();
    }
    public void go() {
        System.out.println("\tгрузовик поехал!");
	    }
}

public class ClassExample
{
    public static void main(String[] args)
    {
        Vehicle vehicle;
        String[] vehicleNames = {"exemple.Automobile",
                                 "exemple.Truck"     , 
                                 "exemple.Aircraft"};
        for(int i = 0; i < vehicleNames.length; i++){
            try {
                String name = vehicleNames[i];
                System.out.println("Class : " + name);
                Class<?> aClass = Class.forName(name);
                System.out.println("\tсоздание автомобиля ...");
                vehicle = (Vehicle)aClass.newInstance();
                System.out.println("\tnewInstance : " +
                                           vehicle.getClass());
                vehicle.go();
            } catch(ClassNotFoundException e){
                System.out.println(e.toString());
            } catch(InstantiationException e){
                System.out.println(e.toString());
            } catch(Throwable th){
                System.out.println("Throwable : " + th.toString());
            }
        }
    }
}