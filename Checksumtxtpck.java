import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class Checksumtxtpck {
	
	  public static void main(String args[]) throws Exception {
		  
		    String datafile = args[0];
	         String md5Portal = args[1];
		    File file1 = new File(datafile);
		      
		    MessageDigest md = MessageDigest.getInstance("MD5");
		 // MessageDigest sha = MessageDigest.getInstance("SHA256");
		    
		    FileInputStream fis = new FileInputStream(datafile);
		    
		    byte[] dataBytes = new byte[1024];
		 
		    int nread = 0; 
		 
		    while ((nread = fis.read(dataBytes)) != -1) {
		      md.update(dataBytes, 0, nread);
		    };
		 
		    byte[] mdbytes = md.digest();
		 
		    StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < mdbytes.length; i++) {
		    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    }
		 
		   System.out.println("MD5:"+sb.toString() );
                boolean IfEqual = true;
	        int lineNum = 1;
	        
	        System.out.println("Comparsion of two different files checksum validation:");
	        
	        while (datafile != null || md5Portal != null)
            {
                    if(datafile == null || md5Portal == null)
                    {
                            IfEqual = false;

                            break;
                    }
                    else if(! datafile.equalsIgnoreCase(md5Portal))
                    {
                            IfEqual = false;

                            break;
                    }

                    

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




		  }

}