import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Menu {


    public static void getPath(){
        final String[] path = {""};
        boolean isDone = false;
        JFrame frame=new JFrame("Agriculture Heat Map");
        final JLabel Head = new JLabel("Agriculture Heat Map Generator", SwingConstants.CENTER);
        final JLabel subHead = new JLabel("Made by: Nour Ramadan, Youssef Bedair, Khaled Ez, Mohanad Ayman");
        JButton button = new JButton("click me");
        JTextArea text = new JTextArea("C:\\Users\\Spectre\\IdeaProjects\\DataProjectBonus\\src\\plants.txt");
        frame.setSize(800,800);
        Head.setBounds((800-150)/2-80, 10, 300, 100);
        subHead.setBounds((800-225)/2-60, 50, 500, 100);
        subHead.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        Head.setHorizontalAlignment(SwingConstants.CENTER);
        Head.setVerticalAlignment(SwingConstants.CENTER);
        Head.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text.setBounds(200,550, 300,20);
        button.setBounds(300,600,100, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path[0] = text.getText();
                System.out.println(path[0]);
                try {
                    Program.initialize(path[0]);
                    frame.setVisible(false);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(button);frame.add(text);frame.add(Head);frame.add(subHead);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void MainProgram(AVLTree plantsIndex){
        JFrame frame = new JFrame("Heat Map");
        JPanel panel = new JPanel();
        panel.setBounds(0,0,800,800);
        frame.setVisible(false);
        JTextArea data = new JTextArea();
        int padding = 20;
        ArrayList<JRadioButton> arrayRadio = new ArrayList<JRadioButton>();
        ButtonGroup buttonGroup = new ButtonGroup();
        ArrayList<String> temp= Program.plantIndex.returnDisplay();
        String[] PlantNames = new String[temp.size()];
        for(int i = 0; i<temp.size(); i++){
            PlantNames[i] = temp.get(i);
        }
        JList list = new JList(PlantNames);
        list.setBounds(600, 20, 200, 500);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                data.setText(plantsIndex.find(temp.get(list.getSelectedIndex())).plantList.toString().replaceAll("\\|\\|", "\n"));
            }
        });
        frame.add(list);
        data.setBounds(20, 20, 200, 400);
        data.setEditable(false);
        frame.add(data);
        frame.add(panel);
        frame.setSize(800,800);
        JButton button = new JButton("Search!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

    }



}
