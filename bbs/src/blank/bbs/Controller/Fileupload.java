package blank.bbs.Controller;
 

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.jfinal.core.Controller; 
import com.jfinal.kit.PathKit;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;

import net.coobird.thumbnailator.Thumbnails;

public class Fileupload extends Controller {

	public  String savephoto() throws Exception{  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		 String dateStr = sdf.format(new Date());
		 String path = PathKit.getWebRootPath();
		 
		 String savePath = path+"/public/attachment/";
		 UploadFile uf = getFile("file",savePath);//file为前台type="file"的name
		 
		 String ufname = uf.getFileName();
		 int index = ufname.lastIndexOf("."); 
		 String last = ufname.substring(index); 
		 String  type = ufname.substring(index+1); 
		DateTime dateTime = new DateTime();
		 if(uf != null) {
		    uf.getFile().renameTo(new File(savePath+dateStr+last)); 
		  //压缩图片
			Thumbnails.of(savePath+dateStr+last)
			          .size(160, 160)//若图片宽比60大，高比60大，图片按比例缩小，横为160或高为160
			          .outputFormat(type )//图片输出格式 
			          .toFile(savePath + dateTime.toString("yyyyMMddhhmmss") +last);//输出地址
		 }
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("success", true);
		 map.put("error", "上传图片出错！请重试");
		 map.put("filename", dateStr+last);
		 map.put("path", "public/attachment/" + dateTime.toString("yyyyMMddhhmmss") +last);
		 render(new JsonRender("tbody",map).forIE());
		 return null;
	}
}
