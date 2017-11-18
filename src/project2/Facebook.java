package project2;

public class Facebook {
	public static void main(String args[]){
		Controller controller = new Controller();
		LoginView login = new LoginView(controller);
		login.draw();
	}
}
