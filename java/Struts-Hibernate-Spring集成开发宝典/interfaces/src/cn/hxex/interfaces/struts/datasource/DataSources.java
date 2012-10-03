package cn.hxex.interfaces.struts.datasource;

import java.util.ArrayList;
import java.util.List;

import cn.hxex.interfaces.struts.bean.BarChart;
import cn.hxex.interfaces.struts.bean.President;

public class DataSources {

	public static List getWeekForecast()
	{
		ArrayList al = new ArrayList();
		
		al.add( new BarChart( "Sunday", 70 ) );
		al.add( new BarChart( "Monday", 50 ) );
		al.add( new BarChart( "Tuesday", 30 ) );
		al.add( new BarChart( "Wednesday", 10 ) );
		al.add( new BarChart( "Thursday", 20 ) );
		al.add( new BarChart( "Friday", 40 ) );
		al.add( new BarChart( "Saturday", 60 ) );
		
		return al;
	}
	
	public static List getPresidents()
	{
		List list = new ArrayList( );
		
        list.add( new President( "Washington", "George", "1789-97") );
        list.add( new President( "Adams", "John", "1797-1801") );
        list.add( new President( "Jefferson", "Thomas", "1801-09") );
        list.add( new President( "Madison", "James", "1809-17") );
        list.add( new President( "Monroe", "James", "1817-25") );
        list.add( new President( "Jackson", "Andrew", "1829-37") );
        list.add( new President( "Van Buren", "Martin", "1837-41") );
        list.add( new President( "Harrison", "William Henry", "1841") );
        list.add( new President( "Tyler", "John", "1841-45") );
        list.add( new President( "Polk", "James", "1845-49") );
        list.add( new President( "Taylor", "Zachary", "1849-50") );
        list.add( new President( "Fillmore", "Millard", "1850-53") );
        list.add( new President( "Pierce", "Franklin", "1853-57") );
        list.add( new President( "Buchanan", "James", "1857") );
        list.add( new President( "Lincoln", "Abraham", "1861-65") );
        list.add( new President( "Johnson", "Andrew", "1865-69") );
        list.add( new President( "Grant", "Ulysses S.", "1869-77") );
        list.add( new President( "Hayes", "Rutherford B.", "1877-81") );
        list.add( new President( "Garfield", "James", "1881") );
        list.add( new President( "Arthur", "Chester", "1881-85") );
        list.add( new President( "Cleveland", "Grover", "1885-89") );
        list.add( new President( "Harrison", "Benjamin", "1889-93") );
        list.add( new President( "Cleveland", "Grover", "1893-97") );
        list.add( new President( "McKinley", "William", "1897-1901") );
        list.add( new President( "Roosevelt", "Theodore", "1901-09") );
        list.add( new President( "Taft", "William H.", "1909-13") );
        list.add( new President( "Wilson", "Woodrow", "1913-21") );
        list.add( new President( "Jackson", "Andrew", "1829-37") );
        list.add( new President( "Harding", "Warren", "1921-23") );
        list.add( new President( "Coolidge", "Calvin", "1923-29") );
        list.add( new President( "Hoover", "Herbert", "1929-33") );
        list.add( new President( "Roosevelt", "Franklin D.", "1933-45") );
        list.add( new President( "Truman", "Harry", "1945-53") );
        list.add( new President( "Eisenhower", "Dwight", "1953-61") );
        list.add( new President( "Kennedy", "John F.", "1961-63") );
        list.add( new President( "Johnson", "Lyndon", "1963-69") );
        list.add( new President( "Nixon", "Richard", "1969-74") );
        list.add( new President( "Ford", "Gerald", "1974-77") );
        list.add( new President( "Carter", "Jimmy", "1977-81") );
        list.add( new President( "Reagan", "Ronald", "1981-89") );
        list.add( new President( "Bush", "George H.W.", "1989-93") );
        list.add( new President( "Clinton", "William J.", "1993-2001") );
        list.add( new President( "Bush", "George W.", "2001-present") );
        
        return list;
	}
}
