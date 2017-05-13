package events;

/*_______________________________________________________________________License
	Copyright © 2014 Roy Pfund
	Licensed under the Apache License, Version 2.0 (the  "License");
____________________________________________________________*/

public class Catcher implements ThrowListener {//implement added to class
    @Override public void Catch() {
        System.out.println("I caught something!!");
    }
}
