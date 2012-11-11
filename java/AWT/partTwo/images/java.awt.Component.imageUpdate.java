public boolean imageUpdate(Image img, int flags,
			       			int x, int y, int w, int h) {
	int rate = -1;

	if ((flags & (FRAMEBITS|ALLBITS)) != 0) {
	    rate = 0;
	} 
	else if ((flags & SOMEBITS) != 0) {
		if (isInc) {
			try {
				rate = incRate;

				if (rate < 0)
					rate = 0;
			} 
			catch (Exception e) {
				rate = 100;
			}
		}
	}
	if (rate >= 0) {
		repaint(rate, 0, 0, width, height);
	}
	return (flags & (ALLBITS|ABORT)) == 0;
}
