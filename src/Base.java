import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class Base extends JFrame {

	protected static ChauffeurList chauffeurList;
	protected static VehicleList vehicleList;
	protected static RouteList routeList;
	protected static ServiceList serviceList;

	protected ButtonHandler handler = new ButtonHandler();
	protected JTabbedPane tabbedPane;
	protected static JXDatePicker datePicker;

	final JFrame frame = new JFrame(Base.class.getSimpleName());

	protected JButton buttonNew;
	protected static JButton buttonView;
	protected static JButton buttonDelete;

	protected JScrollPane servicePane;
	protected JScrollPane chauffeurPane;
	protected JScrollPane vehiclePane;
	protected JScrollPane routePane;

	protected static JTable tableChauffeur;
	protected static JTable tableVehicle;
	protected static JTable tableRoute;
	protected static JTable tableService;

	protected JComponent panel1;
	protected JComponent panel2;
	protected JComponent panel3;
	protected JComponent panel4;

	public Base() {
		super("VIABus");
		createComponents();
		addComponentsToFrame();
		initializeComponents();
	}

	public void createComponents() {
		chauffeurList = new ChauffeurList(ChauffeurList.readFromFile());
		vehicleList = new VehicleList(VehicleList.readFromFile());
		routeList = new RouteList(RouteList.readFromFile());
		serviceList = new ServiceList(ServiceList.readFromFile());

		buttonNew = new JButton("New");
		buttonView = new JButton("View");
		buttonDelete = new JButton("Delete");
		buttonNew.addActionListener(handler);
		buttonView.addActionListener(handler);
		buttonDelete.addActionListener(handler);
	}

	public void initializeComponents() {
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	public JComponent makeContentPanel(Component comp) {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(comp);
		return panel;
	}

	public static JTable makeTable(String fileName) {
		String[] columnNames;
		DefaultTableModel tableModel;
		JXTable table;
		int listSize;
		switch (fileName) {

		// /////////////////////////Chauffeur///////////////////////////
		case "chauffeurList":
			columnNames = new String[] { "First Name", "Last Name", "ID",
					"Mobile Phone", "On Trip", "In Transit", "Available" };

			tableModel = new DefaultTableModel(columnNames, 0);
			table = new JXTable(tableModel) {
				{
					setColumnControlVisible(true);
					getSelectionModel().addListSelectionListener(
							new RowListener());
					setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				}
			};

			buttonView.setEnabled(false);
			buttonDelete.setEnabled(false);

			try {
				listSize = chauffeurList.getSize();
			} catch (NullPointerException e) {
				listSize = 0;
			}

			for (int i = 0; i < listSize; i++) {
				Chauffeur driver = chauffeurList.getChauffeur(i);
				Object[] objs = { driver.getFirstName(), driver.getLastName(),
						driver.getEmployeeId(), driver.getMobile(),
						driver.isOnTrip(), driver.isInTransit(),
						driver.isAvailable() };
				tableModel.addRow(objs);
			}
			return table;

			// /////////////////////////Vehicle///////////////////////////
		case "vehicleList":
			columnNames = new String[] { "Model", "Registration", "Capacity",
					"Type" };

			tableModel = new DefaultTableModel(columnNames, 0);
			table = new JXTable(tableModel) {
				{
					setColumnControlVisible(true);
					getSelectionModel().addListSelectionListener(
							new RowListener());
					setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				}
			};

			buttonView.setEnabled(false);
			buttonDelete.setEnabled(false);

			try {
				listSize = vehicleList.getSize();
			} catch (NullPointerException e) {
				listSize = 0;
			}

			for (int i = 0; i < listSize; i++) {
				Vehicle vehicle = vehicleList.getVehicle(i);
				Object[] objs = { vehicle.getModel(),
						vehicle.getRegistration(), vehicle.getCapacity(),
						vehicle.getType(), };
				tableModel.addRow(objs);
			}
			return table;

			// /////////////////////////Route///////////////////////////
		case "routeList":
			columnNames = new String[] { "Departure", "Destination",
					"Distance", "Duration" };

			tableModel = new DefaultTableModel(columnNames, 0);
			table = new JXTable(tableModel) {
				{
					setColumnControlVisible(true);
					getSelectionModel().addListSelectionListener(
							new RowListener());
					setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					setRowSorter(new TableRowSorter(this.getModel()));
				}
			};

			buttonView.setEnabled(false);
			buttonDelete.setEnabled(false);

			try {
				listSize = routeList.getSize();
			} catch (NullPointerException e) {
				listSize = 0;
			}

			for (int i = 0; i < listSize; i++) {
				Route route = routeList.getRoute(i);
				Object[] objs = { route.getDeparture(), route.getDestination(),
						route.getDistance(), route.getDuration(), };
				tableModel.addRow(objs);
			}
			return table;

			// /////////////////////////Service///////////////////////////
		case "serviceList":
			columnNames = new String[] { "Departure", "Destination",
					"Date of Departure", "Date of Arrival",
					"Time of Departure", "Time of Arrival", "Service" };

			tableModel = new DefaultTableModel(columnNames, 0);
			table = new JXTable(tableModel) {
				{
					setColumnControlVisible(true);
					getSelectionModel().addListSelectionListener(
							new RowListener());
					setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					setRowSorter(new TableRowSorter(this.getModel()));
				}
			};

			buttonView.setEnabled(false);
			// buttonDelete.setEnabled(false);

			return table;

		}

		return null;
	};

	public static class RowListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				buttonView.setEnabled(true);
				buttonDelete.setEnabled(true);
				return;
			}
		}
	}

	public void addComponentsToFrame() {

		datePicker = new JXDatePicker();
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		tableChauffeur = makeTable("chauffeurList");
		tableVehicle = makeTable("vehicleList");
		tableRoute = makeTable("routeList");
		tableService = makeTable("serviceList");

		JPanel options = new JPanel();
		options.setLayout(new GridLayout(3, 1, 0, 8));
		options.add(buttonNew);
		options.add(buttonView);
		options.add(buttonDelete);

		JPanel sidePane = new JPanel();
		sidePane.setLayout(new BoxLayout(sidePane, BoxLayout.Y_AXIS));
		sidePane.add(datePicker);
		sidePane.add(Box.createRigidArea(new Dimension(0, 20)));
		sidePane.add(options);

		servicePane = new JScrollPane(tableService);
		chauffeurPane = new JScrollPane(tableChauffeur);
		vehiclePane = new JScrollPane(tableVehicle);
		routePane = new JScrollPane(tableRoute);

		tabbedPane = new JTabbedPane();

		panel1 = makeContentPanel(servicePane);
		tabbedPane.addTab("Services", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		panel2 = makeContentPanel(chauffeurPane);
		tabbedPane.addTab("Chauffeurs", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		panel3 = makeContentPanel(vehiclePane);
		tabbedPane.addTab("Vehicles", panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		panel4 = makeContentPanel(routePane);
		tabbedPane.addTab("Routes", panel4);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {

				for (int i = ((DefaultTableModel) tableService.getModel()).getRowCount() - 1; i>=0; i--) {
					((DefaultTableModel) tableService.getModel()).removeRow(i);
				}

				
				for (int j = 0; j < serviceList.getSize(); j++) {
					Service service = serviceList.getService(j);
					if (service.getSchedule().getDateStart()
							.equals(datePicker.getDate())) {

						Object[] objs = {
								service.getRoute().getDeparture(),
								service.getRoute().getDestination(),
								service.getSchedule().getDateStart().toString(),
								service.getSchedule().getDateEnd().toString(),
								service.getSchedule().getTimeStart().toString(),
								service.getSchedule().getTimeEnd().toString(),
								service.getService() };
						((DefaultTableModel) tableService.getModel())
								.addRow(objs);
					}
				}

			}

		};
		datePicker.addPropertyChangeListener(listener);

		JPanel contentPane = new JPanel(new FlowLayout());
		contentPane.add(sidePane, BorderLayout.EAST);
		contentPane.add(tabbedPane, BorderLayout.WEST);

		setContentPane(contentPane);

	}

	public static void main(String[] args) {
		Base frame = new Base();
	}

	public class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			// ////////////////////////////////////// Chauffeur Tab
			if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
					.equalsIgnoreCase("chauffeurs")) {

				// NEW
				if (event.getSource() == buttonNew) {
					JFrame WindowNew = new JFrame();
					new Window_Chauffeur("New Chauffeur");

					// DELETE
				} else if (event.getSource() == buttonDelete) {
					String cell = (String) tableChauffeur.getValueAt(
							tableChauffeur.getSelectedRow(), tableChauffeur
									.getColumn("ID").getModelIndex());
					for (int i = 0; i < chauffeurList.getSize(); i++) {
						if (chauffeurList.getChauffeur(i).getEmployeeId() == cell) {
							chauffeurList.removeChauffeur(i);
							((DefaultTableModel) tableChauffeur.getModel())
									.removeRow(tableChauffeur.getSelectedRow());
							ChauffeurList.writeToFile();
						}
					}

					// VIEW
				} else if (event.getSource() == buttonView) {
					JFrame WindowView = new JFrame();
					String cell = (String) tableChauffeur.getValueAt(
							tableChauffeur.getSelectedRow(), tableChauffeur
									.getColumn("ID").getModelIndex());
					new Window_Chauffeur("View Chauffeur", cell);
				}
			}

			// ////////////////////////////////////// Vehicle Tab
			if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
					.equalsIgnoreCase("vehicles")) {

				// NEW
				if (event.getSource() == buttonNew) {
					JFrame WindowNew = new JFrame();
					new Window_Vehicle("New Vehicle");

					// DELETE
				} else if (event.getSource() == buttonDelete) {
					String cell = (String) tableVehicle.getValueAt(tableVehicle
							.getSelectedRow(),
							tableVehicle.getColumn("Registration")
									.getModelIndex());
					for (int i = 0; i < vehicleList.getSize(); i++) {
						if (vehicleList.getVehicle(i).getRegistration() == cell) {
							vehicleList.removeVehicle(i);
							((DefaultTableModel) tableVehicle.getModel())
									.removeRow(tableVehicle.getSelectedRow());
							VehicleList.writeToFile();
						}
					}

					// VIEW
				} else if (event.getSource() == buttonView) {
					JFrame WindowView = new JFrame();
					String cell = (String) tableVehicle.getValueAt(tableVehicle
							.getSelectedRow(),
							tableVehicle.getColumn("Registration")
									.getModelIndex());
					new Window_Vehicle("View Vehicle", cell);
				}
			}

			// ////////////////////////////////////// Route Tab
			if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
					.equalsIgnoreCase("routes")) {

				// NEW
				if (event.getSource() == buttonNew) {
					JFrame WindowNew = new JFrame();
					new Window_Route("New Route");

					// DELETE
				} else if (event.getSource() == buttonDelete) {
					String departure = (String) tableRoute.getValueAt(
							tableRoute.getSelectedRow(),
							tableRoute.getColumn("Departure").getModelIndex());
					String destination = (String) tableRoute
							.getValueAt(tableRoute.getSelectedRow(), tableRoute
									.getColumn("Destination").getModelIndex());
					for (int i = 0; i < routeList.getSize(); i++) {
						if (routeList.getRoute(i).getDeparture() == departure) {
							if (routeList.getRoute(i).getDestination() == destination) {
								routeList.removeRoute(i);
								((DefaultTableModel) tableRoute.getModel())
										.removeRow(tableRoute.getSelectedRow());
								RouteList.writeToFile();
							}
						}
					}

					// VIEW
				} else if (event.getSource() == buttonView) {
					JFrame WindowView = new JFrame();
					String departure = (String) tableRoute.getValueAt(
							tableRoute.getSelectedRow(),
							tableRoute.getColumn("Departure").getModelIndex());
					String destination = (String) tableRoute
							.getValueAt(tableRoute.getSelectedRow(), tableRoute
									.getColumn("Destination").getModelIndex());
					new Window_Route("View Route", departure, destination);
				}
			}
			/*
			 * // ////////////////////////////////////// Passenger Tab if
			 * (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
			 * .equalsIgnoreCase("passengers")) {
			 * 
			 * // NEW if (event.getSource() == buttonNew) { JFrame WindowNew =
			 * new JFrame(); new Window_Passenger("New Passenger");
			 * 
			 * // DELETE } else if (event.getSource() == buttonDelete) { String
			 * cell = (String) tablePassenger.getValueAt(
			 * tablePassenger.getSelectedRow(), tablePassenger
			 * .getColumn("E-mail").getModelIndex()); for (int i = 0; i <
			 * passengerList.getSize(); i++) { if
			 * (passengerList.getPassenger(i).getEmail() == cell) {
			 * passengerList.removePassenger(i); ((DefaultTableModel)
			 * tablePassenger.getModel())
			 * .removeRow(tablePassenger.getSelectedRow());
			 * PassengerList.writeToFile(); } }
			 * 
			 * // VIEW } else if (event.getSource() == buttonView) { JFrame
			 * WindowView = new JFrame(); String cell = (String)
			 * tablePassenger.getValueAt( tablePassenger.getSelectedRow(),
			 * tablePassenger .getColumn("E-mail").getModelIndex()); new
			 * Window_Passenger("View Passenger", cell); } }
			 * 
			 * // ////////////////////////////////////// Customer Tab if
			 * (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
			 * .equalsIgnoreCase("customers")) {
			 * 
			 * // NEW if (event.getSource() == buttonNew) { JFrame WindowNew =
			 * new JFrame(); new Window_Customer("New Customer");
			 * 
			 * // DELETE } else if (event.getSource() == buttonDelete) { String
			 * cell = (String) tableCustomer.getValueAt(
			 * tableCustomer.getSelectedRow(), tableCustomer
			 * .getColumn("E-mail").getModelIndex()); for (int i = 0; i <
			 * customerList.getSize(); i++) { if
			 * (customerList.getCustomer(i).getEmail() == cell) {
			 * customerList.removeCustomer(i); ((DefaultTableModel)
			 * tableCustomer.getModel())
			 * .removeRow(tableCustomer.getSelectedRow());
			 * CustomerList.writeToFile(); } }
			 * 
			 * // VIEW } else if (event.getSource() == buttonView) { JFrame
			 * WindowView = new JFrame(); String cell = (String)
			 * tableCustomer.getValueAt( tableCustomer.getSelectedRow(),
			 * tableCustomer .getColumn("E-mail").getModelIndex()); new
			 * Window_Customer("View Customer", cell); } }
			 */
			// ////////////////////////////////////// Service Tab
			if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
					.equalsIgnoreCase("services")) {

				// NEW
				if (event.getSource() == buttonNew) {
					JFrame WindowNew = new JFrame();
					new Window_Service("New Service");

					// DELETE
				} else if (event.getSource() == buttonDelete) {
					String dateStart = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Date of Departure")
									.getModelIndex());
					String dateEnd = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Date of Arrival")
									.getModelIndex());
					String timeStart = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Time of Departure")
									.getModelIndex());
					String timeEnd = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Time of Arrival")
									.getModelIndex());

					for (int i = 0; i < Base.serviceList.getSize(); i++) {
						if (Base.serviceList.getService(i).getSchedule()
								.getDateStart().toString()
								.equalsIgnoreCase(dateStart))
							if (Base.serviceList.getService(i).getSchedule()
									.getDateEnd().toString()
									.equalsIgnoreCase(dateEnd))
								if (Base.serviceList.getService(i)
										.getSchedule().getTimeStart()
										.toString().equalsIgnoreCase(timeStart))
									if (Base.serviceList.getService(i)
											.getSchedule().getTimeEnd()
											.toString()
											.equalsIgnoreCase(timeEnd)) {

										Chauffeur chauffeurService = serviceList
												.getService(i).getChauffeur();

										for (int n = 0; n < Base.chauffeurList
												.getSize(); n++) {
											if (Base.chauffeurList
													.getChauffeur(n)
													.getEmployeeId()
													.equalsIgnoreCase(
															chauffeurService
																	.getEmployeeId())) {

												Chauffeur chauffeurFromList = Base.chauffeurList
														.getChauffeur(n);

												for (int m = 0; m < chauffeurFromList
														.getSchedule().size(); m++) {

													if (chauffeurFromList
															.getSchedule()
															.get(m)
															.getDateStart()
															.toString()
															.equals(Base.serviceList
																	.getService(
																			i)
																	.getSchedule()
																	.getDateStart()
																	.toString()))
														if (chauffeurFromList
																.getSchedule()
																.get(m)
																.getDateEnd()
																.toString()
																.equals(Base.serviceList
																		.getService(
																				i)
																		.getSchedule()
																		.getDateEnd()
																		.toString()))
															if (chauffeurFromList
																	.getSchedule()
																	.get(m)
																	.getTimeStart()
																	.toString()
																	.equals(Base.serviceList
																			.getService(
																					i)
																			.getSchedule()
																			.getTimeStart()
																			.toString()))
																if (chauffeurFromList
																		.getSchedule()
																		.get(m)
																		.getTimeEnd()
																		.toString()
																		.equals(Base.serviceList
																				.getService(
																						i)
																				.getSchedule()
																				.getTimeEnd()
																				.toString())) {

																	chauffeurService
																			.getSchedule()
																			.remove(m);

																	for (int j = 0; j < Base.tableChauffeur
																			.getModel()
																			.getRowCount(); j++) {
																		String cell = (String) Base.tableChauffeur
																				.getValueAt(
																						j,
																						Base.tableChauffeur
																								.getColumn(
																										"ID")
																								.getModelIndex());

																		if (chauffeurService
																				.getEmployeeId()
																				.equalsIgnoreCase(
																						cell))
																			((DefaultTableModel) Base.tableChauffeur
																					.getModel())
																					.removeRow(j);
																	}

																	Base.chauffeurList
																			.removeChauffeur(n);

																}
												}

											}
										}

										ChauffeurList
												.addChauffeur(chauffeurService);
										Object[] objs = {
												chauffeurService.getFirstName(),
												chauffeurService.getLastName(),
												chauffeurService
														.getEmployeeId(),
												chauffeurService.getMobile(),
												chauffeurService.isOnTrip(),
												chauffeurService.isInTransit(),
												chauffeurService.isAvailable() };
										((DefaultTableModel) Base.tableChauffeur
												.getModel()).addRow(objs);
										ChauffeurList.writeToFile();

										Base.serviceList.removeService(i);
										System.out.println(tableService
												.getSelectedRow());
										((DefaultTableModel) tableService
												.getModel())
												.removeRow(tableService
														.getSelectedRow());

										ServiceList.writeToFile();
									}
					}

					// VIEW
				} else if (event.getSource() == buttonView) {
					JFrame WindowView = new JFrame();
					String dateStart = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Date of Departure")
									.getModelIndex());
					String dateEnd = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Date of Arrival")
									.getModelIndex());
					String timeStart = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Time of Departure")
									.getModelIndex());
					String timeEnd = (String) tableService.getValueAt(
							tableService.getSelectedRow(), tableService
									.getColumn("Time of Arrival")
									.getModelIndex());
					new Window_Service("View Service", dateStart, dateEnd,
							timeStart, timeEnd);
				}
			}
		}
	}
}
