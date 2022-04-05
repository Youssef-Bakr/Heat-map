import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    static String path;
    static AVLTree plantIndex;
    static Scanner sourceFile;

    public static void runProgram(AVLTree plantIndex){

    }
    public static void initialize(String path) throws FileNotFoundException {
        plantIndex = new AVLTree();
        try {
            sourceFile = new Scanner(new File(path));
        }catch(FileNotFoundException ex){
            System.out.println("Error reading the file!");
            System.exit(1);
        }
        while(sourceFile.hasNextLine()){
            String Data = sourceFile.nextLine();
            String plantName = Data.substring(0, Data.indexOf(">") -1);
            String[] plants = Data.substring(Data.indexOf(">") +1).split(",");
            try {
                for (int i = 0; i < plants.length; i++) {
                    plantIndex.insert(plantName, plants[i]);
                }
            } catch(ArrayIndexOutOfBoundsException ignored){

            }
            Menu.MainProgram(plantIndex);
        }
        plantIndex.Display();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Menu.getPath();
    }


}
