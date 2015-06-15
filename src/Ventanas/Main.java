/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Renders.PrecioRender;
import Clases.ArticuloList;
import Clases.ArticuloTableModel;
import BaseDatos.BaseDatos;
import Clases.Articulo;
import Clases.Categoria;
import Clases.CategoriaList;
import Clases.CategoriaTableModel;
import Clases.Cliente;
import Clases.ClienteList;
import Clases.ClienteTableModel;
import Clases.LineaVentaList;
import Clases.LineaVentaTableModel;
import Clases.Lineaventa;
import Clases.Marca;
import Clases.MarcaList;
import Clases.MarcaTableModel;
import Clases.Proveedor;
import Clases.ProveedorList;
import Clases.ProveedorTableModel;
import Clases.Venta;
import Clases.VentaList;
import Clases.VentaTableModel;
import Renders.ComboBoxRender;
import Renders.FechaRender;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Manuel Cañamaque
 */
public class Main extends javax.swing.JFrame {
    public static int rowSelected;
    public static int indexSelected;
    public static JTable[] tablas;
    public static AbstractTableModel[] modelosTablas;    
    private TableRowSorter filtro;
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        //Centrar la ventana
        this.setLocationRelativeTo(null);
        this.setTitle("Tienda");
        
        //Guardamos las tablas en un array.
        this.tablas = new JTable[]{tablaClientes, tablaProveedores, tablaCategorias, tablaMarcas, tablaArticulos, tablaLineasVentas, tablaVentas};
        
        //Guardamos las los modelos de tablas en un array.
        modelosTablas = new AbstractTableModel[]{new ClienteTableModel(), new ProveedorTableModel(), new CategoriaTableModel(), new MarcaTableModel(), new ArticuloTableModel(), new LineaVentaTableModel(), new VentaTableModel()};
        
        //Conectamos con la base de datos
        BaseDatos.conectar("jdbc:mysql://localhost/tienda", "root","");
        
        //Agregamos ordenacion automatica a las tablas
        for (JTable tabla : tablas) {
            tabla.setAutoCreateRowSorter(true);
        }
                
        //Inicializamos las tablas y las actualizamos.
        this.actualizarTablas();
        
        //Configuramos las tablas para que solo se seleccionar una linea.
        for (int i = 0; i < tablas.length; i++) {
            tablas[i].setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        //Deshabilitamos los botones de guardar y deshacer
        boton_Guardar.setEnabled(false);
        boton_Deshacer.setEnabled(false);
        
        //Añadimos los renders a los precios.
        tablaArticulos.getColumnModel().getColumn(6).setCellRenderer(new PrecioRender());
        tablaLineasVentas.getColumnModel().getColumn(4).setCellRenderer(new PrecioRender());
        tablaVentas.getColumnModel().getColumn(3).setCellRenderer(new PrecioRender());
        tablaVentas.getColumnModel().getColumn(4).setCellRenderer(new PrecioRender());
        
        //Añadimos los renders a las fechas
        tablaClientes.getColumnModel().getColumn(3).setCellRenderer(new FechaRender());
        tablaVentas.getColumnModel().getColumn(1).setCellRenderer(new FechaRender());
        
        //Añadimos los Listeners al JTabbedPane y a las tablas.
        this.addTabListeners();
        
        //Añadimos los comboBox a las tablas
        this.setTableComboBox();
        
        //Añadimos listeners para poder seleccionar filas con el click derecho
        this.addMouseListeners();
    }    
    
    //Metodo para buscar en una tabla, junto con el keyReleased del texto
    public void filtro() {
    filtro.setRowFilter(RowFilter.regexFilter(jTextField1.getText()));
    }
    
    public static int getRowSelected(){
        return rowSelected;
    }  

    //Metodo para editar una tabla o añadir un nuevo registro segun el boolean dado
    public void editarTabla(boolean conDatos){
        if(tablas[jTabbedPane1.getSelectedIndex()].getSelectedRow()>-1 && conDatos){
            new Editor(this,true,jTabbedPane1.getSelectedIndex(), conDatos).setVisible(true);
        }
        if(!conDatos){
           new Editor(this,true,jTabbedPane1.getSelectedIndex(), conDatos).setVisible(true); 
        }
    }
    
    //Metodo para añadir comboBox a las tablas
    public void setTableComboBox(){
        JComboBox comboBox;
        TableColumn columna;
        for (int i = 0; i < tablas.length; i++) {            
            switch(i){
                case 4:                    
                    comboBox = new JComboBox();
                    comboBox.setModel((new DefaultComboBoxModel(MarcaList.getArray())));
                    comboBox.setRenderer(new ComboBoxRender());
                    DefaultCellEditor editorMarca = new DefaultCellEditor(comboBox);
                    editorMarca.setClickCountToStart(2);
                    columna = tablas[i].getColumnModel().getColumn(2);                    
                    columna.setCellEditor(editorMarca);
                    columna.setCellRenderer(new DefaultTableCellRenderer());
                    
                    comboBox = new JComboBox();
                    comboBox.setModel((new DefaultComboBoxModel(CategoriaList.getArray())));
                    comboBox.setRenderer(new ComboBoxRender());
                    DefaultCellEditor editorCategoria = new DefaultCellEditor(comboBox);
                    editorCategoria.setClickCountToStart(2);
                    columna = tablas[i].getColumnModel().getColumn(3);                    
                    columna.setCellEditor(editorCategoria);
                    columna.setCellRenderer(new DefaultTableCellRenderer());
                    
                    comboBox = new JComboBox();
                    comboBox.setModel((new DefaultComboBoxModel(ProveedorList.getArray())));
                    comboBox.setRenderer(new ComboBoxRender());
                    DefaultCellEditor editorProveedor = new DefaultCellEditor(comboBox);
                    editorProveedor.setClickCountToStart(2);
                    columna = tablas[i].getColumnModel().getColumn(4);                    
                    columna.setCellEditor(editorProveedor);
                    columna.setCellRenderer(new DefaultTableCellRenderer());                    
                    break;
                case 5:
                    comboBox = new JComboBox();
                    comboBox.setModel((new DefaultComboBoxModel(VentaList.getArray())));
                    comboBox.setRenderer(new ComboBoxRender());
                    DefaultCellEditor editorVenta = new DefaultCellEditor(comboBox);
                    editorVenta.setClickCountToStart(2);
                    columna = tablas[i].getColumnModel().getColumn(1);                    
                    columna.setCellEditor(editorVenta);
                    columna.setCellRenderer(new DefaultTableCellRenderer());
                    
                    comboBox = new JComboBox();
                    comboBox.setModel((new DefaultComboBoxModel(ArticuloList.getArray())));
                    comboBox.setRenderer(new ComboBoxRender());
                    DefaultCellEditor editorArticulo = new DefaultCellEditor(comboBox);
                    editorArticulo.setClickCountToStart(2);
                    columna = tablas[i].getColumnModel().getColumn(2);                    
                    columna.setCellEditor(editorArticulo);
                    columna.setCellRenderer(new DefaultTableCellRenderer());                    
                    break;
                case 6:
                    comboBox = new JComboBox();
                    comboBox.setModel((new DefaultComboBoxModel(ClienteList.getArray())));
                    comboBox.setRenderer(new ComboBoxRender());
                    DefaultCellEditor editorCliente = new DefaultCellEditor(comboBox);
                    editorCliente.setClickCountToStart(2);
                    columna = tablas[i].getColumnModel().getColumn(0);                    
                    columna.setCellEditor(editorCliente);
                    columna.setCellRenderer(new DefaultTableCellRenderer());                   
                    break;
            }
        }   

    }
    
    //Metodo para añadir Listener al jTabbedPane y a la tabla que haya dentro.
    public void addTabListeners(){
        this.addSelectionListeners();
        this.addModifiedListeners();
        jTabbedPane1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                indexSelected=jTabbedPane1.getSelectedIndex();
                if(jTabbedPane1.getSelectedIndex()!=0){                    
                    addSelectionListeners();
                    addModifiedListeners();
                }
            }
        });
    }        

    
    //Metodo para añadir un Listener de fila seleccionada a la tabla actual
    public void addSelectionListeners(){ 
        tablas[jTabbedPane1.getSelectedIndex()].getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                rowSelected=tablas[jTabbedPane1.getSelectedIndex()].getSelectedRow();               
            }            
        });
    }
    
    // Metodo que añade listeners para poder seleccionar filas con el click derecho
    public void addMouseListeners(){
        for (int i = 0; i < tablas.length; i++) {
            final int tabla = i;
            tablas[i].addMouseListener( new MouseAdapter(){            
            @Override
            public void mousePressed( MouseEvent e ){                  
                if (SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isRightMouseButton( e )){
                    int rowNumber = tablas[tabla].rowAtPoint(e.getPoint());
                    tablas[tabla].getSelectionModel().setSelectionInterval( rowNumber, rowNumber );                    
		}
            }
        });
        }
    } 
    
    //Metodo para añadir un Listener de fila seleccionada a la tabla actual
    // si se modifica una fila, se desactivan los botones de insertar, editar y borrar.
    public void addModifiedListeners(){        
        tablas[indexSelected].addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if(tablas[indexSelected].isEditing()){
                    boton_Guardar.setEnabled(true);
                    boton_Deshacer.setEnabled(true);
                    EnableEditionButtons(false);
                }
            }
        });
    }
    public void EnableEditionButtons(boolean estado){
        for (int i = 0; i < jMenuBar1.getComponentCount(); i++) {
            jMenuBar1.getComponent(i).setEnabled(estado);
            jMenuBar1.setEnabled(estado);
            menu_Insertar.setEnabled(estado);
            menu_Borrar.setEnabled(estado);
            menu_Editar.setEnabled(estado);
        }
    }
    
    //Metodo para actualizar los arrayList desde la base de datos.
    public static void actualizarList(){
        ClienteList.updateClienteQuery(); 
        ProveedorList.updateProveedorQuery();
        CategoriaList.updateCategoriaQuery();
        MarcaList.updateMarcaQuery();
        ArticuloList.updateArticuloQuery();
        VentaList.updateVentaQuery();        
        LineaVentaList.updateLineaVentaQuery();
    }
    
    public void actualizarTablas(){ 
        //Cargamos los ArrayList con los datos de la Base de Datos        
        actualizarList();
        
        //Aplicamos los modelos de tablas
        for (int i = 0; i < tablas.length; i++) {
            tablas[i].setModel(modelosTablas[i]);
        }
        
        //Iniciamos la transaccion
        if(!BaseDatos.entityManager.getTransaction().isActive()){
            BaseDatos.entityManager.getTransaction().begin();
        }        
    }
    
    public void deleteRow() {
        int result = JOptionPane.showConfirmDialog(this, 
        "¿Está seguro de que desea borrar la fila seleccionada?","Borrar Fila", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){ 
            //Se inicia una transaccion si no esta iniciada.
            if(!BaseDatos.entityManager.getTransaction().isActive()){
                BaseDatos.entityManager.getTransaction().begin();
            }
            //Borramos el objeto segun la tabla seleccionada de la base de datos
            switch(indexSelected){
                case 0:
//                  Cliente cliente = BaseDatos.entityManager.find(Cliente.class, ClienteList.getClienteAt(rowSelected).getCodCliente());
                    Cliente cliente = ClienteList.getClienteAt(rowSelected);
                    BaseDatos.entityManager.remove(cliente);
                    break;
                case 1:
//                  Proveedor proveedor = BaseDatos.entityManager.find(Proveedor.class, ProveedorList.getProveedorAt(rowSelected).getCodProveedor());
                    Proveedor proveedor = ProveedorList.getProveedorAt(rowSelected);
                    BaseDatos.entityManager.remove(proveedor);
                    break;
                case 2:
//                  Categoria categoria = BaseDatos.entityManager.find(Categoria.class, CategoriaList.getCategoriaAt(rowSelected).getCodCategoria());
                    Categoria categoria = CategoriaList.getCategoriaAt(rowSelected);
                    BaseDatos.entityManager.remove(categoria);
                    break;
                case 3:
//                  Marca marca = BaseDatos.entityManager.find(Marca.class, MarcaList.getMarcaAt(rowSelected).getCodMarca());
                    Marca marca = MarcaList.getMarcaAt(rowSelected);
                    BaseDatos.entityManager.remove(marca);
                    break;
                case 4:
//                  Articulo articulo = BaseDatos.entityManager.find(Articulo.class, ArticuloList.getArticuloAt(rowSelected).getCodArticulo());
                    Articulo articulo = ArticuloList.getArticuloAt(rowSelected);
                    BaseDatos.entityManager.remove(articulo);
                    break;                
                case 5:
//                  Lineaventa lineaVenta= BaseDatos.entityManager.find(Lineaventa.class, LineaVentaList.getLineaVentaAt(rowSelected).getCodLineaVenta());
                    Lineaventa lineaVenta = LineaVentaList.getLineaVentaAt(rowSelected);
                    BaseDatos.entityManager.remove(lineaVenta);
                    break;
                case 6:
//                  Venta venta = BaseDatos.entityManager.find(Venta.class, VentaList.getVentaAt(rowSelected).getCodVenta());                    
                    Venta venta = VentaList.getVentaAt(rowSelected);
                    BaseDatos.entityManager.remove(venta);
                    break;
            }
            //Realizamos el commit de la transaccion y si no da error, borramos el objeto del arrayList
            try {
                BaseDatos.entityManager.getTransaction().commit();
                deleteFromListAt(indexSelected,rowSelected);
            } catch (Exception e) {            
                JOptionPane.showMessageDialog(this, "No se puede borrar el elemento seleccionado porque está siendo usado por otras tablas.", "Error al borrar", JOptionPane.ERROR_MESSAGE);
            }
            
            //Informamos al modelelo de tabla que hay cambios
            modelosTablas[indexSelected].fireTableRowsDeleted(rowSelected, rowSelected);
            
            //Comenzamos otra transaccion
            BaseDatos.entityManager.getTransaction().begin();
        }
}
    //Metodo para borrar un elemento del array.
    public void deleteFromListAt(int listNumber, int rowNumber){
        switch(listNumber){
            case 0:
                ClienteList.deleteClienteAt(rowNumber);
                break;
            case 1:
                ProveedorList.deleteProveedorAt(rowNumber);
                break;
            case 2:
                CategoriaList.deleteCategoriaAt(rowNumber);
                break;
            case 3:
                MarcaList.deleteMarcaAt(rowNumber);
                break;
            case 4:
                ArticuloList.deleteArticuloAt(rowNumber);
                break;
            case 5:
                LineaVentaList.deleteLineaVentaAt(rowNumber);
                break;
            case 6:
                VentaList.deleteVentaAt(rowNumber);
                break;
        }
    }
    
    //Metodo para actualziar los arrayList segund un parametro dado.
    public void actualizarList(int listNumber){
        switch(listNumber){
            case 0:
                ClienteList.updateClienteQuery();
                break;
            case 1:
                ProveedorList.updateProveedorQuery(); 
                break;
            case 2:
                CategoriaList.updateCategoriaQuery();
                break;
            case 3:
                MarcaList.updateMarcaQuery();
                break;
            case 4:
                ArticuloList.updateArticuloQuery();
                break;
            case 5:
                LineaVentaList.updateLineaVentaQuery();
                break;
            case 6:
                VentaList.updateVentaQuery();
                break;
            default:
                ClienteList.updateClienteQuery();
                ProveedorList.updateProveedorQuery();        
                CategoriaList.updateCategoriaQuery();        
                MarcaList.updateMarcaQuery();        
                ArticuloList.updateArticuloQuery();
                LineaVentaList.updateLineaVentaQuery();
                VentaList.updateVentaQuery();                
        }
    }  
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menu_Insertar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menu_Editar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menu_Borrar = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCategorias = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMarcas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaArticulos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaLineasVentas = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        boton_Guardar = new javax.swing.JButton();
        boton_Deshacer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        boton_Insertar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuBar_Nuevo = new javax.swing.JMenu();
        boton_Nuevo_Cliente = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        boton_Nuevo_Proveedor = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        boton_Nueva_Categoria = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        boton_Nueva_Marca = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        boton_Nuevo_Articulo = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        boton_Nueva_LineaVenta = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        boton_Nueva_Venta = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        boton_Editar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        boton_Borrar = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        boton_Consulta = new javax.swing.JMenuItem();

        menu_Insertar.setText("Insertar Fila Nueva");
        menu_Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_InsertarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menu_Insertar);
        jPopupMenu1.add(jSeparator1);

        menu_Editar.setText("Editar Fila");
        menu_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_EditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menu_Editar);
        jPopupMenu1.add(jSeparator2);

        menu_Borrar.setText("Borrar Fila");
        menu_Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_BorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menu_Borrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaClientes.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tablaClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Clientes", jPanel1);

        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaProveedores.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tablaProveedores);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Proovedores", jPanel2);

        tablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaCategorias.setComponentPopupMenu(jPopupMenu1);
        jScrollPane3.setViewportView(tablaCategorias);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 316, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Categorias", jPanel3);

        tablaMarcas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaMarcas.setComponentPopupMenu(jPopupMenu1);
        jScrollPane4.setViewportView(tablaMarcas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 316, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Marcas", jPanel4);

        tablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaArticulos.setComponentPopupMenu(jPopupMenu1);
        jScrollPane5.setViewportView(tablaArticulos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Articulos", jPanel5);

        tablaLineasVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaLineasVentas.setComponentPopupMenu(jPopupMenu1);
        jScrollPane6.setViewportView(tablaLineasVentas);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("LineasVentas", jPanel6);

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaVentas.setComponentPopupMenu(jPopupMenu1);
        jScrollPane7.setViewportView(tablaVentas);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ventas", jPanel7);

        boton_Guardar.setText("Guardar cambios");
        boton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_GuardarActionPerformed(evt);
            }
        });

        boton_Deshacer.setText("Deshacer cambios");
        boton_Deshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_DeshacerActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_Guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton_Deshacer)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton_Guardar)
                        .addComponent(boton_Deshacer))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jMenu1.setText("Insertar");

        boton_Insertar.setText("Fila Nueva");
        boton_Insertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_InsertarMouseClicked(evt);
            }
        });
        boton_Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_InsertarActionPerformed(evt);
            }
        });
        jMenu1.add(boton_Insertar);
        jMenu1.add(jSeparator3);

        menuBar_Nuevo.setText("Nuevo");

        boton_Nuevo_Cliente.setText("Cliente");
        boton_Nuevo_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nuevo_ClienteActionPerformed(evt);
            }
        });
        menuBar_Nuevo.add(boton_Nuevo_Cliente);
        menuBar_Nuevo.add(jSeparator4);

        boton_Nuevo_Proveedor.setText("Proveedor");
        boton_Nuevo_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nuevo_ProveedorActionPerformed(evt);
            }
        });
        menuBar_Nuevo.add(boton_Nuevo_Proveedor);
        menuBar_Nuevo.add(jSeparator5);

        boton_Nueva_Categoria.setText("Categoria");
        boton_Nueva_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nueva_CategoriaActionPerformed(evt);
            }
        });
        menuBar_Nuevo.add(boton_Nueva_Categoria);
        menuBar_Nuevo.add(jSeparator6);

        boton_Nueva_Marca.setText("Marca");
        boton_Nueva_Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nueva_MarcaActionPerformed(evt);
            }
        });
        menuBar_Nuevo.add(boton_Nueva_Marca);
        menuBar_Nuevo.add(jSeparator9);

        boton_Nuevo_Articulo.setText("Articulo");
        boton_Nuevo_Articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nuevo_ArticuloActionPerformed(evt);
            }
        });
        menuBar_Nuevo.add(boton_Nuevo_Articulo);
        menuBar_Nuevo.add(jSeparator8);

        boton_Nueva_LineaVenta.setText("LineaVenta");
        boton_Nueva_LineaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nueva_LineaVentaActionPerformed(evt);
            }
        });
        menuBar_Nuevo.add(boton_Nueva_LineaVenta);
        menuBar_Nuevo.add(jSeparator7);

        boton_Nueva_Venta.setText("Venta");
        boton_Nueva_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nueva_VentaActionPerformed(evt);
            }
        });
        menuBar_Nuevo.add(boton_Nueva_Venta);

        jMenu1.add(menuBar_Nuevo);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        boton_Editar.setText("Fila Seleccionada");
        boton_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_EditarActionPerformed(evt);
            }
        });
        jMenu2.add(boton_Editar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Borrar");

        boton_Borrar.setText("Fila Seleccionada");
        boton_Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_BorrarActionPerformed(evt);
            }
        });
        jMenu3.add(boton_Borrar);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Ejecutar");

        boton_Consulta.setText("Sentencia SQL");
        boton_Consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ConsultaActionPerformed(evt);
            }
        });
        jMenu6.add(boton_Consulta);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_GuardarActionPerformed
        BaseDatos.entityManager.getTransaction().commit();
        BaseDatos.entityManager.getTransaction().begin();
        boton_Guardar.setEnabled(false);
        boton_Deshacer.setEnabled(false);
        EnableEditionButtons(true);
    }//GEN-LAST:event_boton_GuardarActionPerformed

    private void boton_DeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_DeshacerActionPerformed
        BaseDatos.entityManager.getTransaction().rollback();
        actualizarList(-1);
        for (int i = 0; i < modelosTablas.length; i++) {
            modelosTablas[i].fireTableDataChanged();
        }
        BaseDatos.entityManager.getTransaction().begin();
        boton_Guardar.setEnabled(false);
        boton_Deshacer.setEnabled(false);
        EnableEditionButtons(true);
    }//GEN-LAST:event_boton_DeshacerActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int result = JOptionPane.showConfirmDialog(this, 
        "¿Está seguro de que desea cerrar la aplicación?\nPuede haber cambios que no se hayan guardado.","Cerrar Aplicación", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            BaseDatos.entityManager.close();
            System.exit(0);
        }
        
    }//GEN-LAST:event_formWindowClosing

    private void menu_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_InsertarActionPerformed
        if(jMenuBar1.isEnabled() && tablas[indexSelected].isRowSelected(rowSelected)){
            this.editarTabla(false);
        }
    }//GEN-LAST:event_menu_InsertarActionPerformed

    private void menu_BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_BorrarActionPerformed
        if(jMenuBar1.isEnabled() && tablas[indexSelected].isRowSelected(rowSelected)){
            this.deleteRow();
        } 
    }//GEN-LAST:event_menu_BorrarActionPerformed

    private void boton_Nuevo_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nuevo_ClienteActionPerformed
        jTabbedPane1.setSelectedIndex(0);        
        this.editarTabla(false);
    }//GEN-LAST:event_boton_Nuevo_ClienteActionPerformed

    private void boton_Nuevo_ProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nuevo_ProveedorActionPerformed
        jTabbedPane1.setSelectedIndex(1);        
        this.editarTabla(false);
    }//GEN-LAST:event_boton_Nuevo_ProveedorActionPerformed

    private void boton_Nueva_CategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nueva_CategoriaActionPerformed
        jTabbedPane1.setSelectedIndex(2);        
        this.editarTabla(false);
    }//GEN-LAST:event_boton_Nueva_CategoriaActionPerformed

    private void boton_Nueva_MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nueva_MarcaActionPerformed
        jTabbedPane1.setSelectedIndex(3);        
        this.editarTabla(false);
    }//GEN-LAST:event_boton_Nueva_MarcaActionPerformed

    private void boton_Nuevo_ArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nuevo_ArticuloActionPerformed
        jTabbedPane1.setSelectedIndex(4);        
        this.editarTabla(false);
    }//GEN-LAST:event_boton_Nuevo_ArticuloActionPerformed

    private void boton_Nueva_LineaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nueva_LineaVentaActionPerformed
        jTabbedPane1.setSelectedIndex(5);        
        this.editarTabla(false);
    }//GEN-LAST:event_boton_Nueva_LineaVentaActionPerformed

    private void boton_Nueva_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nueva_VentaActionPerformed
        jTabbedPane1.setSelectedIndex(6);        
        this.editarTabla(false);
    }//GEN-LAST:event_boton_Nueva_VentaActionPerformed

    private void menu_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_EditarActionPerformed
        if(jMenuBar1.isEnabled() && tablas[indexSelected].isRowSelected(rowSelected)){
            this.editarTabla(true);
        }
    }//GEN-LAST:event_menu_EditarActionPerformed

    private void boton_InsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_InsertarMouseClicked
        if(jMenuBar1.isEnabled() && tablas[indexSelected].isRowSelected(rowSelected)){
            this.editarTabla(false);
        }
    }//GEN-LAST:event_boton_InsertarMouseClicked

    private void boton_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_EditarActionPerformed
        if(jMenuBar1.isEnabled() && tablas[indexSelected].isRowSelected(rowSelected)){
            this.editarTabla(true);
        }
    }//GEN-LAST:event_boton_EditarActionPerformed

    private void boton_BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_BorrarActionPerformed
        jMenu3.setSelected(false);
        if(jMenuBar1.isEnabled() && tablas[indexSelected].isRowSelected(rowSelected)){
            this.deleteRow();
        } 
    }//GEN-LAST:event_boton_BorrarActionPerformed

    private void boton_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_InsertarActionPerformed
        this.editarTabla(false);
    }//GEN-LAST:event_boton_InsertarActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        jTextField1.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {        
                filtro();
            }
        });
        filtro = new TableRowSorter(tablas[indexSelected].getModel());
        tablas[indexSelected].setRowSorter(filtro);
    }//GEN-LAST:event_jTextField1KeyTyped

    private void boton_ConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ConsultaActionPerformed
        new EditorComandos(this,true).setVisible(true);
    }//GEN-LAST:event_boton_ConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem boton_Borrar;
    private javax.swing.JMenuItem boton_Consulta;
    private javax.swing.JButton boton_Deshacer;
    private javax.swing.JMenuItem boton_Editar;
    private javax.swing.JButton boton_Guardar;
    private javax.swing.JMenuItem boton_Insertar;
    private javax.swing.JMenuItem boton_Nueva_Categoria;
    private javax.swing.JMenuItem boton_Nueva_LineaVenta;
    private javax.swing.JMenuItem boton_Nueva_Marca;
    private javax.swing.JMenuItem boton_Nueva_Venta;
    private javax.swing.JMenuItem boton_Nuevo_Articulo;
    private javax.swing.JMenuItem boton_Nuevo_Cliente;
    private javax.swing.JMenuItem boton_Nuevo_Proveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu menuBar_Nuevo;
    private javax.swing.JMenuItem menu_Borrar;
    private javax.swing.JMenuItem menu_Editar;
    private javax.swing.JMenuItem menu_Insertar;
    private javax.swing.JTable tablaArticulos;
    private javax.swing.JTable tablaCategorias;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaLineasVentas;
    private javax.swing.JTable tablaMarcas;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTable tablaVentas;
    // End of variables declaration//GEN-END:variables

}
