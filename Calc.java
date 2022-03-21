import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calc extends JFrame {

    JTextField textField = new JTextField();
    JTextField result = new JTextField(4);
    JPanel panel = new JPanel();
    JPanel resultPanel = new JPanel();
    String name[] = {"C", "", "", "*",
            "7", "8", "9", "/", "4", "5", "6", "-",
            "1", "2", "3", "+", ".", "0", "", "="}; //버튼 이름
    JButton btn[] = new JButton[name.length];
    Double num = 0.0;
    Double last = 0.0;
    int sign =0;
    String formula = "";

    public Calc(){
        setTitle("계산기");
        setSize(300,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //컨테이너 정의
        Container calc = getContentPane();
        calc.setLayout(new BorderLayout());

        //텍스트필드 정의. 계산기에서 버튼으로 입력하는 텍스트를 보여줄 것이다.
        textField.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        textField.setHorizontalAlignment(JTextField.RIGHT);

        //결과값을 보여주는 텍스트필드
        result.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        result.setHorizontalAlignment(JTextField.RIGHT);
        JLabel label = new JLabel("  ");
        resultPanel.setLayout(new GridLayout(1,2));
        resultPanel.add(label);
        resultPanel.add(result);

        //패널을 정의. 계산기 버튼들이 들어갈 공간이므로 5*4 그리드레이아웃을 사용한다.
        GridLayout grid = new GridLayout(5,4,5, 5);
        panel.setLayout(grid);

        //버튼에 텍스트 넣어주는 반복문
        for(int i=0; i<name.length; i++) {
            btn[i] = new JButton(name[i]);
            panel.add(btn[i]);

            btn[i].addActionListener(new MyActionListener());
        }

        //컨테이너 위쪽에 텍스트필드 배치
        calc.add(textField, BorderLayout.NORTH);
        //컨테이너에 계산기 자판 패널을 넣어준다.
        calc.add(panel, BorderLayout.CENTER);
        //아래쪽에 결과값 보여주는 텍스트필드 배치
        calc.add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    //액션리스너
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = e.getActionCommand();
            if(text.equals("C")){
                textField.setText("");
                result.setText("");
                last = 0.0;
                num = 0.0;
                sign = 0;
                formula = "";
            }
            else if(text.equals("")){
                result.setText(""+ (9.0-8.1));

            }
            else if(text.equals("*")){
                textField.setText("");
                last += num;
                sign = 1;
                formula += num+"*";
            }
            else if(text.equals("/")){
                textField.setText("");
                last += num;
                sign = 2;
                formula += num+"/";
            }
            else if(text.equals("-")){
                textField.setText("");
                last += num;
                sign = 3;
                formula += num+"-";
            }
            else if(text.equals("+")){
                textField.setText("");
                last += num;
                sign = 4;
                formula += num+"+";
            }
            else if(text.equals("=")){
                if(sign == 1){
                    last *= num;
                    result.setText(""+last);
                    sign = 0;
                    formula += num+"";
                    textField.setText(formula);
                }
                else if(sign == 2){
                    last /= num;
                    result.setText(""+last);
                    sign = 0;
                    formula += num+"";
                    textField.setText(formula);
                }
                else if(sign == 3){
                    last -= num;
                    result.setText(""+last);
                    sign = 0;
                    formula += num+"";
                    textField.setText(formula);
                }
                else if(sign == 4){
                    last += num;
                    result.setText(""+last);
                    sign = 0;
                    formula += num+"";
                    textField.setText(formula);
                }

            }
            else {
                textField.setText(textField.getText() + e.getActionCommand());
                num = Double.parseDouble(textField.getText());
            }
        }
    }

    public static void main(String[] args) {
        new Calc();
    }
}


