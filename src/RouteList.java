import java.util.ArrayList;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URISyntaxException;

public class RouteList {
	private static ArrayList<Route> routeList;
	private static final long serialVersionUID = 6529685098267757690L;
	
	// -----------------------------------------------------------

	public RouteList() {
		routeList = new ArrayList<Route>();
	}

	public RouteList(ArrayList<Route> routeList) {
		RouteList.routeList = routeList;
	}

	// -----------------------------------------------------------
	public ArrayList<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(ArrayList<Route> routeList) {
		RouteList.routeList = routeList;
	}
	
	public Route getRoute(int index) {
		return routeList.get(index).copy();
	}

	// -----------------------------------------------------------
	public static void addRoute(Route route) {
		routeList.add(route);
	}

	public void removeRoute(int index) {
		routeList.remove(index);
	}

	public int getSize() {
		return routeList.size();
	}

	// -----------------------------------------------------------
	public static void writeToFile() {
		try {
			// write object to file
			Path path = Paths.get(RouteList.class.getResource(".").toURI());
			System.out.println();
			FileOutputStream fos = new FileOutputStream(path.getParent()
					+ "\\database\\RouteList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(routeList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Route> readFromFile() {

		try {

			// read object from file
			Path path = Paths.get(RouteList.class.getResource(".").toURI());
			FileInputStream fis = new FileInputStream(path.getParent()
					+ "\\database\\routeList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Route> result = (ArrayList<Route>) ois.readObject();
			ois.close();
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			ArrayList<Route> result = new ArrayList<Route>();
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
