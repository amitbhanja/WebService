package com.shop.clientui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.ws.rs.core.MediaType;

import com.shop.rest.ws.CartInfo;
import com.shop.soap.ws.Product;
import com.shop.soap.ws.ProductArray;
import com.shop.soap.ws.Shop;
import com.sun.jersey.api.client.WebResource;

public class ClientUI extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Map buttonProductInfoMap;
	List productOrderedQtyList;
	
	Shop shopServices;
	String clientId;
	public static WebResource resource;
	private List<Product> products;
	private List<CartInfo> cartinfo;
	public static final String SHOW_ALL_PRODUCTS_PATH = "/shop/allProducts";
	public static final String ORDER_PATH = "/shop/Order";
	public ClientUI(String clientId, Shop shop, List<Product> products) {
		super();
		this.clientId = clientId;
		this.products = products;
		this.shopServices = shop;
		productOrderedQtyList = new ArrayList();
		buttonProductInfoMap = new HashMap();
	}
	public ClientUI(String clientId,WebResource resource1){
		resource = resource1;
		this.clientId = clientId;
		this.cartinfo = new ArrayList<CartInfo>();
		WebResource allproducts = resource.path(SHOW_ALL_PRODUCTS_PATH);
		CartInfo[] cart = allproducts.accept(MediaType.APPLICATION_XML).get(CartInfo[].class);
		for(int i = 0;i < cart.length;i++)
		{
			this.cartinfo.add(cart[i]);
		}
		productOrderedQtyList = new ArrayList();
		buttonProductInfoMap = new HashMap();
	}
	
	
	public List<Product> getProducts() {
		return products;
	}



	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	private boolean isRESTService;
	public JFrame frame;
	public static JPanel cartPanel;
	public static JSeparator separator;
	
	public static JTable cartTable;
	public static DefaultTableModel dataModel;
	public static JScrollPane cartScrollPane;
	
	public static JLabel heading;
	public static JLabel prodInfo;

	public static JButton logout;
	public static JButton orderButton;
	public static JLabel successLabel = null;
	public static JLabel failureLabel = null;
	
	
	public void addComponentsToPane(final Container pane) {
		
		System.out.println("STARTED: addComponentsToPane");
		
		cartPanel = new JPanel(null);	
		
		heading = new JLabel("Welcome to Shopping Cart");
		heading.setFont(new Font("", Font.BOLD, 28));
		heading.setBounds(55,50,400,100);
		cartPanel.add(heading);
		
		separator = new JSeparator(SwingConstants.VERTICAL);  
		separator.setBounds(640, 50, 1, 480);
		cartPanel.add(separator);
		
		heading = new JLabel("Products");
		heading.setFont(new Font("", Font.BOLD, 16));
		heading.setBounds(55,150,80,60);
		cartPanel.add(heading);
		
		/*
		 * 
		 * ************** --> First Item <-- *******************
		 */
		String curr_dir = System.getProperty("user.dir");
		System.out.println(curr_dir);
		
		JLabel productLabel;
		JButton addToCartButton;
		int y1 = 200;
		int y2 = 220;
		int y3 = 230;
		int y4 = 252;
		
		System.out.println("Before generic code");
		System.out.println(this.getProducts());
		
		Map<String,Object> productsInfo;
		TableModel model;
		JTable table;
		JLabel quantityJLabel;
		JTextField quantity;
		int size = 0;
		if(isRESTService == false)
			 size = products.size();
		else
			 size = cartinfo.size();
		
		for (int i = 0; i<size; i++) {
			productLabel = new JLabel(new ImageIcon(curr_dir.concat("\\"+(i+1)+".jpg")));
			productLabel.setBounds(50, y1, 70, 70);
			cartPanel.add(productLabel);
			
			addToCartButton = new JButton("Add to cart");
			addToCartButton.setBounds(240, y2, 100, 25);
			cartPanel.add(addToCartButton);
			
			addToCartButton.addActionListener(this);
			
			productsInfo = new HashMap<String,Object>();
			if(isRESTService == false)
			{
			  productsInfo.put("price", products.get(i).getPrice());
			  productsInfo.put("name", products.get(i).getProductName());
			  productsInfo.put("stock", products.get(i).getQuantityAvailable());
			}
			else
			{
				productsInfo.put("price", cartinfo.get(i).getPrice());
				productsInfo.put("name", cartinfo.get(i).getProductName());
				productsInfo.put("stock", cartinfo.get(i).getQuantityAvailable());
			}
			
			model = toTableModel(productsInfo);
			table = new JTable();
			table.setModel(model);
			table.setFillsViewportHeight(true);
			table.setBounds(125, y1, 110, 50);
			cartPanel.add(table);
			System.out.println("Adding table");
			
			quantityJLabel = new JLabel("Quantity: ");
			quantityJLabel.setBounds(125, y3, 53, 60);
			cartPanel.add(quantityJLabel);
			
			quantity = new JTextField("0");
			quantity.setBounds(180, y4, 30, 20);
			cartPanel.add(quantity);
			
			y1+=100; y2+=100;
			y3+=100; y4+=100;
			
			List productQtyList = new ArrayList();
			if(isRESTService == false)
				productQtyList.add(products.get(i));
			else
				productQtyList.add(cartinfo.get(i));
			productQtyList.add(quantity);
			productQtyList.add(i);
			productQtyList.add(model);
			buttonProductInfoMap.put(addToCartButton, productQtyList);
		}
		/*
		 * 
		 * ***********************************
		 * ****** END OF ITEMS ***************
		 * ***********************************
		 */
		
		heading = new JLabel("Your Cart Contains: ");
		heading.setFont(new Font("", Font.BOLD, 28));
		heading.setBounds(680,200,400,100);
		cartPanel.add(heading);
		
		/*
		 * 
		 * ********* Shopping Cart Table **********
		 * ******* Displays Selected Items *********/
		 
		dataModel = new DefaultTableModel();
		dataModel.addColumn("Product");
		dataModel.addColumn("Quantity");
		dataModel.addColumn("Price");
		if(isRESTService == false)
		{
		  for (int i = 0; i<products.size(); i++) {
			  dataModel.addRow(new Object[] { products.get(i).getProductName(), "0", "0.00"});
		  }
		}
		else
		{
			for (int i = 0; i<cartinfo.size(); i++) {
				  dataModel.addRow(new Object[] { cartinfo.get(i).getProductName(), "0", "0.00"});
			  }
		}
		cartTable = new JTable(dataModel);
		cartTable.setFillsViewportHeight(true);
		cartScrollPane = new JScrollPane(cartTable);
		cartScrollPane.setBounds(680, 275, 350, 72);
		cartPanel.add(cartScrollPane);
		
		
		/*
		 * 
		 * Place order button */
		 
		
		orderButton = new JButton("Place Order");
		orderButton.setBounds(680, 375, 150, 25);
		cartPanel.add(orderButton);
		
		
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Place Order Button clicked"); 
				
				
				String orderStatus = null;
				TableModel cartTableModel = cartTable.getModel();
				if(isRESTService == false)
				{
				  ProductArray prodArray = new ProductArray();
				  for (int i=0; i<cartTableModel.getRowCount();i++) {
					
					int qty = Integer.valueOf(cartTableModel.getValueAt(i, 1).toString());
					System.out.println(qty);
					String productName = (String)cartTableModel.getValueAt(i, 0);
					
					Product p = new Product();
					p.setQuantity(new Integer(qty));
					p.setProductName(productName);
					prodArray.getItem().add(p);
				  }
				
				
				orderStatus = shopServices.placeOrder(prodArray);
				System.out.println(orderStatus);
				}
				else
				{
					CartInfo[] cartinfo =  new CartInfo[cartTableModel.getRowCount()];
					 for (int i=0; i<cartTableModel.getRowCount();i++) {
							
							int qty = Integer.valueOf(cartTableModel.getValueAt(i, 1).toString());
							System.out.println(qty);
							String productName = (String)cartTableModel.getValueAt(i, 0);
							cartinfo[i] = new CartInfo();
							cartinfo[i].setProductName(productName);
							cartinfo[i].setQuantity(qty);
							
					 }
					 WebResource order = resource.path(ORDER_PATH);
					 orderStatus = order.accept(MediaType.APPLICATION_JSON).post(String.class, cartinfo);
				}
				
				if (orderStatus.contains("SUCCESS")) {
					Collection c = buttonProductInfoMap.values();
					Iterator iterator = c.iterator();
					
					int index=0;
					while (iterator.hasNext()) {
						List<TableModel> temp = (List<TableModel>)iterator.next();
						TableModel tempModel = temp.get(3);
						JTextField qty = (JTextField) temp.get(1);
						System.out.println(Integer.valueOf(tempModel.getValueAt(0,1).toString()).intValue());
						System.out.println(Integer.valueOf(qty.getText().toString()).intValue());
						tempModel.setValueAt(Integer.valueOf(tempModel.getValueAt(0,1).toString()).intValue()-Integer.valueOf(qty.getText().toString()).intValue(),0,1);
						qty.setText("0");
						index++;
					}
					
					if(failureLabel != null) {
						System.out.println("failureLabel removed here!!");
						cartPanel.remove(failureLabel);
						cartPanel.repaint();
					}
					
					successLabel = new JLabel(orderStatus);
					successLabel.setBounds(680, 395, 225, 100);
					cartPanel.add(successLabel);
					cartPanel.repaint();
					for (int i=0; i<cartTableModel.getRowCount();i++) {
						cartTableModel.setValueAt(0, i, 1);
						cartTableModel.setValueAt("0.00", i, 2);
					}
				}
				else
				{
					Collection c = buttonProductInfoMap.values();
					Iterator iterator = c.iterator();
					
					int index=0;
					while (iterator.hasNext()) {
						List<TableModel> temp = (List<TableModel>)iterator.next();
						JTextField qty = (JTextField) temp.get(1);
						qty.setText("0");
						index++;
					}
					
					if(successLabel != null) {
						System.out.println("SuccessLabel removed here!!");
						cartPanel.remove(successLabel);
						cartPanel.repaint();
					}
					
					failureLabel = new JLabel(orderStatus);
					failureLabel.setBounds(680, 395, 270, 100);
					cartPanel.add(failureLabel);
					cartPanel.repaint();
					
					for (int i=0; i<cartTableModel.getRowCount();i++) {
						cartTableModel.setValueAt(0, i, 1);
						cartTableModel.setValueAt("0.00", i, 2);
					}
				}
			}
		});
		
		
		 /* 
		 * **** Logout Button ****
		 */
		logout = new JButton("logout");
		logout.setBounds(605,555,70,22);
		cartPanel.add(logout);
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Logout Button clicked"); 
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		pane.add(cartPanel);
		
		
	} // End of addComponentsToPane()
	
	
	
	public void createAndShowGUI(boolean isRESTCall) {
		
		isRESTService = isRESTCall;
		System.out.println("STARTED: createAndShowUI");
    	
    	frame = new JFrame("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());
        frame.pack();

        Insets insets = frame.getInsets();
        frame.setSize(new Dimension(insets.left + insets.right + 1800,
                insets.top + insets.bottom + 600));
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("ENDED: createAndShowUI");
        
	} //End of createAndShowGUI()
	
	public static TableModel toTableModel(Map<String, Object> map) {
		DefaultTableModel model = new DefaultTableModel(new Object[] {"Key", "Value" }, 0);
		for (Iterator<Entry<String, Object>> it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)it.next();
			model.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Add to Cart button clicked");
		List productQtyList;
		JButton addToCartButton = (JButton)e.getSource();
		System.out.println("button:"+addToCartButton.getLabel());
		if (buttonProductInfoMap.containsKey(addToCartButton)) {
			productQtyList = (ArrayList)buttonProductInfoMap.get(addToCartButton);
			double price = 0;
			int qty = Integer.parseInt(((JTextField)productQtyList.get(1)).getText());
			if(isRESTService == false)
			{
				price = ((Product)productQtyList.get(0)).getPrice();
				System.out.println("Product "+((Product)productQtyList.get(0)).getProductName()+" added with quntity="+qty);
			}
			else
			{
				price = ((CartInfo)productQtyList.get(0)).getPrice();
				System.out.println("Product "+((CartInfo)productQtyList.get(0)).getProductName()+" added with quntity="+qty);
			}
			int index = (Integer)productQtyList.get(2);
			
			dataModel.setValueAt(qty, index, 1);
			dataModel.setValueAt(price*qty, index, 2);
		}
	}
}
