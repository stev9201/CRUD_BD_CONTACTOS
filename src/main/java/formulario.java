import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Contactos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class formulario extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    // Controlador para manejar operaciones relacionadas con contactos
    private ContactosController controller;

    // Componentes del formulario
    private JLabel texto1, texto2, texto3, texto4, lblResultado;
    private JTextField txtCorreo, txtTelefono, txtMensaje;
    private JButton botonEnviar, botonEditar, botonEliminar;
    private JPanel panel, formularioPanel, botonesPanel;
    private DefaultTableModel modeloTabla;
    private JTable tablaContactos;
    private JScrollPane scrollPane;

    public formulario() {
        // Inicializar el controlador de contactos
        controller = new ContactosController();

        // Configurar el JFrame
        setTitle("Formulario de Contacto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla

        // Inicializar componentes
        texto1 = new JLabel("Ingrese sus datos");
        texto1.setFont(new Font("Arial", Font.BOLD, 18));
        texto1.setForeground(Color.BLUE);
        texto1.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto

        texto2 = new JLabel("Correo:");
        texto3 = new JLabel("Teléfono:");
        texto4 = new JLabel("Mensaje:");

        txtCorreo = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtMensaje = new JTextField(20);

        botonEnviar = new JButton("Guardar petición");
        botonEnviar.setBackground(Color.BLUE);
        botonEnviar.setForeground(Color.WHITE);
        botonEnviar.setFocusPainted(false);
        botonEnviar.addActionListener(this);

        botonEditar = new JButton("Editar Petición");
        botonEditar.setBackground(Color.BLUE);
        botonEditar.setForeground(Color.WHITE);
        botonEditar.setFocusPainted(false);
        botonEditar.addActionListener(this);

        botonEliminar = new JButton("Eliminar Petición");
        botonEliminar.setBackground(Color.BLUE);
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setFocusPainted(false);
        botonEliminar.addActionListener(this);

        lblResultado = new JLabel();
        lblResultado.setForeground(Color.RED);

        // Configurar el panel principal
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.GREEN);  // Cambiar el color de fondo del panel

        // Configurar el panel del formulario con GridBagLayout
        formularioPanel = new JPanel(new GridBagLayout());
        formularioPanel.setBackground(Color.WHITE);
        formularioPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;

        // Añadir el título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formularioPanel.add(texto1, gbc);

        // Añadir etiquetas y campos de texto
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formularioPanel.add(texto2, gbc);

        gbc.gridx = 1;
        formularioPanel.add(txtCorreo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formularioPanel.add(texto3, gbc);

        gbc.gridx = 1;
        formularioPanel.add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formularioPanel.add(texto4, gbc);

        gbc.gridx = 1;
        formularioPanel.add(txtMensaje, gbc);

        // Configurar panel para los botones
        botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.add(botonEnviar);
        botonesPanel.add(botonEditar);
        botonesPanel.add(botonEliminar);

        // Añadir botones y etiqueta de resultado al panel de formulario
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formularioPanel.add(botonesPanel, gbc);

        gbc.gridy = 5;
        formularioPanel.add(lblResultado, gbc);

        // Configurar tabla de contactos
        String[] columnas = {"ID Requerimiento", "Correo Electrónico", "Teléfono", "Mensaje"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Evitar el redimensionamiento automático
        scrollPane = new JScrollPane(tablaContactos);
        scrollPane.setPreferredSize(new Dimension(460, 150));

        // Agregar formularioPanel y scrollPane al panel principal usando BorderLayout
        panel.add(formularioPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Agregar el panel al JFrame
        add(panel);
        setVisible(true);

        // Cargar contactos al iniciar la ventana
        cargarContactos();
    }

    // Método para cargar los contactos desde la base de datos y mostrarlos en la tabla
    private void cargarContactos() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Obtener lista de contactos desde el controlador
        List<Contactos> contactos = controller.consultarContactos();

        // Llenar tabla con los datos de los contactos
        for (Contactos contacto : contactos) {
            Object[] fila = {contacto.getIdRequerimiento(), contacto.getCorreoElectronico(),
                    contacto.getTelefonoContacto(), contacto.getMensaje()};
            modeloTabla.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    	//METODO DE INSERTAR
    	if (e.getSource() == botonEnviar) {
            // Obtener datos de los campos de texto
            String correo = txtCorreo.getText();
            String telefonoTexto = txtTelefono.getText();
            String mensaje = txtMensaje.getText();

            try {
                // Convertir texto de teléfono a entero
                int telefono = Integer.parseInt(telefonoTexto);

                // Llamar al método para crear un nuevo contacto
                controller.createContactos(correo, telefono, mensaje);
                lblResultado.setText("Peticion creada con éxito.");

                // Limpiar campos de texto después de guardar
                limpiarCampos();

                // Actualizar la tabla de contactos
                cargarContactos();

            } catch (NumberFormatException ex) {
                lblResultado.setText("Entrada no válida. Ingrese un número válido para teléfono.");
            }
        
        
        //METODO DE ACTUALIZACION
        } else if (e.getSource() == botonEditar) {
            // Implementar lógica para editar el contacto seleccionado en la tabla
            int filaSeleccionada = tablaContactos.getSelectedRow();
            
            if (filaSeleccionada != -1) {
            	
            	 int filaSeleccionadas = tablaContactos.getSelectedRow();
            	 
            	 
                 // Obtener el ID del requerimiento de la fila seleccionada
                 int idRequerimiento = (int) tablaContactos.getValueAt(filaSeleccionadas, 0);
                 String correo = (String) tablaContactos.getValueAt(filaSeleccionadas, 1);
                 int telefono = (int) tablaContactos.getValueAt(filaSeleccionadas, 2);
                 String mensaje = (String) tablaContactos.getValueAt(filaSeleccionadas, 3);
                 // Establecer los valores en los campos de texto
                
                 txtCorreo.setText(correo);
                 txtTelefono.setText(String.valueOf(telefono));
                 txtMensaje.setText(mensaje);

                 controller.UpdateContactos(idRequerimiento, correo, telefono, mensaje);
                 
                 // Limpiar campos de texto después de editar
                 limpiarCampos();

                 // Actualizar la tabla de contactos
                 cargarContactos();
                 

                // Implementar la lógica para la edición (pendiente)
                lblResultado.setText("Editar peticion con ID: " + idRequerimiento);
            } else {
                lblResultado.setText("Seleccione una peticion para editar.");
            }
        
        
        
        //METODO DE ELIMINACION
        } else if (e.getSource() == botonEliminar) {	
            // Implementar lógica para eliminar el contacto seleccionado en la tabla
            int filaSeleccionada = tablaContactos.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtener el ID del requerimiento de la fila seleccionada
                int idRequerimiento = (int) tablaContactos.getValueAt(filaSeleccionada, 0);

                controller.DeleteContactos(idRequerimiento);

                // Implementar la lógica para la eliminación (pendiente)
                lblResultado.setText("Se ha eliminado la peticion con ID: " + idRequerimiento);
            } else {
                lblResultado.setText("Seleccione una peticion para eliminar.");
            }
        }
    }

    // Método para limpiar los campos de texto después de guardar un contacto
    private void limpiarCampos() {
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtMensaje.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new formulario());
    }
}
