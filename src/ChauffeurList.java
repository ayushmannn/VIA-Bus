import java.util.ArrayList;
import java.nio.file.*;
import java.io.*;
import java.net.URISyntaxException;

public class ChauffeurList {
	private static ArrayList<Chauffeur> chauffeurList;
	private static final long serialVersionUID = 6529685098267757690L;

	public ChauffeurList() {
		chauffeurList = new ArrayList<Chauffeur>();
	}

	public ChauffeurList(ArrayList<Chauffeur> chauffeurList) {
		ChauffeurList.chauffeurList = chauffeurList;
	}

	public static void addChauffeur(Chauffeur chauffeur) {
		chauffeurList.add(chauffeur);
	}

	public void removeChauffeur(int index) {
		chauffeurList.remove(index);
	}

	public Chauffeur getChauffeur(int index) {
		return chauffeurList.get(index).copy();
	}

	public void setList(ArrayList<Chauffeur> chauffeurList) {
		ChauffeurList.chauffeurList = chauffeurList;
	}

	public int getSize() {
		return chauffeurList.size();
	}

	public ArrayList<Chauffeur> getChauffeurList() {
		ArrayList<Chauffeur> allChauffeurs = new ArrayList<Chauffeur>();
		for (int i = 0; i < chauffeurList.size(); i++) {
			allChauffeurs.add(chauffeurList.get(i));
		}
		return chauffeurList;
	}

	public static ArrayList<Chauffeur> readFromFile() {
		try {

			// read object from file
			Path path = Paths.get(ChauffeurList.class.getResource(".").toURI());
			FileInputStream fis = new FileInputStream(path.getParent()
					+ "\\database\\chauffeurList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Chauffeur> result = (ArrayList<Chauffeur>) ois
					.readObject();
			ois.close();

			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			ArrayList<Chauffeur> result = new ArrayList<Chauffeur>();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void writeToFile() {
		try {

			Path path = Paths.get(Chauffeur.class.getResource(".").toURI());
			FileOutputStream fos = new FileOutputStream(path.getParent()
					+ "\\database\\chauffeurList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(chauffeurList);
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

}
