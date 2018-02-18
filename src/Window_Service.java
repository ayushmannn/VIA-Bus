import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Window_Service extends JFrame {

	protected JFrame frame = new JFrame(Base.class.getSimpleName());

	protected JButton buttonSave;
	protected JButton buttonClose;
	protected JButton buttonNewReservation;
	protected JButton buttonViewReservation;
	protected JButton buttonDeleteReservation;

	protected ButtonHandler handler = new ButtonHandler();

	private JXDatePicker datePickerStart = new JXDatePicker();;
	private JXDatePicker datePickerEnd = new JXDatePicker();;

	protected JPanel contentPane;
	protected JPanel timePane;

	protected JLabel labelService;
	protected JLabel labelRoute;
	protected JLabel labelDate;
	protected JLabel labelTime;
	protected JLabel labelChauffeur;
	protected JLabel labelVehicle;

	protected JTextFieldX textFieldDepartureTime;
	protected JTextFieldX textFieldArrivalTime;

	protected JTextFieldX[] fields;

	protected JComboBox comboBoxService;
	protected JComboBox comboBoxRoute;
	protected JComboBox comboBoxChauffeur;
	protected JComboBox comboBoxVehicle;
	
	protected JTable tableReservations;
	protected JScrollPane reservationPane;
	
	protected GridBagConstraints c;

	public static boolean requiredFieldsMissing;
	public static boolean invalidFieldsPresent;

	protected int j = 4;
	
	
	protected Service service;
	
	protected String dateStart = "";
	protected String dateEnd = "";
	protected String timeStart = "";
	protected String timeEnd = "";

	protected Route route;

	protected ArrayList<Reservation> reservations;
	
	protected Window_Service windowService = this;

	public Window_Service(String windowName) {
		super(windowName);
		createComponents();
		addComponentsToFrame();
		initializeComponents();
	}

	public Window_Service(String windowName, String dateStart, String dateEnd,
			String timeStart, String timeEnd) {
		super(windowName);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		createComponents();
		addComponentsToFrame();
		initializeComponents();
		addInfo(dateStart, dateEnd, timeStart, timeEnd);
	}

	public void createComponents() {
		// Buttons
		buttonSave = new JButton("Save Changes and Close");
		buttonClose = new JButton("Exit Without Saving");
		buttonNewReservation = new JButton("New Reservation");
		buttonViewReservation = new JButton("View Reservation");
		buttonDeleteReservation = new JButton("Delete Reservation");
		
		// Labels
		labelService = new JLabel("Service");
		labelRoute = new JLabel("Route");
		labelDate = new JLabel("Date");
		labelTime = new JLabel("Time");
		labelChauffeur = new JLabel("Chauffeur");
		labelVehicle = new JLabel("Vehicle");

		// Text Fields
		textFieldDepartureTime = new JTextFieldX(10, "Departure");
		textFieldArrivalTime = new JTextFieldX(10, "Arrival");
		textFieldArrivalTime.getTextField().setEnabled(false);

		fields = new JTextFieldX[] { textFieldDepartureTime,
				textFieldArrivalTime };

		// Panels
		contentPane = new JPanel();
		timePane = new JPanel();

		// Validators
		new HighlightListener(textFieldDepartureTime, "both", true, 5);
		new HighlightListener(textFieldArrivalTime, "both", true, 0);

		// Misc
		reservations = new ArrayList<Reservation>();
		c = new GridBagConstraints();
		datePickerStart.setDate(new Date());
		datePickerEnd.setEnabled(false);
		tableReservations = makeTable();
		reservationPane = new JScrollPane(tableReservations);

		// Combo Box
		comboBoxChauffeur = new JComboBox();
		comboBoxService = new JComboBox<String>();
		comboBoxRoute = new JComboBox<Route>();
		comboBoxVehicle = new JComboBox<Vehicle>();

		comboBoxService.addItem("-----");
		comboBoxService.addItem("Travel");
		comboBoxService.addItem("One-day Trips");
		comboBoxService.addItem("Bus-and-Chauffeur");
		comboBoxService.addItem("Party-bus");

		// Action Handlers
		buttonSave.addActionListener(handler);
		buttonClose.addActionListener(handler);
		buttonNewReservation.addActionListener(handler);
		buttonViewReservation.addActionListener(handler);
		
		comboBoxChauffeur = new JComboBox();
		comboBoxService.addActionListener(handler);
		comboBoxRoute.addActionListener(handler);
		comboBoxVehicle.addActionListener(handler);
		

		
		service = new Service();

		textFieldDepartureTime.getTextField().getDocument()
				.addDocumentListener(new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent e) {
						warn();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						warn();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						warn();
					}

					public void warn() {
						try {
							String string = textFieldDepartureTime
									.getTextField().getText();

							if (string.length() == 5) {
								String[] parts = string.split(":");
								if (Integer.parseInt(parts[0]) <= 23
										&& Integer.parseInt(parts[1]) <= 59) {
									string = comboBoxRoute.getSelectedItem()
											.toString();
									parts = string.split("-");

									for (int i = 0; i < Base.routeList
											.getSize(); i++) {
										if (Base.routeList.getRoute(i)
												.getDeparture()
												.equalsIgnoreCase(parts[0]))
											if (Base.routeList.getRoute(i)
													.getDestination()
													.equalsIgnoreCase(parts[1])) {
												route = Base.routeList
														.getRoute(i);
											}
									}

									string = textFieldDepartureTime
											.getTextField().getText();
									parts = string.split(":");

									int hour = route.getDuration().getHour()
											+ Integer.parseInt(parts[0]);
									int minute = route.getDuration()
											.getMinute()
											+ Integer.parseInt(parts[1]);
									while (minute > 59) {
										hour++;
										minute = minute - 60;
									}

									int daysForward = 0;

									while (hour > 23) {
										daysForward++;
										hour = hour - 24;
									}

									Calendar c = Calendar.getInstance();
									c.setTime(datePickerStart.getDate());
									c.add(Calendar.DATE, daysForward);
									Date newDate = c.getTime();

									datePickerEnd.setDate(newDate);

									String all = null;

									int length = String.valueOf(hour).length();
									for (int i = 0; i < 2 - length; i++)
										all = "0";

									if (all != null)
										all = all + hour + ":";
									else
										all = hour + ":";

									length = String.valueOf(minute).length();
									for (int i = 0; i < 2 - length; i++)
										all = all + "0";
									all = all + minute;

									textFieldArrivalTime.getTextField()
											.setText(all);
									populateChauffeur();
									populateVehicles();

								}
							} else {
								textFieldArrivalTime.getTextField().setText("");
							}
						} catch (NumberFormatException e) {

						} catch (ArrayIndexOutOfBoundsException e) {

						}

					}

				});
	}

	public JTable makeTable() {
		
		String[] columnNames;
		DefaultTableModel tableModel;
		JXTable table;
		int listSize;

		for (int i = 0; i < Base.serviceList.getSize(); i++) {
			if (Base.serviceList.getService(i).getSchedule().getDateStart()
					.toString().equalsIgnoreCase(dateStart))
				if (Base.serviceList.getService(i).getSchedule().getDateEnd()
						.toString().equalsIgnoreCase(dateEnd))
					if (Base.serviceList.getService(i).getSchedule()
							.getTimeStart().toString()
							.equalsIgnoreCase(timeStart))
						if (Base.serviceList.getService(i).getSchedule()
								.getTimeEnd().toString()
								.equalsIgnoreCase(timeEnd)) {
							service = Base.serviceList.getService(i);
						}
		}
		
		columnNames = new String[] { "Customer", "Nr. of Passengers", "UID"};

		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JXTable(tableModel) {
			{
				setColumnControlVisible(true);
				getSelectionModel().addListSelectionListener(new RowListener());
				setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		};

		//buttonNewReservation.setEnabled(false);
		buttonViewReservation.setEnabled(false);

		try {
			listSize = service.getReservations().size();
		} catch (NullPointerException e) {
			listSize = 0;
		}

		for (int i = 0; i < listSize; i++) {
			Reservation reservation = service.getReservations().get(i);
			reservations.add(reservation);
			Object[] objs = { reservation.getCustomer().getFirstName() + " " + reservation.getCustomer().getLastName(), reservation.getPassengers().size(), reservation.getUniqueID() };
			tableModel.addRow(objs);
		}
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		return table;
	}
	
	private class RowListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				//buttonNewReservation.setEnabled(true);
				buttonViewReservation.setEnabled(true);
				return;
			}
		}
	}

	
	public void initializeComponents() {
		// setSize(500, 800);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	private void addComponentsToFrame() {

		contentPane.setLayout(new GridBagLayout());
		timePane.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.ipadx = 5;
		c.gridx = 0;
		c.gridy = 0;
		timePane.add(textFieldDepartureTime.getTextField(), c);
		c.gridx = 1;
		timePane.add(new JLabel("  "), c);
		c.gridx = 2;
		timePane.add(textFieldArrivalTime.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 5, 10, 5);
		contentPane.add(labelService, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 1;
		c.gridy = 0;
		contentPane.add(comboBoxService, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 0;
		c.gridy = 1;
		contentPane.add(labelRoute, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(comboBoxRoute, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(labelDate, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 1;
		c.gridy = 2;
		contentPane.add(datePickerStart, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 1;
		c.gridy = 3;
		contentPane.add(datePickerEnd, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 4;
		contentPane.add(labelTime, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 1;
		c.gridy = 4;
		contentPane.add(timePane, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 0;
		c.gridy = 5;
		contentPane.add(labelChauffeur, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 1;
		c.gridy = 5;
		contentPane.add(comboBoxChauffeur, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 0;
		c.gridy = 6;
		contentPane.add(labelVehicle, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 1;
		c.gridy = 6;
		contentPane.add(comboBoxVehicle, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 2;
		c.gridy = 0;
		contentPane.add(buttonNewReservation, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 3;
		c.gridy = 0;
		contentPane.add(buttonViewReservation, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		contentPane.add(buttonDeleteReservation, c);

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 5;
		contentPane.add(reservationPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 2;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		contentPane.add(buttonSave, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 5;
		c.gridx = 3;
		c.gridy = 8;
		contentPane.add(buttonClose, c);

		setContentPane(contentPane);
	}

	protected void addInfo(String dateStart, String dateEnd, String timeStart,
			String timeEnd) {
		populateChauffeur();
		populateRoutes();
		populateVehicles();
		for (int i = 0; i < Base.serviceList.getSize(); i++) {
			if (Base.serviceList.getService(i).getSchedule().getDateStart()
					.toString().equalsIgnoreCase(dateStart))
				if (Base.serviceList.getService(i).getSchedule().getDateEnd()
						.toString().equalsIgnoreCase(dateEnd))
					if (Base.serviceList.getService(i).getSchedule()
							.getTimeStart().toString()
							.equalsIgnoreCase(timeStart))
						if (Base.serviceList.getService(i).getSchedule()
								.getTimeEnd().toString()
								.equalsIgnoreCase(timeEnd)) {
							service = Base.serviceList.getService(i);
						}
		}
		datePickerStart.setDate(service.getSchedule().getDateStart());
		datePickerEnd.setDate(service.getSchedule().getDateEnd());
		textFieldDepartureTime.getTextField().setText(
				service.getSchedule().getTimeStart().toString());
		textFieldArrivalTime.getTextField().setText(
				service.getSchedule().getTimeEnd().toString());
		comboBoxService.setSelectedItem(service.getService());
		
		for (int i = 0; i<comboBoxRoute.getItemCount(); i++) {
			if (comboBoxRoute.getItemAt(i).toString().equals(service.getRoute().toString()))
					comboBoxRoute.setSelectedIndex(i);
		}
		for (int i = 0; i<comboBoxVehicle.getItemCount(); i++) {
			if (comboBoxVehicle.getItemAt(i).toString().equals(service.getSchedule().getAssignedVehicle().toString()))
				comboBoxVehicle.setSelectedIndex(i);
		}
		for (int i = 0; i<comboBoxChauffeur.getItemCount(); i++) {
			if (comboBoxChauffeur.getItemAt(i).toString().equals(service.getChauffeur().toString()))
				comboBoxChauffeur.setSelectedIndex(i);
		}
	}

	protected void populateChauffeur() {

		/*
		 * ArrayList<Schedule> tempSchedule = tempChauffeur.getSchedule(); try {
		 * for (int j = 0; j < tempSchedule.size(); j++) { Date tempDateStart =
		 * tempSchedule.get(j).getDateStart(); Date tempDateEnd =
		 * tempSchedule.get(j).getDateEnd(); Time tempTimeStart =
		 * tempSchedule.get(j).getTimeStart(); Time tempTimeEnd =
		 * tempSchedule.get(j).getTimeEnd();
		 * 
		 * System.out.println("Equal start date " +
		 * tempDateStart.equals(datePickerStart.getDate()));
		 * System.out.println("Equal end date " +
		 * !tempDateEnd.equals(datePickerEnd.getDate()));
		 * System.out.println("Equal dates " +
		 * tempDateStart.equals(datePickerEnd.getDate()));
		 * 
		 * if ((tempDateStart.equals(datePickerStart.getDate())) &&
		 * (tempDateEnd.equals(datePickerEnd.getDate())) &&
		 * (tempDateStart.equals(tempDateEnd))) { if ((tempTimeStart.getHour() <
		 * Integer .parseInt(textFieldDepartureTime.getTextField() .getText()))
		 * && tempTimeEnd.getHour() < Integer .parseInt(textFieldArrivalTime
		 * .getTextField().getText()) && (tempTimeStart.getHour() < Integer
		 * .parseInt(textFieldArrivalTime .getTextField().getText())) &&
		 * (tempTimeEnd.getHour() < tempTimeStart .getHour())) {
		 * comboBoxChauffeur.addItem(tempChauffeur); } else if
		 * ((tempTimeStart.getHour() > Integer
		 * .parseInt(textFieldDepartureTime.getTextField() .getText())) &&
		 * (tempTimeEnd.getHour() > Integer .parseInt(textFieldArrivalTime
		 * .getTextField().getText())) && (tempTimeStart.getHour() > Integer
		 * .parseInt(textFieldArrivalTime .getTextField().getText())) &&
		 * (tempTimeEnd.getHour() > tempTimeStart .getHour())) {
		 * comboBoxChauffeur.addItem(tempChauffeur); } } else if
		 * ((tempDateEnd.equals(datePickerEnd.getDate())) &&
		 * (tempDateEnd.equals(datePickerStart.getDate())) &&
		 * !tempDateStart.equals(datePickerStart.getDate())) { String
		 * stringDeparture = textFieldDepartureTime .getTextField().getText();
		 * String[] partsDeparture = stringDeparture.split(":"); if
		 * (tempTimeEnd.getHour() < Integer .parseInt(partsDeparture[0])) {
		 * 
		 * comboBoxChauffeur.addItem(tempChauffeur); } } else if
		 * (tempDateStart.equals(datePickerStart.getDate()) &&
		 * tempDateStart.equals(datePickerEnd.getDate()) &&
		 * !tempDateEnd.equals(datePickerEnd.getDate())) { String stringArrival
		 * = textFieldArrivalTime .getTextField().getText(); String[]
		 * partsArrival = stringArrival.split(":"); if (tempTimeStart.getHour()
		 * > Integer .parseInt(partsArrival[0])) {
		 * comboBoxChauffeur.addItem(tempChauffeur); } }
		 * 
		 * } } catch (NullPointerException e) {
		 * comboBoxChauffeur.addItem(tempChauffeur); }
		 */
		comboBoxChauffeur.removeAllItems();
		comboBoxChauffeur.addItem("-----");
		for (int i = 0; i < Base.chauffeurList.getSize(); i++) {
			Chauffeur tempChauffeur = Base.chauffeurList.getChauffeur(i);
			comboBoxChauffeur.addItem(tempChauffeur);
		}
	}

	protected void populateRoutes() {
		comboBoxRoute.removeAllItems();
		comboBoxRoute.addItem("-----");
		if (comboBoxService.getSelectedItem().toString()
				.equalsIgnoreCase("Travel")) {
			for (int i = 0; i < Base.routeList.getSize(); i++) {
				Route route = Base.routeList.getRoute(i);
				if (route.getDuration().getHour() > 23)
					comboBoxRoute.addItem(route);
			}
		} else if (comboBoxService.getSelectedItem().toString()
				.equalsIgnoreCase("One-day Trips")) {
			for (int i = 0; i < Base.routeList.getSize(); i++) {
				Route route = Base.routeList.getRoute(i);
				if (route.getDuration().getHour() <= 23)
					comboBoxRoute.addItem(route);
			}
		} else {
			for (int i = 0; i < Base.routeList.getSize(); i++) {
				Route route = Base.routeList.getRoute(i);
				comboBoxRoute.addItem(route);
			}
		}
	}

	protected void populateVehicles() {
		comboBoxVehicle.removeAllItems();
		comboBoxVehicle.addItem("-----");
		for (int i = 0; i < Base.vehicleList.getSize(); i++) {
			Vehicle vehicle = Base.vehicleList.getVehicle(i);
			comboBoxVehicle.addItem(vehicle);
		}
	}

	protected class ButtonHandler implements ActionListener {

		protected String message;
		protected String required;
		protected String invalid;
		protected String output;
		protected boolean needInvalid;

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == comboBoxService) {
				comboBoxRoute.removeAllItems();
				comboBoxRoute.addItem("-----");
				populateRoutes();
			}
			
			if (event.getSource() == buttonNewReservation) {
				for (int i = 0; i < Base.serviceList.getSize(); i++) {
					if (Base.serviceList.getService(i).getSchedule().getDateStart()
							.toString().equalsIgnoreCase(dateStart))
						if (Base.serviceList.getService(i).getSchedule().getDateEnd()
								.toString().equalsIgnoreCase(dateEnd))
							if (Base.serviceList.getService(i).getSchedule()
									.getTimeStart().toString()
									.equalsIgnoreCase(timeStart))
								if (Base.serviceList.getService(i).getSchedule()
										.getTimeEnd().toString()
										.equalsIgnoreCase(timeEnd))
									service = Base.serviceList.getService(i);
				}
				new Window_Reservation("New Reservation", windowService, service);
			}
			
			if (event.getSource() == buttonViewReservation) {
				String uniqueId = (String) tableReservations.getValueAt(
						tableReservations.getSelectedRow(), tableReservations
								.getColumn("UID")
								.getModelIndex());
				new Window_Reservation("New Reservation", windowService, uniqueId, service);
			}
			
			if (event.getSource() == buttonDeleteReservation) {
				String uniqueId = (String) tableReservations.getValueAt(
						tableReservations.getSelectedRow(), tableReservations
								.getColumn("UID")
								.getModelIndex());
				for (int i = 0; i < reservations.size(); i++) {
					Reservation reservation = reservations.get(i);	
					if (reservation.getUniqueID() == uniqueId) {
						reservations.remove(i);
						((DefaultTableModel) tableReservations.getModel())
								.removeRow(tableReservations.getSelectedRow());
					}
				}
			}
						

			if (event.getSource() == buttonClose)
				dispose();

			if (event.getSource() == buttonSave) {
				invalidFieldsPresent = false;
				requiredFieldsMissing = false;
				message = "";
				required = "";
				invalid = "";
				output = "";
				needInvalid = false;

				for (int i = 0; i < fields.length; i++) {
					requiredFieldsMissing = requiredFieldsMissing
							|| fields[i].haltRequired;
					if (fields[i].haltRequired)
						if (message.equalsIgnoreCase("")) {
							message += "The following fields need to be completed in order to proceed:";
							output += "\n •" + fields[i].copy().fieldName;
						} else
							output += "\n •" + fields[i].copy().fieldName;
				}

				message += output;
				output = "";

				for (int i = 0; i < fields.length; i++) {
					invalidFieldsPresent = invalidFieldsPresent
							|| fields[i].haltInvalid;
					if (fields[i].haltInvalid) {
						output += "\n •" + fields[i].copy().fieldName;
						needInvalid = true;
					}
				}

				if (needInvalid)
					if (message.equalsIgnoreCase(""))
						message += "The following fields are invalid, change them in order to proceed:";
					else {
						message += "\n \n The following fields are invalid, change them in order to proceed:";
					}

				message += output;

				if (!requiredFieldsMissing && !invalidFieldsPresent) {
					//Service service = new Service();
					String stringStart = textFieldDepartureTime.getTextField()
							.getText();
					String[] partsStart = stringStart.split(":");

					String stringEnd = textFieldArrivalTime.getTextField()
							.getText();
					String[] partsEnd = stringEnd.split(":");

					Time timeTempStart = new Time(
							Integer.parseInt(partsStart[0]),
							Integer.parseInt(partsStart[1]), 0);

					Time timeTempEnd = new Time(Integer.parseInt(partsEnd[0]),
							Integer.parseInt(partsEnd[1]), 0);

					Chauffeur tempChauffeur = (Chauffeur) comboBoxChauffeur
							.getSelectedItem();

					for (int i = 0; i < Base.chauffeurList.getSize(); i++) {
						if (Base.chauffeurList
								.getChauffeur(i)
								.getEmployeeId()
								.equalsIgnoreCase(tempChauffeur.getEmployeeId())) {
							Base.chauffeurList.removeChauffeur(i);
							for (int j = 0; j < Base.tableChauffeur.getModel()
									.getRowCount(); j++) {
								String cell = (String) Base.tableChauffeur
										.getValueAt(j, Base.tableChauffeur
												.getColumn("ID")
												.getModelIndex());
								if (tempChauffeur.getEmployeeId()
										.equalsIgnoreCase(cell)) {
									((DefaultTableModel) Base.tableChauffeur
											.getModel()).removeRow(j);
								}
							}
						}
					}
					Schedule tempSchedule = new Schedule(
							datePickerStart.getDate(), datePickerEnd.getDate(),
							timeTempStart, timeTempEnd);
					tempSchedule.setAssignedVehicle((Vehicle) comboBoxVehicle.getSelectedItem());
					
					try {
						tempChauffeur.getSchedule().add(tempSchedule);

					} catch (NullPointerException e) {
						tempChauffeur.setSchedule(new ArrayList<Schedule>());
						tempChauffeur.getSchedule().add(tempSchedule);
					}

					ChauffeurList.addChauffeur(tempChauffeur);
					Object[] objs = { tempChauffeur.getFirstName(),
							tempChauffeur.getLastName(),
							tempChauffeur.getEmployeeId(),
							tempChauffeur.getMobile(),
							tempChauffeur.isOnTrip(),
							tempChauffeur.isInTransit(),
							tempChauffeur.isAvailable() };
					((DefaultTableModel) Base.tableChauffeur.getModel())
							.addRow(objs);
					ChauffeurList.writeToFile();

					if (!timeEnd.equalsIgnoreCase(""))
						for (int i = 0; i < Base.serviceList.getSize(); i++) {
							if (Base.serviceList.getService(i).getSchedule()
									.getDateStart().toString()
									.equalsIgnoreCase(dateStart))
								if (Base.serviceList.getService(i)
										.getSchedule().getDateEnd().toString()
										.equalsIgnoreCase(dateEnd))
									if (Base.serviceList.getService(i)
											.getSchedule().getTimeStart()
											.toString()
											.equalsIgnoreCase(timeStart))
										if (Base.serviceList.getService(i)
												.getSchedule().getTimeEnd()
												.toString()
												.equalsIgnoreCase(timeEnd)) {
											Base.serviceList.removeService(i);
											((DefaultTableModel) Base.tableService
													.getModel())
													.removeRow(Base.tableService
															.getSelectedRow());

										}
						}


					service.setService(comboBoxService.getSelectedItem()
							.toString());
					
					service.setSchedule(tempSchedule);
					
					String string = textFieldDepartureTime
							.getTextField().getText();

	
						String[] parts = string.split(":");
						if (Integer.parseInt(parts[0]) <= 23
								&& Integer.parseInt(parts[1]) <= 59) {
							string = comboBoxRoute.getSelectedItem()
									.toString();
							parts = string.split("-");

							for (int i = 0; i < Base.routeList
									.getSize(); i++) {
								if (Base.routeList.getRoute(i)
										.getDeparture()
										.equalsIgnoreCase(parts[0]))
									if (Base.routeList.getRoute(i)
											.getDestination()
											.equalsIgnoreCase(parts[1])) {
										route = Base.routeList
												.getRoute(i);
									}
							}
						}
						service.setRoute(route);
					service.setChauffeur(tempChauffeur);
					service.setReservations(reservations);
					ServiceList.addService(service.copy());
					Object[] objs2 = { service.getRoute().getDeparture(),
							service.getRoute().getDestination(),
							service.getSchedule().getDateStart().toString(),
							service.getSchedule().getDateEnd().toString(),
							service.getSchedule().getTimeStart().toString(),
							service.getSchedule().getTimeEnd().toString(),
							service.getService() };
					//((DefaultTableModel) Base.tableService.getModel()).addRow(objs2);
					ServiceList.writeToFile();
					
					dispose();
					Base.datePicker.firePropertyChange("", false, true);
				} else
					JOptionPane.showMessageDialog(frame, message);
			}

		}
	}

}