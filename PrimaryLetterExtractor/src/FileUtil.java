import java.io.*;

import static javax.swing.JOptionPane.showMessageDialog;

class FileUtil {

    // txt 파일 내용을 String 객체로 변경
    public static String getFileContents(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader in;
            in = new BufferedReader(new FileReader(file));
            String line = in.readLine();

            while (line != null) {
                result.append(LetterUtil.convert(line)).append("\n");
                line = in.readLine();
            }
        } catch (FileNotFoundException ex) {
            showMessageDialog(null, "존재하지 않는 파일입니다. 파일을 다시 선택해주세요.");
        } catch (IOException ex) {
            showMessageDialog(null, "추출 중 오류가 발생했습니다. 다시 시도해주세요.");
        }

        return result.toString();
    }

    // 필요할 경우 확장자 설정
    static String addFileExtIfNecessary(String file, String ext) {
        if (file.lastIndexOf('.') == -1) {
            file += ext;
        }

        return file;
    }
}
