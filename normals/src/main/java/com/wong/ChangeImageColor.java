package com.wong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author HuangZhibin
 * 
 *         2018年10月11日 下午1:56:23
 */
public class ChangeImageColor {

	private static List<Color> colors = new ArrayList<>(200);
	static {
		for (int i = 0; i < 200; i++) {
			colors.add(new Color(i, i, i));
		}
	}

	@Test
	public void change() throws Exception {
		String src = "C:\\Users\\Administrator\\Desktop\\get-xcx-qrcode.jpg";
		BufferedImage image = ImageIO.read(new File(src));
		// 244&g=204&b=204
		Color change = new Color(244, 204, 204);

		int width = image.getWidth();
		int height = image.getHeight();
		Color color = null;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				color = new Color(image.getRGB(x, y));
				if (colors.contains(color)) {
					image.setRGB(x, y, change.getRGB());
				} else if (color.getRGB() != -1) {
					System.out.println(color.getRGB());
				}
			}
		}

		ImageIO.write(image, "jpg", new File("C:\\Users\\Administrator\\Desktop\\xxx.jpg"));
	}
	
	@Test
	public void combine() throws Exception {
		String logoSrc = "http://dls2.zgps168.com/15318218456733980";
		String src = "C:\\Users\\Administrator\\Desktop\\test.png";
		BufferedImage image = ImageIO.read(new File(src));
		
		BufferedImage logo = getImage(logoSrc, image.getWidth()*9/20);
		
		//读取二维码图片，并构建绘图对象
		Graphics2D g2 = image.createGraphics();
		
		g2.drawImage(logo, (image.getWidth()-logo.getWidth())/2, (image.getHeight()-logo.getHeight())/2, null);
		
		image.flush();
		
		ImageIO.write(image, "png", new File("C:\\Users\\Administrator\\Desktop\\1.png"));
	}
	
	public BufferedImage getImage(String url, int width) throws Exception {
		url = StringUtils.split(url, "?")[0] + String.format("?roundPic/radius/%d|imageView2/2/w/%d/h/%d", width, width, width);
		BufferedImage image = ImageIO.read(new URL(url));
		
		return image;
	}
}

class RGB {
	int r;
	int g;
	int b;
	
	public RGB(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public int getRgb() {
		int rgb = ((r & 0xFF) << 16) |
				  ((g & 0xFF) << 8)  |
				  ((b & 0xFF) << 0);
		return rgb;
	}

	public static RGB transfer(int rgb) {
		int r = (0x00ff0000 & rgb) >> 16;
		int g = (0x0000ff00 & rgb) >> 8;
		int b = (0x000000ff & rgb);
		
		return new RGB(r, g, b);
	}
}