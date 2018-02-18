import java.util.ArrayList;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URISyntaxException;

public class VehicleList {
	private static ArrayList<Vehicle> vehicleList;

	// -----------------------------------------------------------

	public VehicleList() {
		vehicleList = new ArrayList<Vehicle>();
	}

	public VehicleList(ArrayList<Vehicle> vehicleList) {
		VehicleList.vehicleList = vehicleList;
	}

	// -----------------------------------------------------------
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		VehicleList.vehicleList = vehicleList;
	}

	// -----------------------------------------------------------
	public static void addVehicle(Vehicle vehicle) {
		vehicleList.add(vehicle);
	}

	public void removeVehicle(int index) {
		vehicleList.remove(index);
	}

	public Vehicle getVehicle(int index) {
		return vehicleList.get(index).copy();
	}

	public int getSize() {
		return vehicleList.size();
	}

	// -----------------------------------------------------------
	public static void writeToFile() {
		try {
			Path path = Paths.get(VehicleList.class.getResource(".").toURI());
			System.out.println();
			FileOutputStream fos = new FileOutputStream(path.getParent()
					+ "\\database\\vehicleList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(vehicleList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Vehicle> readFromFile() {

		try {
			
		Path path = Paths.get(VehicleList.class.getResource(".").toURI());
		FileInputStream fis = new FileInputStream(path.getParent()
				+ "\\database\\vehicleList.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Vehicle> result = (ArrayList<Vehicle>) ois.readObject();
		ois.close();
		return result;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			ArrayList<Vehicle> result = new ArrayList<Vehicle>();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
