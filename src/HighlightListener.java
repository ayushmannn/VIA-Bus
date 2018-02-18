import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.*;

public class HighlightListener implements DocumentListener {
	JTextFieldX jtx = null;
	JTextField comp = null;
	Border defaultBorder = null;
	Border requiredBorder = BorderFactory
			.createLineBorder(java.awt.Color.YELLOW);
	Border validBorder = BorderFactory.createLineBorder(java.awt.Color.GREEN);
	Border invalidBorder = BorderFactory.createLineBorder(java.awt.Color.RED);
	String function;
	boolean required;
	int length;

	public HighlightListener(JTextFieldX jtx, String function,
			boolean required, int length) {
		comp = jtx.getTextField();
		defaultBorder = comp.getBorder();
		comp.getDocument().addDocumentListener(this);
		this.function = function;
		this.required = required;
		this.length = length;
		this.jtx = jtx;
		this.maybeHighlight(jtx, function, required, length);

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		maybeHighlight(jtx, function, required, length);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		maybeHighlight(jtx, function, required, length);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		maybeHighlight(jtx, function, required, length);
	}

	public void maybeHighlight(JTextFieldX jtx, String function,
			boolean required, int length) {
		switch (function) {

		case "number":

			if (length != 0) {
				if (comp.getText().trim().length() == length) {
					// All is well
					if (isInteger(comp.getText())) {
						comp.setBorder(validBorder);
						jtx.haltInvalid = false;
						jtx.haltRequired = false;
					} else {
						comp.setBorder(invalidBorder);
						jtx.haltInvalid = true;
						jtx.haltRequired = false;
					}

				} else if (comp.getText().trim().length() <= 0) {
					// not filled in
					if (required) {
						comp.setBorder(requiredBorder);
						jtx.haltRequired = true;
					} else {
						comp.setBorder(defaultBorder);
						jtx.haltRequired = false;
					}
				} else {
					comp.setBorder(invalidBorder);
					jtx.haltInvalid = true;
					jtx.haltRequired = false;
				}
				break;

			} else {
				if (comp.getText().trim().length() > length) {
					// All is well
					if (isInteger(comp.getText())) {
						comp.setBorder(validBorder);
						jtx.haltInvalid = false;
						jtx.haltRequired = false;
					} else {
						comp.setBorder(invalidBorder);
						jtx.haltInvalid = true;
						jtx.haltRequired = false;
					}

				} else if (comp.getText().trim().length() <= 0) {
					// not filled in
					if (required) {
						comp.setBorder(requiredBorder);
						jtx.haltRequired = true;
					} else {
						comp.setBorder(defaultBorder);
						jtx.haltRequired = false;
					}
				}
				break;
			}

		case "letters":
			if (length != 0) {
				if (comp.getText().trim().length() == length) {
					if (isString(comp.getText())) {
						comp.setBorder(validBorder);
						jtx.haltInvalid = false;
						jtx.haltRequired = false;
					} else {
						comp.setBorder(invalidBorder);
						jtx.haltInvalid = true;
						jtx.haltRequired = false;
					}
				} else if (comp.getText().trim().length() <= 0) {
					// not filled in
					if (required)
						comp.setBorder(requiredBorder);
					else
						comp.setBorder(defaultBorder);
					jtx.haltRequired = true;

				} else {
					comp.setBorder(invalidBorder);
					jtx.haltInvalid = true;
					jtx.haltRequired = false;
				}
				break;

			} else {
				if (comp.getText().trim().length() > length) {
					if (isString(comp.getText())) {
						comp.setBorder(validBorder);
						jtx.haltInvalid = false;
						jtx.haltRequired = false;
					} else {
						comp.setBorder(invalidBorder);
						jtx.haltInvalid = true;
						jtx.haltRequired = false;
					}
				} else if (comp.getText().trim().length() <= 0) {
					// not filled in
					if (required) {
						comp.setBorder(requiredBorder);
						jtx.haltRequired = true;
					} else {
						comp.setBorder(defaultBorder);
						jtx.haltRequired = true;
					}
				} else {
					comp.setBorder(invalidBorder);
					jtx.haltInvalid = true;
					jtx.haltRequired = false;
				}
				break;
			}

		case "both":
			if (length != 0) {
				if (comp.getText().trim().length() == length) {
					comp.setBorder(validBorder);
					jtx.haltRequired = false;
					jtx.haltInvalid = false;
				} else if (comp.getText().trim().length() <= 0) {
					// not filled in
					if (required)
						comp.setBorder(requiredBorder);
					else
						comp.setBorder(defaultBorder);

					jtx.haltRequired = true;

				} else {
					comp.setBorder(invalidBorder);
					jtx.haltInvalid = true;
					jtx.haltRequired = false;
				}

				break;
			} else {
				if (comp.getText().trim().length() > length) {
					comp.setBorder(validBorder);
					jtx.haltRequired = false;
					jtx.haltInvalid = false;
				} else if (comp.getText().trim().length() <= 0) {
					// not filled in
					if (required)
						comp.setBorder(requiredBorder);
					else
						comp.setBorder(defaultBorder);
					jtx.haltRequired = true;

				} else {
					comp.setBorder(invalidBorder);
					jtx.haltInvalid = true;
					jtx.haltRequired = false;
				}

				break;
			}

		}
	}

	public static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	public static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

	public boolean isString(String name) {
		return name.matches("[a-zA-Z]+");
	}
}