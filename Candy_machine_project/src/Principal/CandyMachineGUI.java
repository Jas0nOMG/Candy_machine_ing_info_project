package Principal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class CandyMachineGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMonedas;
	private JTextArea txtDisplay;

	// --- INSTANCIAS DE TU LÓGICA ORIGINAL ---
	private CashRegister cashRegister = new CashRegister();
	private Dispenser candy = new Dispenser(100, 50);
	private Dispenser chips = new Dispenser(100, 65);
	private Dispenser gum = new Dispenser(75, 45);
	private Dispenser cookies = new Dispenser(100, 85);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CandyMachineGUI frame = new CandyMachineGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CandyMachineGUI() {
		setTitle("Shelly's Candy Shop - GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); // Layout absoluto para diseño libre

		// Título
		JLabel lblTitulo = new JLabel("Candy Machine");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(10, 11, 414, 30);
		contentPane.add(lblTitulo);

		// Botones de Productos
		JButton btnCandy = new JButton("Candy (50¢)");
		btnCandy.setBounds(40, 60, 150, 40);
		btnCandy.addActionListener(e -> realizarVenta(candy, "Candy"));
		contentPane.add(btnCandy);

		JButton btnChips = new JButton("Chips (65¢)");
		btnChips.setBounds(240, 60, 150, 40);
		btnChips.addActionListener(e -> realizarVenta(chips, "Chips"));
		contentPane.add(btnChips);

		JButton btnGum = new JButton("Gum (45¢)");
		btnGum.setBounds(40, 111, 150, 40);
		btnGum.addActionListener(e -> realizarVenta(gum, "Gum"));
		contentPane.add(btnGum);

		JButton btnCookies = new JButton("Cookies (85¢)");
		btnCookies.setBounds(240, 111, 150, 40);
		btnCookies.addActionListener(e -> realizarVenta(cookies, "Cookies"));
		contentPane.add(btnCookies);

		// Entrada de dinero
		JLabel lblMonedas = new JLabel("Ingresa centavos:");
		lblMonedas.setBounds(40, 180, 110, 20);
		contentPane.add(lblMonedas);

		txtMonedas = new JTextField();
		txtMonedas.setBounds(150, 180, 86, 20);
		contentPane.add(txtMonedas);
		txtMonedas.setColumns(10);

		// Pantalla (Display)
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 220, 414, 150);
		contentPane.add(scrollPane);

		txtDisplay = new JTextArea();
		txtDisplay.setBackground(Color.BLACK);
		txtDisplay.setForeground(Color.GREEN);
		txtDisplay.setEditable(false);
		txtDisplay.setText("Bienvenido!\nSelecciona un producto e ingresa el monto.");
		scrollPane.setViewportView(txtDisplay);

		// Botón Inventario
		JButton btnInventario = new JButton("Ver Estado de Máquina");
		btnInventario.setBounds(10, 390, 414, 30);
		btnInventario.addActionListener(e -> verEstado());
		contentPane.add(btnInventario);
	}

	// --- MÉTODOS DE LÓGICA ADAPTADOS ---

	private void realizarVenta(Dispenser producto, String nombre) {
		try {
			int precio = producto.getProductCost();
			String input = txtMonedas.getText();

			if (input.isEmpty()) {
				txtDisplay.setText("[!] Por favor, ingresa monedas en el cuadro de texto.");
				return;
			}

			int dineroIngresado = Integer.parseInt(input);

			if (producto.getCount() > 0) {
				if (dineroIngresado >= precio) {
					int cambio = dineroIngresado - precio;
					producto.makeSale();
					cashRegister.acceptAmount(precio);

					txtDisplay.setText("ENTREGANDO: " + nombre);
					txtDisplay.append("\nCosto: " + precio + "¢");
					txtDisplay.append("\nCambio: " + cambio + "¢");
					txtDisplay.append("\n\n¡Gracias por su compra!");
					txtMonedas.setText(""); // Limpiar para la siguiente
				} else {
					txtDisplay.setText("DINERO INSUFICIENTE\n");
					txtDisplay.append("Faltan " + (precio - dineroIngresado) + " centavos.");
				}
			} else {
				txtDisplay.setText("AGOTADO: El producto " + nombre + " no está disponible.");
			}
		} catch (NumberFormatException ex) {
			txtDisplay.setText("[ERROR] Solo se permiten números enteros.");
		}
	}

	private void verEstado() {
		StringBuilder sb = new StringBuilder();
		sb.append("=== ESTADO DEL INVENTARIO ===\n");
		sb.append("Caja: $").append(cashRegister.currentBalance() / 100.0).append("\n");
		sb.append("Candy: ").append(candy.getCount()).append(" unid.\n");
		sb.append("Chips: ").append(chips.getCount()).append(" unid.\n");
		sb.append("Gum: ").append(gum.getCount()).append(" unid.\n");
		sb.append("Cookies: ").append(cookies.getCount()).append(" unid.\n");
		txtDisplay.setText(sb.toString());
	}
}