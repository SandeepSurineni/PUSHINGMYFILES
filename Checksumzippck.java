import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class Checksumzippck {
  
	public static void main(String args[]) throws Exception {

		String datafile = args[0];
		String MD5 = args[1];

		String basepath = "C:\\Users\\ssurineni\\Downloads\\codevalidation\\";

		File file1 = new File(basepath + datafile);
		
		//System.out.println("File Absolute Path : " + file1.getAbsolutePath());
		if (file1.exists()) 
		{
			System.out.println("File is Exists");
			//System.out.println("File Absolute Path : " + file1.getAbsolutePath());
		}
		else{
			System.out.println("If is not There :");
		}
			

		MessageDigest md = MessageDigest.getInstance("MD5");
		// MessageDigest sha = MessageDigest.getInstance("SHA256");

		FileInputStream fis = new FileInputStream(file1);

		byte[] dataBytes = new byte[1024];

		int nread = 0;

		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}
		;

		byte[] mdbytes = md.digest();

		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		System.out.println(sb.toString());
		String computechecksum = sb.toString();
		
		if(MD5 != null  && computechecksum!=null)
		{
			if(MD5.equals(computechecksum)) {
				
            System.out.println("Both file have same checksum values:");
			} else {
				System.out.println("Both files are having different checksum values:");
			}
			
		}
		else {
			System.out.println("Null");
		}
	}
}

	