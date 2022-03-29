package by.myproject.main.util;

public final class IDGenerate {
	
    private IDGenerate(){}
    
    static int x = 10;
    static int y = 10000;
    public static int getNewID(){
        return x + (int) (Math.random() * ((y - x) - 1));
    }
}
