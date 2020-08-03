import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig
public class FileUpload extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int x = 0;
		try {
			
		ServletFileUpload ser = new ServletFileUpload(new DiskFileItemFactory());
		
		List<FileItem> files = ser.parseRequest(request);
		
		
			for(FileItem item:files) {
		
			System.out.println(item.getName());
			 if(item.getName().endsWith("exe")) {
				 x=1;
				 
				System.out.println("cannot upload exe files ");
				
				
			}
			 else {
				 x=10;
				 item.write(new File("/Users/nymisha/Downloads/fileupload/"+ item.getName()));
				 request.setAttribute("message", "File Uploaded Successfully!!");
				 System.out.println("Uploaded Succesfully");
			}
		}
		}
		catch(Exception e) {
			if(x==1) {
				request.setAttribute("message", "File shouldnt be of exe !!");
			}
			else if(x==0) {
			 request.setAttribute("message", "File Upload Failed due to " + e);
			 System.out.println(e);}
		}
		
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}
	
}