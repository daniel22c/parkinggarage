import java.util.ArrayList;

public class ParkingGarage {
	ArrayList<Level> levels;
	public ParkingGarage() {
		//init and populate level1 and 2
		levels = new ArrayList<>();
		Level level1 = new Level(1L);
		Row row1 = new Row(1L);
		row1.addSpot(new MotorcycleSpot(1L));
		row1.addSpot(new MotorcycleSpot(2L));
		row1.addSpot(new CompactSpot(3L));
		row1.addSpot(new CompactSpot(4L));
		row1.addSpot(new LargeSpot(5L));
		Row row2 = new Row(2L);
		row2.addSpot(new MotorcycleSpot(6L));
		row2.addSpot(new MotorcycleSpot(7L));
		row2.addSpot(new CompactSpot(8L));
		row2.addSpot(new CompactSpot(9L));
		row2.addSpot(new LargeSpot(10L));
		level1.addRow(row1);
		level1.addRow(row2);
		
		//init and populate level 2 
		Level level2 = new Level(2L);
		row1 = new Row(1L);
		row1.addSpot(new MotorcycleSpot(11L));
		row1.addSpot(new MotorcycleSpot(12L));
		row1.addSpot(new CompactSpot(13L));
		row1.addSpot(new CompactSpot(14L));
		row1.addSpot(new LargeSpot(15L));
		row2 = new Row(2L);
		row2.addSpot(new MotorcycleSpot(16L));
		row2.addSpot(new MotorcycleSpot(17L));
		row2.addSpot(new CompactSpot(18L));
		row2.addSpot(new CompactSpot(19L));
		row2.addSpot(new LargeSpot(20L));
		level2.addRow(row1);
		level2.addRow(row2);
		
		Level level3 = new Level(3L);
		row1 = new Row(1L);
		row1.addSpot(new LargeSpot(21L));
		row1.addSpot(new LargeSpot(22L));
		row1.addSpot(new LargeSpot(23L));
		row1.addSpot(new LargeSpot(24L));
		row1.addSpot(new LargeSpot(25L));
		level3.addRow(row1);
		levels.add(level1);
		levels.add(level2);
		levels.add(level3);
		
	}
	public void displayAvailableSpots() {
		System.out.println("available spots");
		for(Level level:levels) {
			System.out.println("Level:"+level.id);
			for(Row row:level.rows) {
				System.out.println("Row:"+level.id);
				for(Spot spot:row.spots) {
					if(spot.vehicle==null) {
						System.out.println(spot.id+":"+spot.type);
					}
				}
			}
		}
	}
	public void parkVehicle(Long spotId, Vehicle vehicle) {
		for(Level level:levels) {
			for(Row row:level.rows) {
				for(Spot spot:row.spots) {
					if(spot.id==spotId) {
						if(spot.vehicle!=null) {
							System.out.println("Can't park there. Spot taken");
						}else {
							if(vehicle.type==VehicleTypeEnum.Bus) {
								if(spot.type==SpotTypeEnum.Large) {
									//check if next 4 spots are available and are large types
									int idx = row.spots.indexOf(spot);
									try {
										boolean isAvailable=true;
										if(row.spots.size()<(idx+1+4)) {
											System.out.println("Parking space not available");
											return;
										}
										for(int i=0;i<=4;i++) {
											Spot nextSpot = row.spots.get(idx+i);
											if(!nextSpot.type.equals(SpotTypeEnum.Large)) {
												isAvailable = false;
												System.out.println("Parking space not available");
												return;
											}
										}
										if(isAvailable) {
											for(int i=0;i<=4;i++) {
												row.spots.get(idx+i).vehicle = vehicle;
											}
											System.out.println("park successfully at spot "+spotId + " for vehicle "+ vehicle.id);
											return;
										}else {
											System.out.println("Parking space not available");
											return;
										}
									}catch(Exception e){
										System.out.println("Parking space not available");
										return;
									}
								}
							}else if(vehicle.type==VehicleTypeEnum.Car) {
								if(spot.type==SpotTypeEnum.Compact ||
									vehicle.type==VehicleTypeEnum.Car && spot.type==SpotTypeEnum.Large) {
									parkVehicle(spot, vehicle);
									return;
								}
							}else if(vehicle.type==VehicleTypeEnum.Motorcycle) {
								parkVehicle(spot, vehicle);
								return;
							}
						}
					}
				}
			}
		}
	}
	public void parkVehicle(Spot spot, Vehicle vehicle) {
		spot.vehicle = vehicle;
		System.out.println("park successfully at spot "+spot.id + " for vehicle "+ vehicle.id);
	}
	public void clearVehicle(Long spotId) {
		for(Level level:levels) {
			for(Row row:level.rows) {
				for(Spot spot:row.spots) {
					if(spot.id==spotId) {
						if(spot.vehicle==null) {
							System.out.println("No vehicle found in the spot.");
						}else {
							if(spot.vehicle.type.equals(VehicleTypeEnum.Bus)) {
								int idx = row.spots.indexOf(spot);
								if(row.spots.size()<(idx+1+4)) {
									System.out.println("Wrong Parking space specified");
									return;
								}
								boolean isAvailable=true;
								//check if it is a valid parked spot where the bus is
								for(int i=0;i<=4;i++) {
									Spot nextSpot =row.spots.get(i); 
									if(!nextSpot.type.equals(SpotTypeEnum.Large) && nextSpot.vehicle==null){
										isAvailable=false;
									}
								}
								//vacate the parking
								if(isAvailable) {
									for(int i=0;i<=4;i++) {
										row.spots.get(i).vehicle = null;
									}
									System.out.println("Spot cleared");
									return;
								}
							}else {
								spot.vehicle = null;
								System.out.println("Spot cleared");
							}
						}
						
					}
				}
			}
		}
	}
	
}
