package com.wong.feature;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

/**
* @author HuangZhibin
* 
* 2017年12月14日 下午2:39:00
*/
public class ImageCodeUtil {

	private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

	public static void create(String code, OutputStream out, int imgWidth, int imgHeight) throws IOException {
		
		BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D grap = image.createGraphics();
		grap.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		grap.setColor(Color.GRAY);
		grap.fillRect(0, 0, imgWidth, imgHeight);
		
		grap.setColor(getColor(200, 250));
		grap.fillRect(1, 1, imgWidth-1, imgHeight-1);
		
		// 绘制干扰线
		grap.setColor(getColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = RANDOM.nextInt(imgWidth - 1);
			int y = RANDOM.nextInt(imgHeight - 1);
			int xl = RANDOM.nextInt(6) + 1;
			int yl = RANDOM.nextInt(12) + 1;
			grap.drawLine(x, y, x + xl + 40, y + yl + 20);
		}
		
		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * imgWidth * imgHeight);
		for (int i = 0; i < area; i++) {
			int x = RANDOM.nextInt(imgWidth);
			int y = RANDOM.nextInt(imgHeight);
			int rgb = getColor(200, 250).getRGB();
			image.setRGB(x, y, rgb);
		}
		
		int fontSize = imgHeight - 4;
		Font font = new Font("Algerian", Font.ITALIC, fontSize);
		grap.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < code.length(); i++) {
			grap.setColor(getColor(100, 200));
			grap.drawChars(chars, i, 1, ((imgWidth - 10) / code.length()) * i + 5, imgHeight / 2 + fontSize / 2 - 10);
		}
		
		grap.dispose();
		ImageIO.write(image, "jpg", out);
		
	}

	private static Color getColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + RANDOM.nextInt(bc - fc);
		int g = fc + RANDOM.nextInt(bc - fc);
		int b = fc + RANDOM.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
