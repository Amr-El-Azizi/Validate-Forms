import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ValidateForm
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               TextComponentFrame frame = new TextComponentFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

/**
 * A frame with sample text components.
 */
class TextComponentFrame extends JFrame
{
   public TextComponentFrame()
   {
      setTitle("Subscription Form");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      final JTextField personName = new JTextField();
      final JTextField emailField = new JTextField();
      final JTextField zipCode = new JTextField();
      final JTextField birthDate = new JTextField();
      final JTextField phone= new JTextField();
      final JPasswordField passwordField = new JPasswordField();
      
      
      JPanel northPanel = new JPanel();
      northPanel.setLayout(new GridLayout(6, 6)); //dimensons of layout
      northPanel.add(new JLabel("Name :  ", SwingConstants.RIGHT));
      northPanel.add(personName);
      
      northPanel.add(new JLabel("Password :  ", SwingConstants.RIGHT));
      northPanel.add(passwordField);
      
      
      northPanel.add(new JLabel("Email : ", SwingConstants.RIGHT));
      northPanel.add(emailField);
      northPanel.add(new JLabel("Zip Code : ", SwingConstants.RIGHT));
      northPanel.add(zipCode);
      northPanel.add(new JLabel("Year of Birth: ", SwingConstants.RIGHT));
      northPanel.add(birthDate);
      northPanel.add(new JLabel("Phone Number: ", SwingConstants.RIGHT));
      northPanel.add(phone);
      phone.setText("XXX-XXX-XXX");       

      add(northPanel, BorderLayout.NORTH);

      final JTextArea textArea = new JTextArea(8, 40);
      JScrollPane scrollPane = new JScrollPane(textArea);

      add(scrollPane, BorderLayout.CENTER);

      // add button to append text into the text area

      JPanel southPanel = new JPanel();

      JButton insertButton = new JButton("Insert");
      southPanel.add(insertButton);
      insertButton.addActionListener(new ActionListener()
         {
                private boolean isAllAlpha(String str){
                    boolean a = true;
                    for(int i = 0; i < str.length(); i ++)
                    {
                        if(Character.isLetter(str.charAt(i)))
                        a = true;
                        else
                        {
                        a = false;
                        }
                    }
                    return a;
                }
                
                private boolean isAllNumeric(String str){
                String m = "1234567890.";
                int dot = 0;
                for(int i = 0; i<str.length()&&dot<2; i ++){
                    if(m.indexOf(str.charAt(i))==-1)
                    return false;
                    if(str.charAt(i)=='.')
                    dot ++;
                }
                if(dot>1)
                return false;
                return true;
                }
                
                private boolean passCheck(String str)
                {
                    int lc = 0;
                    int uc = 0;
                    int n = 0;
                    String mn = "123456789";
                    String mlc = "qwertyuiopasdfghjklzxcvbnm";
                    String muc = "QWERTYUIOPASDFGHJKLZXCVBNM";
                    for(int i = 0; i<str.length(); i++)
                    {
                        if(mn.indexOf(str.charAt(i))!=-1)
                        n++;
                        if(muc.indexOf(str.charAt(i))!=-1)
                        uc++;
                        if(mlc.indexOf(str.charAt(i))!=-1)
                        lc++;
                    }
                    if(n>0 && uc>0 && lc>0)
                    return true;
                    return false;
                }
                
                private boolean phoneCheck(String str)
                {
                    if(str.length()<12)
                    return false;
                    if(isAllNumeric(str.substring(0,3))!=true)
                    return false;
                    if(isAllNumeric(str.substring(4,7))!=true)
                    return false;
                    if(isAllNumeric(str.substring(8,12))!=true)
                    return false;
                    if(str.charAt(3)!='-'||str.charAt(7)!='-')
                    return false;
                    return true;
                }
                
                private boolean emailCheck(String str)
                {
                    int at = 0;
                    for(int i = 0; i<str.length()-1; i++)
                    {
                        if(str.charAt(i)=='@')
                        {
                            at=1;
                        }
                        if(at==1 && str.charAt(i)=='.')
                        {
                            return true;
                        }
                    }
                    return false;
                }
                

             public void actionPerformed(ActionEvent event)
            {
                char[] cPssword  = passwordField.getPassword();

       
            
   //    ****************************   CHANGE CODE ABOVE   AT OWN RISK           ******************************* 
                
    //*********************  Here is where the action is performed!!!!              
                
                String cName = personName.getText() ;
                String cEmail = emailField.getText();
                String cZipCode = zipCode.getText();
                String cbirthDate = birthDate.getText();
                String cPhone = phone.getText(); 
                String cPassWord = new String(cPssword);

                    
                boolean a = true;
                

                
                if(isAllAlpha(cName) == false || cName.length()<3)
                   textArea.append("Is that really your name? (hint: R2D2 is not a valid name).");
                   
                if(passCheck(cPassWord) == false)
                   textArea.append("\n Your password needs a lower case letter, upper case letter and a number");
                   
                if(cPassWord.length()<4)
                   textArea.append("\n Passwords usually have a length of at least 4, unless you want to be hacked easily. Your choice.");
                   
                if(cPassWord.equals(cName))
                   textArea.append("\n Your name is not a valid/safe password... BENJAMIN");
                   
                if(emailCheck(cEmail)==false)
                    textArea.append("\n Your email is invalid, please give a real one.");
                
                if(isAllNumeric(cZipCode)==false || cZipCode.length()<6 || cZipCode.length()>2)
                   textArea.append("\n Your Zip code is not in the known regions of the Universe... Sorry.");             
                   
                   try{
                       int bDayAsInt = Integer.parseInt( cbirthDate);
                        if(bDayAsInt<1880 || bDayAsInt>2004)
                             textArea.append("\n Please be born sometime reasonable...");
                
                   }
                catch( java.lang.NumberFormatException e){
                    textArea.append("\n Birthdays are in numbers you know?");
                }
                
                if(phoneCheck(cPhone)==false)
                    textArea.append("\n Yellow... Oh wait, your number doesn't exist");
                
                if(textArea.getText().length()==0)
                    textArea.append("Thank You");

                
              //
//********************************************************************************
   //    ****************************       CHANGE CODE BELOW AT OWN RISK    ******************************* 
               
            
            }
            
         });

      add(southPanel, BorderLayout.SOUTH);

      // add a text area with scroll bars

   }

   public static final int DEFAULT_WIDTH = 300;
   public static final int DEFAULT_HEIGHT = 300;
}