// Mobile Access Control: Access Unit Prototype
// (C) 2002, Swisscom Corporate Technology, S. Moscibroda

// Helping class to display Bitmap pictures in a simple way




import java.awt.*;

class BitmapComponent
extends Canvas
{
   private Image img;

   public BitmapComponent(String fname)
   {
     img = getToolkit().getImage(fname);
     MediaTracker mt = new MediaTracker(this);

     mt.addImage(img, 0);
     try {
     //Warten, bis das Image vollständig geladen ist,
      //damit getWidth() und getHeight() funktionieren
       mt.waitForAll();
     } catch (InterruptedException e) {
      //nothing
    }
   }

   public void paint(Graphics g)
   {
     g.drawImage(img,1,1,this);
   }

   public Dimension getPreferredSize()
   {
     return new Dimension(
       img.getWidth(this),
       img.getHeight(this)
     );
   }

   public Dimension getMinimumSize()
   {
     return new Dimension(
       img.getWidth(this),
       img.getHeight(this)
     );
   }
}
