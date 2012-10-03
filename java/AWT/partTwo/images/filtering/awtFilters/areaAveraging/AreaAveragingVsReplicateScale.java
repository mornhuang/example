import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;

public class AreaAveragingVsReplicateScale extends Applet {
 	private Image im;
	private Image aa, rs;
	private int imw, imh;

	public void init() {
		MediaTracker mt  = new MediaTracker(this);
		URL 		 url = getClass().getResource("pic.gif");

		try {
			im = createImage((ImageProducer)url.getContent());
			mt.addImage(im, 0);
			mt.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }	

		imw = im.getWidth(this);
		imh = im.getHeight(this);

		ImageFilter aaFilter = 
			new AreaAveragingScaleFilter(imw, imh);

		ImageFilter rsFilter = 
			new ReplicateScaleFilter(imw, imh);

		FilteredImageSource aaSource = 
			new FilteredImageSource(im.getSource(), aaFilter);

		FilteredImageSource rsSource = 
			new FilteredImageSource(im.getSource(), rsFilter);

		aa 	= createImage(aaSource);	
		rs 	= createImage(rsSource);	
	}
	public void paint(Graphics g) {
		g.drawImage(im,0,0,this);
	  	g.drawImage(rs,imw,0,this);
		g.drawImage(aa,imw*2,0,this);
	}
}
