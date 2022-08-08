import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		//T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			int[] mx = new int[3];
			ArrayList<Friend> arr = new ArrayList<>();
			for (int fr = 0; fr < n; fr++) {
				int m = fs.nextInt();
				String name = fs.next();
				arr.add(new Friend(name));
				for (int i = 0; i < m; i++) {
					String num = fs.next();
					arr.get(fr).verifyNumber(num);
				}
				mx[0] = Math.max(mx[0], arr.get(fr).taxiNumber);
				mx[1] = Math.max(mx[1], arr.get(fr).pizzaNumber);
				mx[2] = Math.max(mx[2], arr.get(fr).girlsNumber);
			}
			String[] keywords = new String[3];
			keywords[0] = "call a taxi";
			keywords[1] = "order a pizza";
			keywords[2] = "go to a cafe with a wonderful girl";
			for (int rep = 0; rep < 3; rep++) {
				out.print("If you want to " + keywords[rep] + ", you should call:");
				ArrayList<String> friendNames = new ArrayList<>();
				for (int i = 0; i < n; i++) {
					if (arr.get(i).matches(mx[rep], rep)) {
						friendNames.add(arr.get(i).name);
					}
				}
				for (int i = 0; i < friendNames.size(); i++) {
					out.print(" " + friendNames.get(i));
					out.print(i < friendNames.size() - 1 ? "," : ".");
				}
				out.println();
			}
		}
		out.close();
	}
	
	static class Friend {
		String name;
		int taxiNumber;
		int pizzaNumber;
		int girlsNumber;
		char[] phoneNumber;
		
		Friend(String name) {
			this.name = name;
			taxiNumber = 0;
			pizzaNumber = 0;
			girlsNumber = 0;
		}
		
		boolean matches(int mx, int index) {
			if (index == 0) {
				return mx == taxiNumber;
			} else if (index == 1) {
				return mx == pizzaNumber;
			}
			return mx == girlsNumber;
		}
		
		void verifyNumber(String num) {
			phoneNumber = num.toCharArray();
			if (isIdentical()) {
				taxiNumber++;
			} else if (isDecreasing()) {
				pizzaNumber++;
			} else {
				girlsNumber++;
			}
		}
		
		boolean isIdentical() {
			for (int i = 1; i < phoneNumber.length; i++) {
				if (phoneNumber[i] == '-') {
					continue;
				}
				if (phoneNumber[i] != phoneNumber[0]) {
					return false;
				}
			}
			return true;
		}
		
		boolean isDecreasing() {
			int prev = extractDigit(phoneNumber[0]);
			for (int i = 1; i < phoneNumber.length; i++) {
				if (phoneNumber[i] == '-') {
					continue;
				}
				if (extractDigit(phoneNumber[i]) >= prev) {
					return false;
				}
				prev = extractDigit(phoneNumber[i]);
			}
			return true;
		}
		
		int extractDigit(char ch) {
			return ch - '0';
		}
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
