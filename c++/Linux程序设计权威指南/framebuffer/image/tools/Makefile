
all: logo_cnv images

logo_cnv: logo_cnv.c
	gcc -o logo_cnv logo_cnv.c

images: penguin.pnm
	ppmquant 224 penguin.pnm > penguin_quant.ppm
	ppmtorgb3 penguin_quant.ppm
	./logo_cnv penguin_quant image
	mv -f image.* ../pics

clean:
	rm -f logo_cnv *.bak penguin_quant.*
	rm -f ../pics/*
