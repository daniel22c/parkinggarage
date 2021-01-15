import java.util.Scanner;

public class ParkingGarageDriver {
	public static void main(String[] args) {
		ParkingGarage pg = new ParkingGarage();
		 Scanner scanner = new Scanner(System.in);
		while(true) {
			ParkingGarageDriver.displayMenu();
			int choice = scanner.nextInt();
			if(choice==1) { //display available spots
				pg.displayAvailableSpots();
			}else if(choice==2) { //park a car
				System.out.println("What type of car is your vehicle?");
				System.out.println("1. Motorcycle");
				System.out.println("2. Car");
				System.out.println("3. Bus");
				int type = scanner.nextInt();
				Vehicle vehicle = null;
				if(type==1) {
					vehicle = new Motorcycle(1L);
				}else if(type==2) {
					vehicle = new Car(2L);
				}else if(type==3) {
					vehicle = new Bus(3L);
				}
				System.out.println("Input spot ID:");
				Long id = scanner.nextLong();
				pg.parkVehicle(id, vehicle);
			}else if(choice==3) { //clear a spot
				System.out.println("Input spot ID:");
				Long id = scanner.nextLong();
				pg.clearVehicle(id);
			}else if(choice==4) { //exit
				System.out.println("Exitting the program.");
				System.exit(1);
			}
			
			
		}
	}
	public static void displayMenu() {
		System.out.println("1. View available parking spots");
		System.out.println("2. Park your car");
		System.out.println("3. Clear spot and checkout");
		System.out.println("4. Exit");
	}
}

