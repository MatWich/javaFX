package code;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DataGenerator {

    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    public Vehicle createVehicle()
    {
        Random r = new Random();
        String name = "Passat";
        String model = this.createRandomName();
        //name = name + " " + model;
        int price = r.nextInt(2000 - 10) + 10;
        int eng_cap = r.nextInt(2000 - 100) + 100;
        int prodY = r.nextInt(2020 - 1800) + 29;
        String s = "Dostepny";

        //return new Vehicle(name, price, prodY, eng_cap, s);
        return new Vehicle(name, model, price, prodY, eng_cap);
    }

    public String createRandomName()
    {
        Random r = new Random();
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = r.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(r.nextInt(lexicon.length())));
            }

        }
        return builder.toString();
    }

    public CarShowroom createShowroom()
    {
        Random r = new Random();
        String name = this.createRandomName();

        CarShowroom cs = new CarShowroom(name);
        for (int i = 0; i < 5; ++i)
        {
            Vehicle veh = this.createVehicle();
            cs.addProduct(veh);
        }

        System.out.println("Carshowroom");
        for(Vehicle veh : cs.getVehicles())
            veh.print();
           // cs.addProduct(createVehicle());

        /*
        cs.addProduct(this.createVehicle());
        cs.addProduct(this.createVehicle());
        cs.addProduct(this.createVehicle());
        cs.addProduct(this.createVehicle());
        cs.addProduct(this.createVehicle());
        */


        return cs;
    }

    public CarShowroomContainer createSalonContainer()
    {
        CarShowroomContainer container = new CarShowroomContainer();
        CarShowroom cs = this.createShowroom();
        container.addCenter(1, cs);

        CarShowroom cs2 = this.createShowroom();
        container.addCenter(2, cs2);

        CarShowroom cs3 = this.createShowroom();
        container.addCenter(3, cs3);

        List<CarShowroom> test = container.salonyList;

        for (CarShowroom csi : test)
        {
            System.out.println(csi.getName());
        }

        return container;
    }

}
