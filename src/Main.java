import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrafficSystem trafficSystem = new TrafficSystem();
        District widzew = new District("Widzew");
        District polesie = new District("Polesie");

        trafficSystem.addDistrict(widzew);
        trafficSystem.addDistrict(polesie);

        Street pilsudzkiego = new Street("Piłsudskiego", widzew);
        Street wlokniarzy = new Street("Włókniarzy", polesie);

        trafficSystem.addStreet(pilsudzkiego);
        trafficSystem.addStreet(wlokniarzy);

        Intersection intersection = new Intersection("Włókniarzy z Piłsudskiego");

        intersection.addStreet(pilsudzkiego);
        intersection.addStreet(wlokniarzy);
        trafficSystem.addIntersection(intersection);

        Vehicle car1 = new Vehicle("EPA5CH7");
        Vehicle car2 = new Vehicle("ELW1DW3");

        trafficSystem.addVehicle(car1);
        trafficSystem.addVehicle(car2);

        pilsudzkiego.addVehicle(car1);
        wlokniarzy.addVehicle(car2);

        System.out.println(trafficSystem.findVehicleLocation("ELW1DW3"));

        System.out.println(trafficSystem.findVehicleLocation("EPA5CH7"));
    }
}
class Vehicle {
    private String licensePlate;
    private String street;
    private String district;


    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
        this.street = null;
        this.district = null;
    }

    public void setLocation(String street, String district) {
        this.street = street;
        this.district = district;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }
}
class District {
    private String name;
    private List<Street> streets;
    public District(String name) {
        this.name = name;
        this.streets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
}
class Street {
    private String name;
    private District district;
    private List<Vehicle> vehicles;


    public Street(String name, District district) {
        this.name = name;
        this.district = district;
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicle.setLocation(this.name, this.district.getName());
    }

    public String getName() {
        return name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
class Intersection {
    private String name;
    private List<Street> streets;


    public Intersection(String name) {
        this.name = name;
        this.streets = new ArrayList<>();
    }

    public void addStreet(Street street) {
        streets.add(street);
    }

    public String getName() {
        return name;
    }

    public List<Street> getStreets() {
        return streets;
    }
}
class TrafficSystem {
    private List<Vehicle> vehicles;
    private List<Street> streets;
    private List<Intersection> intersections;
    private List<District> districts;
    public TrafficSystem() {
        this.vehicles = new ArrayList<>();
        this.streets = new ArrayList<>();
        this.intersections = new ArrayList<>();
        this.districts = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addStreet(Street street) {
        streets.add(street);
    }

    public void addIntersection(Intersection intersection) {
        intersections.add(intersection);
    }

    public void addDistrict(District district) {
        districts.add(district);
    }

    public String findVehicleLocation(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return "Vehicle " + licensePlate + " is on " + vehicle.getStreet() + " street in " + vehicle.getDistrict() + " district.";
            }
        }
        return "Vehicle " + licensePlate + " not found.";
    }
}
