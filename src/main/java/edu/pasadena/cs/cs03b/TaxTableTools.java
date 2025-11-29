package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class TaxTableTools {
		
		private double[] taxRateTable;
		private int[] salaryTable;
		private double highestTaxRate;

		public TaxTableTools() {
			this.taxRateTable = new double[] {0.10, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};
			this.salaryTable = new int[] {0, 11600, 47150, 100525, 191950, 243725, 609350};
			this.highestTaxRate = 0;
		}

		public double getTaxRate(double annualIncome) {
			for (int i = 1; i < salaryTable.length; i++) {
				if (annualIncome <= salaryTable[i]) {
					return taxRateTable[i - 1];
				}
			}
			return taxRateTable[taxRateTable.length - 1];
		}

		public double calculateTax(double annualIncome) {
			double taxRate = getTaxRate(annualIncome);
			return annualIncome * taxRate;
		}

		public double calculateTaxBrackets(double annualIncome) {
			
			double taxRate;
			double totalTaxToPay = 0;
			double taxableIncome = annualIncome;
			double taxBracket = 0;
			int i = 1;

			do {
				if (taxableIncome + salaryTable[i - 1] <= salaryTable[i]) {
					taxRate = getTaxRate(taxableIncome + salaryTable[i - 1]);
				}
				else {
					taxRate = getTaxRate(salaryTable[i]);
					taxBracket = salaryTable[i] - salaryTable[i - 1];
				}
				totalTaxToPay += taxBracket * taxRate;
				taxableIncome -= salaryTable[i] - salaryTable[i - 1];
				i++;

				if (i == salaryTable.length) {
					taxRate = taxRateTable[i - 1];
					totalTaxToPay += taxableIncome * taxRate;
					taxableIncome = 0;
				}

			} while ((taxableIncome > 0));

			highestTaxRate = taxRate;
			return totalTaxToPay;
		}

		public static void runFlatTaxCalc(TaxTableTools tool, double income) {
			double taxRate = tool.getTaxRate(income);
			double taxAmount = tool.calculateTax(income);

			System.out.println("Annual Income: $" + income);
			System.out.println("Tax rate: " + (taxRate * 100) + "%");
			System.out.println("Tax to pay: $" + taxAmount);
		}

		public static void runBracketTaxCalc(TaxTableTools tool, double income) {
			double taxAmount = tool.calculateTaxBrackets(income);
			double highestTaxRate = tool.highestTaxRate;

			System.out.println("Annual Income: $" + income);
			System.out.println("Highest tax rate: " + (highestTaxRate * 100) + "%");
			System.out.println("Tax to pay: $" + taxAmount);
		}

		public static void main(String[] args) {
			
			TaxTableTools taxTools = new TaxTableTools();
			Scanner input = new Scanner(System.in);

			while (true) {
				System.out.println("Enter annual income (-1 to exit):");
				double income = input.nextDouble();

				if (income == -1) {
					System.out.println("Exiting the program.");
					break;
				}
				
				boolean done = false;
				while (!done) {
					System.out.println("Calculate by flat tax rate (enter 1) or by tax brackets (enter 2)?");
					int choice = input.nextInt();

					switch (choice) {
						case 1:
							runFlatTaxCalc(taxTools, income);
							done = true;
							break;
						case 2:
							runBracketTaxCalc(taxTools, income);
							done = true;
							break;
						default:
							System.out.println("Invalid option selected.");
							break;
					}
				}
			}

			input.close();
		}
	}
