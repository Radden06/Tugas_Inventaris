/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package toko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author adity
 */
public class Form_Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form Form_Transaksi
     */
    Connection conn = Koneksi.GetConnection();
    String sql = "";
    ResultSet rs;
    String FakturCode = "";

    public Form_Transaksi() {
        initComponents();
        setLocationRelativeTo(this);
        FakturCode = generateFakturCode();
        txt_faktur.setText(FakturCode);
        GetData();

    }

    public void GetData() {
        FakturCode = generateFakturCode();
        txt_faktur.setText(FakturCode);
        try {

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("No");
            tbl.addColumn("Id Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Harga");
            tbl.addColumn("Qty");
            tbl.addColumn("Total Harga");
            tbl_barang.setModel(tbl);

            String sql = "select * from tb_sales where kode_faktur = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, FakturCode);

            rs = ps.executeQuery();

            int i = 0;
            while (rs.next()) {
                i++;
                tbl.addRow(new Object[]{
                    i,
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("harga_barang"),
                    rs.getString("jumlah"),
                    rs.getString("total")});
            }
            tbl_barang.setModel(tbl);
            hitungTotal();

        } catch (Exception e) {
        }

    }

    public String generateFakturCode() {
        String fakturCode = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(new Date());

            String sql = "SELECT MAX(id_faktur) FROM tb_faktur WHERE id_faktur LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "F" + currentDate + "%");
            ResultSet rs = ps.executeQuery();

            int lastNumber = 0;
            if (rs.next()) {
                String lastFaktur = rs.getString(1); 
                if (lastFaktur != null && !lastFaktur.isEmpty()) {
                    lastNumber = Integer.parseInt(lastFaktur.substring(10));
                }
            }

            int newNumber = lastNumber + 1;
            String formattedNumber = String.format("%03d", newNumber);

            fakturCode = "F" + currentDate + "-" + formattedNumber;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fakturCode;
    }

    public void clearForm() {
        txt_date.setText("");
        txt_faktur.setText("");
        in_harga.setText("");
        in_harga_total.setText("");
        in_id.setText("");
        in_nama.setText("");
        in_qty.setText("");
        o_total_harga.setText("");
        o_kembali.setText("");
        o_jumlah_harga.setText("");
        o_jumlah_uang.setText("");

    }

    private void hitungTotal() {
        int total = 0;

        for (int i = 0; i < tbl_barang.getRowCount(); i++) {
            int hargaObj = Integer.valueOf(tbl_barang.getValueAt(i, 5).toString());

            total += (Integer) hargaObj;  // Jumlahkan harga

        }

        o_total_harga.setText("Rp. " + formatRupiah(total));
        o_jumlah_harga.setText(total + "");
    }

    public static String formatRupiah(double amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return numberFormat.format(amount);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserPanel1 = new datechooser.beans.DateChooserPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_barang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        o_jumlah_uang = new javax.swing.JTextField();
        o_jumlah_harga = new javax.swing.JTextField();
        o_kembali = new javax.swing.JTextField();
        o_total_harga = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txt_date = new java.awt.Label();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_faktur = new java.awt.Label();
        in_id = new javax.swing.JTextField();
        in_nama = new javax.swing.JTextField();
        in_harga = new javax.swing.JTextField();
        in_qty = new javax.swing.JTextField();
        in_harga_total = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_barang.setBackground(new java.awt.Color(204, 204, 204));
        tbl_barang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbl_barang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_barang);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("Beli");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Batal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Jumlah Harga    :");

        jLabel5.setText("Jumlah Uang     :");

        jLabel6.setText("Kembali              :");

        jLabel8.setText("item yang dibeli  :");

        o_jumlah_uang.setBackground(new java.awt.Color(255, 255, 255));
        o_jumlah_uang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        o_jumlah_uang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                o_jumlah_uangKeyTyped(evt);
            }
        });

        o_jumlah_harga.setEditable(false);
        o_jumlah_harga.setBackground(new java.awt.Color(255, 255, 255));
        o_jumlah_harga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        o_kembali.setEditable(false);
        o_kembali.setBackground(new java.awt.Color(255, 255, 255));
        o_kembali.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        o_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o_kembaliActionPerformed(evt);
            }
        });

        o_total_harga.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLabel7.setText("Tanggal   :");

        jLabel10.setText("Rp.");

        jLabel11.setText("Rp.");

        jLabel12.setText("Rp.");

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tambah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txt_date.setText("-");

        jLabel9.setBackground(new java.awt.Color(204, 204, 255));
        jLabel9.setFont(new java.awt.Font("Coolvetica Hv", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("My Casseer");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("ID Barang    :");

        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("No. Faktur   :");

        txt_faktur.setForeground(new java.awt.Color(51, 51, 51));
        txt_faktur.setText("-");

        in_id.setBackground(new java.awt.Color(255, 255, 255));
        in_id.setForeground(new java.awt.Color(102, 102, 102));
        in_id.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                in_idInputMethodTextChanged(evt);
            }
        });
        in_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_idActionPerformed(evt);
            }
        });
        in_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                in_idKeyTyped(evt);
            }
        });

        in_nama.setEditable(false);
        in_nama.setBackground(new java.awt.Color(255, 255, 255));
        in_nama.setForeground(new java.awt.Color(102, 102, 102));
        in_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_namaActionPerformed(evt);
            }
        });

        in_harga.setEditable(false);
        in_harga.setBackground(new java.awt.Color(255, 255, 255));
        in_harga.setForeground(new java.awt.Color(102, 102, 102));
        in_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_hargaActionPerformed(evt);
            }
        });

        in_qty.setBackground(new java.awt.Color(255, 255, 255));
        in_qty.setForeground(new java.awt.Color(102, 102, 102));
        in_qty.setText("1");
        in_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                in_qtyKeyTyped(evt);
            }
        });

        in_harga_total.setEditable(false);
        in_harga_total.setBackground(new java.awt.Color(255, 255, 255));
        in_harga_total.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(in_id, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_harga_total, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txt_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(in_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(in_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(in_harga_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(in_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(in_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jMenu1.setText("Data Barang");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transaksi");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Inventaris");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Report");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(331, 331, 331)
                                        .addComponent(o_total_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(o_kembali, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(o_jumlah_harga))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(o_jumlah_uang)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(o_total_harga))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(o_jumlah_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(o_jumlah_uang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(o_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new DataBarang().show();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Form_Transaksi().show();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Inventaris().show();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Report_Penjualan().show();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BELIII

        if (o_jumlah_uang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jumlah uang tidak boleh kosong.");
        } else if (Integer.valueOf(o_jumlah_uang.getText()) < Integer.valueOf(o_jumlah_harga.getText())) {
            JOptionPane.showMessageDialog(null, "Uang kurang. Uang Anda: " + o_jumlah_uang.getText() + ", Total harga: " + o_jumlah_harga.getText());
        } else {
            try {
                String sql = "insert into tb_faktur (id_faktur) values (?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, FakturCode);

                ps.executeUpdate();
                FakturCode = generateFakturCode();
                GetData();
                clearForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void o_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o_kembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_o_kembaliActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TAMBAH

        try {
            if (FakturCode.isEmpty()
                    || in_id.getText().isEmpty()
                    || in_nama.getText().isEmpty()
                    || in_harga.getText().isEmpty()
                    || in_qty.getText().isEmpty()
                    ||in_qty.getText().equals("0") 
                    || in_harga_total.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
                return;
            }

            sql = "insert into tb_sales (kode_faktur, id_barang, nama_barang, harga_barang, jumlah, total, status_pembayaran) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, FakturCode);
            ps.setString(2, in_id.getText());
            ps.setString(3, in_nama.getText());
            ps.setString(4, in_harga.getText());
            ps.setString(5, in_qty.getText());
            ps.setString(6, in_harga_total.getText());
            ps.setString(7, "belum dibayar");

            ps.executeUpdate();
            clearForm();
            GetData();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void o_jumlah_uangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_o_jumlah_uangKeyTyped
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (Integer.valueOf(o_jumlah_harga.getText()) <= Integer.valueOf(o_jumlah_uang.getText())) {
                    o_kembali.setText((Integer.valueOf(o_jumlah_uang.getText()) - Integer.valueOf(o_jumlah_harga.getText())) + "");

                } else {
                    o_kembali.setText("Uang Kurang");
                }
            }
        });
        timer.setRepeats(false);  
        timer.start();
    }//GEN-LAST:event_o_jumlah_uangKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BATAL

        try {
            if (FakturCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kode faktur tidak boleh kosong.");
            } else {
                String sql = "select * from tb_sales where kode_faktur = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, FakturCode);
                rs = ps.executeQuery();

                if (!rs.isBeforeFirst()) { // Memeriksa apakah ada hasil
                    JOptionPane.showMessageDialog(null, "Tidak ada data yang ditemukan untuk kode faktur: " + FakturCode);
                } else {
                    while (rs.next()) {
                        String sqls = "UPDATE tb_barang SET stok = stok + ? WHERE tb_barang.id = ?";
                        PreparedStatement pss = conn.prepareStatement(sqls);
                        pss.setString(1, rs.getString("jumlah"));
                        pss.setString(2, rs.getString("id_barang"));
                        pss.executeUpdate();
                    }

                    // Menampilkan dialog konfirmasi sebelum menghapus data
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Apakah Anda yakin ingin membatalkan transaksi dengan kode faktur " + FakturCode + "?",
                            "Konfirmasi ",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (confirm == JOptionPane.YES_OPTION) {
                        String sqls2 = "DELETE FROM tb_sales WHERE tb_sales.kode_faktur = ?";
                        PreparedStatement pss2 = conn.prepareStatement(sqls2);
                        pss2.setString(1, FakturCode);
                        pss2.executeUpdate();

                        GetData();
                        clearForm();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void in_idInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_in_idInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_in_idInputMethodTextChanged

    private void in_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_idActionPerformed

    private void in_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_idKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_in_idKeyTyped

    private void in_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_qtyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_in_qtyKeyTyped

    private void in_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_namaActionPerformed

    private void in_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_hargaActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserPanel dateChooserPanel1;
    private javax.swing.JTextField in_harga;
    private javax.swing.JTextField in_harga_total;
    private javax.swing.JTextField in_id;
    private javax.swing.JTextField in_nama;
    private javax.swing.JTextField in_qty;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField o_jumlah_harga;
    private javax.swing.JTextField o_jumlah_uang;
    private javax.swing.JTextField o_kembali;
    private javax.swing.JLabel o_total_harga;
    private javax.swing.JTable tbl_barang;
    private java.awt.Label txt_date;
    private java.awt.Label txt_faktur;
    // End of variables declaration//GEN-END:variables
}
