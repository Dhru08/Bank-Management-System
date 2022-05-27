package javafiles;

import java.io.*;
import java.util.*;

public class Main {
	public static final String RESET = "\u001B[0m";
	public static final String YELLOW = "\u001B[33m";
	public static final String GREEN = "\u001B[32m";
	public static final String RED = "\u001B[31m";
	public static final String CYAN = "\u001B[36m";
	public static final String PURPLE = "\u001B[35m";

	static String getNumber(Scanner sc) {
		String no = "";
		System.out.println(YELLOW + "Enter Phone Number" + RESET);
		no = sc.next();
		while (no.length() != 10) {
			System.out.println(RED + "Wrong phone number! Enter Again." + RESET);
			no = sc.next();
		}
		return no;
	}

	static void optreturn(Scanner sc) {
		Random rnd = new Random();
		String x = String.valueOf(rnd.nextInt(10000));
		System.out.println(PURPLE + "OTP IS : " + x + RESET);
		System.out.println(YELLOW + "Enter OTP : " + RESET);
		String num = sc.next();
		while (!num.equals(x)) {
			System.out.println(RED + "Invalid otp! Try again." + RESET);
			num = sc.next();
		}
	}

	static String generatepin() {
		Random rnd = new Random();
		return String.valueOf(rnd.nextInt(10000));
	}

	static String generateacn() {
		Random rnd = new Random();
		return String.valueOf(rnd.nextInt(1000000));
	}

	static int containsAndGet(String s, ArrayList<String[]> ss, int x) {
		for (int i = 0; i < ss.size(); i++) {
			if (s.equals(ss.get(i)[x])) {
				return i;
			}
		}
		return -1;
	}

	static void Notes(int amount) {
		System.out.println(YELLOW + "*********************************************************" + RESET);
		System.out.println(YELLOW + "********************** CURRENCY *************************" + RESET);
		System.out.println(YELLOW + "*********************************************************" + RESET);
		int arr[] = { 2000, 500, 100 };
		for (int i = 0; i < 3; i++) {
			System.out.println(amount / arr[i] + "  X  " + arr[i] + "Rs");
			amount %= arr[i];
		}
	}

	static void ShowInfo(int z, ArrayList<String[]> data) {
		System.out.println(YELLOW + "*********************************************************" + RESET);
		System.out.println(YELLOW + "**********************ACCOUNT INFO***********************" + RESET);
		System.out.println(YELLOW + "*********************************************************" + RESET);
		System.out.println(PURPLE + "Customer Id: " + RESET + data.get(z)[0]);
		System.out.println(PURPLE + "Password: " + RESET + "*******");
		System.out.println(PURPLE + "Account No: " + RESET + data.get(z)[2]);
		System.out.println(PURPLE + "Phone No: " + RESET + data.get(z)[3]);
		System.out.println(PURPLE + "Name : " + RESET + data.get(z)[4]);
		System.out.println(PURPLE + "ATM Pin: " + RESET + "****");
		System.out.println(PURPLE + "Amount: " + RESET + data.get(z)[6]);
	}

	static void updateData(File files, ArrayList<String[]> data) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(files);
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < 6; j++)
				build.append(data.get(i)[j] + " ");
			build.append(data.get(i)[6] + "\n");
		}
		out.print(build);
		out.close();
	}

	public static void main(String[] args) throws Exception {
		File files = new File("Data.txt");
		Scanner cc = new Scanner(files);
		ArrayList<String[]> data = new ArrayList<>();
		while (cc.hasNextLine()) {
			String s = cc.nextLine();
			String temp[] = s.split(" ");
			data.add(temp);
		}
		cc.close();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(YELLOW + "*********************************************************" + RESET);
			System.out.println(YELLOW + "*****************BANK MANAGEMENT SYSTEM******************" + RESET);
			System.out.println(YELLOW + "*********************************************************" + RESET);
			System.out
					.println(CYAN + "1.Create new account\n" + "2.Login for existing user\n" + "3.For exit\n" + RESET);
			int n = sc.nextInt();
			if (n == 1) {
				String no = getNumber(sc);
				optreturn(sc);
				System.out.println(YELLOW + "Enter your name" + RESET);
				String name = sc.next();
				System.out.println(YELLOW + "Enter customer id" + RESET);
				String cusid = sc.next();
				System.out.println(YELLOW + "Enter password of your choise" + RESET);
				String pass = sc.next();
				String atmpin = generatepin();
				String acn = generateacn();
				System.out.println(GREEN + "Your account is generated Successfully" + RESET);
				System.out.println(
						"Your Account no is: " + PURPLE + acn + RESET + " Your atm pin is: " + PURPLE + atmpin + RESET);
				String s[] = { cusid, pass, acn, no, name, atmpin, "0" };
				data.add(s);
				updateData(files, data);
			} else if (n == 2) {
				System.out.println(YELLOW + "Enter Customer id" + RESET);
				String cusid = sc.next();
				int z = containsAndGet(cusid, data, 0);
				if (z == -1) {
					System.out.println(RED
							+ "Customer Didn't matched with any account!\nYou Dont have account so first create one."
							+ RESET);
					continue;
				}
				System.out.println(YELLOW + "Enter password: " + RESET);
				String pass = sc.next();
				while (!pass.equals(data.get(z)[1])) {
					System.out.println(RED + "Wrong Password Enter Again" + RESET);
					pass = sc.next();
				}
				System.out.println(GREEN + "Log in successfull" + RESET);
				while (true) {
					System.out.println(CYAN + "1.Fund transfer\n" + "2.Change password\n" + "3.Show account info\n"
							+ "4.Deposit money\n" + "5.Withdraw cash through ATM\n" + "6.Change ATM Pin\n" + "7.exit"
							+ RESET);
					int d = sc.nextInt();
					if (d == 1) {
						System.out.println(YELLOW + "Enter benificiary account no: " + RESET);
						String accno = sc.next();
						int index = containsAndGet(accno, data, 2);
						while (index == -1) {
							System.out.println(RED + "Invalid Account No Enter again:" + RESET);
							accno = sc.next();
							index = containsAndGet(accno, data, 2);
						}
						System.out.println(YELLOW + "Enter Amount:" + RESET);
						int amount = sc.nextInt();
						while (amount > Integer.parseInt(data.get(z)[6])) {
							System.out.println(RED + "Invalid Amount Input Again!" + RESET);
							amount = sc.nextInt();
						}
						System.out.println(GREEN + "Transaction Successfull" + RESET);
						data.get(index)[6] = String.valueOf(amount + Integer.parseInt(data.get(index)[6]));
						data.get(z)[6] = String.valueOf(Integer.parseInt(data.get(z)[6]) - amount);
						updateData(files, data);
					} else if (d == 2) {
						System.out.println(YELLOW + "Enter New Password" + RESET);
						String s = sc.next();
						data.get(z)[1] = s;
						System.out.println(GREEN + "Password updated" + RESET);
						updateData(files, data);
					} else if (d == 3) {
						ShowInfo(z, data);
						updateData(files, data);
					} else if (d == 4) {
						System.out.println(YELLOW + "Enter Amount to Deposit: " + RESET);
						int amount = sc.nextInt();
						data.get(z)[6] = String.valueOf(amount + Integer.parseInt(data.get(z)[6]));
						System.out.println(GREEN + "Money Deposited." + RESET);
						updateData(files, data);
					} else if (d == 5) {
						System.out.println(YELLOW + "Enter ATM PIN" + RESET);
						String pin = sc.next();
						while (!pin.equals(data.get(z)[5])) {
							System.out.println(RED + "Wrong Pin Enter Again" + RESET);
							pin = sc.next();
						}
						System.out.println(PURPLE + "Account Balance: " + data.get(z)[6] + RESET);
						System.out.println(YELLOW + "Enter Amount" + RESET);
						int amount = sc.nextInt();
						while (amount > Integer.parseInt(data.get(z)[6]) || amount % 100 != 0) {
							System.out.println(
									RED + "Enter Amount in multiple of 100 or less than your account balance" + RESET);
							amount = sc.nextInt();
						}
						data.get(z)[6] = String.valueOf(Integer.parseInt(data.get(z)[6]) - amount);
						Notes(amount);
						updateData(files, data);
					} else if (d == 6) {
						System.out.println(YELLOW + "Enter New Pin" + RESET);
						String s = sc.next();
						data.get(z)[5] = s;
						System.out.println(GREEN + "PIN updated" + RESET);
						updateData(files, data);
					} else
						break;
				}
			} else {
				break;
			}
		}
	}
}
