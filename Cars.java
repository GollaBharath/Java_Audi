public class Cars {
    
}

abstract class Car {
    void accelerate(){

    }
    void brake(){

    }
    abstract void gears();
    abstract void engine();
    abstract void steering();
}

class Manual extends Car {
    void clutch(){

    }
    void engine() {
        
    }
    void gears() {
        
    }
    void steering(){

    }
}

class Auto extends Car {
    void sportsModel(){

    }
    void engine() {
        
    }
    void gears() {
        
    }
    void steering(){

    }
}