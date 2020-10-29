package com.example.rpc.ui.interval;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntervalIntakeViewTable {
	
	private JFrame frame;
    private JTable table;
    private JTextField id;
    private JTextField itemId;
    private JButton execute;
    private JLabel ss;
    private JLabel mm;
    private JLabel hh;
    
    public void addActionListener(ActionListener actionListener) {
    	this.execute.addActionListener(actionListener);
    }

   
    public JButton getExecute() {
		return execute;
	}

	public void setExecute(JButton execute) {
		this.execute = execute;
	}

	public void setHours(int hour) {
    	hh.setText(String.valueOf(hour)+":");
    }
    
    public void setMinutes(int minute) {
    	mm.setText(String.valueOf(minute)+":");
    }
    
    public void setSeconds(int second) {
    	ss.setText(String.valueOf(second));
    }

  
    public JTable getTable() {
        return table;
    }

    public String getId() {
        return id.getText();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    

    
    public String getItemId() {
		return itemId.getText();
	}

	public void setItemId(String itemId) {
		this.itemId.setText(itemId);;
	}

	public void setId(String id) {
		this.id.setText(id);
	}

	@Autowired
    public IntervalIntakeViewTable() {
        initialize();
    }


    private void initialize() {

        frame = new JFrame();
        frame.setBounds(0, 0, 1800, 900);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(195, 50, 949, 251);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Medical Plan Id:");
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel.setBounds(190, 329, 160, 19);
        frame.getContentPane().add(lblNewLabel);

        id = new JTextField();
        id.setBounds(313, 322, 192, 26);
        frame.getContentPane().add(id);
        id.setColumns(10);
        


        JLabel lblItemId = new JLabel("Drug Id:");
        lblItemId.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 15));
        lblItemId.setBounds(190, 366, 79, 19);
        frame.getContentPane().add(lblItemId);

        itemId = new JTextField();
        itemId.setColumns(10);
        itemId.setBounds(313, 359, 192, 26);
        frame.getContentPane().add(itemId);
        
        
        execute = new JButton("Take");
        execute.setBounds(313, 390, 192, 38);
        frame.getContentPane().add(execute);
        
        hh = new JLabel("HH:");
        hh.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 25));
        hh.setBounds(190, 450, 60, 50);
        frame.getContentPane().add(hh);
        
        mm = new JLabel("MM:");
        mm.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 25));
        mm.setBounds(250, 450, 60, 50);
        frame.getContentPane().add(mm);
        
        ss = new JLabel("SS");
        ss.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 25));
        ss.setBounds(310, 450, 60, 50);
        frame.getContentPane().add(ss);
        
        
        


        
    }
    
    public void setFrameVisible(boolean visible) {
    	this.frame.setVisible(visible);
    }

    public void refreshTable(Object[][] data, String[] columnNames) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }


}
