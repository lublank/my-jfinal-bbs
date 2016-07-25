package blank.bbs.route;
 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

 

public class ImageCaptcha {  
	private final static Random random = new Random();

	/**
	 * 画随机码图
	 * 
	 * @param text
	 * @param out
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static void _Render(String text, OutputStream out, int width,
			int height) throws IOException {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();

		// 设定图像背景色(设置为白色)
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		String[] set = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
				"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
				"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", };
		// 备选字体
		String[] fontTypes = { "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53",
				"\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" };
		// 创建内存图像
		// BufferedImage image = new BufferedImage(width, height,
		// BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		// Graphics g = image.getGraphics();
		// 设定图像背景色(设置为白色)
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 创建随机类的实例
		Random random = new Random();
		// 在图片背景上增加噪点
		g.setColor(getRandColor(160, 200));
		// 定义图片字体的样式
		g.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(22);
			int yl = random.nextInt(29);
			g.setColor(getRandColor(160, 250));
			g.drawLine(x, y, x + xl, y + yl); // 线条数
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int len = 4;// 生成四位验证码
		for (int i = 0; i < len; i++) {
			int nRand = random.nextInt(set.length);
			String subRand = set[nRand];

			// 设置字体的随机颜色
			g.setColor(getRandColor(10, 150));
			// 设置随机字体及大小
			g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)],
					Font.BOLD, 18 + random.nextInt(6)));

			// 将此字母画到图片上
			g.drawString(subRand, 17 * i, 18);
			// 将产生的四个随机数组合在一起。
			randomCode.append(subRand);
		}
		ImageIO.write(bi, "png", out);
	}

	public static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
 
}
