package sudoku;

public class SudokuGenerator {

	    int[] mat[];
	    int colRow; // number of columns/rows.
	    int SRN; // square root of N
	    int missingDigits; // No. Of missing digits
	 
	    // Constructor
	    SudokuGenerator(int N, int K)
	    {
	        this.colRow = N;
	        this.missingDigits = K;
	 
	        // Compute square root of N
	        Double SRNd = Math.sqrt(N);
	        SRN = SRNd.intValue();
	 
	        mat = new int[N][N];
	    }
	 
	    // Sudoku Generator
	    public void fillValues()
	    {
	        // Fill the diagonal of SRN x SRN matrices
	        fillDiagonal();
	 
	        // Fill remaining blocks
	        fillRemaining(0, SRN);
	 
	        // Remove Randomly K digits to make game
	        removeKDigits();
	    }
	 
	    // Fill the diagonal SRN number of SRN x SRN matrices
	    void fillDiagonal()
	    {
	 
	        for (int i = 0; i<colRow; i=i+SRN)
	 
	            // for diagonal box, start coordinates->i==j
	            fillBox(i, i);
	    }
	 
	    // Returns false if given 3 x 3 block contains num.
	    boolean unUsedInBox(int rowStart, int colStart, int num)
	    {
	        for (int i = 0; i<SRN; i++)
	            for (int j = 0; j<SRN; j++)
	                if (mat[rowStart+i][colStart+j]==num)
	                    return false;
	 
	        return true;
	    }
	 
	    // Fill a 3 x 3 matrix.
	    void fillBox(int row,int col)
	    {
	        int num;
	        for (int i=0; i<SRN; i++)
	        {
	            for (int j=0; j<SRN; j++)
	            {
	                do
	                {
	                    num = randomGenerator(colRow);
	                }
	                while (!unUsedInBox(row, col, num));
	 
	                mat[row+i][col+j] = num;
	            }
	        }
	    }
	 
	    // Random generator
	    int randomGenerator(int num)
	    {
	        return (int) Math.floor((Math.random()*num+1));
	    }
	 
	    // Check if safe to put in cell
	    boolean CheckIfSafe(int i,int j,int num)
	    {
	        return (unUsedInRow(i, num) &&
	                unUsedInCol(j, num) &&
	                unUsedInBox(i-i%SRN, j-j%SRN, num));
	    }
	 
	    // check in the row for existence
	    boolean unUsedInRow(int i,int num)
	    {
	        for (int j = 0; j<colRow; j++)
	           if (mat[i][j] == num)
	                return false;
	        return true;
	    }
	 
	    // check in the row for existence
	    boolean unUsedInCol(int j,int num)
	    {
	        for (int i = 0; i<colRow; i++)
	            if (mat[i][j] == num)
	                return false;
	        return true;
	    }
	 
	    // A recursive function to fill remaining
	    // matrix
	    boolean fillRemaining(int i, int j)
	    {
	        //  System.out.println(i+" "+j);
	        if (j>=colRow && i<colRow-1)
	        {
	            i = i + 1;
	            j = 0;
	        }
	        if (i>=colRow && j>=colRow)
	            return true;
	 
	        if (i < SRN)
	        {
	            if (j < SRN)
	                j = SRN;
	        }
	        else if (i < colRow-SRN)
	        {
	            if (j==(int)(i/SRN)*SRN)
	                j =  j + SRN;
	        }
	        else
	        {
	            if (j == colRow-SRN)
	            {
	                i = i + 1;
	                j = 0;
	                if (i>=colRow)
	                    return true;
	            }
	        }
	 
	        for (int num = 1; num<=colRow; num++)
	        {
	            if (CheckIfSafe(i, j, num))
	            {
	                mat[i][j] = num;
	                if (fillRemaining(i, j+1))
	                    return true;
	 
	                mat[i][j] = 0;
	            }
	        }
	        return false;
	    }
	 
	    // Remove the K no. of digits to
	    // complete game
	    public void removeKDigits()
	    {
	        int count = missingDigits;
	        while (count != 0)
	        {
	            int cellId = randomGenerator(colRow*colRow)-1;
	 
	            // System.out.println(cellId);
	            // extract coordinates i  and j
	            int i = (cellId/colRow);
	            int j = cellId%9;
	            if (j != 0)
	                j = j - 1;
	 
	            // System.out.println(i+" "+j);
	            if (mat[i][j] != 0)
	            {
	                count--;
	                mat[i][j] = 0;
	            }
	        }
	    }
	    
	 
	    // Print sudoku
	    public void printSudoku()
	    {
	        for (int i = 0; i<colRow; i++)
	        {
	            for (int j = 0; j<colRow; j++)
	                System.out.print(mat[i][j] + " ");
	            System.out.println();
	        }
	        System.out.println();
	    }
	  
	 /*
	    // Driver code
	    public static void main(String[] args)
	    {
	        int N = 9, K = 20;
	        SudokuGenerator sudoku = new SudokuGenerator(N, K);
	        sudoku.fillValues();
	        sudoku.printSudoku();
	    }
	    */
	}

