public class CatAndMouseSolution {

    static String catAndMouse(int x, int y, int z) {


        if (Math.abs(z - x) < Math.abs(z - y)) {
            return "Cat A";
        } else if (Math.abs(z - y) < Math.abs(z - x)) {
            return "Cat B";
        } else {
            return "Mouse C";
        }
    }


    public static void main(String[] args){
        System.out.println(catAndMouse(1,2,3));
        System.out.println(catAndMouse(1,3,2));
    }

}
