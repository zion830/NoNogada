import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainFrame extends JFrame implements ActionListener {
    private JButton addFileBtn = new JButton("파일 선택");
    private JLabel fileNameLabel = new JLabel("파일을 선택해주세요.");
    private JButton startExtractBtn = new JButton("초성 추출하기");
    private JFileChooser chooser = new JFileChooser();

    private File selectedFile = null;

    MainFrame() {
        super("초성추출기");
        setSize(500, 100);
        setLocationRelativeTo(null);
    }

    void createPanel() {
        MainFrame mainPane = new MainFrame();
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
            fileNameLabel.setText("선택된 파일 : " + filePath);

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
                    selectedFile = chooser.getSelectedFile();
                    String path = selectedFile.getPath();

                    try {
                        FileWriter fw = new FileWriter(FileUtil.addFileExtIfNecessary(path, ".txt"));
                        fw.write(content);
                        fw.flush();
                        fw.close();

                        showMessageDialog(null, "파일이 " + path + "에 저장되었습니다.");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        showMessageDialog(null, "추출 중 오류가 발생했습니다. 다시 시도해주세요.");
                    }
                }
            }
        }
    }

}
