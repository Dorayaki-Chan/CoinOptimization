package otazero.github.io.coinoptimization;

import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* example */
    private EditText editText;
    private TextView textView;

    /* My create */
    /*
    private EditText editTextYukichi;
    private EditText editTextIchiyo;
    private EditText editTextShurei;
    private EditText editTextHideyo;
    private EditText editTextGomarumaru;
    private EditText editTextHitomarumaru;
    private EditText editTextGomaru;
    private EditText editTextHitomaru;
    private EditText editTextGo;
    private EditText editTextHito;
    */
    private EditText[] editTextHenkan = new EditText[10];
    private EditText kaikei;

    private TextView[] textViewKaikei = new TextView[10];
    private TextView textViewTsuri;

    //通貨
    private int[] tmpKahei = {10000, 5000, 2000, 1000, 500, 100, 50, 10, 5, 1};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* example */
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        Button button = findViewById(R.id.button);

        /* My create */
        /*
        editTextYukichi = findViewById(R.id.editTextNumber_Yukichi);
        editTextIchiyo = findViewById(R.id.editTextNumber_Ichiyo);
        editTextShurei = findViewById(R.id.editTextNumber_Shurei);
        editTextHideyo = findViewById(R.id.editTextNumber_Hideyo);
        editTextGomarumaru = findViewById(R.id.editTextNumber_Gomarumaru);
        editTextHitomarumaru = findViewById(R.id.editTextNumber_Hitomarumaru);
        editTextGomaru = findViewById(R.id.editTextNumber_Gomaru);
        editTextHitomaru = findViewById(R.id.editTextNumber_Hitomaru);
        editTextGo = findViewById(R.id.editTextNumber_Go);
        editTextHito = findViewById(R.id.editTextNumber_Hito);
        */
        editTextHenkan[0] = findViewById(R.id.editTextNumber_Yukichi);
        editTextHenkan[1] = findViewById(R.id.editTextNumber_Ichiyo);
        editTextHenkan[2] = findViewById(R.id.editTextNumber_Shurei);
        editTextHenkan[3] = findViewById(R.id.editTextNumber_Hideyo);
        editTextHenkan[4] = findViewById(R.id.editTextNumber_Gomarumaru);
        editTextHenkan[5] = findViewById(R.id.editTextNumber_Hitomarumaru);
        editTextHenkan[6] = findViewById(R.id.editTextNumber_Gomaru);
        editTextHenkan[7] = findViewById(R.id.editTextNumber_Hitomaru);
        editTextHenkan[8] = findViewById(R.id.editTextNumber_Go);
        editTextHenkan[9] = findViewById(R.id.editTextNumber_Hito);
        kaikei = findViewById(R.id.editTextNumber_Kaikei);

        textViewKaikei[0] = findViewById(R.id.text_view_sheet_Yukichi);
        textViewKaikei[1] = findViewById(R.id.text_view_sheet_Ichiyo);
        textViewKaikei[2] = findViewById(R.id.text_view_sheet_Shurei);
        textViewKaikei[3] = findViewById(R.id.text_view_sheet_Hideyo);
        textViewKaikei[4] = findViewById(R.id.text_view_sheet_Gomarumaru);
        textViewKaikei[5] = findViewById(R.id.text_view_sheet_Hitomarumaru);
        textViewKaikei[6] = findViewById(R.id.text_view_sheet_Gomaru);
        textViewKaikei[7] = findViewById(R.id.text_view_sheet_Hitomaru);
        textViewKaikei[8] = findViewById(R.id.text_view_sheet_Go);
        textViewKaikei[9] = findViewById(R.id.text_view_sheet_Hito);

        textViewTsuri = findViewById(R.id.textView_tsuri);

        /* ボタンが押されたら */
        button.setOnClickListener( v-> {

            /* example */
            // エディットテキストのテキストを取得
            String text = editText.getText().toString();
            // 取得したテキストを TextView に張り付ける
            textView.setText(text);

            /* My create */
            /*
            String strYukichi = editTextYukichi.getText().toString();
            String strIchiyo = editTextIchiyo.getText().toString();
            String strShurei = editTextShurei.getText().toString();
            String strHideyo = editTextHideyo.getText().toString();
            String strGomarumaru = editTextGomarumaru.getText().toString();
            String strHitomarumaru = editTextHitomarumaru.getText().toString();
            String strGomaru = editTextGomaru.getText().toString();
            String strHitomaru = editTextHitomaru.getText().toString();
            String strGo = editTextGo.getText().toString();
            String strHito = editTextHito.getText().toString();
            */
            String strkaikei = kaikei.getText().toString();
            int numkaikei = 0;
            if (strkaikei.length() > 0) {
                numkaikei = Integer.parseInt(strkaikei);
            }
            System.out.println(numkaikei);


            //集合Aとその合計
            int sumA = 0;
            String[] strHenkan = new String[10];
            int shugoA[] = new int[10];
            for (int i = 0; i < editTextHenkan.length; i++){
                strHenkan[i] = editTextHenkan[i].getText().toString();
                if (strHenkan[i].length() > 0) {
                    shugoA[i] = Integer.parseInt(strHenkan[i]);
                }
                else{
                    shugoA[i] = 0;
                }
                sumA += shugoA[i] * tmpKahei[i];
            }

            //集合B
            int sumB = sumA - numkaikei;
            int shugoB[] = new int[10];
            int shugoC[] = new int[10];
            int shugoA01[] = new int[10];
            int shugoB01[] = new int[10];
            int otsuri = 0;
            for (int i = 0; i < tmpKahei.length; i++){
                int count = 0;
                for (int j = tmpKahei[i]; sumB >= j; sumB -= j){
                    count++;
                }
                shugoB[i] = count;

                //集合C
                shugoC[i] =shugoA[i] > shugoB[i] ? shugoB[i]:shugoA[i];

                //集合A'と集合B'
                shugoA01[i] = shugoA[i] - shugoC[i];
                shugoB01[i] = shugoB[i] - shugoC[i];

                //おつり計算
                otsuri += shugoB01[i] * tmpKahei[i];

                //支払い枚数出力
                textViewKaikei[i].setText(String.valueOf(shugoA01[i]));
            }
            textViewTsuri.setText(String.valueOf(otsuri));

            /*
            int numYukichi = Integer.parseInt(strYukichi);
            int numShurei = Integer.parseInt(strShurei);
            int numHideyo = Integer.parseInt(strHideyo);
            int numGomarumaru = Integer.parseInt(strGomarumaru);
            int numHitomarumaru = Integer.parseInt(strHitomarumaru);
            int numGomaru = Integer.parseInt(strGomaru);
            int numHitomaru = Integer.parseInt(strHitomaru);
            int numGo = Integer.parseInt(strGo);
            int numHito = Integer.parseInt(strHito);
            */

        });
    }
}