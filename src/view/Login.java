package view;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginController;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import model.dao.AgendamentoDAO;
import model.dao.ClienteDAO;
import model.dao.ServicoDAO;

/**
 *
 * @author Gabriel Braga
 */
public class Login extends javax.swing.JFrame {

    private final LoginController controller;

    public Login() {
        initComponents();
        this.setResizable(false);
        controller = new LoginController(this);
        AgendamentoDAO.obter_todos_agendamentos();
        ServicoDAO.obter_todos_servicos();
        ClienteDAO.obter_todos_clientes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campo_usuario = new javax.swing.JTextField();
        campo_senha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Login");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuário");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Senha");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, -1, -1));

        campo_usuario.setBackground(new java.awt.Color(102, 102, 102));
        campo_usuario.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        campo_usuario.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(campo_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 450, 40));

        campo_senha.setBackground(new java.awt.Color(102, 102, 102));
        campo_senha.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        campo_senha.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(campo_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 450, 40));

        btnEntrar.setBackground(new java.awt.Color(102, 102, 102));
        btnEntrar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, 450, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/painel-login.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Logo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 1100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        controller.entrar_no_sistema();
    }//GEN-LAST:event_btnEntrarActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JPasswordField campo_senha;
    private javax.swing.JTextField campo_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JPasswordField getCampoSenha() {
        return campo_senha;
    }

    public JTextField getCampoUsuario() {
        return campo_usuario;
    }

    public void exibir_mesagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "AVISO", JOptionPane.INFORMATION_MESSAGE);
    }

}