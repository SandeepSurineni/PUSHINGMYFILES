import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Filecomparsion {

        public static void main(String args[]) throws IOException {

        boolean IfEqual = true;
        int lineNum = 1;

        BufferedReader reader1 = new BufferedReader(new FileReader(args[0]));
        BufferedReader reader2 = new BufferedReader(new FileReader(args[1]));
        System.out.println("Comparsion of two different files checksum validation:");

        String data = reader1.readLine();
        String dat = reader2.readLine();

        while (data != null || dat != null)
                {
                        if(data == null || dat == null)
                        {
                                IfEqual = false;

                                break;
                        }
                        else if(! data.equalsIgnoreCase(dat))
                        {
                                IfEqual = false;

                                break;
                        }

                        data = reader1.readLine();

                        dat = reader2.readLine();

                        lineNum++;
                }

if(IfEqual)

                {
                        System.out.println("Two files have same checksum values ");
                }
                else
                {
                        System.out.println("Two files have different checksum values");


                }

reader1.close();

reader2.close();

}


}
