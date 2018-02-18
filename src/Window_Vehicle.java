import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;


public class Window_Vehicle extends JFrame {

	protected JFrame frame = new JFrame(Base.class.getSimpleName());

	protected JButton buttonSave;
	protected JButton buttonClose;
	
	protected ButtonHandler handler = new ButtonHandler();
	
	protected JPanel contentPane;
	protected JPanel typePanel;

	protected JLabel model;
	protected JLabel registration;
	protected JLabel capacity;
	protected JLabel type;

	protected JTextFieldX textFieldModel;
	protected JTextFieldX textFieldRegistration;
	protected JTextFieldX textFieldCapactiy;

	protected JTextFieldX[] fields;

	protected JRadioButton regularRadio;
	protected JRadioButton partyRadio;

	protected GridBagConstraints c;

	public static boolean requiredFieldsMissing;
	public static boolean invalidFieldsPresent;
	protected int j;
	protected Vehicle vehicle;
	protected String cell = "";

	public Window_Vehicle(String windowName) {
		super(windowName);	
		createComponents();		
		addComponentsToFrame();
		initializeComponents();
	}
	
	public Window_Vehicle(String windowName, String cell) {
		super(windowName);
		this.cell = cell;		
		createComponents();
		addComponentsToFrame();
		initializeComponents();
		addInfo(cell);
	}

	
	public void createComponents() {
		
		//Buttons
		buttonSave = new JButton("Save Changes and Close");
		buttonClose = new JButton("Exit Without Saving");
		
		// Labels
		model = new JLabel("Model");
		registration = new JLabel("Registration");
		capacity = new JLabel("Capacity");
		type = new JLabel("Type");

		// Text Fields
		textFieldModel = new JTextFieldX(10, "Model");
		textFieldRegistration = new JTextFieldX(10, "Registration");
		textFieldCapactiy = new JTextFieldX(10, "Capacity");
		fields = new JTextFieldX[] { textFieldModel, textFieldRegistration,
				textFieldCapactiy };

		// Panels
		contentPane = new JPanel();
		typePanel = new JPanel();

		// Radio

		regularRadio = new JRadioButton("Regular Bus", true);
		partyRadio = new JRadioButton("Party Bus", false);

		// Validators
		new HighlightListener(textFieldModel, "letters", true, 0);
		new HighlightListener(textFieldRegistration, "both", true, 7);
		new HighlightListener(textFieldCapactiy, "number", true, 0);

		// Placeholders
		PromptSupport.setPrompt("Manufacturer + Model",
				textFieldModel.getTextField());
		PromptSupport.setPrompt("8 Characters",
				textFieldRegistration.getTextField());
		PromptSupport.setPrompt("Total Person Capacity",
				textFieldCapactiy.getTextField());

		// Action Handlers
		buttonSave.addActionListener(handler);
		buttonClose.addActionListener(handler);
		regularRadio.addActionListener(handler);
		partyRadio.addActionListener(handler);
		

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

		typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
		typePanel.add(regularRadio);
		typePanel.add(partyRadio);

		contentPane.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 10, 10);
		contentPane.add(model, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		contentPane.add(textFieldModel.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 1;
		contentPane.add(registration, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(textFieldRegistration.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(capacity, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 2;
		contentPane.add(textFieldCapactiy.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 3;
		contentPane.add(type, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 3;
		contentPane.add(typePanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 4;
		contentPane.add(buttonSave, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 4;
		contentPane.add(buttonClose, c);

		setContentPane(contentPane);
	}

	protected void addInfo(String cell) {
		for (int i = 0; i < Base.vehicleList.getSize(); i++) {
			if (Base.vehicleList.getVehicle(i).getRegistration()
					.equalsIgnoreCase(cell))
				vehicle = Base.vehicleList.getVehicle(i);
		}

		textFieldModel.getTextField().setText(vehicle.getModel());
		textFieldRegistration.getTextField().setText(vehicle.getRegistration());
		textFieldCapactiy.getTextField().setText("" + vehicle.getCapacity());
		if (vehicle.getType().equalsIgnoreCase(regularRadio.getText())) {
			regularRadio.setSelected(true);
			partyRadio.setSelected(false);
		}
			
		else {
			regularRadio.setSelected(false);
			partyRadio.setSelected(true);
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

			if (event.getSource() == regularRadio) {
				regularRadio.setSelected(true);
				partyRadio.setSelected(false);
			}
			
			if (event.getSource() == partyRadio) {
				regularRadio.setSelected(false);
				partyRadio.setSelected(true);
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
				
				for (int j = 0; j < Base.vehicleList.getSize(); j++)
					if (Base.vehicleList
							.getVehicle(j).getRegistration()
							.equalsIgnoreCase(
									textFieldRegistration.getTextField().getText()) && !Base.vehicleList.getVehicle(j).equals(vehicle)) {
						requiredFieldsMissing = true;
						message += "Duplicate Registration, please enter a different one.";

					}
				
				if (!requiredFieldsMissing && !invalidFieldsPresent) {
					Vehicle vehicleTemp = new Vehicle();
					vehicleTemp.setModel(textFieldModel.getTextField().getText());
					vehicleTemp.setRegistration(textFieldRegistration
							.getTextField().getText());
					vehicleTemp.setCapacity(Integer.parseInt(textFieldCapactiy
							.getTextField().getText()));
					for (int i = 0; i<typePanel.getComponents().length; i++) {
						if (typePanel.getComponents()[i] instanceof JRadioButton) {
							JRadioButton radioTemp = (JRadioButton) typePanel.getComponents()[i];
							if (radioTemp.isSelected())
								vehicleTemp.setType(radioTemp.getText());
						}
					}
					
					if (!cell.equalsIgnoreCase(""))
						for (int i = 0; i < Base.vehicleList.getSize(); i++) {
							if (Base.vehicleList.getVehicle(i).getRegistration() == cell) {
								Base.vehicleList.removeVehicle(i);
								((DefaultTableModel) Base.tableVehicle.getModel())
										.removeRow(Base.tableVehicle
												.getSelectedRow());
	
							}
						}

					VehicleList.addVehicle(vehicleTemp);
					Object[] objs = { vehicleTemp.getModel(), vehicleTemp.getRegistration(), vehicleTemp.getCapacity(), vehicleTemp.getType(), };
					VehicleList.writeToFile();
					((DefaultTableModel) Base.tableVehicle.getModel())
							.addRow(objs);
					dispose();
				} else
					JOptionPane.showMessageDialog(frame, message);
			}

		}
	}

}
