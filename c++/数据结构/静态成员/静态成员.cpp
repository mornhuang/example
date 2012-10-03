# include <stdio.h>

class Pumpkin
{
private:
	int weight;
	static int total_weight;
	static int total_number;
public:
	Pumpkin(int w)
	{
	   weight = w;
	   total_weight +=w;
	   total_number++;
	}
	~Pumpkin()
	{
	   total_weight -=weight;
	   total_number--;
	}
	void display()
	{
	   printf("The pumpkin weights %d pounds\n",
		   weight);
       printf("The pumpkin total_weight %d pounds\n",
		   total_weight);
       printf("The pumpkin total_number %d pounds\n",
		   total_number);
	}
};

int Pumpkin::total_weight=0;
int Pumpkin::total_number=0;

int main()
{
    Pumpkin p1(55),p2(20),p3(12);
	p1.display();
	p2.display();
	p3.display();
}