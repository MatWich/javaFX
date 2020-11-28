package code;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.*;

import static java.lang.System.exit;

public class CarShowroom implements java.lang.Comparable<CarShowroom>, Serializable {
    //pola
    String name = "";
    List<Vehicle> vehicles= new ArrayList<>();
    HashMap<String, Integer> VehicleAmount = new HashMap<String, Integer>();
    HashMap<Vehicle, Integer> VehicleAmountVeh = new HashMap<Vehicle, Integer>();
    int max_capacity = 100;
    int current_cap = 0;

    public CarShowroom() {

    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public HashMap<String, Integer> getVehicleAmount() {
        return VehicleAmount;
    }

    public HashMap<Vehicle, Integer> getVehicleAmountVeh() {
        return VehicleAmountVeh;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public int getCurrent_cap() {
        return current_cap;
    }

    public void setname(String name)
    {
        this.name = name;
    }

    public CarShowroom(String n)
    {
        this.name = n;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProduct(Vehicle veh)
    {
        // zmienna lokana a nie nazwa salonu
        String name = veh.brand + " " + veh.model; // klucz do hashmapy

        if (this.current_cap < this.max_capacity)   // warunek zeby mozna bylo dodac
        {
            veh.setSalonName(this.name);
            if (this.VehicleAmount.containsKey(name))
            {
                int old_amount = this.VehicleAmount.get(name);  // zeby wiedziec ile jest ich obecnie

                // dwie hash mapy tylko maja rozne klucze K: Vehicle albo K: String name
                this.VehicleAmount.replace(name, old_amount, old_amount+1);
                this.VehicleAmountVeh.replace(veh, old_amount, old_amount+1);
                this.current_cap += 1;
            }
            else
            {
                this.VehicleAmount.put(name, 1);
                veh.setSalonName(this.name);
                this.VehicleAmountVeh.put(veh, 1);
                this.vehicles.add(veh);
                this.current_cap += 1;
               // System.out.println("Dodano pomyslnie");
            }

        }
        else
        {
            System.err.println("Przekroczono pojemnosc ...");
            exit(-1);
        }


    }

    public void getProduct(Vehicle veh)
    {
        String name = veh.brand + " " + veh.model;
        if (this.VehicleAmount.get(name) == 1)
        {
            System.out.println("Ni ma juz takiego i nie bedzie");
            this.VehicleAmount.remove(name);
            this.VehicleAmountVeh.remove(veh);
            this.current_cap -= 1;
            this.vehicles.remove(veh);
        }
        else
        {
            int old_value = this.VehicleAmount.get(name);
            this.VehicleAmount.replace(name, old_value, old_value-1);
            this.VehicleAmountVeh.replace(veh, old_value, old_value-1);
            this.current_cap -= 1;
        }
    }


    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setVehicleAmount(HashMap<String, Integer> vehicleAmount) {
        VehicleAmount = vehicleAmount;
    }

    public void setVehicleAmountVeh(HashMap<Vehicle, Integer> vehicleAmountVeh) {
        VehicleAmountVeh = vehicleAmountVeh;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public void setCurrent_cap(int current_cap) {
        this.current_cap = current_cap;
    }

    public void removeProduct(Vehicle veh)
    {
        if (this.VehicleAmount.get(veh.name) > 0)
        {
            int amount = this.VehicleAmount.get(veh.name);
            this.vehicles.remove(veh);
            this.VehicleAmount.remove(veh.name);
            this.VehicleAmountVeh.remove(veh);
            this.current_cap -= amount;

        }
        else
        {
            System.err.println("Ni mo takiego");
        }

    }

    public Vehicle search(String name) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        Collections.sort(this.vehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                String str1 = o1.brand + " " + o1.model;
                String str2 = o2.brand + " " + o2.model;
                return str1.compareTo(str2);
            }
        });

        //String name = name;
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < this.vehicles.size(); i++) {
            keys.add(this.vehicles.get(i).brand + " " + this.vehicles.get(i).model);
        }

        //Collections.sort(keys);
        String Name = name;
        int pos = Collections.binarySearch(keys, Name, comparator);
        return this.vehicles.get(pos);
    }



    // ma szukac po polowie nazwy XD
    public List<Vehicle> searchPartial(String nam)
    {
        List<Vehicle> result = new ArrayList<Vehicle>();
        for(Vehicle i : this.vehicles)
        {
            String key = i.brand + " " + i.model;

            if (key.contains(nam))
            {
                result.add(i);
            }
        }
        return result;
    }
    /*
    public int countByCondition(ItemCondition req)
    {
        List<Vehicle> result = new ArrayList<Vehicle>();

        for (Vehicle i : this.vehicles)
        {
            if (i.condition == req)
                result.add(i);
        }
        return result.size();
    }
*/
    public double percentCapacity()
    {
        double result = (double)this.current_cap / (double)this.max_capacity;
        return result*100;
    }


    public void summary()
    {
        for (Vehicle i : this.vehicles)
        {
            //System.out.println(i.brand + " " + i.model + "\n" + "Condition: " + i.condition);
            System.out.println("Engine capacity:" + i.eng_capacity + "\n" + "Production Year: " + i.prod_year);
            System.out.println("Price: " + i.price);

        }

    }
/*
    public List<Vehicle> sortByName()
    {
        Comparator<Vehicle> comparator = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                SimpleStringProperty str1 = o1.name;
                SimpleStringProperty str2 = o2.name;
                return str1.compareTo(str2);
            }
        };
        List<Vehicle> cop = new ArrayList<Vehicle>(this.vehicles);

        Collections.sort(cop, comparator);
        this.summary();
        return cop;
    }
*/
    public ArrayList<Vehicle> sortByAmount()
    {
       List<Map.Entry<Vehicle, Integer>> list = new LinkedList<Map.Entry<Vehicle, Integer>>(this.VehicleAmountVeh.entrySet());

       // Sort
        Collections.sort(list, new Comparator<Map.Entry<Vehicle, Integer>>() {
            @Override
            public int compare(Map.Entry<Vehicle, Integer> o1, Map.Entry<Vehicle, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

       // put data from sorted list to hashmap
        HashMap<Vehicle, Integer> temp = new LinkedHashMap<Vehicle, Integer>();
        ArrayList<Vehicle> temp2 = new ArrayList<Vehicle>();

        for (Map.Entry<Vehicle, Integer> aa : list)
        {
            temp.put(aa.getKey(), aa.getValue());
            temp2.add(aa.getKey());
        }


        return temp2;
    }

    public Vehicle max()
    {
        Map.Entry<Vehicle, Integer> maxEntry = Collections.max(this.VehicleAmountVeh.entrySet(), (Map.Entry<Vehicle, Integer> e1, Map.Entry<Vehicle, Integer> e2 ) -> e1.getValue().compareTo(e2.getValue()));
        return maxEntry.getKey();


    }

    @Override
    public int compareTo(CarShowroom o) {
        return this.name.compareTo(o.name);
    }
}
