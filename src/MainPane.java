import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainPane extends JFrame implements ActionListener {
    private JButton addFileBtn = new JButton("파일 선택");
    private JLabel fileNameLabel = new JLabel(".txt 확장자의 파일을 선택해주세요.");
    private JButton startExtractBtn = new JButton("초성 추출하기");
    private JFileChooser chooser = new JFileChooser();

    private File selectedFile = null;

    private MainPane() {
        super("초성추출기");
        setSize(500, 100);
        setLocationRelativeTo(null);
    }

    private void createPanel() {
        MainPane mainPane = new MainPane();
        JPanel jPanel = new JPanel();

        jPanel.add(addFileBtn);
        jPanel.add(fileNameLabel);
        jPanel.add(startExtractBtn);

        addFileBtn.addActionListener(this);
        startExtractBtn.addActionListener(this);

        mainPane.add(jPanel, BorderLayout.CENTER);
        mainPane.setVisible(true);
        mainPane.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedBtn = (JButton) e.getSource();

        if (clickedBtn.equals(addFileBtn)) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
            chooser.setDialogTitle("파일 열기");
            chooser.setFileFilter(filter);

            int result = chooser.showOpenDialog(null);
            String filePath = chooser.getSelectedFile().getPath();
            fileNameLabel.setText(filePath);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
            }
        } else if (clickedBtn.equals(startExtractBtn)) {
            if (selectedFile == null) {
                showMessageDialog(null, "파일을 먼저 선택해주세요.");
            } else {
                chooser.setDialogTitle("파일 저장 경로 선택");
                int result = chooser.showSaveDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    String content = FileUtil.getFileContents(selectedFile);

                    try {
                        FileWriter fw = new FileWriter("초성추출_" + selectedFile.getPath() + ".txt");
                        fw.write(content);
                        fw.flush();
                        fw.close();
                    } catch (Exception e2) {
                        showMessageDialog(null, "추출 중 오류가 발생했습니다. 다시 시도해주세요.");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        MainPane mainPane = new MainPane();
        mainPane.createPanel();
    }
}
