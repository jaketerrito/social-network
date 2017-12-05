# social-network
My own social network, based off facebook.
The program uses the Model View Controller design pattern, totally decoupling the model from the gui.
The model is a singleton that holds all of the user data and contains all the functionality for manipulating this data.
The controller uses data from the model in order to generate the view (ProfileView,LoginView, SettingsView...)
and notifies the model whenever there is new user input. The model stores user data dynamically during runtime,
but writes to files when the program is closed.

User data is stored in a file, here's an example for Bart Simpson.

     username: bart
     password: 1234
     name: Bart Simpson
     image: home/downloads/bart.png
     friends: lisa,milhouse,homer
     posts:
     1512497630800 ,lisa,homer, Eat my shorts!

The last line is a post, with a long for the time posted(System.currentTimeMillis()), followed by a list of usernames that liked the post, than the text itself.
This was my first time using swing and I was crunched for time, proud of what I was able to create.

The javadocs can be found [here](https://jaketerrito.github.io/social-network/index.html).
