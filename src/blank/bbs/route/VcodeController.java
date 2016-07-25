package blank.bbs.route; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; 
import javax.imageio.ImageIO;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import org.patchca.background.BackgroundFactory;
import org.patchca.filter.ConfigurableFilterFactory;
import org.patchca.filter.library.AbstractImageOp;
import org.patchca.filter.library.WobbleImageOp;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.word.RandomWordFactory; 
import com.jfinal.core.Controller;

public class VcodeController extends Controller { 
	
	private ConfigurableCaptchaService configurableCaptchaService = null;
	private RandomFontFactory fontFactory = null;
	private RandomWordFactory wordFactory = null;
	private TextRenderer textRenderer = null; 
	
	
	public VcodeController() {
		super();
	}

	public void destroy() {
		wordFactory = null;
		fontFactory = null;
		textRenderer = null;
		configurableCaptchaService = null; 
	}

	
	public void index() throws ServletException, IOException{
		init(getRequest(),getResponse()); 
		this.destroy(); 
		renderNull();
	}
	
	public void init(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		configurableCaptchaService = new ConfigurableCaptchaService();
		List<String> families = new ArrayList<String>();
		families.add("Times New Roman");
		// 随机字体生成器
		fontFactory = new RandomFontFactory();
		fontFactory.setMaxSize(26);
		fontFactory.setMinSize(24);
		fontFactory.setFamilies(families);
		configurableCaptchaService.setFontFactory(fontFactory);

		// 随机字符生成器，去除掉泳衣混淆的字母和数字，如0和O等
		wordFactory = new RandomWordFactory();
		wordFactory.setCharacters("abcdefghijklmnpqrstwxyz123456789");
		wordFactory.setMaxLength(4);
		wordFactory.setMinLength(4);
		configurableCaptchaService.setWordFactory(wordFactory);

		// 自定义验证码图片背景
		MyCustomBackgroundFactory backgroundFacoty = new MyCustomBackgroundFactory();
		configurableCaptchaService.setBackgroundFactory(backgroundFacoty);

		// 图片滤镜设置
		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
		WobbleImageOp wobbleImageOp = new WobbleImageOp();
		wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
		wobbleImageOp.setyAmplitude(1.0);
		wobbleImageOp.setxAmplitude(2.0);
		filters.add(wobbleImageOp);
		filterFactory.setFilters(filters);
		configurableCaptchaService.setFilterFactory(filterFactory);

		// 文字渲染器设置
		textRenderer = new BestFitTextRenderer();
		textRenderer.setBottomMargin(3);
		textRenderer.setTopMargin(3);
		configurableCaptchaService.setTextRenderer(textRenderer);

		// 验证码图片的大小
		configurableCaptchaService.setWidth(80);
		configurableCaptchaService.setHeight(27);
		getpicture(request, response);
	}

	public void getpicture(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/gif");
		response.setHeader("cache",
				"no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		HttpSession session = request.getSession();
		OutputStream outputStream = response.getOutputStream();
		// 得到验证码对象，有验证码照片和验证码字符串
		Captcha captcha = configurableCaptchaService.getCaptcha();
		// 取得验证码字符串放入session
		String validationCode = captcha.getChallenge();
		session.setAttribute("validationCode", validationCode);
		// 取得验证码照片并输出
		BufferedImage bufferedImage = captcha.getImage();
		ImageIO.write(bufferedImage, "gif", outputStream);
		outputStream.flush();
		outputStream.close();
	}

	private class MyCustomBackgroundFactory implements BackgroundFactory {
		// 创建随机类的实例
		private Random random = new Random();

		public void fillBackground(BufferedImage image) {
			Graphics graphics = image.getGraphics();

			// 验证码图片的宽高
			int imgWidth = image.getWidth();
			int imgHeight = image.getHeight();

			// 设定图像背景色
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, imgWidth, imgHeight);

			// 在图片背景上增加噪点
			graphics.setColor(ImageCaptcha.getRandColor(180, 250));

			// 线条数
			for (int i = 0; i < 50; i++) {
				int x = random.nextInt(imgWidth);
				int y = random.nextInt(imgHeight);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				graphics.setColor(ImageCaptcha.getRandColor(100, 250));
				graphics.drawLine(x, y, x + xl, y + yl);
			}
		}

	}

}
