package com.tampro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class Element{
	private int no;
	private String symbol;
	private double weight;
	private String name;
	

	public Element() {
		super();
	}
	public Element(String data) {
		String[] a = data.split(","); // cắt chuỗi ngăn cách bởi dấu , 
		// gặp dẫu , là cắt , bỏ vào mãng
		this.no = Integer.parseInt(a[0]);
		this.symbol=a[1];
		this.weight = Double.parseDouble(a[2]);
		this.name = a[3];
	}
	public Element(int no, String symbol, double weight, String name) {
		super();
		this.no = no;
		this.symbol = symbol;
		this.weight = weight;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Element [no=" + no + ", symbol=" + symbol + ", weight=" + weight + ", name=" + name + "]";
	}

}
class PeriodicTable{
	List<Element> elements;
	
	public PeriodicTable(){
		this.elements = loadElements();
	}
	private List<Element> loadElements(){
		try {
			//Files.lines(Paths.get("data.txt")) lấy tất cả các dòng trong file trên đường dẫn này
		return 	Files.lines(Paths.get("data.txt"))//Stream chứa chuỗi Stream<String>
			.map(s->new Element(s)) //elements Stream<Elemets> 
			.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;		
	}

	public List<Element> findByName(String pattern){
		return elements.stream() //Stream chua element
				.filter(e->e.getName().contains(pattern)) // lọc , tên của elements có chứa pattern
				.collect(Collectors.toList());
	}
	public List<Element> findByWeight(double min , double max){
		return elements.stream() // stream<Element>
				.filter(s->s.getWeight() >=  min && s.getWeight() <= max )
				.collect(Collectors.toList());
	}
	public List<Element> findAll(){
		return elements.stream() 
				.collect(Collectors.toList());
	}
}


public class Main {
	public static void main(String[] args) {
		PeriodicTable table = new PeriodicTable();
		System.out.println("search By name");
		List<Element> result = table.findByName("K");
		result.forEach(items->{System.out.println(items.getName());});
		System.out.println("search By weight");
		List<Element> resultW = table.findByWeight(0, 250);
		resultW.forEach(items->{System.out.println(items);});
		System.out.println("In list");
		table.findAll().forEach(items->{
			System.out.println(items);
		});
	}

}





