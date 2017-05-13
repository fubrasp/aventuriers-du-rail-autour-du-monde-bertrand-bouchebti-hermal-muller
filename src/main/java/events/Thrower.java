package events;

/*_______________________________________________________________________License
	Copyright © 2014 Roy Pfund
	Licensed under the Apache License, Version 2.0 (the  "License");
____________________________________________________________*/

import java.util.*;

public class Thrower {

    //Liste des catchers/receveurs
    public List<ThrowListener> listeners = new ArrayList<ThrowListener>();

    public void addThrowListener(ThrowListener toAdd){ listeners.add(toAdd); }

    //Envoie des evenements
    public void Throw(){ for (ThrowListener hl : listeners) hl.Catch();
    }
}
