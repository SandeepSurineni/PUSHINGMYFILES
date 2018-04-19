import java.io.File;
public class FileExtensionType {
	
    public static void main(String[] args) {

         //accept file name or directory name through command line args   
        String fname =args[0];

        File file = new File(fname);
		
		System.out.println("Argument is "+args[0]);
        //System.out.println("File extension Type is :"+getFileExtension(file));

        if(getFileExtension(file).endsWith("zip") || getFileExtension(file).endsWith("tar")) {
        	System.out.println(" File is Valid " +file.toString());
        }
        
     else 
     {
         System.out.println("File is not Valid");
     }
        
       // System.out.println("File extension Type is:"+getFileExtension(file));    
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
}
