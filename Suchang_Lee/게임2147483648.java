import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 게임2147483648 {

	public static void main (String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long [][] arr = new long [8][8];
		for (int i = 0; i < 8; i++) {
			String [] s = br.readLine().split(" ");
			for (int j = 0; j < 8; j++) {
				arr[i][j] = Long.parseLong(s[j]);
			}
		}

	    String s = br.readLine();
	    if (s.equals("U")) {
	    	for (int j = 0; j < 8; j++) {
    			ArrayList<Long> aList = new ArrayList<>();
	    		for (int i = 0; i < 8; i++) {
	    			if (arr[i][j] != 0) aList.add(arr[i][j]);
	    		}
	    		int t = 0;

	    		if (aList.size() != 0) {
	    			long pivot = aList.get(0);
		    		for (int k = 1; k < aList.size(); k++) {
		    			if (pivot == aList.get(k)) {
		    				arr[t++][j] = pivot * 2;
		    				pivot = 0;
		    			}
		    			else {
		    				if(pivot!=0) arr[t++][j] = pivot;
		    				pivot = aList.get(k);
		    			}
		    		}
    				if(pivot!=0) arr[t++][j] = pivot;
	    		}
	    		if (t < 8) {
	    			for (int v = t; v < 8; v++) {
	    				arr[v][j] = 0;
	    			}
	    		}
	    	}
	    }
	    else if (s.equals("D")) {
	    	for (int j = 0; j < 8; j++) {
    			ArrayList<Long> aList = new ArrayList<>();
	    		for (int i = 7; i >= 0; i--) {
	    			if (arr[i][j] != 0) aList.add(arr[i][j]);
	    		}
	    		int t = 7;

	    		if (aList.size() != 0) {
	    			long pivot = aList.get(0);
		    		for (int k = 1; k < aList.size(); k++) {
		    			if (pivot == aList.get(k)) {
		    				arr[t--][j] = pivot * 2;
		    				pivot = 0;
		    			}
		    			else {
		    				if(pivot!=0) arr[t--][j] = pivot;
		    				pivot = aList.get(k);
		    			}
		    		}
    				if(pivot!=0) arr[t--][j] = pivot;
	    		}
	    		if (t >= 0) {
	    			for (int v = t; v >=0; v--) {
	    				arr[v][j] = 0;
	    			}
	    		}
	    	}
	    }
	    else if (s.equals("L")) {
	    	for (int i = 0; i < 8; i++) {
    			ArrayList<Long> aList = new ArrayList<>();
	    		for (int j = 0; j < 8; j++) {
	    			if (arr[i][j] != 0) aList.add(arr[i][j]);
	    		}
	    		int t = 0;

	    		if (aList.size() != 0) {
	    			long pivot = aList.get(0);
		    		for (int k = 1; k < aList.size(); k++) {
		    			if (pivot == aList.get(k)) {
		    				arr[i][t++] = pivot * 2;
		    				pivot = 0;
		    			}
		    			else {
		    				if(pivot!=0) arr[i][t++] = pivot;
		    				pivot = aList.get(k);
		    			}
		    		}
    				if(pivot!=0) arr[i][t++] = pivot;
	    		}
	    		if (t < 8) {
	    			for (int v = t; v < 8; v++) {
	    				arr[i][v] = 0;
	    			}
	    		}
	    	}
	    }
	    else {
	    	for (int i = 0; i < 8; i++) {
    			ArrayList<Long> aList = new ArrayList<>();
	    		for (int j = 7; j >= 0; j--) {
	    			if (arr[i][j] != 0) aList.add(arr[i][j]);
	    		}
	    		int t = 7;

	    		if (aList.size() != 0) {
	    			long pivot = aList.get(0);
		    		for (int k = 1; k < aList.size(); k++) {
		    			if (pivot == aList.get(k)) {
		    				arr[i][t--] = pivot * 2;
		    				pivot = 0;
		    			}
		    			else {
		    				if(pivot!=0) arr[i][t--] = pivot;
		    				pivot = aList.get(k);
		    			}
		    		}
    				if(pivot!=0) arr[i][t--] = pivot;
	    		}
	    		if (t >= 0) {
	    			for (int v = t; v >=0; v--) {
	    				arr[i][v] = 0;
	    			}
	    		}
	    	}
	    }

	    for (int i = 0; i < 8; i++) {
	    	for (int j = 0; j < 8; j++) {
	    		if(j == 7) {
	    			System.out.print(arr[i][j]);
	    			continue;
	    		}
	    		System.out.print(arr[i][j]+" ");
	    	}
	    	System.out.println("");
	    }
	}
}
