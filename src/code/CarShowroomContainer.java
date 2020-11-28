package code;

import java.io.Serializable;
import java.util.*;

import static java.lang.System.exit;

public class CarShowroomContainer implements Serializable {

    Map<Integer, CarShowroom> salony = new HashMap<Integer, CarShowroom>();
    List<CarShowroom> salonyList = new ArrayList<>();

    public void addCenter(int name, CarShowroom cs) {

            this.salony.put(name, cs);
            this.salonyList.add(cs);

    }

    public void setSalony(Map<Integer, CarShowroom> salony) {
        this.salony = salony;
    }

    public void setSalonyList(List<CarShowroom> salonyList) {
        this.salonyList = salonyList;
    }

    public Map<Integer, CarShowroom> getSalony() {
        return salony;
    }


    public List<CarShowroom> getSalonyList() {
        return salonyList;
    }

    public void removeCenter(int name)
    {
        for( int i : salony.keySet())
        {
            if(i ==name) {
                salony.remove(i);
                return;
            }
        }

        System.out.println("CarShowroom not found...");
    }

    List<CarShowroom> findEmpty()
    {
        List<CarShowroom> rooms = new ArrayList<CarShowroom>();
        for (CarShowroom i : this.salony.values())
            if (i.vehicles.size() == 0)
                rooms.add(i);

        return rooms;

    }

    public void summary()
    {
        for (CarShowroom i : this.salony.values())
        {
            System.out.println(i.name + " percentage capacity " + i.percentCapacity() + "%");
            //System.out.println(i.current_cap);
            //System.out.println(i.max_capacity);
        }

    }

    ///////////////////////////////
    public List sort()
    {
        int n = salony.size();
        Set names = salony.keySet();
        String[] arr = new String[names.size()];
        names.toArray(arr);

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (arr[i].compareTo(arr[j])>0)
                {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        List<String> list = Arrays.asList(arr);
        /*
        Map <String, CarShowroom> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++)
        {
            map.put(list.get(i), salony.get(list.get(i)));
        }*/


        return list;

    }
}
