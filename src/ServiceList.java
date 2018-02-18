import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ServiceList {
	private static ArrayList<Service> serviceList;
	private static final long serialVersionUID = 6529685098267757690L;

	public ServiceList() {
		serviceList = new ArrayList<Service>();
	}

	public ServiceList(ArrayList<Service> service) {
		ServiceList.serviceList = service;
	}

	public static void addService(Service service) {
		serviceList.add(service);
	}

	public void removeService(int index) {
		serviceList.remove(index);
	}

	public Service getService(int index) {
		return serviceList.get(index).copy();
	}

	@Override
	public String toString() {
		String all = "";
		for (int i = 0; i < serviceList.size(); i++) {
			all += serviceList.get(i);
			all += "/n";
		}
		return all;
	}

	public void setList(ArrayList<Service> serviceList) {
		ServiceList.serviceList = serviceList;
	}

	public ArrayList<Service> getServiceList() {
		ArrayList<Service> allServices = new ArrayList<Service>();
		for (int i = 0; i < serviceList.size(); i++) {
			allServices.add(serviceList.get(i));
		}
		return serviceList;
	}

	public static void writeToFile() {
		try {

			Path path = Paths.get(ServiceList.class.getResource(".").toURI());
			FileOutputStream fos = new FileOutputStream(path.getParent()
					+ "\\database\\serviceList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(serviceList);
			oos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Service> readFromFile() {

		try {

			// read object from file
			Path path = Paths.get(ServiceList.class.getResource(".").toURI());
			FileInputStream fis = new FileInputStream(path.getParent()
					+ "\\database\\serviceList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Service> result = (ArrayList<Service>) ois.readObject();
			ois.close();
			return result;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			ArrayList<Service> result = new ArrayList<Service>();
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

	public int getSize() {
		return serviceList.size();
	}
	//

}
