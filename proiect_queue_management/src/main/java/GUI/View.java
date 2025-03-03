package GUI;

import Logic.SelectionPolicy;
import Logic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class View extends JFrame {
    private JLabel labelNumberOfClients;
    private JTextField textfieldNumberOfClients;
    private JLabel labelNumberOfQueues;
    private JTextField textfieldNumberOfQueues;
    private JLabel labelSimulationInterval;
    private JTextField textfieldSimulationInterval;
    private JLabel labelMinArrivalTime;
    private JTextField textfieldMinArrivalTime;
    private JTextField textfieldMaxArrivalTime;
    private JLabel labelMinServiceTime;
    private JTextField textfieldMinServiceTime;
    private JTextField textfieldMaxServiceTime;
    private JComboBox policyComboBox;
    private ArrayList<JTextField> queueTextFields;
    private JTextField waitingLineTextField;
    private JTextField timeTextField;


    public View() {
        setTitle("Queues Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(150, 96, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        //petru interfata a doua
        queueTextFields = new ArrayList<>();
        waitingLineTextField = new JTextField();
        timeTextField=new JTextField();

        // Numbers Panel
        JPanel numbersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numbersPanel.setOpaque(false);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        labelNumberOfClients = new JLabel("Number of clients");
        labelNumberOfClients.setForeground(Color.white);
        labelNumberOfClients.setFont(new Font("Arial", Font.BOLD, 20));
        textfieldNumberOfClients = createTextField();
        numbersPanel.add(labelNumberOfClients);
        numbersPanel.add(textfieldNumberOfClients);

        labelNumberOfQueues = new JLabel("Number of queues");
        labelNumberOfQueues.setForeground(Color.white);
        labelNumberOfQueues.setFont(new Font("Arial", Font.BOLD, 20));
        textfieldNumberOfQueues = createTextField();
        numbersPanel.add(labelNumberOfQueues);
        numbersPanel.add(textfieldNumberOfQueues);

        labelSimulationInterval = new JLabel("Simulation interval");
        labelSimulationInterval.setForeground(Color.white);
        labelSimulationInterval.setFont(new Font("Arial", Font.BOLD, 20));
        textfieldSimulationInterval = createTextField();
        numbersPanel.add(labelSimulationInterval);
        numbersPanel.add(textfieldSimulationInterval);

        mainPanel.add(numbersPanel);

        // Arrival Time Panel
        JPanel arrivalTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        arrivalTimePanel.setOpaque(false);

        labelMinArrivalTime = new JLabel("Minimum and maximum arrival time");
        labelMinArrivalTime.setForeground(Color.white);
        labelMinArrivalTime.setFont(new Font("Arial", Font.BOLD, 20));
        textfieldMinArrivalTime = createTextField();
        textfieldMaxArrivalTime = createTextField();
        arrivalTimePanel.add(labelMinArrivalTime);
        arrivalTimePanel.add(textfieldMinArrivalTime);
        arrivalTimePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        arrivalTimePanel.add(textfieldMaxArrivalTime);
        mainPanel.add(arrivalTimePanel);

        // Service Time Panel
        JPanel serviceTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        serviceTimePanel.setOpaque(false);

        labelMinServiceTime = new JLabel("Minimum and maximum service time");
        labelMinServiceTime.setForeground(Color.white);
        labelMinServiceTime.setFont(new Font("Arial", Font.BOLD, 20));
        textfieldMinServiceTime = createTextField();
        textfieldMaxServiceTime = createTextField();
        serviceTimePanel.add(labelMinServiceTime);
        serviceTimePanel.add(textfieldMinServiceTime);
        serviceTimePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        serviceTimePanel.add(textfieldMaxServiceTime);
        mainPanel.add(serviceTimePanel);

        // Combo box and ENTER Button Panel
        JPanel comboEnterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboEnterPanel.setOpaque(false);

        JLabel policyLabel = new JLabel("Selection Policy");
        policyLabel.setForeground(Color.white);
        policyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        policyComboBox = new JComboBox<>();
        SelectionPolicy[] policies = SelectionPolicy.values();
        for (SelectionPolicy policy : policies)
        {
            policyComboBox.addItem(policy);
        }
        policyComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        comboEnterPanel.add(policyLabel);
        comboEnterPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        comboEnterPanel.add(policyComboBox);

        JButton enterButton = new JButton("ENTER");
        enterButton.setBackground(new Color(211, 137, 211));
        enterButton.setForeground(Color.white);
        enterButton.setPreferredSize(new Dimension(100, 50));
        comboEnterPanel.add(enterButton);

        mainPanel.add(comboEnterPanel);

        setVisible(true);

        enterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int nbClients = 0;
                int nbQueues = 0;
                int simInterval = 0;
                int minArrivalTime = 0, maxArrivalTime = 0;
                int minServiceTime = 0, maxServiceTime = 0;
                boolean start=true;
                int numberOfQueues=0;

                try
                {
                    nbClients=Integer.parseInt(textfieldNumberOfClients.getText());
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid number of clients");
                    start=false;
                }
                try
                {
                    nbQueues=Integer.parseInt(textfieldNumberOfQueues.getText());
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid number of queues");
                    start=false;
                }
                try
                {
                    simInterval=Integer.parseInt(textfieldSimulationInterval.getText());
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Simulation Interval");
                    start=false;
                }
                try
                {
                    minArrivalTime=Integer.parseInt(textfieldMinArrivalTime.getText());
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid minimum arrival time");
                    start=false;
                }
                try
                {
                    maxArrivalTime=Integer.parseInt(textfieldMaxArrivalTime.getText());
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid maximum arrival time");
                    start=false;
                }
                try
                {
                    minServiceTime=Integer.parseInt(textfieldMinServiceTime.getText());
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid minimum service time");
                    start=false;
                }
                try
                {
                    maxServiceTime=Integer.parseInt(textfieldMaxServiceTime.getText());
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid maximum service time");
                    start=false;
                }
                try
                {
                    numberOfQueues = Integer.parseInt(textfieldNumberOfQueues.getText());
                    openNewWindow(numberOfQueues);
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "wrong");
                    start=false;
                }

                if(minArrivalTime > maxArrivalTime)
                {
                    start=false;
                    JOptionPane.showMessageDialog(null, "minArrivalTime is grater than maxArrivalTime");
                }
                if(minServiceTime > maxServiceTime)
                {
                    start=false;
                    JOptionPane.showMessageDialog(null, "minServiceTime is grater than maxServiceTime");
                }

                if(nbClients<0 || nbQueues<0 || simInterval<0 || minArrivalTime<0 || maxArrivalTime<0 || minServiceTime<0 || maxServiceTime<0)
                {
                    start=false;
                    JOptionPane.showMessageDialog(null, "one ore more inputs are negative");
                }
                if (start)
                {
                    SelectionPolicy sel= (SelectionPolicy) policyComboBox.getSelectedItem();
                    SimulationManager sim=new SimulationManager(View.this, simInterval, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime,  nbClients, nbQueues, sel);
                    Thread a=new Thread(sim);
                    a.start();
                }
            }

        });
    }

    private JTextField createTextField()
    {
        JTextField textField = new JTextField(18);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBackground(new Color(245, 187, 245));
        return textField;
    }

    private void openNewWindow(int numberOfQueues)
    {
        JFrame parameterInputWindow = new JFrame("Simulation setup");
        parameterInputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parameterInputWindow.setSize(500, 500);
        parameterInputWindow.setLocationRelativeTo(this);

        parameterInputWindow.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                super.windowClosed(e);
                System.exit(0);
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(150, 96, 150));
        parameterInputWindow.add(mainPanel);


        JLabel waitingLineLabel = new JLabel("Waiting Line");
        waitingLineLabel.setForeground(Color.white);
        waitingLineLabel.setFont(new Font("Arial", Font.BOLD, 20));
        waitingLineTextField = createTextField();
        mainPanel.add(waitingLineLabel);
        mainPanel.add(waitingLineTextField);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setForeground(Color.white);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeTextField = createTextField();
        mainPanel.add(timeLabel);
        mainPanel.add(timeTextField);

        for (int i = 0; i < numberOfQueues; i++)
        {
            JLabel labelQueue = new JLabel("Queue " + (i + 1) + "");
            labelQueue.setForeground(Color.white);
            labelQueue.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField textFieldQueue = createTextField();
            textFieldQueue.setPreferredSize(new Dimension(200, 30));
            mainPanel.add(labelQueue);
            mainPanel.add(textFieldQueue);
            queueTextFields.add(textFieldQueue);
        }

        parameterInputWindow.setVisible(true);
    }

    public void updateWaitingLineText(String s)
    {
        System.out.println(s);
        waitingLineTextField.setText(s);
    }

    public void updateTimeText(String timeText)
    {
        timeTextField.setText(timeText);
    }

    public void updateQueueTextFields(String[] queueStatus)
    {
        for (int i = 0; i < queueStatus.length; i++)
        {
            queueTextFields.get(i).setText(queueStatus[i]);
        }
    }
}
