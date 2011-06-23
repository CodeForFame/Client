package org.rscdaemon.client.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class ErrorWindow extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorWindow() {
        initComponents();
    }
    public static ErrorWindow e = new ErrorWindow();

    public void addText(final String t) {
    	SwingUtilities.invokeLater(new Runnable() {  
    		public void run() {  
    			jTextArea1.append(t + "\n");  
    		  }  
    	});
    }
    private void initComponents() {

    	this.setTitle("Oops, an error! Please submit a report!");
    	this.setResizable(false);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        sendReportButton = new javax.swing.JButton();

        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Error data:");

        sendReportButton.setText("Send report");
        sendReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String email = "";
                while(email.equalsIgnoreCase("") || email == null || !email.contains("@") || !email.contains(".")) {
                	email = JOptionPane.showInputDialog("Please enter your valid email");
                }
                try {  
                	String data = URLEncoder.encode("report", "UTF-8") + "=" + URLEncoder.encode(jTextArea1.getText(), "UTF-8"); 
                	data += "&" +  URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                	URL url = new URL("http://rscangel.org/crash.php"); 
                	URLConnection conn = url.openConnection(); 
                	conn.setDoOutput(true); 
                	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
                	wr.write(data); 
                	wr.flush(); 

                	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
                	String line; 
                	while ((line = rd.readLine()) != null) { 
                		if(line.equalsIgnoreCase("OK")) {
                			e.setVisible(false);
                			JOptionPane.showMessageDialog(null, "Report sent, thank you!");
                			}
                		else if(line.equalsIgnoreCase("Just sent")) {
                			JOptionPane.showMessageDialog(null, "It appears you just sent in a report! Please wait atleast a minute to send in another one");
                			}
                		else {
                			JOptionPane.showMessageDialog(null, "There was an error while submitting the report, please post the text in the white box on forums at http://rscangel.org/forums");
                			}
                		} 
                	wr.close(); 
                	rd.close(); 
                	}
                catch (Exception e) { 
                	e.printStackTrace();
                } 
                		
        	
            }
        });


       
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()

                                .addComponent(sendReportButton)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(83, 83, 83))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendReportButton)
              )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        pack();
    }
    public static void initilize() {
    	 e.setResizable(false);
    	 e.setVisible(false);
    	 e.addText("Client Report:\n");
    	 
    	 e.addText("isMacOS: " + (System.getProperty("mrj.version") != null));
         Properties pr = System.getProperties();
         TreeSet propKeys = new TreeSet(pr.keySet()); 
         for (Iterator<?> it = propKeys.iterator(); it.hasNext(); ) {
             String key = (String)it.next();
             e.addText("" + key + "=" + pr.get(key));
         }
         e.addText("\nErrors:\n");
         //redirectSystemStreams();
    }
    public static int lines = 0;
    public static void drawError(final String t) {
    	if(lines++ > 100)
    		return;
    	SwingUtilities.invokeLater(new Runnable() {  
    		public void run() {  
    			if(!e.isVisible())
    	    		e.setVisible(true);
    			jTextArea1.append(t);  
    		  }  
    	});  
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
       initilize();
    }
    private javax.swing.JButton sendReportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
	public static void redirectSystemStreams() {  
    	OutputStream out = new OutputStream() {  
    		@Override  
    		public void write(int b) throws IOException {  
    			ErrorWindow.drawError(String.valueOf((char) b));  
    		}  
    		@Override  
    		public void write(byte[] b, int off, int len) throws IOException {  
    			ErrorWindow.drawError(new String(b, off, len));  
    	     }  
    	   
    	     @Override  
    	     public void write(byte[] b) throws IOException {  
    	       write(b, 0, b.length);  
    	     }  
    	   };  
    	   
    	   //System.setOut(new PrintStream(out, true));  
    	   System.setErr(new PrintStream(out, true));  
    }
}
