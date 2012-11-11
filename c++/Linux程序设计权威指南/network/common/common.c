
#include <sys/types.h>
#include <errno.h>

//code from UNIX Network Programming by W.Richard Stevens
ssize_t readn(int fd, void *vptr, size_t n)
{
	ssize_t nleft, nread;
	char *ptr;

	ptr = vptr;
	nleft = n;
	while(nleft > 0){
		if( (nread = read(fd, ptr, nleft)) < 0 ){
			if(errno == EINTR) nread = 0;
			else return -1;
		} else if(nread == 0)
			break;		//EOF
		nleft -= nread;
		ptr += nread;
	}
	return (n - nleft);
}

ssize_t writen(int fd, void *vptr, size_t n)
{
	size_t nleft, nwritten;
	char *ptr;

	ptr = vptr;
	nleft = n;

	while(nleft > 0){
		if( (nwritten = write(fd, ptr, nleft)) <=0 ){
			if(errno == EINTR) nwritten = 0;
			else return -1;		//error
		}
		nleft -= nwritten;
		ptr += nwritten;
	}
	return n;
}


ssize_t readline(int fd, void *vptr, size_t maxlen)
{
	ssize_t n, rc;
	char c, *ptr;

	ptr = vptr;
	for(n = 1; n < maxlen; n++){
		again:
		if( (rc = read(fd, &c, 1)) == 1){
			*ptr++ = c;
			if(c == '\n') break;	//readover
		} else if(rc == 0){
			if(n == 1) return 0;	//EOF no data read
			else break;		//EOF some data read
		} else {
			if(errno == EINTR) goto again;
			return -1;		//error
		}
	}
	*ptr = 0;
	return n;
}

