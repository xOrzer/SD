package Yessaï;

import popop.ChatClientFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;


public class Client extends ChatClientFrame implements ClientInterface {

    JFrame frm = new JFrame();
    JButton b;

    ServerInterface serv;
    ClientInterface stub;

    static String appliName = "TCP Chat";



    Dimension prefSize = new Dimension(500, 300);
    JTextArea myDisplay;
    JTextField myText ;
    JList myGroup ;
    private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM) ;


    @Override
    public void showYourName() throws RemoteException {
        String name = this.userName;
        System.out.println(name);
        frm.setTitle(name);
    }



    public Client(String aHost, String aUserName) throws RemoteException {
        super(aHost, aUserName);
        try{
            stub = (ClientInterface) UnicastRemoteObject.exportObject(this, 0);

            serv = (ServerInterface) Naming.lookup("rmi://localhost/server");

            serv.sayHello();
            serv.register(stub);
            System.out.println("Client.Client()");
        }catch(Exception e){
            e.printStackTrace();
        }

        buildUI();
    }

    public Client(String aHost) throws RemoteException {
        this(aHost, null) ;
    }


    void buildUI() throws RemoteException {

        getContentPane().setLayout(new BorderLayout()) ;

        myDisplay = new JTextArea() ;
        myDisplay.setEditable(false) ;
        myDisplay.setLineWrap(true) ;
        JScrollPane scroll = new JScrollPane(myDisplay) ;
        add(scroll, BorderLayout.CENTER) ;

        myText = new JTextField() ;
        add(myText, BorderLayout.SOUTH) ;

        myGroup = new JList() ;
        myGroup.setPrototypeCellValue("Greg is ze Best !") ;
        scroll = new JScrollPane(myGroup) ;
        add(scroll, BorderLayout.EAST) ;



        myText.grabFocus();
        setTitle(this.userName) ;
        setSize(prefSize) ;
        setVisible(true) ;
    }
/*
    private void buildUI(){
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b = new JButton("!!! CLICK !!!");
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    serv.sayHello();
                    serv.register(stub);
                } catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        });
        frm.getContentPane().add(b);

        frm.setSize(300, 200);
        frm.setVisible(true);
    }*/


    public static void main(String[] args) throws RemoteException {
        System.out.println("Client demarre");
        new Client("localhost");
    }
}
