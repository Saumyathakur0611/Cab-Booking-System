import java.util.*;
class QueueCab{
	String name;
	int i;
	String ph_no;
	QueueCab next,curr;
	String email;
	String source;
	String dest;
	String mode;
	double cost;
	QueueCab(String n,String ph_no,QueueCab next){
		
		this.name=n;
		this.next=next;
		this.ph_no=ph_no;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPh_no() {
		return ph_no;
	}
	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}
	public QueueCab getNext() {
		return next;
	}
	public void setNext(QueueCab next) {
		this.next = next;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}


}
public class Cab {
	Scanner sc=new Scanner(System.in);
	QueueCab curr;
	QueueCab front;
	QueueCab rear;
	int dist;

	public Cab()
	{
		front = null;
		rear  = null;
	}
	int empty()
	{
		if(front==null)
			return 1;
		else
			return 0;
	}

	QueueCab login() {

		String name, email,ph_no;
		QueueCab curr1=null;
		System.out.println("\nAre you an existing user?(y/n)");
		String o=sc.next();
		char p=o.charAt(0);
		if(p=='y') {
			System.out.println("\nUSER LOGIN");
			System.out.println("Enter name:");
			name=sc.next();
			System.out.println("Enter phone number:");
			ph_no=sc.next();

		}
		else {
			System.out.println("\nSIGN UP");
			System.out.println("Enter name:");
			name=sc.next();
			System.out.println("Enter email address:");
			email=sc.next();
			//book a cab+
			System.out.println("Enter phone number:");
			ph_no=sc.next();


		}
		curr1=new QueueCab(name,ph_no,curr1);
		return curr1;



	}

	void book()
	{
		int ch=0;

		do
		{
			System.out.println("Enter your name:");
			String name=sc.next();
			System.out.println("Enter your phone number:");
			String ph_no=sc.next();
			QueueCab curr1=new QueueCab(name,ph_no,curr);

			Random r=new Random();
			dist=r.nextInt(50);
			System.out.println("Enter your pickup point:");
			curr1.source=sc.next();
			System.out.println("Enter your destination:");
			curr1.dest=sc.next();
			System.out.println("Distance between "+curr1.source+" ------> "+curr1.dest+" is "+dist+"km");
			System.out.println("Our available rides:");
			System.out.println("1.Car\n2.Auto\n3.Bike");
			System.out.println("Enter choice for mode of transport");
			ch=sc.nextInt();
			if(ch<=3 && ch>=1)
			{
				totalcost(ch,curr1);
			}
			else
				System.out.println("INVALID CHOICE");
			System.out.println("");
			System.out.println("\nAre you sure you want to book?(y/n)");
			String b=sc.next();
			char s=b.charAt(0);
			if(s=='n'||s=='N') {
				System.out.println("BOOKING CANCELLED");

			}
			else {
				System.out.println("BOOKING CONFIRMED!");

				if(empty()==1)
				{
					front=curr1;
					rear=curr1;
				}
				else
				{
					QueueCab ptr=front;

					while(ptr.next!=null) {
						ptr=ptr.next;
					}

					ptr.next=curr1;

				}
			}

			System.out.println("\nDo you want to place another order 1.yes 0.exit");
			ch=sc.nextInt();
		}while(ch==1);
	}


	void totalcost(int ch,QueueCab curr)
	{
		double cost = 0;
		if(ch==1)
		{
			if(dist<=2)
				cost=30;
			if(dist>2 && dist<=5)
				cost=21+((dist-2)*30);
			if(dist>5 && dist<=20)
				cost=21+60+((dist-5)*25);
			if(dist>20)
				cost=21+60+225+((dist-20)*20);
			System.out.println("car:- Rs:"+cost);
			curr.setMode("car");
		}
		if(ch==2)
		{
			if(dist<=2)
				cost=21;
			if(dist>2 && dist<=5)
				cost=21+((dist-2)*20);
			if(dist>5 && dist<=20)
				cost=21+60+((dist-5)*15);
			if(dist>20)
				cost=21+60+225+((dist-20)*10);
			System.out.println("auto:- Rs:"+cost);
			curr.setMode("auto");
		}
		if(ch==3)
		{
			if(dist<=2)
				cost=18;
			if(dist>2 && dist<=5)
				cost=21+((dist-2)*18);
			if(dist>5 && dist<=20)
				cost=21+60+((dist-5)*12);
			if(dist>20)
				cost=21+60+225+((dist-20)*8);
			System.out.println("bike:- Rs:"+cost);
			curr.setMode("bike");
		}
		curr.cost=cost;
	}
	void display() {
		if (front == null) {
			System.out.println("No Ride in Queue");
		}
		else {
			System.out.println("\n***** Ride Details*****");
			curr = front;

			while(curr!= null) {
				System.out.println();

				System.out.println("Passenger name: "+ curr.name);
				System.out.println("Passenger no: "+ curr.ph_no);
				System.out.println("Passenger Journey: "+ curr.source+ " to "+ curr.dest) ;
				System.out.println("Mode of tranasport:"+curr.mode);
				System.out.println("Journey cost: "+ curr.cost);
				curr = curr.next;
			}

		}
	}


	void dequeue() 
	{

		if(empty()==1)
		{
			System.out.println("No cab bookings available");
		}
		else
		{
			
			System.out.println("Customer dropped!");
			System.out.println("Customer name: "+front.name);
			System.out.println("Customer Phone No.: "+front.ph_no);
			System.out.println("Cab ride Cost: "+front.cost);
			front=front.next;
			
		}

	}




	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int flag=0;
		int p=1;
		Cab c=new Cab();
	System.out.println("WELCOME TO CAB BOOKING!");
		do {
			System.out.println("\nServices Available:");
			int k;
			System.out.println("1. Register/Login ");
			System.out.println("2. Book a cab");
			System.out.println("3. Display customer list");
			System.out.println("4. Drop a customer");

			System.out.println("5. Exit");
			System.out.println("Enter your option:");
			k=sc.nextInt();
			switch(k) {
			case 1:c.login();
			flag=1;break;

			case 2:
				if(flag==1) {
					c.book();
				}
				else {
					System.out.println("Please login first to use our services");	
					c.login();
					flag=1;
				}

				break;
			case 3: c.display();
			break;
			case 4:c.dequeue();
			break;
			case 5:System.out.println("\nThank you for using our services!");
				System.exit(0);
			break;
			default:System.out.println("Incorrect option"); break;

			}
			System.out.println("\nDo you want to continue?");
			System.out.println("press 1 to continue and 0 to exit");
			p=sc.nextInt();



		}
		while(p==1);
		if(p!=1) {
			System.out.println("\nThank you for using our services!");
			System.exit(0);
		}

	}
}
/*
OUTPUT:
WELCOME TO CAB BOOKING!

Services Available:
1. Register/Login 
2. Book a cab
3. Display customer list
4. Drop a customer
5. Exit
Enter your option:
1

Are you an existing user?(y/n)
y

USER LOGIN
Enter name:
Arnav
Enter phone number:
4455664455

Do you want to continue?
press 1 to continue and 0 to exit
1

Services Available:
1. Register/Login 
2. Book a cab
3. Display customer list
4. Drop a customer
5. Exit
Enter your option:
2
Enter your name:
Tania
Enter your phone number:
7788779988
Enter your pickup point:
Baner
Enter your destination:
karvenagar
Distance between Baner ------> karvenagar is 41km
Our available rides:
1.Car
2.Auto
3.Bike
Enter choice for mode of transport
2
auto:- Rs:516.0


Are you sure you want to book?(y/n)
y
BOOKING CONFIRMED!

Do you want to place another order 1.yes 0.exit
1
Enter your name:
Yadav
Enter your phone number:
8855664422
Enter your pickup point:
Yerawada
Enter your destination:
balewadi
Distance between Yerawada ------> balewadi is 48km
Our available rides:
1.Car
2.Auto
3.Bike
Enter choice for mode of transport
3
bike:- Rs:530.0


Are you sure you want to book?(y/n)
y
BOOKING CONFIRMED!

Do you want to place another order 1.yes 0.exit
1
Enter your name:
Ria
Enter your phone number:
5544665544
Enter your pickup point:
Camp
Enter your destination:
Ghorpadi
Distance between Camp ------> Ghorpadi is 45km
Our available rides:
1.Car
2.Auto
3.Bike
Enter choice for mode of transport
1
car:- Rs:806.0


Are you sure you want to book?(y/n)
y
BOOKING CONFIRMED!

Do you want to place another order 1.yes 0.exit
1
Enter your name:
Dinesh
Enter your phone number:
7788552200
Enter your pickup point:
kalewadi
Enter your destination:
rahatni
Distance between kalewadi ------> rahatni is 12km
Our available rides:
1.Car
2.Auto
3.Bike
Enter choice for mode of transport
2
auto:- Rs:186.0


Are you sure you want to book?(y/n)
n
BOOKING CANCELLED

Do you want to place another order 1.yes 0.exit
0

Do you want to continue?
press 1 to continue and 0 to exit
1

Services Available:
1. Register/Login 
2. Book a cab
3. Display customer list
4. Drop a customer
5. Exit
Enter your option:
3

***** Ride Details*****

Passenger name: Tania
Passenger no: 7788779988
Passenger Journey: Baner to karvenagar
Mode of tranasport:auto
Journey cost: 516.0

Passenger name: Yadav
Passenger no: 8855664422
Passenger Journey: Yerawada to balewadi
Mode of tranasport:bike
Journey cost: 530.0

Passenger name: Ria
Passenger no: 5544665544
Passenger Journey: Camp to Ghorpadi
Mode of tranasport:car
Journey cost: 806.0

Do you want to continue?
press 1 to continue and 0 to exit
1

Services Available:
1. Register/Login 
2. Book a cab
3. Display customer list
4. Drop a customer
5. Exit
Enter your option:
4
Customer dropped!
Customer name: Tania
Customer Phone No.: 7788779988
Cab ride Cost: 516.0

Do you want to continue?
press 1 to continue and 0 to exit
1

Services Available:
1. Register/Login 
2. Book a cab
3. Display customer list
4. Drop a customer
5. Exit
Enter your option:
3

***** Ride Details*****

Passenger name: Yadav
Passenger no: 8855664422
Passenger Journey: Yerawada to balewadi
Mode of tranasport:bike
Journey cost: 530.0

Passenger name: Ria
Passenger no: 5544665544
Passenger Journey: Camp to Ghorpadi
Mode of tranasport:car
Journey cost: 806.0

Do you want to continue?
press 1 to continue and 0 to exit
1

Services Available:
1. Register/Login 
2. Book a cab
3. Display customer list
4. Drop a customer
5. Exit
Enter your option:
5

Thank you for using our services!

 */
