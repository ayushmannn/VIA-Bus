import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

public class Window_Reservation extends JFrame {

	protected JFrame frame = new JFrame(Base.class.getSimpleName());

	protected JButton buttonAddCustomer;
	protected JButton buttonViewCustomer;
	protected JButton buttonDeleteCustomer;
	protected JButton buttonAddPassenger;
	protected JButton buttonViewPassenger;
	protected JButton buttonDeletePassenger;
	protected JButton buttonSave;
	protected JButton buttonClose;

	protected ButtonHandler handler = new ButtonHandler();

	protected JPanel contentPane;

	protected JLabel labelSpecialService;
	protected JLabel labelAdults;
	protected JLabel labelChildren;
	protected JLabel labelPassengers;
	protected JLabel labelCustomer;
	protected JLabel labelPrice;

	protected JPanel adultPane;
	protected JPanel childrenPane;
	protected JPanel pricePane;

	protected JTextFieldX textFieldFirstName;
	protected JTextFieldX textFieldLastName;
	protected JTextFieldX textFieldAffiliation;
	protected JTextFieldX textFieldPrice;
	protected JTextFieldX textFieldAdults;
	protected JTextFieldX textFieldChildren;

	protected JTextFieldX[] fields;

	protected GridBagConstraints c;

	protected JTable tablePassenger;

	protected JScrollPane passengerPane;

	public static boolean requiredFieldsMissing;
	public static boolean invalidFieldsPresent;

	protected int j;
	protected int adults = 0;
	protected int kids = 0;

	protected ArrayList<Passenger> passengersArr;
	protected Service service;
	protected Reservation reservation;
	protected Customer customer;
	protected String cell = "";
	protected String uniqueID;

	protected Window_Reservation window = this;
	protected Window_Service windowService;

	public Window_Reservation(String windowName, Window_Service windowService,
			Service service) {
		super(windowName);
		this.windowService = windowService;
		this.service = service;
		createComponents();
		addComponentsToFrame();
		initializeComponents();
	}

	public Window_Reservation(String windowName, Window_Service windowService,
			String cell, Service service) {
		super(windowName);
		this.windowService = windowService;
		this.service = service;
		this.cell = cell;
		createComponents();
		addComponentsToFrame();
		initializeComponents();
		addInfo(cell, service);
	}

	public void createComponents() {

		// Buttons
		buttonAddCustomer = new JButton("Add Customer");
		buttonViewCustomer = new JButton("View Customer");
		buttonDeleteCustomer = new JButton("Delete Customer");

		buttonAddPassenger = new JButton("Add Passenger");
		buttonViewPassenger = new JButton("View Passenger");
		buttonDeletePassenger = new JButton("Delete Passenger");

		buttonSave = new JButton("Save Changes and Close");
		buttonClose = new JButton("Exit Without Saving");

		// Labels

		labelAdults = new JLabel("Adults");
		labelChildren = new JLabel("Children");
		labelPassengers = new JLabel("Passengers");
		labelCustomer = new JLabel("Customer");
		labelPrice = new JLabel("Price");

		// Text Fields

		textFieldFirstName = new JTextFieldX(10, "First Name");
		textFieldLastName = new JTextFieldX(10, "Last Name");
		textFieldAffiliation = new JTextFieldX(10, "Affiliation");
		textFieldChildren = new JTextFieldX(2, "Children");
		textFieldAdults = new JTextFieldX(2, "Adults");
		textFieldPrice = new JTextFieldX(2, "Price");

		fields = new JTextFieldX[] { textFieldFirstName, textFieldLastName,
				textFieldAffiliation };

		// Panels
		contentPane = new JPanel();
		adultPane = new JPanel();
		childrenPane = new JPanel();
		pricePane = new JPanel();

		// Validators
		new HighlightListener(textFieldFirstName, "letters", true, 0);
		new HighlightListener(textFieldLastName, "letters", true, 0);
		new HighlightListener(textFieldAffiliation, "letters", true, 0);

		// Action Handlers
		buttonAddCustomer.addActionListener(handler);
		buttonViewCustomer.addActionListener(handler);
		buttonDeleteCustomer.addActionListener(handler);

		buttonAddPassenger.addActionListener(handler);
		buttonViewPassenger.addActionListener(handler);
		buttonDeletePassenger.addActionListener(handler);

		buttonSave.addActionListener(handler);
		buttonClose.addActionListener(handler);

		// Misc
		passengersArr = new ArrayList<Passenger>();
		tablePassenger = makeTable();
		passengerPane = new JScrollPane(tablePassenger);

		c = new GridBagConstraints();

		textFieldFirstName.getTextField().setEnabled(false);
		textFieldLastName.getTextField().setEnabled(false);
		textFieldAffiliation.getTextField().setEnabled(false);

		textFieldAdults.getTextField().setEnabled(false);
		textFieldChildren.getTextField().setEnabled(false);
		
		reservation = new Reservation();

	}

	public void calculateAge() {
		Date dateobj = new Date();
		adults = 0;
		kids = 0;

		for (int i = 0; i < passengersArr.size(); i++) {
			Passenger passengerTemp = passengersArr.get(i);
			if ((dateobj.getYear() + 1900)
					- (passengerTemp.getDateBirth().getYear() + 1900) > 18) {
				adults++;
			} else
				kids++;
		}
		window.textFieldAdults.getTextField().setText("" + adults);
		window.textFieldChildren.getTextField().setText("" + kids);
	}

	public void calculatePrice() {
		Date dateobj = new Date();
		int price = 0;

		for (int i = 0; i < passengersArr.size(); i++) {
			Passenger passengerTemp = passengersArr.get(i);
			for (int j = 0; j < passengerTemp.getSpecialServices().size(); j++) {
				if (passengerTemp.getSpecialServices().get(j).toString()
						.equals("Breakfast, Lunch & Dinner"))
					if ((dateobj.getYear() + 1900)
							- (passengerTemp.getDateBirth().getYear() + 1900) > 18) {
						price =  price + 60;
					} else 
						price = price + 40;
				if (passengerTemp.getSpecialServices().get(j).toString()
						.equals("Party Guide"))
					if ((dateobj.getYear() + 1900)
							- (passengerTemp.getDateBirth().getYear() + 1900) > 18) {
						price =  price + 100;
					} else 
						price = price + 80;
				if (passengerTemp.getSpecialServices().get(j).toString()
						.equals("Ticket"))
					if ((dateobj.getYear() + 1900)
							- (passengerTemp.getDateBirth().getYear() + 1900) > 18) {
						price =  price + 130;
					} else 
						price = price + 70;
			}
		}
		
		textFieldPrice.getTextField().setText(price + "");
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
		childrenPane.setLayout(new GridBagLayout());
		pricePane.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		pricePane.add(labelPrice, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		pricePane.add(textFieldPrice.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		childrenPane.add(labelChildren, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		childrenPane.add(textFieldChildren.getTextField(), c);

		adultPane.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		adultPane.add(labelAdults, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		adultPane.add(textFieldAdults.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		contentPane.add(labelCustomer, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 1;
		contentPane.add(buttonAddCustomer, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(buttonViewCustomer, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 2;
		c.gridy = 1;
		contentPane.add(buttonDeleteCustomer, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(textFieldFirstName.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 2;
		contentPane.add(textFieldLastName.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 2;
		c.gridy = 2;
		contentPane.add(textFieldAffiliation.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 3;
		contentPane.add(labelPassengers, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 3;
		contentPane.add(childrenPane, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 2;
		c.gridy = 3;
		contentPane.add(adultPane, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 4;
		contentPane.add(buttonAddPassenger, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 4;
		contentPane.add(buttonViewPassenger, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 2;
		c.gridy = 4;
		contentPane.add(buttonDeletePassenger, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 3;
		contentPane.add(passengerPane, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		contentPane.add(buttonSave, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 6;
		contentPane.add(buttonClose, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 2;
		c.gridy = 6;
		contentPane.add(pricePane, c);

		setContentPane(contentPane);
	}

	protected void addInfo(String cell, Service service) {
		for (int i = 0; i < service.getReservations().size(); i++) {
			if (service.getReservations().get(i).getUniqueID()
					.equalsIgnoreCase(cell))
				reservation = service.getReservations().get(i);
		}
		
		customer = reservation.getCustomer();
		
		textFieldFirstName.getTextField().setText(
				reservation.getCustomer().getFirstName());
		textFieldLastName.getTextField().setText(
				reservation.getCustomer().getLastName());
		textFieldAffiliation.getTextField().setText(
				reservation.getCustomer().getAffiliation());

	}

	public ArrayList<Passenger> getPassengers() {
		return passengersArr;
	}

	public JTable makeTable() {

		String[] columnNames;
		DefaultTableModel tableModel;
		JXTable table;
		int listSize;

		columnNames = new String[] { "First Name", "Last Name",
				"Special Service", "E-mail" };

		tableModel = new DefaultTableModel(columnNames, 0);

		table = new JXTable(tableModel) {
			{
				setColumnControlVisible(true);
				getSelectionModel().addListSelectionListener(new RowListener());
				setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		};

		for (int i = 0; i < service.getReservations().size(); i++) {
			if (service.getReservations().get(i).getUniqueID()
					.equalsIgnoreCase(cell))
				reservation = service.getReservations().get(i);
		}

		try {
			listSize = reservation.getPassengers().size();
		} catch (NullPointerException e) {
			listSize = 0;
		}

		for (int i = 0; i < listSize; i++) {
			Passenger passenger = reservation.getPassengers().get(i);
			passengersArr.add(passenger);
			Object[] objs = { passenger.getFirstName(),
					passenger.getLastName(), passenger.reservationsToString(),
					passenger.getEmail() };
			tableModel.addRow(objs);
		}
		calculateAge();
		calculatePrice();
		return table;
	}

	private class RowListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				// buttonNewReservation.setEnabled(true);
				buttonViewPassenger.setEnabled(true);
				return;
			}
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

			if (event.getSource() == buttonAddCustomer) {

				new Window_Customer("New Customer", window);

			}

			if (event.getSource() == buttonViewCustomer) {
				for (int i = 0; i < service.getReservations().size(); i++) {
					if (service.getReservations().get(i).getUniqueID()
							.equalsIgnoreCase(cell))
						reservation = service.getReservations().get(i);
				}
				Customer customerTemp = reservation.getCustomer();
				new Window_Customer("View Customer", window,
						customerTemp.getEmail(), reservation);
			}

			if (event.getSource() == buttonDeleteCustomer) {
				customer = null;
				textFieldFirstName.getTextField().setText("");
				textFieldLastName.getTextField().setText("");
				textFieldAffiliation.getTextField().setText("");
			}

			if (event.getSource() == buttonAddPassenger) {

				new Window_Passenger("New Passenger", window);

			}

			if (event.getSource() == buttonViewPassenger) {

				String cell = (String) tablePassenger.getValueAt(tablePassenger
						.getSelectedRow(), tablePassenger.getColumn("E-mail")
						.getModelIndex());
				for (int i = 0; i < service.getReservations().size(); i++) {
					if (service.getReservations().get(i).getUniqueID()
							.equalsIgnoreCase(cell))
						reservation = service.getReservations().get(i);
				}
				new Window_Passenger("View Passenger", window, cell, reservation);

			}

			if (event.getSource() == buttonDeletePassenger) {
				String cell = (String) tablePassenger.getValueAt(tablePassenger
						.getSelectedRow(), tablePassenger.getColumn("E-mail")
						.getModelIndex());
				for (int i = 0; i < passengersArr.size(); i++) {
					Passenger passenger = passengersArr.get(i);
					if (passenger.getEmail() == cell) {
						passengersArr.remove(i);
						((DefaultTableModel) tablePassenger.getModel())
								.removeRow(tablePassenger.getSelectedRow());
					}
				}
				calculatePrice();
				calculateAge();
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
						}
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
					Reservation reservation = new Reservation();
					reservation.setPassengers(passengersArr);
					reservation.setCustomer(customer.copy());
					
					System.out.println(customer.toString());
					if (!cell.equalsIgnoreCase(""))
						for (int i = 0; i < service.getReservations().size(); i++)
							if (service.getReservations().get(i).getUniqueID()
									.equalsIgnoreCase(cell)) {
								reservation = service.getReservations().get(i);
								service.getReservations().get(i)
										.setPassengers(passengersArr);
								windowService.reservations.remove(i);
								((DefaultTableModel) windowService.tableReservations
										.getModel())
										.removeRow(windowService.tableReservations
												.getSelectedRow());

							}

					windowService.reservations.add(reservation);
					windowService.service.setReservations(windowService.reservations);
					Object[] objs = {
							reservation.getCustomer().getFirstName() + " "
									+ reservation.getCustomer().getLastName(),
							reservation.getPassengers().size(),
							reservation.getUniqueID() };
					((DefaultTableModel) windowService.tableReservations
							.getModel()).addRow(objs);
					dispose();
				} else
					JOptionPane.showMessageDialog(frame, message);
			}

		}
	}

}
