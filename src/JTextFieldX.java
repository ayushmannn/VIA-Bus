import javax.swing.JTextField;

		public class JTextFieldX extends java.awt.Component {
			
			public boolean haltRequired;
			public boolean haltInvalid;
			public String fieldName;
			public JTextField textField;
			public int length;
			
			public JTextFieldX(int length, String fieldName) {		
				textField= new JTextField(length);
				this.fieldName = fieldName;
				this.length = length;
			}
			
			public JTextField getTextField() {
				return textField;
			}
			
			public JTextFieldX copy() {
				JTextFieldX jtextfieldxCopy = new JTextFieldX(this.length, this.fieldName);
				jtextfieldxCopy.haltRequired = this.haltRequired;
				jtextfieldxCopy.haltInvalid = this.haltInvalid;
				return jtextfieldxCopy;
			}

			public boolean isHaltRequired() {
				return haltRequired;
			}

			public void setHaltRequired(boolean haltRequired) {
				this.haltRequired = haltRequired;
			}

			public boolean isHaltInvalid() {
				return haltInvalid;
			}

			public void setHaltInvalid(boolean haltInvalid) {
				this.haltInvalid = haltInvalid;
			}

			public String getFieldName() {
				return fieldName;
			}

			public void setFieldName(String fieldName) {
				this.fieldName = fieldName;
			}

			public int getLength() {
				return length;
			}

			public void setLength(int length) {
				this.length = length;
			}

			public void setTextField(JTextField textField) {
				this.textField = textField;
			}
			

		}