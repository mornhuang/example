import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Find extends Applet {
	TextArea     editor           = new TextArea(5,20);
	Label        findLabel        = new Label("find:"),
	             changeLabel      = new Label("change:");

	TextField    findField        = new TextField(5),
	             changeField      = new TextField(5);

	Button       findButton       = new Button("Find"),
	             changeFindButton = new Button("Change & Find"),
	             changeButton     = new Button("Change");

	public void init() {
		Panel north  = new Panel(), 
		      center = new Panel(), 
			  south  = new Panel();

		north.add(editor);       

		center.setLayout(new GridLayout(2,2));
		center.add(findLabel);
		center.add(findField);    
		center.add(changeLabel); 
		center.add(changeField);  
		
		south.add(findButton); 
		south.add(changeFindButton);
		south.add(changeButton);

		setLayout(new BorderLayout());
		add(north, "North");
		add(center, "Center");
		add(south, "South");

		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				findNext(findField.getText());
			}
		});
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				replace(changeField.getText());
			}
		});
		changeFindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int start = editor.getSelectionStart(), 
					end   = editor.getSelectionEnd();
				
				if(start != end) 
					replace(changeField.getText());

				findNext(findField.getText());
			}
		});
	}
	void replace(String change) {
		int start = editor.getSelectionStart(), 
			end   = editor.getSelectionEnd();
				
		if(start != end) 
			editor.replaceRange(change,start,end);
	}
	void findNext(String find) {
		String edit   = editor.getText();
		int    start  = editor.getSelectionStart(), 
		       end    = editor.getSelectionEnd(),
		       index  =  -1;

		index = edit.indexOf(find, start+1);

		if(index == -1 && start != 0) {
			index = edit.indexOf(find);
		}
		if(index != -1) {
			editor.setSelectionStart(index);
			editor.setSelectionEnd(index + find.length());
		}
	}
}
