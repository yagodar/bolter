/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yagodar.bolter.view;

import java.awt.Desktop;
import java.awt.Frame;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author АППДКт78М
 */
public class AboutDialog extends javax.swing.JDialog {

    /**
     * Creates new form AboutDialog
     *
     * @param parent
     */
    public AboutDialog(Frame parent) {
        super(parent);

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jButtonOk = new javax.swing.JButton();
        jLabelApp = new javax.swing.JLabel();
        jLabelVersion = new javax.swing.JLabel();
        jLabelBolterLink = new javax.swing.JLabel();
        jLabelAuthorLink = new javax.swing.JLabel();
        jLabelAuthor = new javax.swing.JLabel();
        jLabelAuthorEmail = new javax.swing.JLabel();
        jLabelDonateText = new javax.swing.JLabel();
        jLabelDonateWebMoney = new javax.swing.JLabel();
        jTextFieldWebMonerWallet = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelAppInfo1 = new javax.swing.JLabel();
        jLabelAppInfo2 = new javax.swing.JLabel();
        jLabelAppInfoYandexLink = new javax.swing.JLabel();
        jLabelAppInfo3 = new javax.swing.JLabel();
        jLabelGoogleLink = new javax.swing.JLabel();
        jLabelAppInfo4 = new javax.swing.JLabel();
        jLabelAppInfo5 = new javax.swing.JLabel();
        jLabelAppInfo6 = new javax.swing.JLabel();
        jLabelBolterSourceLink = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("О программе");
        setMaximumSize(new java.awt.Dimension(700, 325));
        setMinimumSize(new java.awt.Dimension(700, 325));
        setModal(true);
        setName("aboutDalog"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 325));
        setResizable(false);

        jButtonOk.setText("OK");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jLabelApp.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabelApp.setText("Bolter");

        jLabelVersion.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabelVersion.setText("v. 1.0.0");

        jLabelBolterLink.setText("<html><body><a href=\"\">обсуждение</a></body></html>\n");
        jLabelBolterLink.setToolTipText("http://www.vk.com/bolter.app");
        jLabelBolterLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBolterLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBolterLinkMouseClicked(evt);
            }
        });

        jLabelAuthorLink.setText("<html><body><a href=\"\">yagodar.com</a></body></html>");
        jLabelAuthorLink.setToolTipText("http://www.yagodar.com");
        jLabelAuthorLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAuthorLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAuthorLinkMouseClicked(evt);
            }
        });

        jLabelAuthor.setText("<html><body><a href=\"\">Ягодаров Андрей Евгеньевич</a></body></html>\n");
        jLabelAuthor.setToolTipText("http://www.vk.com/yagodar");
        jLabelAuthor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAuthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAuthorMouseClicked(evt);
            }
        });

        jLabelAuthorEmail.setText("<html><body><a href=\"\">yagodarov.a.e@gmail.com</a></body></html>\n\n");
        jLabelAuthorEmail.setToolTipText("mailto:yagodarov.a.e@gmail.com");
        jLabelAuthorEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAuthorEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAuthorEmailMouseClicked(evt);
            }
        });

        jLabelDonateText.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabelDonateText.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelDonateText.setText("Если Вам понравилась программа и Вы хотите мне пожертвовать:");

        jLabelDonateWebMoney.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabelDonateWebMoney.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelDonateWebMoney.setText("кошелёк WebMoney:");

        jTextFieldWebMonerWallet.setEditable(false);
        jTextFieldWebMonerWallet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldWebMonerWallet.setText("R740059839091");
        jTextFieldWebMonerWallet.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabelAppInfo1.setText("Программа для организации интернет поиска по определенным сайтам и по определенному промежутку времени.");

        jLabelAppInfo2.setText("Используются ведущие поисковые движки: ");

        jLabelAppInfoYandexLink.setText("<html><body><a href=\"\">Yandex</a></body></html>");
        jLabelAppInfoYandexLink.setToolTipText("http://www.yandex.ru");
        jLabelAppInfoYandexLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAppInfoYandexLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAppInfoYandexLinkMouseClicked(evt);
            }
        });

        jLabelAppInfo3.setText(", ");

        jLabelGoogleLink.setText("<html><body><a href=\"\">Google</a></body></html>");
        jLabelGoogleLink.setToolTipText("http://www.google.ru");
        jLabelGoogleLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelGoogleLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelGoogleLinkMouseClicked(evt);
            }
        });

        jLabelAppInfo4.setText(".");

        jLabelAppInfo5.setText("Поиск ведется путем перенаправления сформированного программой запроса на сайты поисковых движков. ");

        jLabelAppInfo6.setText("<html><body>Запросы формируются с использованием языка запросов определенного поискового движка, а также с использованием параметров GET-запроса определенного движка.</body></html>");

        jLabelBolterSourceLink.setText("<html><body><a href=\"\">исходники</a></body></html>\n");
        jLabelBolterSourceLink.setToolTipText("https://github.com/yagodar/bolter");
        jLabelBolterSourceLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBolterSourceLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBolterSourceLinkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOk))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelAuthorLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAuthorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelDonateWebMoney)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldWebMonerWallet, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelDonateText))
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelApp)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelBolterLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelBolterSourceLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabelAppInfo1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelAppInfo2)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabelAppInfoYandexLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabelAppInfo3)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabelGoogleLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabelAppInfo4))
                                    .addComponent(jLabelAppInfo5))
                                .addGap(0, 1, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelAppInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelApp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVersion)
                    .addComponent(jLabelBolterLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBolterSourceLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAppInfo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAppInfo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAppInfo2)
                        .addComponent(jLabelAppInfoYandexLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelAppInfo4)
                        .addComponent(jLabelGoogleLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAppInfo5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAppInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDonateText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAuthorLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAuthorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDonateWebMoney)
                    .addComponent(jTextFieldWebMonerWallet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOk)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jLabelBolterLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBolterLinkMouseClicked
        browseLink(jLabelBolterLink.getToolTipText());
    }//GEN-LAST:event_jLabelBolterLinkMouseClicked

    private void jLabelAppInfoYandexLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAppInfoYandexLinkMouseClicked
        browseLink(jLabelAppInfoYandexLink.getToolTipText());
    }//GEN-LAST:event_jLabelAppInfoYandexLinkMouseClicked

    private void jLabelGoogleLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelGoogleLinkMouseClicked
        browseLink(jLabelGoogleLink.getToolTipText());
    }//GEN-LAST:event_jLabelGoogleLinkMouseClicked

    private void jLabelAuthorLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAuthorLinkMouseClicked
        browseLink(jLabelAuthorLink.getToolTipText());
    }//GEN-LAST:event_jLabelAuthorLinkMouseClicked

    private void jLabelAuthorEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAuthorEmailMouseClicked
         browseLink(jLabelAuthorEmail.getToolTipText());
    }//GEN-LAST:event_jLabelAuthorEmailMouseClicked

    private void jLabelAuthorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAuthorMouseClicked
        browseLink(jLabelAuthor.getToolTipText());
    }//GEN-LAST:event_jLabelAuthorMouseClicked

    private void jLabelBolterSourceLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBolterSourceLinkMouseClicked
        browseLink(jLabelBolterSourceLink.getToolTipText());
    }//GEN-LAST:event_jLabelBolterSourceLinkMouseClicked

    private void browseLink(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (IOException | URISyntaxException ex) {}
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelApp;
    private javax.swing.JLabel jLabelAppInfo1;
    private javax.swing.JLabel jLabelAppInfo2;
    private javax.swing.JLabel jLabelAppInfo3;
    private javax.swing.JLabel jLabelAppInfo4;
    private javax.swing.JLabel jLabelAppInfo5;
    private javax.swing.JLabel jLabelAppInfo6;
    private javax.swing.JLabel jLabelAppInfoYandexLink;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelAuthorEmail;
    private javax.swing.JLabel jLabelAuthorLink;
    private javax.swing.JLabel jLabelBolterLink;
    private javax.swing.JLabel jLabelBolterSourceLink;
    private javax.swing.JLabel jLabelDonateText;
    private javax.swing.JLabel jLabelDonateWebMoney;
    private javax.swing.JLabel jLabelGoogleLink;
    private javax.swing.JLabel jLabelVersion;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldWebMonerWallet;
    // End of variables declaration//GEN-END:variables
}
