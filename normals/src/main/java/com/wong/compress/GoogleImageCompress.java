package com.wong.compress;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
* @author HuangZhibin
* 
* 2018年7月14日 上午10:32:45
*/
public class GoogleImageCompress {

	@Test
	public void compress() throws IOException {
		String path = "C:\\Users\\Administrator\\Desktop\\N-0033-33.jpg";
		
//		Thumbnails.of(path).scale(0.5).toFile("C:\\Users\\Administrator\\Desktop\\test.jpg");
//		Thumbnails.of(path).size(750, 750).toFile("C:\\Users\\Administrator\\Desktop\\test1.jpg");
//		Thumbnails.of(path).width(800).toFile("C:\\Users\\Administrator\\Desktop\\test2.jpg");
		Thumbnails.of(path)
        	.height(800)
        	.rotate(90)
        	.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new URL("https://dls.zgps168.com/statics/images/common/e_login_top_bg.png")), 0.5f)
        	.outputQuality(0.8)
        	.toFile("C:\\Users\\Administrator\\Desktop\\test.jpg");
	}
}
