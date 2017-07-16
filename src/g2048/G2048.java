package g2048;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class G2048 {
	private JFrame frame;
	boolean success;
	int arr[][]=new int[4][4];
	JButton btn[][]=new JButton[4][4];
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G2048 window = new G2048();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public G2048() {
		initialize();
	}

	private void initialize() {
		for(int[] x:arr)
		{
			for(int y:x)
			{
				y=0;
			}
		}
		frame = new JFrame();
		frame.setFocusable(true);
		frame.getContentPane();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout gl=new GridLayout();
		gl.setColumns(4);
		gl.setRows(4);
		gl.setHgap(10);
		gl.setVgap(10);
		frame.getContentPane().setLayout(gl);
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				btn[i][j]=new JButton("");
				btn[i][j].setFont(new Font("Tahoma", Font.PLAIN, 41));
				btn[i][j].setHorizontalAlignment(JLabel.CENTER);
				btn[i][j].addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						boolean success=false;
						switch(e.getKeyCode())
						{
						
						case 38:
							//scrollUp();
							System.out.println(e.getKeyCode());
							
							success=moveLeft();
							fetchArrtoBtn();
							drawBox();
							break;
						case 39:
							System.out.println(e.getKeyCode());
							success=moveRight();
							fetchArrtoBtn();
							drawBox();
							break;
						case 40:
							System.out.println(e.getKeyCode());
							success=moveDown();
							fetchArrtoBtn();
							drawBox();
							break;
						case 37:
							System.out.println(e.getKeyCode());
							success=moveUp();
							fetchArrtoBtn();
							drawBox();
						default:
							System.out.println(e.getKeyCode());
							//newBox();
							drawBox();
						}
						if(success)
						{
							newBox();
							drawBox();
						}
					}
					
				});
				
		frame.getContentPane().add(btn[i][j]);
		
		
			}
		
		newBox();
		drawBox();
		fetchBtntoArr();
	}
	void drawBox()
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				switch(btn[i][j].getText())
				{
				case "":
					btn[i][j].setForeground(Color.blue);
					btn[i][j].setBackground(Color.BLACK);;
					break;
				case "2":
					btn[i][j].setForeground(Color.red);
					btn[i][j].setBackground(Color.CYAN);;
					break;
				case "4":
					btn[i][j].setForeground(Color.green);
					btn[i][j].setBackground(Color.DARK_GRAY);;
					break;
				case "8":
					btn[i][j].setForeground(Color.yellow);
					btn[i][j].setBackground(Color.GRAY);;
					break;
				case "16":
					btn[i][j].setForeground(Color.blue);
					btn[i][j].setBackground(Color.white);;
					break;
				case "32":
					btn[i][j].setForeground(Color.orange);
					btn[i][j].setBackground(Color.green);;
					break;
				case "64":
					btn[i][j].setForeground(Color.pink);
					btn[i][j].setBackground(Color.YELLOW);;
					break;
				case "128":
					btn[i][j].setForeground(Color.red);
					btn[i][j].setBackground(Color.pink);;
					break;
				case "256":
					btn[i][j].setForeground(Color.pink);
					btn[i][j].setBackground(Color.red);;
					break;
				case "512":
					btn[i][j].setForeground(Color.blue);
					btn[i][j].setBackground(Color.magenta);;
					break;
				case "1024":
					btn[i][j].setForeground(Color.magenta);
					btn[i][j].setBackground(Color.ORANGE);;
					break;

				}
			}
	}
	void newBox()
	{
		 Random r1 = new Random();
		 Random r2 = new Random();
         int x = Math.abs(r1.nextInt()) % 4 ;
         int y = Math.abs(r2.nextInt()) % 4 ;
         int count=0;
         while(count==0)
         if(arr[x][y]==0)
         {
        	 btn[x][y].setText("2");
        	 arr[x][y]=2;
        	 count=1;
         }
         else
         {
        	 r1 = new Random();
    		 r2 = new Random();
              x = Math.abs(r1.nextInt()) % 4 ;
              y = Math.abs(r2.nextInt()) % 4 ;
         }
         System.out.println("new box created"+x+"\t"+y);
	}
void fetchBtntoArr()
	{
	for(int i=0;i<4;i++)
		for(int j=0;j<4;j++)
		{
			if(btn[i][j].getText().equals(""))
			{
				arr[i][j]=0;
			}
			else
			{
				arr[i][j]=Integer.parseInt(btn[i][j].getText());
			}
		}
	}
boolean moveUp() {
	boolean success = true;
	int x;
	for (x=0;x<4;x++) {
		 slideArray();
	}
	return success;
}

boolean moveLeft() {
	boolean success;
	rotateBoard();
	success = moveUp();
	rotateBoard();
	rotateBoard();
	rotateBoard();
	return success;
}

boolean moveRight() {
	boolean success;
	rotateBoard();
	rotateBoard();
	success = moveUp();
	rotateBoard();
	rotateBoard();
	return success;
}

boolean moveDown() {
	boolean success;
	rotateBoard();
	rotateBoard();
	rotateBoard();
	success = moveUp();
	rotateBoard();
	return success;
}
void fetchArrtoBtn()
{
for(int i=0;i<4;i++)
	for(int j=0;j<4;j++)
	{
		if(arr[i][j]==0)
		{
			btn[i][j].setText("");
		}
		else
		{
			btn[i][j].setText(arr[i][j]+"");
		}
	}

}
void slideArray()
{
	int t;
	int stop=0;
	for(int x[]:arr)
		{
		for(int i=0;i<4;i++)
		{
		if (x[i]!=0) {
			t = findTarget(x,i,stop);
			// if target is not original position, then move or merge
			if (t!=i) {
				// if target is zero, this is a move
				if (x[t]==0) {
					x[t]=x[i];
				} else if (x[t]==x[i]) {
					// merge (increase power of two)
					x[t]=2*x[t];
					// increase score
					// set stop to avoid double merge
					stop = t+1;
				}
				x[i]=0;
				success = true;
			}
		}
		}
		}
}
int findTarget(int array[],int x,int stop) {
	int t;
	// if the position is already on the first, don't evaluate
	if (x==0) {
		return x;
	}
	for(t=x-1;t>=0;t--) {
		if (array[t]!=0) {
			if (array[t]!=array[x]) {
				// merge is not possible, take next position
				return t+1;
			}
			return t;
		} else {
			// we should not slide further, return this one
			if (t==stop) {
				return t;
			}
		}
	}
	// we did not find a
	return x;
}
void rotateBoard() {
	int i,j,n=4;
	int tmp;
	for (i=0; i<n/2; i++) {
		for (j=i; j<n-i-1; j++) {
			tmp = arr[i][j];
			arr[i][j] = arr[j][n-i-1];
			arr[j][n-i-1] = arr[n-i-1][n-j-1];
			arr[n-i-1][n-j-1] = arr[n-j-1][i];
			arr[n-j-1][i] = tmp;
		}
	}

}
}


