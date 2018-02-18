import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

public class Window_Chauffeur extends JFrame {

	protected JFrame frame = new JFrame(Base.class.getSimpleName());

	protected JButton buttonSave;
	protected JButton buttonClose;

	protected ButtonHandler handler = new ButtonHandler();

	protected JPanel contentPane;
	protected JPanel labels;
	protected JPanel textFields;
	protected JPanel wishServicePanel;
	protected JPanel wishVehiclePanel;

	protected JLabel firstName;
	protected JLabel lastName;
	protected JLabel address;
	protected JLabel id;
	protected JLabel mobile;
	protected JLabel wishService;
	protected JLabel wishVehicle;

	protected JTextFieldX textFieldFirstName;
	protected JTextFieldX textFieldLastName;
	protected JTextFieldX textFieldAddress;
	protected JTextFieldX textFieldId;
	protected JTextFieldX textFieldMobile;
	protected JTextFieldX[] fields;

	protected JComboCheckBox serviceList;
	protected JComboBox vehicleList;

	protected GridBagConstraints c;

	protected JCheckBox[] serviceStrings;

	public static boolean requiredFieldsMissing;
	public static boolean invalidFieldsPresent;

	protected int j = 4;
	protected Chauffeur driver;
	protected String cell = "";

	public Window_Chauffeur(String windowName) {
		super(windowName);
		createComponents();
		for (int i = 0; i < serviceList.getCheckBoxArray().length; i++)
			serviceList.getCheckBoxArray()[i].setSelected(true);
		addComponentsToFrame();
		initializeComponents();
	}

	public Window_Chauffeur(String windowName, String cell) {
		super(windowName);
		this.cell = cell;
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
		id = new JLabel("ID");
		mobile = new JLabel("Moblie Phone Nr.");
		wishService = new JLabel("Service Preference");
		wishVehicle = new JLabel("Vehicle Preference");

		// Text Fields
		textFieldFirstName = new JTextFieldX(10, "First Name");
		textFieldLastName = new JTextFieldX(10, "Last Name");
		textFieldAddress = new JTextFieldX(10, "Address");
		textFieldId = new JTextFieldX(10, "ID");
		textFieldMobile = new JTextFieldX(10, "Moblie Phone Nr.");
		fields = new JTextFieldX[] { textFieldFirstName, textFieldLastName,
				textFieldAddress, textFieldId, textFieldMobile };

		// Panels
		contentPane = new JPanel();
		textFields = new JPanel();
		labels = new JPanel();
		wishServicePanel = new JPanel();
		wishVehiclePanel = new JPanel();

		serviceStrings = new JCheckBox[] { new JCheckBox("Any", true),
				new JCheckBox("Travel", false),
				new JCheckBox("One-day Trips", false),
				new JCheckBox("Bus-and-Chauffeur", false),
				new JCheckBox("Party-bus", false) };

		// Lists

		vehicleList = new JComboBox(serviceStrings);
		serviceList = new JComboCheckBox(serviceStrings);
		serviceList.setSelectedIndex(0);
		vehicleList.setSelectedIndex(0);

		// Action Handlers
		buttonSave.addActionListener(handler);
		buttonClose.addActionListener(handler);
		serviceList.addActionListener(handler);
		vehicleList.addActionListener(handler);

		// Validators
		new HighlightListener(textFieldFirstName, "letters", true, 0);
		new HighlightListener(textFieldLastName, "letters", true, 0);
		new HighlightListener(textFieldAddress, "both", true, 0);
		new HighlightListener(textFieldId, "number", true, 5);
		new HighlightListener(textFieldMobile, "number", true, 8);

		// Placeholders
		PromptSupport.setPrompt("Michael", textFieldFirstName.getTextField());
		PromptSupport.setPrompt("Rasmussen", textFieldLastName.getTextField());
		PromptSupport.setPrompt("Kollegievænget 30",
				textFieldAddress.getTextField());
		PromptSupport.setPrompt("5 digits", textFieldId.getTextField());
		PromptSupport.setPrompt("8 digits", textFieldMobile.getTextField());

		// Misc

		c = new GridBagConstraints();
	}

	public void initializeComponents() {
		// setSize(500, 800);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	private void addComponentsToFrame() {

		wishServicePanel.setLayout(new BoxLayout(wishServicePanel,
				BoxLayout.Y_AXIS));
		wishServicePanel.add(serviceList);

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
		contentPane.add(id, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 3;
		contentPane.add(textFieldId.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 4;
		contentPane.add(mobile, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 4;
		contentPane.add(textFieldMobile.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 5;
		contentPane.add(wishService, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 5;
		contentPane.add(wishServicePanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 6;
		contentPane.add(wishVehicle, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 6;
		contentPane.add(wishVehiclePanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 7;
		contentPane.add(buttonSave, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 7;
		contentPane.add(buttonClose, c);

		setContentPane(contentPane);
	}

	protected void addInfo(String cell) {
		for (int i = 0; i < Base.chauffeurList.getSize(); i++) {
			if (Base.chauffeurList.getChauffeur(i).getEmployeeId()
					.equalsIgnoreCase(cell))
				driver = Base.chauffeurList.getChauffeur(i);
		}

		textFieldFirstName.getTextField().setText(driver.getFirstName());
		textFieldLastName.getTextField().setText(driver.getLastName());
		textFieldAddress.getTextField().setText(driver.getAddress());
		textFieldId.getTextField().setText(driver.getEmployeeId());
		textFieldId.getTextField().setEnabled(false);
		textFieldMobile.getTextField().setText(driver.getMobile());
		for (int i = 0; i < driver.getWishService().size(); i++)
			for (int j = 0; j < serviceList.getCheckBoxArray().length; j++) {
				JCheckBox checkTemp = serviceList
						.getCheckBoxArray()[j];
				if (driver.getWishService().get(i)
						.equalsIgnoreCase(checkTemp.getText())) {
					checkTemp.setSelected(true);
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

			if (event.getSource() == buttonClose)
				dispose();

			if (event.getSource() == serviceList) {
				JCheckBox checkTemp = (JCheckBox) serviceList.getSelectedItem();
				if (checkTemp.getText().equalsIgnoreCase("Any")) {

					for (int i = 1; i < serviceList.getCheckBoxArray().length; i++) {
						JCheckBox checkTemp2 = serviceList
								.getCheckBoxArray()[i];
						checkTemp2.setSelected(true);
					}
					serviceList.getCheckBoxArray()[0].setSelected(false);
					j = 4;
				} else {
					if (checkTemp.isSelected()) {
						JCheckBox checkTemp2 = serviceList
								.getCheckBoxArray()[0];
						checkTemp2.setSelected(false);
						j--;
						if (j == 0) {
							for (int i = 0; i < serviceList.getCheckBoxArray().length; i++) {
								checkTemp2 = serviceList
										.getCheckBoxArray()[i];
								checkTemp2.setSelected(true);
								checkTemp.setSelected(false);
							}
							j = 4;
						}
					} else {
						j++;
						if (j == 4) {
							JCheckBox checkTemp2 = serviceList
									.getCheckBoxArray()[0];
							checkTemp2.setSelected(true);
						}
					}
					System.out.println(j);

				}

				wishServicePanel.revalidate();
				wishServicePanel.repaint();
			}

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

				if (needInvalid)
					if (message.equalsIgnoreCase(""))
						message += "The following fields are invalid, change them in order to proceed:";
					else {
						message += "\n \n The following fields are invalid, change them in order to proceed:";
					}

				message += output;

				for (int i = 0; i < fields.length; i++) {
					invalidFieldsPresent = invalidFieldsPresent
							|| fields[i].haltInvalid;
					if (fields[i].haltInvalid) {
						output += "\n •" + fields[i].copy().fieldName;
						needInvalid = true;
					}
				}
				
				for (int j = 0; j < Base.chauffeurList.getSize(); j++)
					if (Base.chauffeurList
							.getChauffeur(j)
							.getEmployeeId()
							.equalsIgnoreCase(
									textFieldId.getTextField().getText())  && !Base.chauffeurList.getChauffeur(j).equals(driver)) {
						requiredFieldsMissing = true;
						message += "Duplicate Employee ID, please enter a different one.";

					}

				if (!requiredFieldsMissing && !invalidFieldsPresent) {
					Chauffeur driverTemp = new Chauffeur(textFieldFirstName
							.getTextField().getText(), textFieldLastName
							.getTextField().getText(), textFieldAddress
							.getTextField().getText());
					driverTemp.setEmployeeId(textFieldId.getTextField().getText());
					driverTemp.setMobile(textFieldMobile.getTextField().getText());
					ArrayList<String> servicePref = new ArrayList<String>();

					for (int i = 0; i < serviceList.getCheckBoxArray().length; i++) {
						JCheckBox checkTemp = serviceList
								.getCheckBoxArray()[i];
						if (checkTemp.isSelected())
							servicePref.add(checkTemp.getText());
					}

					driverTemp.setWishService(servicePref);

					if (!cell.equalsIgnoreCase(""))
						for (int i = 0; i < Base.chauffeurList.getSize(); i++) {
							if (Base.chauffeurList.getChauffeur(i)
									.getEmployeeId() == cell) {
								Base.chauffeurList.removeChauffeur(i);
								((DefaultTableModel) Base.tableChauffeur
										.getModel())
										.removeRow(Base.tableChauffeur
												.getSelectedRow());

							}
						}

					ChauffeurList.addChauffeur(driverTemp);
					Object[] objs = { driverTemp.getFirstName(),
							driverTemp.getLastName(), driverTemp.getEmployeeId(),
							driverTemp.getMobile(), driverTemp.isOnTrip(),
							driverTemp.isInTransit(), driverTemp.isAvailable() };
					((DefaultTableModel) Base.tableChauffeur.getModel())
							.addRow(objs);
					ChauffeurList.writeToFile();
					dispose();
				} else
					JOptionPane.showMessageDialog(frame, message);
			}

		}
	}

	public class JComboCheckBox extends JComboBox {
		protected JCheckBox[] items;

		public JComboCheckBox() {
			addStuff();
		}

		public JComboCheckBox(JCheckBox[] items) {
			super(items);
			this.items = items;
			addStuff();
		}

		public JComboCheckBox(Vector items) {
			super(items);
			addStuff();
		}

		public JComboCheckBox(ComboBoxModel aModel) {
			super(aModel);
			addStuff();
		}

		private void addStuff() {
			setRenderer(new ComboBoxRenderer());
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					itemSelected();
				}
			});
		}

		private JCheckBox[] getCheckBoxArray() {
			return items;
		}

		private void itemSelected() {
			if (getSelectedItem() instanceof JCheckBox) {
				JCheckBox jcb = (JCheckBox) getSelectedItem();
				jcb.setSelected(!jcb.isSelected());
			}
		}

		class ComboBoxRenderer implements ListCellRenderer {
			private JLabel defaultLabel;

			public ComboBoxRenderer() {
				setOpaque(true);
			}

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				if (value instanceof Component) {
					Component c = (Component) value;
					if (isSelected) {
						c.setBackground(list.getSelectionBackground());
						c.setForeground(list.getSelectionForeground());
					} else {
						c.setBackground(list.getBackground());
						c.setForeground(list.getForeground());
					}
					return c;
				} else {
					if (defaultLabel == null)
						defaultLabel = new JLabel(value.toString());
					else
						defaultLabel.setText(value.toString());
					return defaultLabel;
				}
			}
		}
	}

}