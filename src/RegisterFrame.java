
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.*;
import nl.captcha.Captcha;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author F3ZLoV
 */

public class RegisterFrame extends javax.swing.JFrame {
    private CaptchaGenerator captchaGenerator;
    private Captcha currentCaptcha;
    /**
     * Creates new form MainFrame
     */
    public RegisterFrame() {
        captchaGenerator = new CaptchaGenerator();
        initComponents();
        refreshCaptcha();
    }
    
    private void refreshCaptcha() {
        currentCaptcha = new Captcha.Builder(200, 50)
                .addText(new DefaultTextProducer())
                .addBackground(new GradiatedBackgroundProducer())
                .gimp()
                .build();

        lblCaptchaImage.setIcon(new ImageIcon(currentCaptcha.getImage()));
    }
    
    private boolean validateCaptcha(String userInput) {
        return currentCaptcha.isCorrect(userInput);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPassword = new javax.swing.JPasswordField();
        txtPasswordCheck = new javax.swing.JPasswordField();
        lblRegister = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblPasswordCheck = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        btnIDDupCheck = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        lblCaptchaImage = new javax.swing.JLabel();
        txtCaptchaInput = new javax.swing.JTextField();
        btnRefreshCaptcha = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("은행 계좌 관리 시스템");

        lblRegister.setText("회원 가입");

        lblID.setText("아이디");

        lblPassword.setText("비밀번호");

        lblPasswordCheck.setText("비밀번호 확인");

        lblName.setText("성명");

        txtName.setToolTipText("");

        btnIDDupCheck.setText("중복 확인");
        btnIDDupCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIDDupCheckActionPerformed(evt);
            }
        });

        btnRegister.setText("회원 가입");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnRefreshCaptcha.setText("새로고침");
        btnRefreshCaptcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshCaptchaActionPerformed(evt);
            }
        });

        btnBack.setText("닫기");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPasswordCheck)
                                    .addComponent(lblPassword)
                                    .addComponent(lblName))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnIDDupCheck))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtPassword)
                                                .addComponent(txtPasswordCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblCaptchaImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCaptchaInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnRefreshCaptcha))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(lblRegister))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(btnRegister)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblRegister)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIDDupCheck))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPasswordCheck)
                    .addComponent(txtPasswordCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblCaptchaImage, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaptchaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefreshCaptcha))
                .addGap(77, 77, 77)
                .addComponent(btnRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIDDupCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIDDupCheckActionPerformed
        String inputId = txtID.getText();

        if (inputId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
            return;
        }

        boolean isDup = false;
        DB_MAN db = new DB_MAN();
        try {
            db.dbOpen();
            isDup = db.checkDuplicateId(inputId);
            db.dbClose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DB 오류 : " + e.getMessage());
            return;
        }

        if (isDup) {
            JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.");
        } else {
            JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
        }
    }//GEN-LAST:event_btnIDDupCheckActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String idData = txtID.getText();
        String pwData = new String(txtPassword.getPassword());
        String pwConf = new String(txtPasswordCheck.getPassword());
        String nameData = txtName.getText();
        String captchaInput = txtCaptchaInput.getText();

        if (idData.isEmpty() || pwData.isEmpty() || pwConf.isEmpty() || nameData.isEmpty() || captchaInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "모든 필드를 채워주세요.");
            return;
        }

        if (!pwData.equals(pwConf)) {
            JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
            return;
        }

        if (!validateCaptcha(captchaInput)) {
            JOptionPane.showMessageDialog(null, "CAPTCHA가 일치하지 않습니다.");
            return;
        }

        // DB 작업
        DB_MAN db = new DB_MAN();
        try {
            db.dbOpen();
            if (db.checkDuplicateId(idData)) {
                JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.");
            } else {
                db.insertMember(idData, pwData, nameData);
                JOptionPane.showMessageDialog(null, "회원 가입 완료!");
            }
            db.dbClose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DB 오류 : " + e.getMessage());
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnRefreshCaptchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshCaptchaActionPerformed
        lblCaptchaImage.setIcon(captchaGenerator.generateCaptcha());
        txtCaptchaInput.setText(""); // 입력 필드 초기화
    }//GEN-LAST:event_btnRefreshCaptchaActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
        MainFrame mainFrame = new MainFrame ();
        mainFrame.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnIDDupCheck;
    private javax.swing.JButton btnRefreshCaptcha;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblCaptchaImage;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordCheck;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JTextField txtCaptchaInput;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordCheck;
    // End of variables declaration//GEN-END:variables
}
