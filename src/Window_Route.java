import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

public class Window_Route extends JFrame {

	protected JFrame frame = new JFrame(Base.class.getSimpleName());

	protected JButton buttonSave;
	protected JButton buttonClose;

	protected ButtonHandler handler = new ButtonHandler();

	protected JPanel contentPane;

	protected JLabel departure;
	protected JLabel destination;
	protected JLabel distance;
	protected JLabel duration;

	protected JTextFieldX textFieldDeparture;
	protected JTextFieldX textFieldDestination;
	protected JTextFieldX textFieldDistance;
	protected JTextFieldX textFieldDuration;

	protected JTextFieldX[] fields;

	protected GridBagConstraints c;

	public static boolean requiredFieldsMissing;
	public static boolean invalidFieldsPresent;

	protected int j;
	protected Route route;
	protected String departureString = "";
	protected String destinationString = "";
	protected Time timeTemp;

	public Window_Route(String windowName) {
		super(windowName);
		createComponents();
		addComponentsToFrame();
		initializeComponents();
	}

	public Window_Route(String windowName, String departureString,
			String destinationString) {
		super(windowName);
		this.departureString = departureString;
		this.destinationString = destinationString;
		createComponents();
		addComponentsToFrame();
		initializeComponents();
		addInfo(departureString, destinationString);

	}

	public void createComponents() {

		// Buttons
		buttonSave = new JButton("Save Changes and Close");
		buttonClose = new JButton("Exit Without Saving");

		// Labels
		departure = new JLabel("Departure");
		destination = new JLabel("Destination");
		distance = new JLabel("Distance");
		duration = new JLabel("Duration");

		// Text Fields
		textFieldDeparture = new JTextFieldX(10, "Departure");
		textFieldDestination = new JTextFieldX(10, "Registration");
		textFieldDistance = new JTextFieldX(10, "Distance");
		textFieldDuration = new JTextFieldX(10, "Duration");
		textFieldDuration.getTextField().setEnabled(false);

		fields = new JTextFieldX[] { textFieldDeparture, textFieldDestination,
				textFieldDistance, textFieldDuration };

		// Panels
		contentPane = new JPanel();

		// Validators
		new HighlightListener(textFieldDeparture, "both", true, 0);
		new HighlightListener(textFieldDestination, "both", true, 0);
		new HighlightListener(textFieldDistance, "number", true, 0);

		// Placeholders
		PromptSupport.setPrompt("X Km", textFieldDistance.getTextField());

		// Action Handlers
		buttonSave.addActionListener(handler);
		buttonClose.addActionListener(handler);

		// Misc

		c = new GridBagConstraints();
		
		textFieldDistance.getTextField().getDocument().addDocumentListener(
				new DocumentListener() {
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
							timeTemp = new Time((Integer.parseInt(textFieldDistance.getTextField().getText()) * 1000) / 17);
						} catch (NumberFormatException e) {
							
						}
						textFieldDuration.getTextField().setText(timeTemp.toString());
					}

				});
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
		contentPane.add(departure, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		contentPane.add(textFieldDeparture.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 1;
		contentPane.add(destination, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(textFieldDestination.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(distance, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 2;
		contentPane.add(textFieldDistance.getTextField(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 3;
		contentPane.add(duration, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 3;
		contentPane.add(textFieldDuration.getTextField(), c);

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

	protected void addInfo(String departureString, String destinationString) {
		for (int i = 0; i < Base.routeList.getSize(); i++) {
			if (Base.routeList.getRoute(i).getDeparture()
					.equalsIgnoreCase(departureString))
				if (Base.routeList.getRoute(i).getDestination()
						.equalsIgnoreCase(destinationString))
					route = Base.routeList.getRoute(i);
		}

		textFieldDeparture.getTextField().setText(route.getDeparture());
		textFieldDestination.getTextField().setText(route.getDestination());
		textFieldDistance.getTextField().setText("" + route.getDistance());
		textFieldDuration.getTextField()
				.setText(route.getDuration().toString());
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
					Route route = new Route();
					route.setDeparture(textFieldDeparture.getTextField()
							.getText());
					route.setDestination(textFieldDestination.getTextField()
							.getText());
					route.setDistance(Integer.parseInt(textFieldDistance
							.getTextField().getText()));
					route.setDuration(timeTemp);

					if (!departureString.equalsIgnoreCase(""))
						for (int i = 0; i < Base.routeList.getSize(); i++) {
							if (Base.routeList.getRoute(i).getDeparture()
									.equalsIgnoreCase(departureString))
								if (Base.routeList.getRoute(i).getDestination()
										.equalsIgnoreCase(destinationString)) {
									Base.routeList.removeRoute(i);
									((DefaultTableModel) Base.tableRoute
											.getModel())
											.removeRow(Base.tableRoute
													.getSelectedRow());

								}
						}

					RouteList.addRoute(route);

					Object[] objs = { route.getDeparture(),
							route.getDestination(), route.getDistance(),
							route.getDuration(), };
					RouteList.writeToFile();
					((DefaultTableModel) Base.tableRoute.getModel())
							.addRow(objs);
					RouteList.writeToFile();
					dispose();
				} else
					JOptionPane.showMessageDialog(frame, message);
			}

		}
	}

}
