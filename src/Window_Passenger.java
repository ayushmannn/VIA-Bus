import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

public class Window_Passenger extends JFrame {

	protected JFrame frame = new JFrame(Base.class.getSimpleName());

	protected JButton buttonSave;
	protected JButton buttonClose;

	protected ButtonHandler handler = new ButtonHandler();

	protected JPanel contentPane;

	protected JLabel firstName;
	protected JLabel lastName;
	protected JLabel address;
	protected JLabel dateBirth;
	protected JLabel email;
	// protected JLabel newsletter;
	protected JLabel specialService;

	protected JTextFieldX textFieldFirstName;
	protected JTextFieldX textFieldLastName;
	protected JTextFieldX textFieldAddress;
	protected JTextFieldX textFieldDateBirth;
	protected JTextFieldX textFieldEmail;

	protected JTextFieldX[] fields;

	protected JCheckBox checkBoxNewsletter;
	protected JCheckBox checkBoxFood;
	protected JCheckBox checkBoxguide;
	protected JCheckBox checkBoxticket;

	protected JPanel checkBoxPanel;

	protected GridBagConstraints c;
	protected Window_Reservation window;

	public static boolean requiredFieldsMissing;
	public static boolean invalidFieldsPresent;

	protected int j = 4;
	protected Passenger passenger;
	protected Reservation reservation;
	protected String cell = "";

	public Window_Passenger(String windowName, Window_Reservation window) {
		super(windowName);
		this.window = window;
		this.reservation = reservation;
		createComponents();
		addComponentsToFrame();
		initializeComponents();
	}

	public Window_Passenger(String windowName, Window_Reservation window,
			String cell, Reservation reservation) {
		super(windowName);
		this.window = window;
		this.cell = cell;
		this.reservation = reservation;
		createComponents();
		addComponentsToFrame();
		initializeComponents();
		addInfo(cell);
	}

	public void createComponents() {
		// Buttons
		buttonSave = new JButton("Save Changes and Close");
		buttonClose = new JButton("Exit Without Saving");

		// Labels
		firstName = new JLabel("First Name");
		lastName = new JLabel("Last Name");
		address = new JLabel("Address");
		dateBirth = new JLabel("Date of Birth");
		email = new JLabel("E-mail");
		// newsletter = new JLabel("Newsletter");
		specialService = new JLabel("Special Services");

		// Text Fields
		textFieldFirstName = new JTextFieldX(10, "First Name");
		textFieldLastName = new JTextFieldX(10, "Last Name");
		textFieldAddress = new JTextFieldX(10, "Address");
		textFieldDateBirth = new JTextFieldX(10, "Date of Birth");
		textFieldEmail = new JTextFieldX(10, "ID");

		fields = new JTextFieldX[] { textFieldFirstName, textFieldLastName,
				textFieldAddress, textFieldDateBirth, textFieldEmail };

		// Panels
		contentPane = new JPanel();
		checkBoxPanel = new JPanel();

		// Validators
		new HighlightListener(textFieldFirstName, "letters", true, 0);
		new HighlightListener(textFieldLastName, "letters", true, 0);
		new HighlightListener(textFieldAddress, "both", true, 0);
		new HighlightListener(textFieldDateBirth, "both", true, 0);
		new HighlightListener(textFieldEmail, "both", true, 0);

		// Placeholders
		PromptSupport.setPrompt("Michael", textFieldFirstName.getTextField());
		PromptSupport.setPrompt("Rasmussen", textFieldLastName.getTextField());
		PromptSupport.setPrompt("Kollegievænget 30",
				textFieldAddress.getTextField());
		PromptSupport.setPrompt("day/month/year",
				textFieldDateBirth.getTextField());
		PromptSupport.setPrompt("example@mail.com",
				textFieldEmail.getTextField());

		// Misc

		c = new GridBagConstraints();
		checkBoxNewsletter = new JCheckBox("Newsletter");
		checkBoxFood = new JCheckBox("Breakfast, Lunch & Dinner");
		checkBoxguide = new JCheckBox("Party Guide");
		;
		checkBoxticket = new JCheckBox("Ticket");

		// Action Handlers
		buttonSave.addActionListener(handler);
		buttonClose.addActionListener(handler);
		checkBoxNewsletter.addActionListener(handler);
	}

	public void initializeComponents() {
		// setSize(500, 800);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	private void addComponentsToFrame() {

		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		checkBoxPanel.add(checkBoxFood);
		checkBoxPanel.add(checkBoxguide);
		checkBoxPanel.add(checkBoxticket);
		checkBoxPanel.add(checkBoxNewsletter);

		contentPane.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		contentPane.add(firstName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		contentPane.add(textFieldFirstName.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 1;
		contentPane.add(lastName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(textFieldLastName.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(address, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 2;
		contentPane.add(textFieldAddress.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 3;
		contentPane.add(dateBirth, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 3;
		contentPane.add(textFieldDateBirth.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 4;
		contentPane.add(email, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 4;
		contentPane.add(textFieldEmail.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 5;
		contentPane.add(specialService, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 5;
		contentPane.add(checkBoxPanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 6;
		contentPane.add(buttonSave, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 6;
		contentPane.add(buttonClose, c);

		setContentPane(contentPane);
	}

	protected void addInfo(String cell) {
		for (int i = 0; i < Base.serviceList.getSize(); i++) {
			for (int j = 0; j < Base.serviceList.getService(i).getReservations().size(); j++)
				for (int m = 0; m< Base.serviceList.getService(i).getReservations().get(j).getPassengers().size(); m++)
					if (Base.serviceList.getService(i).getReservations().get(j).equals(reservation)) {
						passenger = Base.serviceList.getService(i).getReservations().get(j).getPassengers().get(m);
						System.out.println("Found passenger");
					}
					
		}

		textFieldFirstName.getTextField().setText(passenger.getFirstName());
		textFieldLastName.getTextField().setText(passenger.getLastName());
		textFieldAddress.getTextField().setText(passenger.getAddress());
		String date = "";
		String day = "" + passenger.getDateBirth().getDate();
		if (Integer.parseInt(day) < 10)
			date += "0" + day + "/";
		String month = "" + (passenger.getDateBirth().getMonth() + 1);
		if (Integer.parseInt(month) < 10)
			date += "0" + month + "/";
		String year = "" + (passenger.getDateBirth().getYear() + 1900);
		date += year;
		textFieldDateBirth.getTextField().setText(date);
		textFieldDateBirth.getTextField().setEnabled(false);
		textFieldEmail.getTextField().setText(passenger.getEmail());
		
		for (int i = 0; i < passenger.getSpecialServices().size(); i++) {
			if (passenger.getSpecialServices().get(i).equals(checkBoxFood.getText()))
					checkBoxFood.setSelected(true);
			if (passenger.getSpecialServices().get(i).equals(checkBoxguide.getText()))
				checkBoxguide.setSelected(true);
			if (passenger.getSpecialServices().get(i).equals(checkBoxticket.getText()))
				checkBoxticket.setSelected(true);
		}
		
		if (passenger.isNewsletter())
			checkBoxNewsletter.setSelected(true);
		else
			checkBoxNewsletter.setSelected(false);
	}

	protected class ButtonHandler implements ActionListener {

		protected String message;
		protected String required;
		protected String invalid;
		protected String output;
		protected boolean needInvalid;

		@Override
		public void actionPerformed(ActionEvent event) {

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
					Passenger passenger = new Passenger(textFieldFirstName
							.getTextField().getText(), textFieldLastName
							.getTextField().getText(), textFieldAddress
							.getTextField().getText());
					String string = textFieldDateBirth.getTextField().getText();
					String[] parts = string.split("/");
					Date date = new Date(Integer.parseInt(parts[2]) - 1900,
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[0]));
					passenger.setDateBirth(date);
					passenger.setEmail(textFieldEmail.getTextField().getText());

					if (checkBoxFood.isSelected())
						passenger.getSpecialServices().add(
								checkBoxFood.getText());

					if (checkBoxguide.isSelected())
						passenger.getSpecialServices().add(
								checkBoxguide.getText());

					if (checkBoxticket.isSelected())
						passenger.getSpecialServices().add(
								checkBoxticket.getText());

					if (checkBoxNewsletter.isSelected())
						passenger.setNewsletter(true);
					else
						passenger.setNewsletter(false);
					
					if (!cell.equalsIgnoreCase(""))
						for (int i = 0; i < window.getPassengers().size(); i++) {
							if (window.getPassengers().get(i).getEmail() == cell) {
								window.getPassengers().remove(i);
								((DefaultTableModel) window.tablePassenger
										.getModel())
										.removeRow(window.tablePassenger
												.getSelectedRow());

							}
						}

					window.getPassengers().add(passenger);			
					window.calculateAge();
					
					Object[] objs = { passenger.getFirstName(),
							passenger.getLastName(),
							passenger.reservationsToString(),
							passenger.getEmail() };
					((DefaultTableModel) window.tablePassenger.getModel())
							.addRow(objs);
					window.calculatePrice();
					dispose();
				} else
					JOptionPane.showMessageDialog(frame, message);
			}

		}
	}

}