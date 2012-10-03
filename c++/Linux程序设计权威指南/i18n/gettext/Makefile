
all: sample mo


sample: sample.c
	gcc -o sample sample.c

mo:
	mkdir -p locale/zh_CN.GB2312/LC_MESSAGES
	mkdir -p locale/zh_TW.Big5/LC_MESSAGES
	cd locale; ln -s zh_CN.GB2312 zh_CN.GBK
	msgfmt -o locale/zh_CN.GB2312/LC_MESSAGES/sample.mo sample.po.zh_CN.GB2312
	msgfmt -o locale/zh_TW.Big5/LC_MESSAGES/sample.mo sample.po.zh_TW.Big5

clean:
	rm -f *.bak sample
	rm -fr locale
