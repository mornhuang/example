import java.awt.*;
 
	class ActionTest extends Frame {
		Button    	button = new Button("Cancel");
		Checkbox  	checkbox = new Checkbox(
									"Something to check about");
		TextField 	textfield = new TextField(25);
		Choice    	choice 	= new Choice();
		List 	 	 	 list 	= new List();
		MenuItem  	quitItem = new MenuItem("quit");

		static public void main(String args[]) {
			Frame frame = new ActionTest();
			frame.reshape(100,100,200,200);
			frame.show();
		}
		public ActionTest() {
			super("Action Test");

			MenuBar menubar  = new MenuBar();
			Menu    fileMenu = new Menu("File");

			fileMenu.add("menu item");
			fileMenu.add(quitItem);
			menubar.add(fileMenu);
			setMenuBar(menubar);

			choice.addItem("One");
			choice.addItem("Two");
			choice.addItem("Three");
			choice.addItem("Four");

			list.addItem("item One");
			list.addItem("item Two");
			list.addItem("item Three");
			list.addItem("item Four");
			list.addItem("item Five"); 
			list.addItem("item Six");
 
			setLayout(new GridLayout(0,1));
			add(button);
			add(checkbox);
			add(list);
			add(textfield);
			add(choice);
		}
		public boolean action(Event event, Object what) {
			if(event.target == quitItem) {
				System.exit(0);
			}
			System.out.print(event.target.getClass().getName());
			System.out.println(" " + what.getClass().getName() + 
			                   "= " + what);
			return true;
		}
	}
