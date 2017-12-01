package project2;

public class Facebook {
	public static void main(String args[]){
		Controller controllerA = new Controller(Model.getInstance("resources/users"));
		Controller controllerB = new Controller(Model.getInstance("resources/users"));
	}
}
