package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		String path = "c:\\temp\\in.txt";
		Double salary = 2000.0;
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			
			List<Employee> emp = new ArrayList<Employee>();
			
			while(line != null) {
				String fields[] = line.split(",");
				emp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			
			List<String> email = emp.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());

			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) +  ":");
			email.forEach(System.out::println);
			
			List<Double> sumSalary = emp.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.collect(Collectors.toList());
			
			
			double sum = 0.0;
			for(double s : sumSalary) {
				sum = sum + s;
			}
			System.out.println("Sum of salary of people whose name starts with 'M': "+String.format("%.2f", sum));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
