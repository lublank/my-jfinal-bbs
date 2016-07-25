package blank.bbs.handle;
  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import com.jfinal.handler.Handler;

public class Basehandler extends Handler {
	
	String charset = "UTF-8";
	
	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) 
				{ 
		try {
				request.setCharacterEncoding(this.charset);
				response.setCharacterEncoding(this.charset);
			} catch (Exception e) { 
				e.printStackTrace();
			}; 
		nextHandler.handle(target, request, response, isHandled); 
	}

}
