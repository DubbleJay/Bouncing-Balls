import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList; 
import javax.swing.event.*;


public class Exercise22_5 {
   
   private JButton suspend = new JButton("Suspend");
   private JButton resume = new JButton("Resume");
   private JButton plus = new JButton("+1");
   private JButton minus = new JButton("-1");
   circlePanel panel = new circlePanel(); 
   private ArrayList<Circle> list = new ArrayList<Circle>();
   private int speed = 5; 
   private Timer timer = new Timer(speed, new TimeListener2());
   private JSlider slide = new JSlider(0, 30, 5); 
   
   Exercise22_5(){
      
     JPanel buttons = new JPanel();
      JPanel p = new JPanel();
      p.add(getComp(new JLabel("Speed")));
      p.add(getComp(slide)); 
      buttons.setBackground(Color.black); 
      buttons.add(getComp(suspend));  
      buttons.add(getComp(resume)); 
      buttons.add(getComp(plus)); 
      buttons.add(getComp(minus)); 
      JFrame frame = new JFrame(); 
      frame.add(buttons,  BorderLayout.SOUTH); 
      frame.add(panel,  BorderLayout.CENTER);
      //frame.add(getComp(p),  BorderLayout.NORTH);
      frame.setSize(700, 700);
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      timer.start();
      
      slide.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            System.out.println(slide.getValue()); 
            speed = slide.getValue();
            timer.stop();
            timer.start();
            //panel.repaint(); 
         }
       });
      
      plus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
               list.add(new Circle());     
               panel.repaint(); 
            
         }
      });
      
      minus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
               list.remove(list.size() - 1);     
               panel.repaint(); 
            
         }
      });
      
      suspend.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            timer.stop(); 
         }
      });
      
      resume.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            timer.start(); 
         }
      });
       
    }//end constructor
   
    public static void main(String[] args) {
      new Exercise22_5();
    }//end main method
    
    class circlePanel extends JPanel {
      public void paintComponent (Graphics g) {
         g.setColor(Color.yellow); 
         g.fillRect(0 ,0, this.getWidth(), this.getHeight());
         
         for(int i = 0; i < list.size(); i++){
            g.setColor(Color.red); 
            g.fillOval(list.get(i).x, list.get(i).y, list.get(i).w, list.get(i).h); 
         }
      }
    }//end inner class
    
    public JComponent getComp(JComponent comp) {
      comp.setBackground(Color.black);
      comp.setForeground(Color.white);
      return comp; 
    } 
    
    class TimeListener2 implements ActionListener {

      public void actionPerformed(ActionEvent e) {
      
         for(int i = 0; i < list.size(); i++){
            if(list.get(i).y == 617)
               list.get(i).up = true;
            
            if(list.get(i).y == 0)
               list.get(i).up = false;
            
            if(list.get(i).up == false)
               list.get(i).y++; 
               
             if(list.get(i).up == true)
               list.get(i).y--;
         }
         
         panel.repaint();
         
      
      }
    }//end inner class
    
    
}//end class

class Circle {

   int x; 
   int y;
   int w; 
   int h; 
   boolean up;
   
   Circle(){
      x = (int)(Math.random() * 682);
      y = (int)(Math.random() * 617);
      w = 40; 
      h = 40; 
      
   }
   
   
}//end class 