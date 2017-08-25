package p2;






import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JLabel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Random;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



class mm{
	 String cid;
	 String cname;
	 String major;
	 String cl;
	 int time1;
	 int time2;
	 int tid;
	 String tname;
	 
	 mm(){
		 cname=null;
		 major=null;
		 cl=null;
		 tname=null;
		 tid=0;
	 }
}
public class main {

	public int opp=0;
	public static Best best;                           // ��¼��Ѵ𰸵Ķ���
	public static final int POP_SIZE = 1000;             // ��Ⱥ��С���������Ⱥ��30�����壨��x����ɣ�
	public static String[][] pop = new String[POP_SIZE][1000];    // String�����飬�����Ⱥ��ÿ�����壨��x���ı���
	public static String[][] result = new String[POP_SIZE][1000]; // double�����飬�������������Ⱥ�е�ÿ�����壨��x��
	public static int[] fitness = new int[POP_SIZE];//double�ͣ������Ⱥ��ÿ�����壨��x������Ӧֵ
	public static final int LENGTH = 22; // x�ı��볤�ȣ���ΪҪ��ȷ��С�������λ������ʮ���Ƶ��������֣�1λ����С�����֣�6λ������7λ������Ҫ��22λ�Ķ���������ʾ��
	public static final int conversionFactor = 4194303;//ת�����ӣ�22λ�����������ܱ�ʾ����ʮ������Ϊ2^22-1
	public static Random random = new Random();     // ���ڲ���������Ĺ���
	public static final double PM = 0.01;           // ������
	public static double[] p = new double[POP_SIZE];// ���̶ķ���������Ӧ�ȸ���
	public static double[] q = new double[POP_SIZE];// q[i]��ǰn��p֮��
	public static String d[]=new String[50];
	public static int mis=0;
	public static int[] unfi = new int[POP_SIZE];
	int k1,k2;	//��ѡ�����ӽ�����������	
	JFrame frame;
	private JTable table;
	private JPanel panel;
	private JButton button;

	public Map mapt = new HashMap();
	public  Map mapc = new HashMap();
	public  Map rmapt = new HashMap();
	public  Map rmapc = new HashMap();
	public  Map mmap = new HashMap();
	public JTextField textField;
	 private JLabel lblNewLabel;
public String Dir;
	 public int cishu=0;

	    int num=55*2;
		mm a[]=new mm[10000];
		Best best1 = new Best();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
	
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}
	public void fitness() {
		int Max=0;
		int flag=0;
		int unfit=0;
		int fit1=0;
		int sum=0;
		for (int i = 0; i <30; i++) {
			
			Map map1= new HashMap();
			Map map2= new HashMap();
			Map map3= new HashMap();
			Map map4= new HashMap();
			unfit=0;
			fit1=0;	//������ͬһ��һ��������ͬ�Ŀ�
			int tot1=0;
			int tot2=0;
			int tot3=0;
			int tot4=0;
			for (int j = 0; j < mis; j++) {
				if(map1.containsKey(result[i][j].substring(10, 25))==false){	//ʱ��  ����
					map1.put(result[i][j].substring(10, 25), tot1);
					 tot1++;
				}
				if(map2.containsKey(result[i][j].substring(5, 15))==false){	//�༶ ʱ��
					map2.put(result[i][j].substring(5, 15), tot2);
					 tot2++;
				}
				if(map3.containsKey(result[i][j].substring(0, 5)+result[i][j].substring(10, 15))==false){	//��ʦ ʱ��
					map3.put(result[i][j].substring(0, 5)+result[i][j].substring(10, 15), tot3);
					 tot3++;
				}
				for (int k = j+1; k < mis; k++) {
				/*
				 * 
				 * �������һ���༶������ͬһ������ͬ�Ŀ�
				 * 
				 * 
				 */
				if(result[i][j].substring(5,10).equals(result[i][k].substring(5, 10))&&result[i][j].substring(0,5).equals(result[i][k].substring(0, 5))){	//ѧ����ͬ�����ҿγ���ͬ
					int t1=0;
					for(int ii=4;ii>=0;ii--){
						if(result[i][j].substring(10,15).charAt(ii)=='1')	t1+=Math.pow(2, 4-j);
					}
					int t2=0;
					for(int ii=4;ii<=0;ii--){
						if(result[i][k].substring(10,15).charAt(ii)=='1')	t2+=Math.pow(2, 4-j);
					}
					t1/=4;
					t2/=4;
					if(Math.abs(t1-t2)>=2){
						fit1++;
					}
				}
				
				}
		}
			
			fitness[i] =fit1*10+(tot1+tot2+tot3)*5;
			if(Max<fitness[i]){
				flag=i;
				Max=fitness[i] ;
			}
			sum+=fitness[i];
			//System.out.println(cishu+"�� fitness="+ fitness[i]+" "+(tot1+tot2+tot3)+" "+fit1+"f=" + best1.fitness);
		}
		if(cishu>=10){
		for(int w=0;w<5;w++){
	  		for(int j=0;j<mis;j++){
	  			result[opp+w][j]=best1.x[j];
	  		}
	  	}
		}
	  	opp+=5;
	  	opp%=30;
		for (int i = 0; i <30; i++) {
			p[i]=(double)fitness[i]/sum*1.0;
		}

		cishu++;
		
		System.out.println("��ֳ����"+cishu+" "+"��Ӧ��"+best1.fitness);
		for(int i=0;i<30;i++){
			for(int j=0;j<mis;j++){
				
				pop[i][j]=result[i][j];
			}
		}
		
	}
	
	/*
	 * ���뷽��������ֵ��ʾΪ�������ֽ�����ʽ
	 */
	public void encoding()
	{
		/*for (int i = 0; i < result.length; i++) {
			//�Ŵ�result[i]�����ڱ��롣
			//d1��ֵ:(|result[i]��A��ľ���|/(������ĳ���))*ת������
			double d1 = ((result[i] - A) / (B - A)) * (conversionFactor);
			//��d1ǿ������ת����int�ͣ�������ת���ɶ����Ƶ��ַ�����������pop�����С�
			pop[i] = Integer.toBinaryString((int) d1);
		}
		//���μ���pop�����ÿһ�������ĳ���!=22,����ǰ�油��0��ʹ��ﵽ22λ
		for (int i = 0; i < pop.length; i++) {
			if (pop[i].length() < LENGTH) {
				int k = LENGTH - pop[i].length();
				for (int j = 0; j < k; j++) {
					pop[i] = "0" + pop[i];
				}
			}
		}*/
		int i,j,k;
	}
	
	/*
	 * ѡ�����,�������̶��㷨����Ⱥ��ѡ��������������ӽ���
	 */
	public void selection()
	{
		do {
			k1 = roulettewheel();
			k2 = roulettewheel();
		} while (k1 != k2);
	}
	
	/*
	 * ���̶��㷨����Ӧֵ��ĸ��屻ѡ�еĻ��ʸ���Щ��
	 */
	
	int roulettewheel()
	{
	    double m = 0;
	    double r =random.nextDouble(); //rΪ0��1�������
	    int i = 0;
	    int flag=0;
	    int max=9999999;
	    for(i=0;i<30; i++)
	    {
	            /* �������������m~m+P[i]������Ϊѡ����i
	             *  ���i��ѡ�еĸ�����P[i]
	             */
	             m = m + p[i];
	             if(r<=m)
	            	 break;
	    }
	    
	    //ѡ������̭
	  	
	    return i;
	    
	  
	   // return i;
	}
	
	/*
	 * ���뷽�������������ֽ��뻹ԭ
	 */
	public void decoding() {
		/*for (int i = 0; i < pop.length; i++) {
			int k = Integer.parseInt(pop[i], 2);
			result[i] = A + k * (B - A) / (conversionFactor);
		}*/
		int i,j,k;
		for(i=0;i<30;i++){
			for(j=0;j<mis;j++){

				result[i][j]=pop[i][j];
			}
		}
		
	}

	/*
	 * �������
	 */
	public void crossover() {
		//���������Ⱦɫ�巢�����������λ��
		int position1 = random.nextInt(29);
		int position2 = random.nextInt(29);
		//s1�ַ������г�s11��s12��s2�ַ������г�s21��s22
		String s11 = null, s12 = null, s21 = null, s22 = null;
		//System.out.println(pop[k1][position1]+" "+k1+" "+k2);
		s11 = pop[k1][position1].substring(0, 15);
		s12 = pop[k1][position1].substring(15, 25);
		s21 = pop[k2][position2].substring(0, 15);
		s22 = pop[k2][position2].substring(15, 25);
		//����ƴ���ַ�������������Ⱥ
		pop[k1][position1] = s11 + s22;
		pop[k2][position2] = s21 + s12;
		
		
		
		
	}

	/*
	 * ���������������Ⱦɫ���ϵ�ÿ�����򶼿��ܷ���
	 */
	public void mutation() {
		//��i�����壨��Ⱦɫ�壩
		for (int i = 0; i < 30; i++) {
			//��i�������j�Ż���
			for (int j = 0; j <mis; j++) {
				double k = random.nextDouble();
				//����������������С�ڱ����ʣ�����б��������
				if (0.5 > k) {
					mutation(i, j);
				}
			}
		}
	}
	//��������λ���ǡ�1����Ϊ��0������������λ���ǡ�0����Ϊ��1����
	public void mutation(int i, int j) {
		
		//ֻ���Ľ�ѧʱ��ͽ�ѧ�ص㣬�����Ľ�ʦ�Ϳγ�
		String s = pop[i][j];
		//���ѡ����ĵĻ���

		//System.out.println("����ǰ"+s);
		StringBuffer sb = new StringBuffer(s);
		
		String w=s.substring(0, 10);
		String w1= (String) s.subSequence(10, 15);	//��ѧʱ��  һ����С��20��,��˱���ʱע�����
		StringBuffer sb1 = new StringBuffer(w1);
		while(true){
			int p1=random.nextInt(5);	//ʱ�����
			//System.out.println(sb1+" "+p1);
			if (sb1.charAt(p1) == '0')
				sb1.setCharAt(p1, '1');
			else
				sb1.setCharAt(p1, '0');
			w1=sb1.toString();
			if(w1.compareTo("10011")<1){		//���ϱ�����
				break;
			}
		}
		
		String w2= (String) s.subSequence(15, 25);	//��ѧ�ص�  һ����С��20��,��˱���ʱע�����
		StringBuffer sb2 = new StringBuffer(w2);
		while(true){
			int p2=random.nextInt(10);	//ʱ�����
			//System.out.println(sb2+" "+p2);
			if (sb2.charAt(p2) == '0')
				sb2.setCharAt(p2, '1');
			else
				sb2.setCharAt(p2, '0');
			w2=sb2.toString();
			if(w2.compareTo("0000000111")<1){		//���ϱ�����
				break;
			}
		}
		s=w+w1+w2;
		//System.out.println("�����"+s);
		pop[i][j] =s;

	}
	
	/*
	 * һ�ν���
	 */
	public void evolution() {

		encoding();         //����

		fitness();          //������Ӧ��
		selection();        //ѡ��
		crossover();        //����

		mutation();         //����

		decoding();         //����
	}

	/*
	 * �����������̣�n ��ʾ�������ٴ�
	 */
	public void dispose(int n) {
		
		for (int i = 0; i < n; i++) {

			evolution();
			findBestOne();
		}

		fitness();          //������Ӧ��
	}

	/*
	 * ȡ�ý��
	 */
	public int max = -999999999;
	public double findBestOne() {
		for (int i=0;i<30; i++) {
			//System.out.println(fitness[i]);
			if (fitness[i] > max) {
				max = fitness[i];
				best1.fitness = max;
				for(int j=0;j<mis;j++){
					//System.out.println("----"+result[i][j]);
					best1.x[j] = result[i][j];
				}
				best1.str = pop[i];
			}
		}
		return max;
	}
static 	public  Object  [][]cellData=new Object[100][10];
	/*
	 * ������Ѹ���Ķ���
	 */
	class Best { 
		public int generations;
		public String str[];
		public double fitness;
		public String x[]=new String[10000];
	}
	/**
	 * Initialize the contents of the frame.
	 */
	mm aa[][][]= new mm[100][100][100];
	mm w[]=new mm[10000];
	boolean check(int x,int y,int cl,int z){	//����Ƿ��֮ǰ�Ź��Ŀγ̷�����ͻ
		int i,j,k;
		
		mm ww=a[z];
		boolean flag=true;

		//ȷ���������û��������,ȷ�������ʦ��������ʦҲû��
		for(k=0;k<cl;k++){
			if(aa[x][y][k].cl==null)	continue;

			if(aa[x][y][k].cl.equals(ww.cl)||aa[x][y][k].cname.equals(ww.cname)){
				flag=false;
				//System.out.println(aa[x][y][k].cl+" "+ww.cl+" "+aa[x][y][k].cname+" "+" "+x+" "+y+" "+z);
			}
		}
		
		
		int ch[]=new int[5];
		ch[0]=x-1;
		ch[1]=x+1;
		ch[2]=x;
		//System.out.println("****");
		for(int ii=0;ii<3;ii++){
			int qq=ch[ii];
			if(qq<1||qq>5)	continue;
			
			for(j=1;j<=4;j++){
				for(k=0;k<7;k++){
					if(aa[qq][j][k].cl==null)	continue;
					//System.out.println(aa[i][j][k].cl+" "+aa[i][j][k].cname+" "+i+" "+j+" "+k);
					if(aa[qq][j][k].cl.equals(ww.cl)&&aa[qq][j][k].cname.equals(ww.cname)){			//�ڼ������һ���ʱ���ڣ����ڸ���ͬ�İ�����ͬ�Ŀ�
						flag=false;
					}
				}
			}
		}

		if(!flag)
			return false;
		else return true;
		 
	 }
	
	


	private void datachuli() {
		// TODO Auto-generated method stub
		for(int i=0;i<100*2+2;i++){
			a[i]=new mm();
		}
		for(int i=0;i<=54;i++){
			a[2*i].cname=cellData[i][1].toString();
		    a[2*i].cl=cellData[i][3].toString();
		    a[2*i].tid=Integer.parseInt( cellData[i][6].toString());
		    a[2*i].tname=cellData[i][7].toString();
			
			a[2*i+1].cname=cellData[i][1].toString();
		    a[2*i+1].cl=cellData[i][3].toString();
		    a[2*i+1].tid=Integer.parseInt(cellData[i][6].toString());
		    a[2*i+1].tname=cellData[i][7].toString();
			}
		
		for(int i=0;i<55*2;i++){
			System.out.println(a[i].cname+" "+a[i].tname+" "+a[i].cl);
		}
	/*	
	    a[0].cname="���ݿ�ϵͳ";
		a[0].cl="�ƿ�-1";
		a[0].tid=1;
		a[0].tname="������";
		
		a[1]=new mm();
		a[1].cname="���ݿ�ϵͳ";
		a[1].cl="�ƿ�-1";
		a[1].tid=1;
		a[1].tname="������";
		
		a[2].cname="���ݿ�ϵͳ";
		a[2].cl="�ƿ�-2";
		a[2].tid=1;
		a[2].tname="������";
		
		a[3].cname="���ݿ�ϵͳ";
		a[3].cl="�ƿ�-2";
		a[3].tid=1;
		a[3].tname="������";
		
		a[4].cname="����ϵͳA";
		a[4].cl="�ƿ�-1";
		a[4].tid=2;
		a[4].tname="������";
		
		a[5].cname="����ϵͳA";
		a[5].cl="�ƿ�-1";
		a[5].tid=2;
		a[5].tname="������";
		
		a[6].cname="����ϵͳA";
		a[6].cl="�ƿ�-2";
		a[6].tid=2;
		a[6].tname="������";
		
		a[7].cname="����ϵͳA";
		a[7].cl="�ƿ�-2";
		a[7].tid=2;
		a[7].tname="������";
		
		*/
		
		 String d[]=new String[1000];
		int tott=0;
		int totc=0;
		mis=0;
		int ii=1;
		int jj=0;
		for(int i=0;i<num;i++){
			if(mapt.containsKey(a[i].tname)==false){
				mmap.put(a[i].tname,a[i].cname);
				
				
			}
			if(mapt.containsKey(a[i].tname)==false){
				 rmapt.put(tott,a[i].tname);
				 mapt.put(a[i].tname, tott);
				 tott++;
			}
			int t1=mapt.get(a[i].tname).hashCode();
			String s1 = "";
			int temp=t1;
			int w=5;
			while(true){
				s1=String.valueOf((temp&1))+s1; ;
				temp>>=1;
				w--;
				if(temp==0)	break;
			}
			while(w!=0){
				s1="0"+s1;
				w--;
			}
			
			if(mapc.containsKey(a[i].cl)==false){
				rmapc.put(totc,a[i].cl);
				 mapc.put(a[i].cl, totc);
				 totc++;
			}
			int t2=mapc.get(a[i].cl).hashCode();
			String s2="";
			temp=t2;
			w=5;
			while(true){
				s2=String.valueOf((temp&1))+s2 ;
				temp>>=1;
				w--;
				if(temp==0)	break;
			}
			while(w!=0){
				s2="0"+s2;
				w--;
			}

			//System.out.println(s1+" "+s2);
			
					
					String ss1="";
					temp=ii;
					w=5;
					while(true){
						ss1=String.valueOf((temp&1))+ss1 ;
						temp>>=1;
						w--;
						if(temp==0)	break;
					}
					while(w!=0){
						ss1="0"+ss1;
						w--;
					}
					//System.out.println("ss1="+ss1);
					String ss2="";
					temp=jj;
					w=10;
					while(true){
						ss2=String.valueOf((temp&1))+ss2 ;
						temp>>=1;
						w--;
						if(temp==0)	break;
					}
					while(w!=0){
						ss2="0"+ss2;
						w--;
					}
					
					//System.out.println("ss2="+ss2);
					d[mis++]=s1+s2+/*ss1+ss2*/"000010000000001";

					
					if(ii==19&&jj==4){
						continue;
					}
					ii++;
				if(ii==19){
					jj++;
					ii%=19;
				}
			
			}
				
	}
			
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		 
		/*******************************************************����************************************************************/
		/***********************************************************************************************************************/
	
		
		
		
		
		/***********************************************************************************************************************/
		/***********************************************************************************************************************/
		
		
		
		
		
		//String d[] = {"0000100001000010000000001" ,"0000100010000010000000001","0001000010000010000000001","0001000010000010000000001","0001100010000010000000001","0001100001000010000000001"};  
		for(int i=0;i<mis;i++){
			System.out.println(d[i]);
		}
		System.out.println("----------");
		
		
		
		 for (int i = 0; i <30; i++) {
					for(int j=0;j<mis;j++)
						result[i][j]= d[j];
				}
			new createtable();
		 
		 
		 
		 
	}
	
	public class createtable implements TableModelListener
	{
	    JTable table = null;
	    MyTable my = null;
	    public void chaxun(final int x){
			final JFrame frame1 = new JFrame();
		   		frame1.setBounds(500, 500, 200, 100);
		   		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   		frame1.setVisible(true);
		   		
	 	   	JPanel panel1 = new JPanel();
			frame1.getContentPane().add(panel1, BorderLayout.CENTER);
			JLabel label = new JLabel();
			if(x==0)
	 	   		label.setText("��������Ҫ��ѯ�İ༶"); 
			else if(x==1)
				label.setText("��������Ҫ��ѯ�Ľ�ʦ"); 
			else if(x==2)
				label.setText("��������Ҫ��ѯ�Ľ���"); 
			
			panel1.add(label);
			
			textField = new JTextField();
			panel1.add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("��ѯ");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

		 	   		String xx=textField.getText();
		 	   		if(x==0)
		 	   			my.check(0, xx);
		 	   		else if(x==1)
		 	   			my.check(1, xx);
		 	   	else if(x==2)
	 	   			my.check(2, xx);
					table.repaint();
				}

			});
			panel1.add(btnNewButton);

			table.repaint();
			
		}
	    public createtable() 
	{
	    	 my=new MyTable();
	    	   my.addTableModelListener(this);
	        table=new JTable(my);
	        table.setPreferredScrollableViewportSize(new Dimension(1000, 800));
	        table.setRowHeight(100);
	        JScrollPane s = new JScrollPane(table);
	        frame.getContentPane().add(s, BorderLayout.CENTER);
		       frame.setTitle("����¼�����");
	        frame.pack();
	        frame.setVisible(true);
	        frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	                System.exit(0);
	            }
	        });
	        
	        
	        panel=new JPanel();
	        button = new JButton("��ʼ�ſ�");
	        frame.getContentPane().add(panel, BorderLayout.SOUTH);
			 //debug
			 lblNewLabel = new JLabel("�ſ�");
			 panel.add(lblNewLabel);
			 /*********************************************************************************************************************/
			 
			 //�����ſ��㷨
			 button.addActionListener(new ActionListener() {
				 
			 	public void actionPerformed(ActionEvent arg0) {
			 		
			 		/*for(int i=0;i<5;i++){
			 			for(int j=1;j<5;j++){
			 				my.setValueAt(null, i, j);
			 			}
			 		}*/
			 		my.clear();
			 		table.repaint();//ϵͳ���»��Ʊ��
			 		
			 		
			 		
			 		//String d[] = {"0000100001000010000000001" ,"0000100010000010000000001","0001000010000010000000001","0001000010000010000000001"};  
					// ��ʼ����ʼ��Ⱥ����������
					/*System.out.println("��Ⱥ������....");
					// �������������10000��
					dispose(5000);
					
					findBestOne();       //ȡ�ý��
					System.out.println("+++++++++++++++++++++++++++���Ϊ��");
					for(int i=0;i<mis;i++){
						System.out.println(best1.x[i]);
						String a1=best1.x[i].substring(0,5);	//��ʦ
						int t1=0;
						for(int j=a1.length()-1;j>=0;j--){
							if(a1.charAt(j) =='1'){
								t1+=Math.pow(2, a1.length()-1-j);
							}
						}
						String a2=best1.x[i].substring(5,10);	//�γ�
						int t2=0;
						for(int j=a2.length()-1;j>=0;j--){
							if(a2.charAt(j)=='1'){
								t2+=Math.pow(2, a2.length()-1-j);
							}
						}
						String a3=best1.x[i].substring(10,15);	//�γ�
						int t3=0;
						for(int j=a3.length()-1;j>=0;j--){
							if(a3.charAt(j)=='1'){
								t3+=Math.pow(2, a3.length()-1-j);
							}
						}
						String a4=best1.x[i].substring(15,25);	//�γ�
						int t4=0;
						for(int j=a4.length()-1;j>=0;j--){
							if(a4.charAt(j)=='1'){
								t4+=Math.pow(2, a4.length()-1-j);
							}
						}
						//System.out.println(t1+" "+t2+" "+t3+" "+t4);
						
						
						
						my.mySetValueAt((String)rmapt.get(t1)+"��ʦ"+"("+(String)mmap.get((String)rmapt.get(t1))+")"+"��"+t4+"����"+"��"+(String)rmapc.get(t2)+"��",t3%4,t3/4+1);

						 my.sava();
					}
					System.out.println("f=" + best1.fitness);
			 		
			 		
			 		
			 		
			 		
			 		*/
			 		
			 		
			 		
			 		
			 		
			 		
			 		boolean vis[]=new boolean[10000];
			 		//�����ı����㷨����̭
			 		for(int i=0;i<9999;i++){
			 			w[i]=new mm();
			 		}
			 		for(int i=0;i<100;i++){
			 			for(int j=0;j<100;j++){
			 				for(int k=0;k<100;k++){
			 					aa[i][j][k]=new mm();
			 				}
			 			}
			 		}
			 		lblNewLabel.setText("23333");
			 		
			 		int i,j,k;
			 		
			 		for(int pp=0;pp<=2;pp++){
			 		for(i=1;i<=5;i++){
			 			for(j=1;j<=4;j++){
			 				
			 				for(k=0;k<7;k++){
			 					
			 					for(int ii=0;ii<mis;ii++){		//������ii������
			 						if(vis[ii])	continue;
			 						if(check(i,j,k,ii)){	//����Ƿ��֮ǰ�Ź��Ŀγ̷�����ͻ
			 							
			 				 			System.out.println("����ɹ�"+a[ii].tname+" "+a[ii].cname+" "+a[ii].cl+" "+i+j+k);
			 							aa[i][j][k]=a[ii];

			 					 		my.mySetValueAt((String)a[ii].tname+"��ʦ"+"("+a[ii].cname+")"+"��"+k+"����"+"��"+a[ii].cl+"��*",j-1,i);
			 							vis[ii]=true;
			 							break;
			 						}
			 						else{
			 							continue;
			 						}
			 						
			 					}
			 					
			 				}
			 				
			 				
			 			}
			 			
			 			
			 		}
			 		
			 		for(int ii=0;ii<mis;ii++){		//������ii������
			 			if(!vis[ii]){
			 				System.out.println(a[ii].cname);
			 			}
			 		}
			 		my.sava();
			 		 table.repaint();//ϵͳ���»��Ʊ��
			 	}
			 	}
			 });
			 panel.add(button);
			 /*********************************************************************************************************************/
			 table.repaint();//ϵͳ���»��Ʊ��
	        
	       
	        
			 
			 
			 
			 
			 JMenuBar menuBar = new JMenuBar();
				frame.setJMenuBar(menuBar);
				
				
				JMenu fmenu = new JMenu("�ļ�");
				menuBar.add(fmenu);
				JMenuItem fMenuItem1 = new JMenuItem("excel����");
				fMenuItem1.addActionListener(new ActionListener()
		 	     {
		 	    	public void actionPerformed(ActionEvent Event)
		     	  {
		 	    		FileChooser ab=new FileChooser();
		 	    		Dir=ab.ac();
		 	 	      file.read(Dir,cellData);
		 	 	      table.repaint();
		 	 	      datachuli();
		     	  }
		 	    });
				fmenu.add(fMenuItem1);
				
				
				
				
				
				
				
				
				
				
				
				JMenu menu = new JMenu("����");
				menuBar.add(menu);
				
				JMenuItem menuItem1_1 = new JMenuItem("�ֶ��ſ�");
				menuItem1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						final JFrame frame1 = new JFrame();
				   		frame1.setBounds(250, 250, 900, 200);
				   		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				   		frame1.setVisible(true);
				   		
			 	   	JPanel panel1 = new JPanel();
					frame1.getContentPane().add(panel1, BorderLayout.CENTER);
					
					JLabel label = new JLabel("��������Ҫ�޸ĵĽ�ʦ�༶��ʱ��");
					panel1.add(label);
					
					textField = new JTextField();
					panel1.add(textField);
					textField.setColumns(10);
					
					
					
					final JTextField textField_4 = new JTextField();
					panel1.add(textField_4);
					textField_4.setColumns(10);
					
					final JTextField textField_5 = new JTextField();
					panel1.add(textField_5);
					textField_5.setColumns(10);
					
					
					JPanel panel_2 = new JPanel();
					frame1.getContentPane().add(panel_2, BorderLayout.SOUTH);
					
					JLabel lblNewLabel = new JLabel("������ʱ��͵ص�");
					panel_2.add(lblNewLabel);
					
					final JTextField textField_1 = new JTextField();
					panel_2.add(textField_1);
					textField_1.setColumns(10);
					
					
					final JTextField textField_2 = new JTextField();
					panel_2.add(textField_2);
					textField_2.setColumns(10);
					
					JPanel panel_3 = new JPanel();
					frame1.getContentPane().add(panel_3, BorderLayout.EAST);
					
					JLabel lblNewLabel_1 = new JLabel("���ڰ�����-�δ�  ���������������ĽڿΣ�����3-2");
					panel_3.add(lblNewLabel_1);
					
					JButton btnNewButton = new JButton("�ſ�");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String s1=textField.getText();
							String s2=textField_4.getText();
							String s3=textField_5.getText();
							String ss1=textField_1.getText();
							String ss2=textField_2.getText();
							my.sp(s1,s2,s3,ss1,ss2);
							table.repaint();
						}

					});
					panel1.add(btnNewButton);
					
						
					}
				});
				
				
				
				
				
				menu.add(menuItem1_1);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				JMenu menu_1 = new JMenu("��ѯ");
				menuBar.add(menu_1);
				
				JMenuItem menuItem = new JMenuItem("���༶");
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						chaxun(0);
						
					}
				});
				menu_1.add(menuItem);
				
				JMenuItem menuItem_1 = new JMenuItem("����ʦ");
				menuItem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						chaxun(1);
						
					}
				});
				menu_1.add(menuItem_1);
				
				JMenuItem menuItem_2 = new JMenuItem("������");
				menuItem_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						chaxun(2);
						
					}
				});
				menu_1.add(menuItem_2);
				
				JMenuItem menuItem_3 = new JMenuItem("�ָ�");
				menuItem_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						my.restore();
						table.repaint();
					}
				});
				menu_1.add(menuItem_3);
				
				JMenu menu_2 = new JMenu("����");
				menuBar.add(menu_2);
				
			 
				
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
	        
	        
	        
	    }
	    /*public void tableChanged(TableModelEvent e)
	    {//���������ݱ仯�¼�
	        int row = e.getFirstRow(); //�����ѡ���ݵ�����
	        int grade1=((Integer)(my.getValueAt(row,1))).intValue();//��������еڶ��е�ֵ
	        int grade2=((Integer)(my.getValueAt(row,2))).intValue();//��ô��е����е�ֵ
		       int total = grade1+grade2;//�����λ�õ�ֵ���е���
	     	   my.mySetValueAt(new Integer(total),row,3);//���仯��ֵ����������
	    	   table.repaint();//ϵͳ���»��Ʊ��
	    }*/
	    class MyTable extends AbstractTableModel {//ʹ��AbstractTableModel���������ģ��
			 Object[][] p = 
		{
					 {"1-2", "", null, null, null, null},
						{"3-4", null, null, null, null, null},
						{"5-6", null, null, null, null, null},
						{"7-8", null, null, null, null, null},
						{"9-10", null, null, null, null, null},
		       };
			 Object[][] pp = 
					{
								 {"1-2", "", null, null, null, null},
									{"3-4", null, null, null, null, null},
									{"5-6", null, null, null, null, null},
									{"7-8", null, null, null, null, null},
									{"9-10", null, null, null, null, null},
					       };
		       String[] n = {"", "\u661F\u671F\u4E00", "\u661F\u671F\u4E8C", "\u661F\u671F\u4E09", "\u661F\u671F\u56DB", "\u661F\u671F\u4E94"};  
		       
		       public void sava(){
		    	   for(int i=0;i<5;i++){
		    		   for(int j=0;j<=5;j++){
		    			   pp[i][j]=p[i][j];
		    			  // System.out.println((String)p[i][j]);
		    		   }
		    	   }
		       }
		       public void restore(){
		    	   for(int i=0;i<5;i++){
		    		   for(int j=0;j<=5;j++){
		    			   p[i][j]=pp[i][j];
		    		   }
		    	   }
		       }
		       public void check(int x,String cl){
		    	   for(int i=0;i<5;i++){
		    		   for(int j=0;j<=5;j++){
		    			   p[i][j]=pp[i][j];
		    		   }
		    	   }
		    	   if(x==0)
		    	   {	//���༶��ѯ

			    	   cl=cl+"��";
		    		   for(int i=0;i<5;i++)
		    		   {
			    		   for(int j=1;j<=5;j++)
			    		   {
			    			   String w1=(String) p[i][j];
			    			   if(w1==null)	continue;
			    			   int index=w1.indexOf(cl);
			    			   int index1=index;
			    			   int index2=index;
			    			   //System.out.println(index1+" "+index2);
			    			   if(index!=-1)
			    			   {	//�ҵ��༶ǰ׺
			    				   
			    				   while(true)
			    				   {
			    					   index1--;
			    					   if(index1==-1||w1.charAt(index1)=='*')
			    					   {
			    						   index1++;
			    						   break;
			    					   }
			    				   }
			    				   while(true)
			    				   {
			    					   index2++;
			    					   if(w1.charAt(index2)=='*')
			    					   {
			    						   break;
			    					   }
			    				   }
			    				   p[i][j]=(Object)(w1.substring(index1, index2+1));
			    			   }
			    			   else 
			    				   p[i][j]=null;

			    		  }
		    		   }
		    	   }
		    	   if(x==1)
		    	   {	//����ʦ��ѯ

			    	   cl=cl+"��";
		    		   for(int i=0;i<5;i++)
		    		   {
			    		   for(int j=1;j<=5;j++)
			    		   {
			    			   String w1=(String) p[i][j];
			    			   if(w1==null)	continue;
			    			   int index=w1.indexOf(cl);
			    			   int index1=index;
			    			   int index2=index;
			    			   if(index!=-1)
			    			   {	//�ҵ��༶ǰ׺
			    				   
			    				   while(true)
			    				   {
			    					   index1--;
			    					   if(index1==-1||w1.charAt(index1)=='*')
			    					   {
			    						   index1++;
			    						   break;
			    					   }
			    				   }
			    				   while(true)
			    				   {
			    					   index2++;
			    					   if(w1.charAt(index2)=='*')
			    					   {
			    						   break;
			    					   }
			    				   }
			    				   p[i][j]=(Object)(w1.substring(index1, index2+1));
			    			   }
			    			   else 
			    				   p[i][j]=null;
			    		  }
		    		   }
		    	   }
		    	   
		    	   if(x==2)
		    	   {	//�����Ҳ�ѯ

			    	   cl=cl+"��";
		    		   for(int i=0;i<5;i++)
		    		   {
			    		   for(int j=1;j<=5;j++)
			    		   {
			    			   String w1=(String) p[i][j];
			    			   if(w1==null)	continue;
			    			   int index=w1.indexOf(cl);
			    			   int index1=index;
			    			   int index2=index;
			    			   if(index!=-1)
			    			   {	//�ҵ��༶ǰ׺
			    				   
			    				   while(true)
			    				   {
			    					   index1--;
			    					   if(index1==-1||w1.charAt(index1)=='*')
			    					   {
			    						   index1++;
			    						   break;
			    					   }
			    				   }
			    				   while(true)
			    				   {
			    					   index2++;
			    					   if(w1.charAt(index2)=='*')
			    					   {
			    						   break;
			    					   }
			    				   }
			    				   p[i][j]=(Object)(w1.substring(index1, index2+1));
			    			   }
			    			   else 
			    				   p[i][j]=null;
			    		  }
		    		   }
		    	   }
		    	   
		    	   
		    	   
		       }
		       public void sp(String s1,String s2,String s3,String ss1,String ss2)	//��ʦ �༶ ʱ��            ʱ�� ����
		       {
			    	   for(int i=0;i<5;i++){
			    		   for(int j=0;j<=5;j++){
			    			   p[i][j]=pp[i][j];
			    		   }
			    	   }
			    	   
			    	   s2=s2+"��";
			    	   //System.out.println(s2+"*********"+s3);
			    	   int jjj=s3.charAt(0)-'0';
    				   int iii=s3.charAt(2)-'0'-1;
			    			   String w1=(String) p[iii][jjj];
			    			   int index=w1.indexOf(s2);
			    			   int index1=index;
			    			   int index2=index;
			    			   if(index!=-1)
			    			   {	//�ҵ��༶ǰ׺
			    				   
			    				   while(true)
			    				   {
			    					   index1--;
			    					   if(index1==-1||w1.charAt(index1)=='*')
			    					   {
			    						   index1++;
			    						   break;
			    					   }
			    				   }
			    				   while(true)
			    				   {
			    					   index2++;
			    					   if(w1.charAt(index2)=='*')
			    					   {
			    						   break;
			    					   }
			    				   }
			    				   
			    				   String neww1=w1.substring(0, index1)+w1.substring(index2+1, w1.length());
			    				   p[iii][jjj]=(Object)neww1;
			    				   
			    				   System.out.println(mmap.get(s1));
			    				   String ww=s1+"��ʦ"+"("+mmap.get(s1)+")"+"��"+ss2+"���ҽ�"+s2+"*";
			    				    
			    				   int jj=ss1.charAt(0)-'0';
			    				   int ii=ss1.charAt(2)-'0'-1;
			    				   if(p[ii][jj]==null){
				    				   p[ii][jj]=(Object)ww;
			    				   }
			    				   else{
			    					   String ooo=(String) p[ii][jj];
			    					   ooo=ooo+ww;
			    					   p[ii][jj]=(Object)ooo;
			    				   }

			    		}
			    	   
			    	   
		    	   
		    	   
		    	   

		    		    	   this.sava();
		       }
		       
		    public int getColumnCount() 
		{
		        return n.length;
		    }
		    public int getRowCount() 
		{
		        return p.length;
		    }
		    public String getColumnName(int col) 
		{
		        return n[col];
		    }
		    public Object getValueAt(int row, int col) 
		{
		        return p[row][col];
		    }
			    public boolean isCellEditable(int rowIndex, int columnIndex) 
		{	//�жϵ�Ԫ���Ƿ���Ա༭
				return true;	
		    }
		    	public void setValueAt(Object value, int row, int col)
		{
		        	p[row][col] = value;
		        	fireTableCellUpdated(row, col);
		    }
		        public void mySetValueAt(Object value, int row, int col) 
		{
		        	//System.out.println(row+" "+col);
		        	String w1=(String) value;
		        	String w2=(String) p[row][col];
		        	if(w2==null){
		        		 p[row][col] =(Object) (w1);
		        	}
		        	else{
		        		 p[row][col] =(Object) (w1+w2);
		        	}
		    	      
		       }
		        public void clear(){
		        	for(int i=0;i<5;i++){
		        		for(int j=1;j<=5;j++){
		        			p[i][j]=null;
		        		}
		        	}
		        }
		}
		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
	
	
	

}
