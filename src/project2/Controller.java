package project2;


public class Controller {
	Model model;
	View current;
	public Controller(){
		model = new Model("users");
		current = new LoginView(this);
		current.draw();
	}
	
	public boolean login(String username, String password){
		if (model.setUser(username, password)) {
			current.close();
			current = new ProfileView(model.getUser(username),model.getFriendsPosts());
			current.draw();
			return true;
		}
		return false;
	}
	
	public void viewProfile(String username) {
		current.close();
		current = new ProfileView(model.getUser(username),model.getFriendsPosts());
		current.draw();
	}
	
	public void register() {
		current.close();
		current = new RegisterView(this);
		current.draw();
	}
	
	public void registerUser(String username,String password,String name, String imageLocation) {
		model.register(username, password, name, imageLocation);
		current.close();
		current = new LoginView(this);
		current.draw();
	}
	
	public boolean usernameAvailable(String username) {
		if(null == model.getUser(username)){
			return true;
		}
		return false;
	}
	
}
