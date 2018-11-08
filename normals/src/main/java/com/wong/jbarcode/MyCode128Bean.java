package com.wong.jbarcode;

import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Encoder;
import org.krysalis.barcode4j.impl.code128.DefaultCode128Encoder;

/**
* @author HuangZhibin
* 
* 2018年3月1日 上午11:14:30
*/
public class MyCode128Bean extends Code128Bean {

	@Override
	public BarcodeDimension calcDimensions(String msg) {
		Code128Encoder encoder = new DefaultCode128Encoder(getCodeset());
        int msgLen = 0;

        msgLen = encoder.encode(msg).length + 1;

        final double width = ((msgLen * 11) + 13) * getModuleWidth();
        final double qz = (hasQuietZone() ? quietZone : 0);
        final double vqz = (hasQuietZone() ? quietZoneVertical.doubleValue() : 0);

        return new BarcodeDimension(width, getHeight(),
                width + (2 * qz), getHeight() + (2 * vqz),
                qz, vqz);
	}

	private int i = 0;
	@Override
	public double getBarWidth(int width) {
		i++;
		double w = super.getBarWidth(width)*0.95;
		System.out.println("w:"+w);
		System.out.println(i);
		return w;
	}
}
