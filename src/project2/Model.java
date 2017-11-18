package project2;

import java.util.*;

public class Model {
	ArrayList<View> views = new ArrayList<View>();
	
	public boolean setUser(String username,String password){
		if(username.equals("jake") && password.equals("pass")){
			ProfileView profile = new ProfileView();
			profile.draw();
			return true;
		}
		return false;
	}
}
