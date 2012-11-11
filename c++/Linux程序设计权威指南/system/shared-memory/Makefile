
all: sharedmem shm-semaphore

sharedmem: sharedmem.c
	gcc -o sharedmem sharedmem.c

shm-semaphore: shm-semaphore.c
	gcc -o shm-semaphore shm-semaphore.c

clean:
	rm -f *.o *.bak sharedmem shm-semaphore

