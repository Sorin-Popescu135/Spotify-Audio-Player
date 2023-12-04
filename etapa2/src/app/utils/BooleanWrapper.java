package app.utils;

public class BooleanWrapper {
    private boolean bool;

    public BooleanWrapper(boolean bool){
        this.bool = bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public boolean getBool(){ return this.bool;}
}
