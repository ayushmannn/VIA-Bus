import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;
import org.jdesktop.swingx.prompt.PromptSupport;

public class Window_Customer extends JFrame {

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
	protected JLabel newsletter;
	protected JLabel status;
	protected JLabel affiliation;
	protected JLabel frequency;
	protected JLabel discount;

	protected JTextFieldX textFieldFirstName;
	protected JTextFieldX textFieldLastName;
	protected JTextFieldX textFieldAddress;
	protected JTextFieldX textFieldDateBirth;
	protected JTextFieldX textFieldEmail;
	protected JTextFieldX textFieldStatus;
	protected JTextFieldX textFieldAffiliation;
	protected JTextFieldX textFieldFrequency;
	protected JTextFieldX textFieldDiscount;

	protected JTextFieldX[] fields;

	protected JCheckBox checkBoxNewsletter;

	protected GridBagConstraints c;

	protected Window_Reservation window;
	
	protected Reservation reservation;

	public static boolean requiredFieldsMissing;
	public static boolean invalidFieldsPresent;

	protected int j = 4;
	protected Customer customer;
	protected String cell = "";

	public Window_Customer(String windowName, Window_Reservation window) {
		super(windowName);
		this.window = window;
		createComponents();
		addComponentsToFrame();
		initializeComponents();
	}

	public Window_Customer(String windowName, Window_Reservation window,
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
		newsletter = new JLabel("Newsletter");
		status = new JLabel("Status");
		affiliation = new JLabel("Affiliation");
		frequency = new JLabel("Frequency");
		discount = new JLabel("Discount");

		// Text Fields
		textFieldFirstName = new JTextFieldX(10, "First Name");
		textFieldLastName = new JTextFieldX(10, "Last Name");
		textFieldAddress = new JTextFieldX(10, "Address");
		textFieldDateBirth = new JTextFieldX(10, "Date of Birth");
		textFieldEmail = new JTextFieldX(10, "ID");
		textFieldStatus = new JTextFieldX(10, "Status");

		textFieldAffiliation = new JTextFieldX(10, "Affiliation");

		textFieldFrequency = new JTextFieldX(10, "Frequency");

		textFieldDiscount = new JTextFieldX(10, "Discount");

		fields = new JTextFieldX[] { textFieldFirstName, textFieldLastName,
				textFieldAddress, textFieldDateBirth, textFieldEmail,
				textFieldStatus, textFieldAffiliation, textFieldFrequency,
				textFieldDiscount };

		// Panels
		contentPane = new JPanel();

		// Validators
		new HighlightListener(textFieldFirstName, "letters", true, 0);
		new HighlightListener(textFieldLastName, "letters", true, 0);
		new HighlightListener(textFieldAddress, "both", true, 0);
		new HighlightListener(textFieldDateBirth, "both", true, 0);
		new HighlightListener(textFieldEmail, "both", true, 0);
		new HighlightListener(textFieldStatus, "letters", true, 0);
		new HighlightListener(textFieldAffiliation, "both", true, 0);
		new HighlightListener(textFieldFrequency, "number", true, 0);
		new HighlightListener(textFieldDiscount, "number", true, 2);

		// Placeholders
		PromptSupport.setPrompt("Michael", textFieldFirstName.getTextField());
		PromptSupport.setPrompt("Rasmussen", textFieldLastName.getTextField());
		PromptSupport.setPrompt("Kollegievænget 30",
				textFieldAddress.getTextField());
		PromptSupport.setPrompt("day/month/year",
				textFieldDateBirth.getTextField());
		PromptSupport.setPrompt("example@mail.com",
				textFieldEmail.getTextField());
		PromptSupport.setPrompt("e.g. Secretary, Private Person etc.",
				textFieldStatus.getTextField());
		PromptSupport.setPrompt("e.g Company/Institution Name, None",
				textFieldAffiliation.getTextField());
		PromptSupport.setPrompt("2 digits", textFieldDiscount.getTextField());
		// Misc

		c = new GridBagConstraints();
		checkBoxNewsletter = new JCheckBox();

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
		contentPane.add(newsletter, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 5;
		contentPane.add(checkBoxNewsletter, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 6;
		contentPane.add(status, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 6;
		contentPane.add(textFieldStatus.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 7;
		contentPane.add(affiliation, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 7;
		contentPane.add(textFieldAffiliation.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 8;
		contentPane.add(frequency, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 8;
		contentPane.add(textFieldFrequency.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 9;
		contentPane.add(discount, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 9;
		contentPane.add(textFieldDiscount.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 10;
		contentPane.add(buttonSave, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 10;
		contentPane.add(buttonClose, c);

		setContentPane(contentPane);
	}

	protected void addInfo(String cell) {
		for (int i = 0; i < Base.serviceList.getSize(); i++) {
			for (int j = 0; j < Base.serviceList.getService(i).getReservations().size(); j++)
					if (Base.serviceList.getService(i).getReservations().get(j).equals(reservation)) {
						customer = Base.serviceList.getService(i).getReservations().get(j).getCustomer();
						System.out.println("Found Customer");
					}
					
		}

		textFieldFirstName.getTextField().setText(customer.getFirstName());
		textFieldLastName.getTextField().setText(customer.getLastName());
		textFieldAddress.getTextField().setText(customer.getAddress());
		String date = "";
		String day = "" + customer.getDateBirth().getDate();
		if (Integer.parseInt(day) < 10)
			date += "0" + day + "/";
		String month = "" + (customer.getDateBirth().getMonth() + 1);
		if (Integer.parseInt(month) < 10)
			date += "0" + month + "/";
		String year = "" + (customer.getDateBirth().getYear() + 1900);
		date += year;
		textFieldDateBirth.getTextField().setText(date);
		textFieldDateBirth.getTextField().setEnabled(false);
		textFieldEmail.getTextField().setText(customer.getEmail());
		if (customer.isNewsletter())
			checkBoxNewsletter.setSelected(true);
		else
			checkBoxNewsletter.setSelected(false);
		textFieldStatus.getTextField().setText(customer.getStatus());
		textFieldAffiliation.getTextField().setText(customer.getAffiliation());
		textFieldFrequency.getTextField().setText("" + customer.getFrequency());
		textFieldDiscount.getTextField().setText("" + customer.getDiscount());
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
					Customer customer = new Customer(textFieldFirstName
							.getTextField().getText(), textFieldLastName
							.getTextField().getText(), textFieldAddress
							.getTextField().getText());
					String string = textFieldDateBirth.getTextField().getText();
					String[] parts = string.split("/");
					Date date = new Date(Integer.parseInt(parts[2]) - 1900,
							Integer.parseInt(parts[1]),
							Integer.parseInt(parts[0]));
					customer.setDateBirth(date);
					customer.setEmail(textFieldEmail.getTextField().getText());
					if (checkBoxNewsletter.isSelected())
						customer.setNewsletter(true);
					else
						customer.setNewsletter(false);

					customer.setStatus(textFieldStatus.getTextField().getText());
					customer.setAffiliation(textFieldAffiliation.getTextField()
							.getText());
					customer.setFrequency(Integer.parseInt(textFieldFrequency
							.getTextField().getText()));
					customer.setDiscount(Integer.parseInt(textFieldDiscount
							.getTextField().getText()));
					customer.setFrequency(customer.getFrequency() + 1);


					window.customer = customer.copy();
					window.reservation.setCustomer(customer);
					window.textFieldFirstName.getTextField().setText(
							customer.getFirstName());
					window.textFieldLastName.getTextField().setText(
							customer.getLastName());
					window.textFieldAffiliation.getTextField().setText(
							customer.getAffiliation());
					
					Object[] objs = { customer.getFirstName(),
							customer.getLastName(), customer.getAddress(),
							customer.getDateBirth(), customer.getEmail(),
							customer.isNewsletter(), customer.getStatus(),
							customer.getAffiliation(), customer.getFrequency(),
							customer.getDiscount() };
					dispose();
				} else
					JOptionPane.showMessageDialog(frame, message);
			}

		}
	}

}