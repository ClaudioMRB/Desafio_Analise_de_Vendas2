package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;

import entities.Sale;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			HashSet<String> list2 = new HashSet<String>();

			for (Sale vendedores : list) {
				list2.add(vendedores.getSeller());
			}
			System.out.println();
			System.out.println("Total de Vendas por Vendedor ");
			HashMap<String, Double> list3 = new HashMap<>();

			Double totalPorVendedor;

			for (String vendedores : list2) {
				totalPorVendedor = list.stream().filter(v -> v.getSeller().equals(vendedores)).map(v -> v.getTotal())
						.reduce(0.0, (x, y) -> x + y);
				list3.put(vendedores, totalPorVendedor);
			}

			for (Entry<String, Double> vendedor : list3.entrySet()) {
				System.out.print(vendedor.getKey() + " - R$ ");
				System.out.println(String.format("%.2f", vendedor.getValue()));
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();

	}

}